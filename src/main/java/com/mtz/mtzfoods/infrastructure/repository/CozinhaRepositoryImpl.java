package com.mtz.mtzfoods.infrastructure.repository;

import com.mtz.mtzfoods.domain.model.Cozinha;
import com.mtz.mtzfoods.domain.repository.CozinhaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {


    @PersistenceContext // Serve como Autoride
    private EntityManager manager;

    @Override
    public List<Cozinha> todas() {
        return manager.createQuery("from Cozinha", Cozinha.class)
                .getResultList();

    }

    @Override
    public List<Cozinha> consultarPorNome(String nomeCozinha) {
        //Usando JPQL
        return manager.createQuery("from Cozinha where nome like :nome", Cozinha.class)
                .setParameter("nome", "%" + nomeCozinha + "%")
                .getResultList();
    }

    @Override
    public Cozinha potId(Long id) {
        return manager.find(Cozinha.class, id);
    }

    @Override
    @Transactional
    public Cozinha adicionar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }

    @Override
    @Transactional
    public void remover(Long id) {
        Cozinha cozinha = potId(id);
        if (cozinha == null) {
            /**
             * {@link EmptyResultDataAccessException} : Recebe como parametro
             * O tamanho esperado, ou seja, esperada apenas 1 obj (Cozinha)
             * */
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cozinha);
    }
}
