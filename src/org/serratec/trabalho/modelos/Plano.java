package org.serratec.trabalho.modelos;

import org.serratec.trabalho.enums.PlanoEnum;
import org.serratec.trabalho.excecoes.ValorInvalidoException;

public class Plano {

    private final String descricao;
    private final double valor;
    private final PlanoEnum plano;

    public Plano(PlanoEnum plano, String descricao, double valor) throws ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException("O valor do plano deve ser maior que zero.");
        }
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

	public void exibirPlano() { // tirar isso e usar o toString no lugar?
		System.out.println("      Periodicidade - " + plano
						+ "\n      Descrição - " + descricao
						+ "\n      Valor - R$" + valor);
	}


	@Override
	public String toString() {
		return "Periodicidade: " + plano +
				"\n      Descrição - " + descricao + 
				"\n      Valor - R$" + valor;
	}
	
	
}
