package com.mtz.mtzfoods.domain.repository;

import com.mtz.mtzfoods.domain.model.Restaurante;

import java.util.List;

//@Repository
public interface RestauranteRepository {

    List<Restaurante> todas();

    Restaurante potId(Long id);

    Restaurante adicionar(Restaurante restaurante);

    void remover(Restaurante restaurante);
}
