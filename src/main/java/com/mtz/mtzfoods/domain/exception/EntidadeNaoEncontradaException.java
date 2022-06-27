package com.mtz.mtzfoods.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public abstract class EntidadeNaoEncontradaException extends NegocioException
{

    public EntidadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public EntidadeNaoEncontradaException(Long estadoId){
        this(String.format("Não existe um cadastro de estado com código %d ", estadoId));
    }
}
