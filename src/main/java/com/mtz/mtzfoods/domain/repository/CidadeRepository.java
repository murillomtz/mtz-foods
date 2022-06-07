package com.mtz.mtzfoods.domain.repository;

import com.mtz.mtzfoods.domain.model.Cidade;

import java.util.List;

//@Repository
public interface CidadeRepository {

    List<Cidade> todas();

    Cidade potId(Long id);

    Cidade adicionar(Cidade cidade);

    void remover(Long id);
}
