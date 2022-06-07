package com.mtz.mtzfoods.infrastructure.repository;

import com.mtz.mtzfoods.domain.model.Cozinha;
import com.mtz.mtzfoods.domain.model.Restaurante;
import com.mtz.mtzfoods.domain.repository.RestauranteRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {


    @PersistenceContext // Serve como Autoride
    private EntityManager manager;

    @Override
    public List<Restaurante> todas() {
        return manager.createQuery("from Restaurante", Restaurante.class)
                .getResultList();

    }

    @Override
    public Restaurante potId(Long id) {
        return manager.find(Restaurante.class, id);
    }

    @Override
    @Transactional
    public Restaurante adicionar(Restaurante restaurante) {
        return manager.merge(restaurante);
    }

    @Override
    @Transactional
    public void remover(Long id) {

        Restaurante restaurante = potId(id);
        if (restaurante == null) {
            /**
             * {@link EmptyResultDataAccessException} : Recebe como parametro
             * O tamanho esperado, ou seja, esperada apenas 1 obj (Cozinha)
             * */
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(restaurante);
    }
}
