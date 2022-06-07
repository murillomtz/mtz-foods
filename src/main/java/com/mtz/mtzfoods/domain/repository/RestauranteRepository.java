package com.mtz.mtzfoods.domain.repository;

import com.mtz.mtzfoods.domain.model.Restaurante;
import org.springframework.stereotype.Component;

import java.util.List;

//@Repository
@Component
public interface RestauranteRepository {

    List<Restaurante> todas();

    Restaurante potId(Long id);

    Restaurante adicionar(Restaurante restaurante);

    void remover(Long id);
}
