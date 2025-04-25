package org.serratec.trabalho.principal;

import org.serratec.trabalho.arquivos.LeitorCSV;
import org.serratec.trabalho.enums.CargoFuncionario;
import org.serratec.trabalho.excecoes.ValorInvalidoException;
import org.serratec.trabalho.menu.Menu;
import org.serratec.trabalho.metodos.BancoDeDados;
import org.serratec.trabalho.modelos.Funcionario;
import org.serratec.trabalho.modelos.Pessoa;

public class AcademiaApp {

	public static void main(String[] args) throws ValorInvalidoException {
		Pessoa funcionario = new Funcionario("Paulo", "22222222222", "987456", CargoFuncionario.DONO);
		BancoDeDados.adicionarPessoaNaListaCorreta(funcionario);

	LeitorCSV.inicializador();
	Menu.Login();

	}

}
