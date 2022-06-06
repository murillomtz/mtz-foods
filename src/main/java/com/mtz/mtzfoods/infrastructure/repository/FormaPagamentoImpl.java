package com.mtz.mtzfoods.infrastructure.repository;

import com.mtz.mtzfoods.domain.model.FormaPagamento;
import com.mtz.mtzfoods.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class FormaPagamentoImpl implements FormaPagamentoRepository {


    @PersistenceContext // Serve como Autoride
    private EntityManager manager;

    @Override
    public List<FormaPagamento> todas() {
        return manager.createQuery("from FormaPagamento", FormaPagamento.class)
                .getResultList();

    }

    @Override
    public FormaPagamento potId(Long id) {
        return manager.find(FormaPagamento.class, id);
    }

    @Override
    @Transactional
    public FormaPagamento adicionar(FormaPagamento formaPagamento) {
        return manager.merge(formaPagamento);
    }

    @Override
    @Transactional
    public void remover(FormaPagamento formaPagamento) {
        formaPagamento = potId(formaPagamento.getId());
        manager.remove(formaPagamento);
    }
}
