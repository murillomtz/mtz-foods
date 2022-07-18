package com.mtz.mtzfoods.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mtz.mtzfoods.domain.model.Estado;
import com.mtz.mtzfoods.domain.model.Restaurante;

import java.util.List;

public class CidadeMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;

}
