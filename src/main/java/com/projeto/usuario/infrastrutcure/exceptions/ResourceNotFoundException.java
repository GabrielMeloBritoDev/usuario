package com.projeto.usuario.infrastrutcure.exceptions;

public class ResourceNotFoundException  extends  RuntimeException{

    public ResourceNotFoundException(String mesangem){
        super(mesangem);
    }
    public ResourceNotFoundException(String mensagem, Throwable throwable){
        super(mensagem,throwable);
    }
}
