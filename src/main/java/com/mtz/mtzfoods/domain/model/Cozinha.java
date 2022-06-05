package com.mtz.mtzfoods.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
//@JsonRootName("gastronomia")
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)//Somente se for incluido explicitamente
//@Table(name = "tab_cozinhas")
public class Cozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

//    @Column(name = "nom_cozinha", length = 90)
    //@JsonIgnore
   // @JsonProperty(value = "titulo")
    @Column(nullable = false)
    private String nome;

}
