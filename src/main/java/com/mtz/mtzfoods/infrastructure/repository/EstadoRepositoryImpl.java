package com.mtz.mtzfoods.infrastructure.repository;

import com.mtz.mtzfoods.domain.model.Estado;
import com.mtz.mtzfoods.domain.repository.EstadoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {


    @PersistenceContext // Serve como Autoride
    private EntityManager manager;

    @Override
    public List<Estado> todas() {
        return manager.createQuery("from Estado", Estado.class)
                .getResultList();

    }

    @Override
    public Estado potId(Long id) {
        return manager.find(Estado.class, id);
    }

    @Override
    @Transactional
    public Estado adicionar(Estado estado) {
        return manager.merge(estado);
    }

    @Override
    @Transactional
    public void remover(Estado estado) {
        estado = potId(estado.getId());
        manager.remove(estado);
    }
}
