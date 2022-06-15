package com.mtz.mtzfoods.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@JsonRootName("gastronomia")
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)//Somente se for incluido explicitamente
//@Table(name = "tab_cozinhas")
public class Cozinha {

    //@NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    //@Column(name = "nom_cozinha", length = 90)
    //@JsonIgnore
    //@JsonProperty(value = "titulo")
    @Column(nullable = false)
    private String nome;

    /**
     * Importante verificar a importancia do OneToMany, pode nao ser necessario
     * fazer a interligação
     * */
    @JsonIgnore
    @OneToMany(mappedBy = "cozinha")
    private List<Restaurante> restaurantes = new ArrayList<>();
}
