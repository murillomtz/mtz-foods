package com.mtz.mtzfoods.domain.repository;

import com.mtz.mtzfoods.domain.model.Estado;
import org.springframework.stereotype.Component;

import java.util.List;

//@Repository
@Component
public interface EstadoRepository {

    List<Estado> todas();

    Estado potId(Long id);

    Estado adicionar(Estado estado);

    void remover(Long id);
}
