package com.mtz.mtzfoods.infrastructure.repository;

import com.mtz.mtzfoods.domain.model.Cidade;
import com.mtz.mtzfoods.domain.repository.CidadeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {
    @PersistenceContext // Serve como Autoride
    private EntityManager manager;

    @Override
    public List<Cidade> todas() {
        return manager.createQuery("from Cidade", Cidade.class)
                .getResultList();
    }
    
    @Override
    public Cidade potId(Long id) {
        return manager.find(Cidade.class, id);
    }

    @Override
    @Transactional
    public Cidade adicionar(Cidade cidade) {
        return manager.merge(cidade);
    }

    @Override
    @Transactional
    public void remover(Long id) {

        Cidade cidade = potId(id);
        if (cidade == null) {
            /**
             * {@link EmptyResultDataAccessException} : Recebe como parametro
             * O tamanho esperado, ou seja, esperada apenas 1 obj (Cozinha)
             * */
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cidade);
    }
}
