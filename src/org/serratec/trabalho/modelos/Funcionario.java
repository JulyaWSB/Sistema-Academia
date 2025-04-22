package org.serratec.trabalho.modelos;

import java.time.LocalDate;

import org.serratec.trabalho.enums.CargoFuncionario;
import org.serratec.trabalho.enums.Especialidades;
import org.serratec.trabalho.enums.PlanoEnum;
import org.serratec.trabalho.metodos.BancoDeDados;
import org.serratec.trabalho.metodos.PlanoMetodos;
import org.serratec.trabalho.metodos.UsuarioMetodos;

public class Funcionario extends Pessoa{
	private CargoFuncionario cargo;

    public Funcionario(String nome, String cpf, String senha, CargoFuncionario cargo) {
        super(nome, cpf, senha);
        this.cargo = cargo;
    }

    public CargoFuncionario getCargo() {
        return cargo;
    }
    
    public static void cadastrarPlano (PlanoEnum plano, String descricao, double valor) {
		if (PlanoMetodos.planoExistente(plano, descricao, valor)) {
			System.out.println("Falha no cadastro: Plano já cadastrado."); //trocar por exceção
			return;
		}
		Plano novoPlano = new Plano(plano, descricao, valor);
		BancoDeDados.adicionarPlano(novoPlano);
		System.out.println("Plano cadastrado com sucesso!");
	}

	public void cadastrarAluno(String nome, String cpf, String senha, LocalDate dataMatricula, Plano plano) {
		if (UsuarioMetodos.cpfExistente(cpf)) {
			System.out.println("Falha no cadastro: CPF já cadastrado."); // trocar por exceções? 
			return;
		}

		Aluno novoAluno = new Aluno(nome, cpf, senha, dataMatricula, plano);
		BancoDeDados.adicionarAluno(novoAluno);
		System.out.println("Aluno cadastrado com sucesso!");
	}

	public void cadastrarPersonal(String nome, String cpf, String senha, String cref, Especialidades especialidade){
		if (UsuarioMetodos.cpfExistente(cpf)) {
			System.out.println("Falha no cadastro: CPF já cadastrado.");// trocar por exceções? 
			return;
		} 
		Personal novoPersonal = new Personal(nome, cpf, senha, cref, especialidade);
		BancoDeDados.adicionarPersonal(novoPersonal);
		System.out.println("Personal cadastrado com sucesso!"); 
	}
	
	public void emitirRelatorios() {
		
	} 
	
	public void valorTotalReceber() {
	       /*O item 5 do funcionário deverá mostrar, de acordo com o plano vinculado a
	    	cada aluno o valor total esperado de faturamento no mês;*/
	    }
    
}