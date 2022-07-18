package com.mtz.mtzfoods.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mtz.mtzfoods.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

//@JsonRootName("gastronomia")
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)//Somente se for incluido explicitamente
//@Table(name = "tab_cozinhas")
public class Cozinha {

    @NotNull(groups = Groups.CozinhaId.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    //@Column(name = "nom_cozinha", length = 90)
    //@JsonIgnore
    //@JsonProperty(value = "titulo")
    @Column(nullable = false)
    private String nome;

//    @Column(name = "observacao", nullable = false)
//    private String descricao;

    /**
     * Importante verificar a importancia do OneToMany, pode nao ser necessario
     * fazer a interligação
     */
    @OneToMany(mappedBy = "cozinha")
    private List<Restaurante> restaurantes = new ArrayList<>();
}
