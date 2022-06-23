package com.mtz.mtzfoods.domain.service;

import com.mtz.mtzfoods.domain.exception.EntidadeEmUsoException;
import com.mtz.mtzfoods.domain.exception.EntidadeNaoEncontradaException;
import com.mtz.mtzfoods.domain.model.Cidade;
import com.mtz.mtzfoods.domain.model.Estado;
import com.mtz.mtzfoods.domain.repository.CidadeRepository;
import com.mtz.mtzfoods.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {
    public static final String MSG_CIDADE_EM_USO = "Cidade de codigo %d não pode ser removida," +
            "pois está em uso. ";
    public static final String MSG_CIDADE_NAO_ENCONTRADA = "Não existe um cadastro de cidade com código %d";
    @Autowired
    private CidadeRepository repository;

    @Autowired
    private CadastroEstadoService estadoService;

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();

        Estado estado = estadoService.buscarOuFalhar(estadoId);

//		Estado estado = estadoRepository.findById(estadoId)
//			.orElseThrow(() -> new EntidadeNaoEncontradaException(
//					String.format("Não existe cadastro de estado com código %d", estadoId)));

        cidade.setEstado(estado);

        return repository.save(cidade);
    }

    public void excluir(Long cidadeId) {
        try {
            repository.deleteById(cidadeId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId));
        } catch (DataIntegrityViolationException e) {
            /**
             * {@link DataIntegrityViolationException}: não tem haver com
             * a camada de negocio, ela pertence a camada de infraestrutura
             * por isso, criamos uma Exception personalisada para "traduzir"
             * para camada de negocio.
             * */

            throw new EntidadeEmUsoException(
                    String.format(MSG_CIDADE_EM_USO, cidadeId)
            );
        }
    }

    public Cidade buscarOuFalhar(Long cidadeId) {
        return repository.findById(cidadeId)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException(
                                String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId)));
    }
}
