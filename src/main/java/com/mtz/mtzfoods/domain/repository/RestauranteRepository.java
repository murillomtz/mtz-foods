package com.mtz.mtzfoods.domain.repository;

import com.mtz.mtzfoods.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * {@link JpaSpecificationExecutor}: Prepara o repository para receber um espcication
 * implements Specification<Restaurante>
 */
@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>,
        JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries,
        JpaSpecificationExecutor<Restaurante> {

    /**
     * Macete para fazer um join entre a tabela restaurante e cozinha
     *
     * */
    @Query("from Restaurante r join fetch r.cozinha left join fetch r.formasPagamento")
    List<Restaurante> findAll();

    /**
     * Between - Faz a busca entre um intervalo.
     * Ex: Busca entre 10 até 15 = 11,12...14
     */
    List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    /**
     * And - Faz a busca entre dois valores
     * Ex: Busca por dois valores. TaxaFrete e O id DENTRO DA ENTIDADE COZINHA (MAGICA? kkk).
     * <p>
     * Containing- Faz uma busca usando o SQL LIKE
     * Ex: %strin%
     */
    //List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);
    //@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
    // A QUERY FOI FEITA NO ARQUIVO orm.xml
    List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinhaId);

    /**
     * First - Consulta filtrando so o PRIMEIRO resultado
     */
    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

    /**
     * Top x - Vai pegar os primeiros o X representa quantos
     * Ex: Top2 -> Vai trazer os dois primeiros da consulta
     */
    List<Restaurante> findTop2ByNomeContaining(String nome);

    int countByCozinhaId(Long cozinha);

}
