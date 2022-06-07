package com.mtz.mtzfoods.domain.service;

import com.mtz.mtzfoods.domain.exception.EntidadeEmUsoException;
import com.mtz.mtzfoods.domain.exception.EntidadeNaoEncontradaException;
import com.mtz.mtzfoods.domain.model.Cozinha;
import com.mtz.mtzfoods.domain.model.Estado;
import com.mtz.mtzfoods.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {
    @Autowired
    private EstadoRepository repository;


    public Estado salvar(Estado estado) {
      return repository.adicionar(estado);
    }

    public void excluir(Long estadoId) {
        try {
            repository.remover(estadoId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de estado com código %d", estadoId));
        } catch (DataIntegrityViolationException e) {
            /**
             * {@link DataIntegrityViolationException}: não tem haver com
             * a camada de negocio, ela pertence a camada de infraestrutura
             * por isso, criamos uma Exception personalisada para "traduzir"
             * para camada de negocio.
             * */
            throw new EntidadeEmUsoException(
                    String.format("Estado de codigo %d não pode ser removida," +
                            "pois está em uso. ", estadoId)
            );
        }
    }
}
