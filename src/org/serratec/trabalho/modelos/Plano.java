package org.serratec.trabalho.modelos;

import org.serratec.trabalho.enums.PlanoEnum;

public class Plano {

	private final String descricao;
	private final double valor;
	private final PlanoEnum plano;

	public Plano(PlanoEnum plano, String descricao, double valor) {
		this.plano = plano;
		this.descricao = descricao;
		this.valor = valor;
	}

	
	public PlanoEnum getPlano() {
		return plano;
	}


	public String getDescricao() {
		return descricao;
	}

	public double getValor() {
		return valor;
	}

	public void exibirPlano() {
		System.out.println("Periodicidade: " + plano
						+ "\nDescrição: " + descricao
						+ "\nValor: R$" + valor);
	}
}
