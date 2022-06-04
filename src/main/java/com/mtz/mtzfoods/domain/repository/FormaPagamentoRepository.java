package com.mtz.mtzfoods.domain.repository;

import com.mtz.mtzfoods.domain.model.Cozinha;
import com.mtz.mtzfoods.domain.model.FormaPagamento;

import java.util.List;

//@Repository
public interface FormaPagamentoRepository {

    List<FormaPagamento> todas();

    FormaPagamento potId(Long id);

    FormaPagamento adicionar(FormaPagamento formaPagamento);

    void remover(FormaPagamento formaPagamento);
}
