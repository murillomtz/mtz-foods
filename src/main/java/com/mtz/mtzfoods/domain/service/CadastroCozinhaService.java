package com.mtz.mtzfoods.domain.service;

import com.mtz.mtzfoods.domain.exception.CozinhaNaoEncontradaException;
import com.mtz.mtzfoods.domain.exception.EntidadeEmUsoException;
import com.mtz.mtzfoods.domain.model.Cozinha;
import com.mtz.mtzfoods.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroCozinhaService {
    public static final String MSG_COZINHA_EM_USO = "Cozinha de codigo %d não pode ser removida," +
            "pois está em uso. ";
    @Autowired
    private CozinhaRepository repository;

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return repository.save(cozinha);

    }

    @Transactional
    public void excluir(Long cozinhaId) {
        try {
            repository.deleteById(cozinhaId);
            repository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new CozinhaNaoEncontradaException(cozinhaId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_COZINHA_EM_USO, cozinhaId));
        }
    }

    public Cozinha buscarOuFalhar(Long cozinhaId) {
        return repository.findById(cozinhaId)
                .orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
    }
}
