package org.serratec.trabalho.modelos;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.serratec.trabalho.enums.CargoFuncionario;
import org.serratec.trabalho.enums.Especialidades;
import org.serratec.trabalho.enums.PlanoEnum;
import org.serratec.trabalho.excecoes.ValorInvalidoException;
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
    
    public void cadastrarPlano (PlanoEnum plano, String descricao, double valor) throws ValorInvalidoException {
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
	
	public void exibirTotalReceberNoMes(List<Aluno> alunos) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o mês e ano para análise (MM/yyyy): ");
        String mes = scanner.nextLine();

        YearMonth anoMes = YearMonth.parse(mes, DateTimeFormatter.ofPattern("MM/yyyy"));

        double totalNoMes = 0.0;

        for (Aluno aluno : alunos) {
            YearMonth dataMatricula = YearMonth.from(aluno.getDataMatricula());
            if (dataMatricula.compareTo(anoMes) <= 0) {
                totalNoMes += aluno.getPlano().getValor();
            }
        }

        System.out.println("Total a receber no mês " + mes + ": " + totalNoMes);
    }
	
	public void contarAlunosAtivosNoMes(List<Aluno> alunos) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o mês e ano para análise (MM/yyyy): ");
        String mes = scanner.nextLine();

        YearMonth anoMes = YearMonth.parse(mes, DateTimeFormatter.ofPattern("MM/yyyy"));

        int contador = 0;
        for (Aluno aluno : alunos) {
            YearMonth dataMatricula = YearMonth.from(aluno.getDataMatricula());
            if (dataMatricula.compareTo(anoMes) <= 0) {
                contador++;
            }
        }
        System.out.println(contador != 0? "Alunos ativos: " + contador + " no mês de " + mes + "." : " ");
    }
    
}