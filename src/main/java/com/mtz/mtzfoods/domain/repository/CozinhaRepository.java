package com.mtz.mtzfoods.domain.repository;

import com.mtz.mtzfoods.domain.model.Cozinha;
import org.springframework.stereotype.Component;

import java.util.List;

//@Repository
@Component
public interface CozinhaRepository {

    List<Cozinha> todas();

    List<Cozinha> consultarPorNome(String nome);

    Cozinha potId(Long id);

    Cozinha adicionar(Cozinha cozinha);

    void remover(Long id);
}
