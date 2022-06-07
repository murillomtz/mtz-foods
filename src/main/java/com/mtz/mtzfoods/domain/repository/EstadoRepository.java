package com.mtz.mtzfoods.domain.repository;

import com.mtz.mtzfoods.domain.model.Cozinha;
import com.mtz.mtzfoods.domain.model.Estado;

import java.util.List;

//@Repository
public interface EstadoRepository {

    List<Estado> todas();

    Estado potId(Long id);

    Estado adicionar(Estado estado);

    void remover(Long id);
}
