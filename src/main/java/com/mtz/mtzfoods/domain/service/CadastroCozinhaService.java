package com.mtz.mtzfoods.domain.service;

import com.mtz.mtzfoods.domain.exception.EntidadeEmUsoException;
import com.mtz.mtzfoods.domain.exception.EntidadeNaoEncontradaException;
import com.mtz.mtzfoods.domain.model.Cozinha;
import com.mtz.mtzfoods.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.adicionar(cozinha);
    }

    public void excluir(Long cozinhaId) {
        try {
            cozinhaRepository.remover(cozinhaId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de coinha com código %d", cozinhaId));
        } catch (DataIntegrityViolationException e) {
            /**
             * {@link DataIntegrityViolationException}: não tem haver com
             * a camada de negocio, ela pertence a camada de infraestrutura
             * por isso, criamos uma Exception personalisada para "traduzir"
             * para camada de negocio.
             * */
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de codigo %d não pode ser removida," +
                            "pois está em uso. ", cozinhaId)
            );
        }
    }
}
