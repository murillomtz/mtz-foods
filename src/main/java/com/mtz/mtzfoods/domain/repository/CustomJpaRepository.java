package com.mtz.mtzfoods.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * O spring nao deve considerar que a classe é um repository
 * então ele nao estancia
 */
@NoRepositoryBean
public interface CustomJpaRepository<T, ID> extends JpaRepository<T, ID> {


    Optional<T> buscarPrimeiro();

    void detach(T entity);
}
