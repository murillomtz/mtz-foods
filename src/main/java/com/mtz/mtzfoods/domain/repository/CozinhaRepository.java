package com.mtz.mtzfoods.domain.repository;

import com.mtz.mtzfoods.domain.model.Cozinha;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface CozinhaRepository {

    List<Cozinha> todas();

    Cozinha potId(Long id);

    Cozinha adicionar(Cozinha cozinha);

    void remover(Long id);
}
