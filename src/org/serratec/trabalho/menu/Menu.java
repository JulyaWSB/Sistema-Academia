package org.serratec.trabalho.menu;

import java.time.LocalDate;
import java.util.Scanner;

import org.serratec.trabalho.enums.Especialidades;
import org.serratec.trabalho.enums.PlanoEnum;
import org.serratec.trabalho.metodos.BancoDeDados;
import org.serratec.trabalho.metodos.PlanoMetodos;
import org.serratec.trabalho.modelos.Aluno;
import org.serratec.trabalho.modelos.Funcionario;
import org.serratec.trabalho.modelos.Personal;
import org.serratec.trabalho.modelos.Plano;

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
			case 1 -> alunoLogado.exibirDados();
			case 2 -> personalContratar(alunoLogado);
			case 3 -> alunoLogado.visualizarAvaliacoes(alunoLogado);
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
			case 1 -> personalLogado.visualizarAlunos();
			case 2 -> avaliacaoRegistrar(personalLogado);
			case 3 -> personalLogado.visualizarAvaliacoes();
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
			case 1 -> planoCadastrar(funcionarioLogado);
			case 2 -> alunoCadastrar(funcionarioLogado);
			case 3 -> personalCadastrar(funcionarioLogado);
			//case 4 -> funcionarioLogado.emitirRelatorios(); // tem que ser feito ainda
			//case 5 -> funcionarioLogado.valorTotalAReceber(); // tem que ser feito ainda
			case 6 -> System.out.println("____Programa Encerrando____");
			default -> System.out.println("____Opção Invalida, tente novamente!____");
			}

		}while(opcao != 6);
	}


	// metodos para menus:
	
	public void personalContratar(Aluno alunoLogado) {
		if (BancoDeDados.listaPersonal().isEmpty()) {
			System.out.println("Não há Personal Trainers disponíveis.");
			return;
		}
		System.out.println("Lista de Personals Disponíveis");
		for (Personal p: BancoDeDados.listaPersonal()) {
			System.out.println("- ");  p.exibirPersonal(); // tem que preencher o "exibirPersonal" na classe Personal ainda
			System.out.println("\n");
		}
		System.out.println("\n Digite o nome do Personal que deseja contratar:");
		String nomePersonal = sc.nextLine();

		for (Personal p: BancoDeDados.listaPersonal()) {
			if (p.getNome().equals(nomePersonal)) {
				alunoLogado.contratarPersonal(p);
				System.out.println("Personal contratado com sucesso!");
			}
		}	
	}

	public void avaliacaoRegistrar(Personal personalLogado) {
		System.out.println("Alunos Disponíveis: ");
		personalLogado.visualizarAlunos();
		System.out.println("Digite o nome do aluno a registrar avaliação: ");
		String nomeAluno = sc.nextLine();
		for (Aluno al : BancoDeDados.listaAlunos()) {
			if (al.getNome().equals(nomeAluno)) {
				System.out.println("Insira a data do registro: ");
				String data = sc.nextLine();
				LocalDate dataRegistro = LocalDate.parse(data);
				System.out.println("Descrição da avaliação:");
				String descricao = sc.nextLine();
				personalLogado.registrarAvaliacao(al, dataRegistro, personalLogado, descricao);
			}
		}}


	public void planoCadastrar(Funcionario funcionarioLogado) {
		System.out.println("Escolha a periodicidade:\nMENSAL_1_MODALIDADE\n"
				+ "    MENSAL_2_MODALIDADES\n"
				+ "    MENSAL_TOTAL\n"
				+ "    TRIMESTRAL_TOTAL\n"
				+ "    SEMESTRAL_TOTAL\n"
				+ "    ANUAL_TOTAL");
		String periodicidade = sc.nextLine().toUpperCase();
		PlanoEnum period = PlanoEnum.valueOf(periodicidade);
		System.out.println("\nRegistre a descrição: ");
		String descricao = sc.nextLine();
		
		System.out.println("Informe o valor: R$");
		Double valor = sc.nextDouble();
		funcionarioLogado.cadastrarPlano(period, descricao, valor);
	}
	
	public void alunoCadastrar(Funcionario funcionarioLogado){
		System.out.println("Insira o nome do aluno: ");
		String nome = sc.nextLine();
		System.out.println("Insira o CPF: ");
		String cpf = sc.nextLine();
		System.out.println("Insira a senha: ");
		String senha = sc.nextLine();
		System.out.println("Data de matricula: ");
		String data = sc.nextLine();
		LocalDate dataMatricula = LocalDate.parse(data);
		
		PlanoMetodos.listarPlanos();
		System.out.println("Digite o número do plano desejado:");
		int numPlano = sc.nextInt();
		sc.nextLine();
		if (numPlano < 1 || numPlano> BancoDeDados.planos.size()) {
			System.out.println("Opção Inválida. Digite um número da lista.");
			return;
		}
		Plano planoEscolhido= BancoDeDados.planos.get(numPlano -1);
		
		funcionarioLogado.cadastrarAluno(nome, cpf, senha, dataMatricula, planoEscolhido);
		}
	

	public void personalCadastrar(Funcionario funcionarioLogado){
		System.out.println("Insira o nome do personal: ");
		String nome = sc.nextLine();
		System.out.println("Insira o CPF: ");
		String cpf = sc.nextLine();
		System.out.println("Insira a senha: ");
		String senha = sc.nextLine();
		System.out.println("Insira a CREF:");
		String cref = sc.nextLine();
		String especialidade = sc.nextLine().toUpperCase();
		Especialidades espec = Especialidades.valueOf(especialidade);
		funcionarioLogado.cadastrarPersonal(nome, cpf, senha, cref, espec);
	}

	
}

