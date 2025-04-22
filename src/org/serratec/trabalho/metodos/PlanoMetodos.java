package org.serratec.trabalho.metodos;

import org.serratec.trabalho.enums.PlanoEnum;
import org.serratec.trabalho.modelos.Plano;

public class PlanoMetodos {

	public static boolean planoExistente (PlanoEnum plano, String descricao, double valor) {
		for (Plano pl : BancoDeDados.listaPlanos()) {
			if ((pl.getPlano().equals(plano) & pl.getDescricao().equals(descricao) & pl.getValor()==(valor)))
				return true;}
		return false;
	}

	public static void listarPlanos(){
		if (BancoDeDados.listaPlanos().isEmpty()) {
			System.out.println("Não há planos cadastrados.");
			return;
		}
		for (int i = 0; i < BancoDeDados.planos.size(); i++) {
			Plano pl = BancoDeDados.planos.get(i);
			System.out.println((i + 1) + ". " + pl.toString());
		}}

	
}
