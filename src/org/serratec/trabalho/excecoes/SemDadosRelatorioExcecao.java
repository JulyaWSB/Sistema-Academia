package org.serratec.trabalho.excecoes;

public class SemDadosRelatorioExcecao extends Exception {
	public SemDadosRelatorioExcecao(String tipo) {
		super("Não há dados suficientes para gerar relariorio de " + tipo);
	}

}
