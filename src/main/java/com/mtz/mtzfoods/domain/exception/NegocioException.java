package com.mtz.mtzfoods.domain.exception;

//@ResponseStatus(value = HttpStatus.CONFLICT)
public class NegocioException extends RuntimeException {

    public NegocioException(String mensagem) {
        super(mensagem);
    }

    public NegocioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
