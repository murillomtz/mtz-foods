package com.mtz.mtzfoods.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
/**
 * Builder - Padrão de projeto para construir obj nume linguagem mais fluida e legivel*/
@Getter
@Builder
public class Problema {

    private LocalDateTime dataHora;
    private String mensagem;
}
