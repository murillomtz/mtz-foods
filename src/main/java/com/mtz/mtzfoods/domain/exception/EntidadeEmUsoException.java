package com.mtz.mtzfoods.domain.exception;

//@ResponseStatus(value = HttpStatus.CONFLICT)//, reason = "Entidade nao encontrada")
public class EntidadeEmUsoException extends RuntimeException {

    public EntidadeEmUsoException(String mensagem) {
        super(mensagem);
    }

}
