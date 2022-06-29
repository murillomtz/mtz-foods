package com.mtz.mtzfoods.domain.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public abstract class EntidadeNaoEncontradaException extends NegocioException{

    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
