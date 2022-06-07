package com.mtz.mtzfoods.domain.repository;

import com.mtz.mtzfoods.domain.model.FormaPagamento;
import org.springframework.stereotype.Component;

import java.util.List;

//@Repository
@Component
public interface FormaPagamentoRepository {

    List<FormaPagamento> todas();

    FormaPagamento potId(Long id);

    FormaPagamento adicionar(FormaPagamento formaPagamento);

    void remover(FormaPagamento formaPagamento);
}
