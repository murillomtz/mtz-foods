package com.mtz.mtzfoods.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.CONFLICT)//, reason = "Entidade nao encontrada")
public class EntidadeEmUsoException extends RuntimeException {

    public EntidadeEmUsoException(String mensagem) {
        super(mensagem);
    }

}
