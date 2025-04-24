package org.serratec.trabalho.metodos;

import org.serratec.trabalho.excecoes.LoginExcecao;
import org.serratec.trabalho.modelos.Pessoa;

public class UsuarioMetodos {

	public static boolean cpfExistente(String cpf) {
		for (Pessoa p : BancoDeDados.listaTodasAsPessoas()) {
			if (p.getCpf().equals(cpf)) {
				return true;
			}
		}
		return false;
	}

	public static Pessoa validarLogin(String cpf, String senha) throws LoginExcecao{
		for(Pessoa p : BancoDeDados.listaTodasAsPessoas()){
			if ( p.getCpf().equals(cpf) && p.getSenha().equals(senha)) {
				return p;
			}
		}	throw new LoginExcecao();
	}
	
}
