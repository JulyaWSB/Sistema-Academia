package org.serratec.trabalho.excecoes;

public class ValorInvalidoException extends Exception {
    public ValorInvalidoException() {
        super("Valor inválido: o valor não pode ser negativo ou zero.");
    }

    public ValorInvalidoException(String mensagem) {
        super(mensagem);
    }
}