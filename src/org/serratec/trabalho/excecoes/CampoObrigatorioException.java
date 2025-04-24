package org.serratec.trabalho.excecoes;

public class CampoObrigatorioException extends RuntimeException {
    public CampoObrigatorioException(String campo) {
        super("O campo '" + campo + "' é obrigatório e não pode estar vazio ou nulo.");
    }
}