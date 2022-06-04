package com.mtz.mtzfoods.domain.repository;

import com.mtz.mtzfoods.domain.model.Cozinha;
import com.mtz.mtzfoods.domain.model.Permissao;

import java.util.List;

//@Repository
public interface PermissaoRepository {

    List<Permissao> todas();

    Permissao potId(Long id);

    Permissao adicionar(Permissao permissao);

    void remover(Permissao permissao);
}
