package com.mtz.mtzfoods.domain.repository;

import com.mtz.mtzfoods.domain.model.Permissao;
import org.springframework.stereotype.Component;

import java.util.List;

//@Repository
@Component
public interface PermissaoRepository {

    List<Permissao> todas();

    Permissao potId(Long id);

    Permissao adicionar(Permissao permissao);

    void remover(Permissao permissao);
}
