package org.serratec.trabalho.metodos;

import org.serratec.trabalho.modelos.Aluno;
import org.serratec.trabalho.modelos.Funcionario;
import org.serratec.trabalho.modelos.Personal;
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

	public Pessoa validarLogin(String cpf, String senha) {
		for(Pessoa p : BancoDeDados.listaTodasAsPessoas()){
			if ( p.getCpf().equals(cpf) && p.getSenha().equals(senha)) {
				return p;
			}
		}	return null; // adicionar exceçao: LoginInvalidoException
	}

	
	public String identificarTipoPorCpf(String cpf) {
	    Pessoa pessoa = BancoDeDados.buscarPessoaPorCpf(cpf);

	    if (pessoa instanceof Aluno) {
	        return "Aluno";
	    } else if (pessoa instanceof Personal) {
	        return "Personal";
	    } else if (pessoa instanceof Funcionario) {
	        return "Funcionario";
	    }
	    return ""; // trocar por exceção?
	}


}
