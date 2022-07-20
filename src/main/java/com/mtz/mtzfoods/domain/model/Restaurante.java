package com.mtz.mtzfoods.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mtz.mtzfoods.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

   // @NotBlank
    @Column(nullable = false)
    private String nome;

  //  @NotNull
   // @PositiveOrZero
    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    /**
     * {@link ManyToOne} : Varias Cozinhas para 1 restaurante
     * {@link JoinColumn} : Usado para definir nome da tabalea em casa de ligação
     * {@link JsonIgnoreProperties} : Usado geralmente quando usammos o LAZY, ele serve para
     * ignorar propriedades e nao a entidade toda.{@link  allowGetters = true} - permitir metodos de GET, logo
     * irá mostrar as informações q o json ignorou
     * <p>
     * OBS:
     */
    //@Valid
    //@NotNull
   // @Valid
   // @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
   // @NotNull
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;

    /**
     * Essa proprioedade é uma classe encorpravel "Embeddavel"
     */
    @Embedded
    private Endereco endereco;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    /**
     * {@link UpdateTimestamp} -> Sempre q a entidade for atualizada ele irá definir uma nova hora, a hora atual.
     */
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataAtualizacao;

    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();
}
