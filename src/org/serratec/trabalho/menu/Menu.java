package org.serratec.trabalho.menu;

import java.util.Scanner;

import org.serratec.trabalho.modelos.Aluno;
import org.serratec.trabalho.modelos.Funcionario;
import org.serratec.trabalho.modelos.Personal;

public class Menu {
	Scanner sc = new Scanner(System.in);
	int opcao;

	public void exibirMenuAluno(Aluno alunoLogado) {
		do {
			String menu = """
					\n____BEM-VINDO ALUNO____
					\n____MENU____
					1.Visualizar Dados Pessoais e Plano Contratado
					2.Contratar Personal Trainer
					3.Visualizar Avaliações Físicas
					4.Sair
					""";
			System.out.println(menu);
			opcao = sc.nextInt();
			sc.nextLine();
			
			switch(opcao) {
			case 1 -> exibirDados();
			case 2 -> contratarPersonal();
			case 3 -> visualizarAvaliacoes();
			case 4 -> System.out.println("____Programa Encerrando____");
			default -> System.out.println("____Opção Invalida, tente novamente!____");
			}
			
		}while(opcao != 4);
	}
	
	public void exibirMenuPersonal(Personal personalLogado) {
		do {
			String menu = """
					\n____BEM-VINDO PERSONAL____
					\n____MENU____
					1.Visualizar Alunos
					2.Registrar Avaliações Físicas dos Alunos
					3.Visualizar Lista de Avaliações Realizadas
					4.Sair
					""";
			System.out.println(menu);
			opcao = sc.nextInt();
			sc.nextLine();
			
			switch(opcao) {
			case 1 -> Personal.visualizarAlunos();
			case 2 -> Personal.registrarAvaliacao();
			case 3 -> Personal.visualizarAvaliacoes();
			case 4 -> System.out.println("____Programa Encerrando____");
			default -> System.out.println("____Opção Invalida, tente novamente!____");
			}
			
		}while(opcao != 4);
	}
	
	public void exibirMenuFuncionario(Funcionario funcionarioLogado) {
		do {
			String menu = """
					\n____BEM-VINDO FUNCIONARIO____
					\n____MENU____
					1.Cadastrar Novo Plano
					2.Cadastrar Novo Aluno
					3.Cadastrar Novo Personal Trainer
					4.Emitir Relatórios
					5.Valor Total a Receber no Mês
					6.Sair
					""";
			System.out.println(menu);
			opcao = sc.nextInt();
			sc.nextLine();
			
			switch(opcao) {
			case 1 -> cadastrarPlano();
			case 2 -> cadastrarAluno();
			case 3 -> cadastrarPersonal();
			case 4 -> emitirRelatorios();
			case 5 -> valorTotalAReceber();
			case 6 -> System.out.println("____Programa Encerrando____");
			default -> System.out.println("____Opção Invalida, tente novamente!____");
			}
			
		}while(opcao != 6);
	}
}