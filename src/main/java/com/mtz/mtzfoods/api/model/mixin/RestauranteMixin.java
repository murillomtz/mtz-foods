package com.mtz.mtzfoods.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mtz.mtzfoods.domain.model.Cozinha;
import com.mtz.mtzfoods.domain.model.Endereco;
import com.mtz.mtzfoods.domain.model.FormaPagamento;
import com.mtz.mtzfoods.domain.model.Produto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestauranteMixin {
    /** {@link JsonIgnoreProperties} : Usado geralmente quando usammos o LAZY, ele serve para
     * ignorar propriedades e nao a entidade toda.{@link  allowGetters = true} - permitir metodos de GET, logo
     * irá mostrar as informações q o json ignorou
    * */
    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();
}
