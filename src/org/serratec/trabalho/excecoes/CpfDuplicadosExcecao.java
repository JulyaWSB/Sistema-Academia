package org.serratec.trabalho.excecoes;

public class CpfDuplicadosExcecao extends Exception {
	public CpfDuplicadosExcecao(String cpf) {
		super("Já existe o cpf " + cpf + " registrado no nossos sistemas!!");
	}

}
