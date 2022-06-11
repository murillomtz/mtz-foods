package com.mtz.mtzfoods.domain.repository;

import com.mtz.mtzfoods.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    /***
     * Containing- Faz uma busca usando o SQL LIKE
     * Ex: %strin%
     */

    List<Cozinha> findTodasByNomeContaining(String nome);

    Optional<Cozinha> findByNome(String nome);

    /**
     * Exists - Traz um boolean
     * */
    boolean existsByNome(String nome);

}
