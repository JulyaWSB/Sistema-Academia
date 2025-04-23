package org.serratec.trabalho.excecoes;

public class LoginExcecao extends Exception {
	public LoginExcecao() {
		super("CPF ou LOGIN invalidos");
	}

}