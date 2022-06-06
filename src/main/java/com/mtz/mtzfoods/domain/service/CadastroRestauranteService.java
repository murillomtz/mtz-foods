package com.mtz.mtzfoods.domain.service;

import com.mtz.mtzfoods.domain.exception.EntidadeEmUsoException;
import com.mtz.mtzfoods.domain.exception.EntidadeNaoEncontradaException;
import com.mtz.mtzfoods.domain.model.Cozinha;
import com.mtz.mtzfoods.domain.model.Restaurante;
import com.mtz.mtzfoods.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {
    @Autowired
    private RestauranteRepository repository;

    public Restaurante salvar(Restaurante restaurante) {
        return repository.adicionar(restaurante);
    }

    public void excluir(Long restauranteId) {
        try {
            repository.remover(restauranteId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de coinha com código %d", restauranteId));
        } catch (DataIntegrityViolationException e) {
            /**
             * {@link DataIntegrityViolationException}: não tem haver com
             * a camada de negocio, ela pertence a camada de infraestrutura
             * por isso, criamos uma Exception personalisada para "traduzir"
             * para camada de negocio.
             * */
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de codigo %d não pode ser removida," +
                            "pois está em uso. ", restauranteId)
            );
        }
    }
}
