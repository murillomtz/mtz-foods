package com.mtz.mtzfoods.domain.repository;

import com.mtz.mtzfoods.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
    //List<Cozinha> consultarPorNome(String nome);

}
