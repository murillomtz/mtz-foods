package com.mtz.mtzfoods.domain.repository;

import com.mtz.mtzfoods.domain.model.Cidade;
import org.springframework.stereotype.Component;

import java.util.List;

//@Repository
@Component
public interface CidadeRepository {

    List<Cidade> todas();

    Cidade potId(Long id);

    Cidade adicionar(Cidade cidade);

    void remover(Long id);
}
