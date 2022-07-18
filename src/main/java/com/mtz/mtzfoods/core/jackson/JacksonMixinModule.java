package com.mtz.mtzfoods.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mtz.mtzfoods.api.model.mixin.CidadeMixin;
import com.mtz.mtzfoods.api.model.mixin.CozinhaMixin;
import com.mtz.mtzfoods.api.model.mixin.RestauranteMixin;
import com.mtz.mtzfoods.domain.model.Cidade;
import com.mtz.mtzfoods.domain.model.Cozinha;
import com.mtz.mtzfoods.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        //SetMix... é usado para fazer  corelação entre a Classe da entidade e o "Mixing"
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }
}
