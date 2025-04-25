package org.serratec.trabalho.menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.serratec.trabalho.enums.Especialidades;
import org.serratec.trabalho.enums.PlanoEnum;
import org.serratec.trabalho.excecoes.CpfDuplicadosExcecao;
import org.serratec.trabalho.excecoes.LoginExcecao;
import org.serratec.trabalho.excecoes.PlanoExcecao;
import org.serratec.trabalho.excecoes.ValorInvalidoException;
import org.serratec.trabalho.metodos.BancoDeDados;
import org.serratec.trabalho.metodos.PlanoMetodos;
import org.serratec.trabalho.metodos.UsuarioMetodos;
import org.serratec.trabalho.modelos.Aluno;
import org.serratec.trabalho.modelos.Funcionario;
import org.serratec.trabalho.modelos.Personal;
import org.serratec.trabalho.modelos.Pessoa;
import org.serratec.trabalho.modelos.Plano;

public class Menu {
	Scanner sc = new Scanner(System.in);
	
	public static void Login() throws ValorInvalidoException {
	    Scanner sc = new Scanner(System.in);
	    String cpf;
	    System.out.println("\n ------- Login -------");
	    do {
            System.out.println("Insira o CPF (apenas números, 11 dígitos): ");
            cpf = sc.nextLine();

            if (!validarCpf(cpf)) {
                System.out.println("CPF inválido. Deve conter exatamente 11 dígitos numéricos.");
            }
        } while (!validarCpf(cpf));

	    System.out.println("Insira sua senha: ");
	    String senha = sc.nextLine();
	    
	    Pessoa pessoaLogada;
		try {
			pessoaLogada = UsuarioMetodos.validarLogin(cpf, senha);
			exibirMenuCorreto(pessoaLogada);
		} catch (LoginExcecao e) {
			System.out.println(e.getMessage());
			retornoLogin();
		}
	       
	}
	
	
	public static  void exibirMenuCorreto(Pessoa pessoaLogada) throws ValorInvalidoException { 
		if (pessoaLogada instanceof Aluno) {
			exibirMenuAluno((Aluno) pessoaLogada);
		} else if (pessoaLogada instanceof Personal) {
			exibirMenuPersonal((Personal) pessoaLogada);
		} else if (pessoaLogada instanceof Funcionario) {
			exibirMenuFuncionario((Funcionario) pessoaLogada);
		} else {
			System.out.println("Tipo de usuário desconhecido."); 
		}}

	
	
	

	public static void exibirMenuAluno(Aluno alunoLogado) throws ValorInvalidoException {
		Scanner sc = new Scanner(System.in);
		int opcao;
		do {
			System.out.println("\n------- BEM-VINDO " + alunoLogado.getNome().toUpperCase() + " -------");
			String menu = """
					\n------- MENU ALUNO -------
					1.Visualizar Dados Pessoais e Plano Contratado
					2.Contratar Personal Trainer
					3.Visualizar Avaliações Físicas
					4.Sair da Conta
					5.Sair do Sistema
					""";
			System.out.println(menu);
			opcao = sc.nextInt();
			sc.nextLine();

			switch(opcao) {
			case 1 -> alunoLogado.exibirDados();
			case 2 -> personalContratar(alunoLogado);
			case 3 -> alunoLogado.visualizarAvaliacoes(alunoLogado);
			case 4 -> retornoLogin();
			case 5 -> System.out.println("== Programa Encerrando ==");
			default -> System.out.println("== Opção Invalida, tente novamente! ==");
			}

		}while(opcao != 5);
	}

	public static void exibirMenuPersonal(Personal personalLogado) throws ValorInvalidoException {
		Scanner sc = new Scanner(System.in);
		int opcao;
		do {
			System.out.println("\n------- BEM-VINDO " + personalLogado.getNome().toUpperCase() + " -------");
			String menu = """
					\n------- MENU PERSONAL -------
					1.Visualizar Alunos
					2.Registrar Avaliações Físicas dos Alunos
					3.Visualizar Lista de Avaliações Realizadas
					4.Sair da Conta
					5.Sair do Sistema
					""";
			System.out.println(menu);
			opcao = sc.nextInt();
			sc.nextLine();

			switch(opcao) {
			case 1 -> personalLogado.visualizarAlunos();
			case 2 -> avaliacaoRegistrar(personalLogado);
			case 3 -> personalLogado.visualizarAvaliacoes();
			case 4 -> retornoLogin();
			case 5 -> System.out.println("== Programa Encerrando ==");
			default -> System.out.println("== Opção Invalida, tente novamente! ==");
			}

		}while(opcao != 5);
	}

	public static void exibirMenuFuncionario(Funcionario funcionarioLogado) throws ValorInvalidoException {
		Scanner sc = new Scanner(System.in);
		int opcao;
		do {
			System.out.println("\n------- BEM-VINDO " + funcionarioLogado.getNome().toUpperCase() + " -------");
			String menu = """
					\n------- MENU fUNCIONÁRIO -------
					1.Cadastrar Novo Plano
					2.Cadastrar Novo Aluno
					3.Cadastrar Novo Personal Trainer
					4.Emitir Relatórios
					5.Valor Total a Receber no Mês
					6.Verificar Quantidade de Alunos Ativos no Mês
					7.Sair da Conta
					8.Sair do Sistema
					""";
			System.out.println(menu);
			opcao = sc.nextInt();
			sc.nextLine();

			switch(opcao) {
			case 1 -> planoCadastrar(funcionarioLogado);
			case 2 -> alunoCadastrar(funcionarioLogado);
			case 3 -> personalCadastrar(funcionarioLogado);
			case 4 -> funcionarioLogado.emitirRelatorios(); 
			case 5 -> funcionarioLogado.exibirTotalReceberNoMes(BancoDeDados.listaAlunos());
			case 6 -> funcionarioLogado.contarAlunosAtivosNoMes(BancoDeDados.listaAlunos());
			case 7 -> retornoLogin();
			case 8 -> System.out.println("== Programa Encerrando ==");
			default -> System.out.println("== Opção Invalida, tente novamente! ==");
			}

		}while(opcao != 8);
	}


	// metodos para menus:

	public static void personalContratar(Aluno alunoLogado) {
		Scanner sc = new Scanner(System.in);
		boolean achouPersonal = false;
		if (BancoDeDados.listaPersonal().isEmpty()) {
			System.out.println("Não há Personal Trainers disponíveis.");
			return;
		}
		System.out.println("Lista de Personals Disponíveis");
		for (Personal p: BancoDeDados.listaPersonal()) {
			System.out.print("- ");  p.exibirPersonal(); 
		}
		System.out.println("\nDigite o nome do Personal que deseja contratar:");
		String nomePersonal = sc.nextLine();

		for (Personal p: BancoDeDados.listaPersonal()) {
			if (p.getNome().equalsIgnoreCase(nomePersonal)) {
				alunoLogado.contratarPersonal(p);
				achouPersonal = true;
				break;
			}}
		if (!achouPersonal) {
			System.out.println("Personal não encontrado. Verifique o nome digitado.");
			return;}
	}

	public static void avaliacaoRegistrar(Personal personalLogado) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Alunos Disponíveis: ");
		boolean temAlunos = personalLogado.visualizarAlunos();
		
		if (!temAlunos){
			return;
		}
		System.out.println("Digite o nome do aluno a registrar avaliação: ");
		String nomeAluno = sc.nextLine();

		boolean encontrouAluno = false;
		for (Aluno al : BancoDeDados.listaAlunos()) {
			if (al.getNome().equalsIgnoreCase(nomeAluno)) {
				System.out.println("Insira a data do registro: ");
				String data = sc.nextLine();
				DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate dataRegistro = LocalDate.parse(data, formatador); //adicionar tratamento caso insira data em formato invalido??
				System.out.println("Descrição da avaliação:");
				String descricao = sc.nextLine();
				personalLogado.registrarAvaliacao(al, dataRegistro, personalLogado, descricao);
				encontrouAluno = true;
				break;
			} 
		}if (!encontrouAluno) 
		{ System.out.println("Aluno não encontrado. Verifique o nome digitado.");}
	}


	public static void planoCadastrar(Funcionario funcionarioLogado) throws ValorInvalidoException  { //podemos adicionar alguns tratamentos de erros
		Scanner sc = new Scanner(System.in);
		try {
	        System.out.println("Escolha a periodicidade:\nMENSAL_1_MODALIDADE\n"
	                + "MENSAL_2_MODALIDADES\n"
	                + "MENSAL_TOTAL\n"
	                + "TRIMESTRAL_TOTAL\n"
	                + "SEMESTRAL_TOTAL\n"
	                + "ANUAL_TOTAL");
	        String periodicidade = sc.nextLine().toUpperCase();

	        PlanoEnum period = PlanoEnum.valueOf(periodicidade); 

	        System.out.println("\nRegistre a descrição: ");
	        String descricao = sc.nextLine();

	        System.out.println("Informe o valor: R$");
	        Double valor = sc.nextDouble();
	        sc.nextLine();

	        funcionarioLogado.cadastrarPlano(period, descricao, valor);

	    } catch (PlanoExcecao e) {
	        System.out.println("Erro ao cadastrar plano: " + e.getMessage());
	    } catch (IllegalArgumentException e) {
	        System.out.println("Periodicidade inválida. Por favor, digite uma opção válida.");
	    } catch (InputMismatchException e) {
	        System.out.println("Erro: valor inválido. Certifique-se de digitar um número para o valor.");
	        sc.nextLine();
	    }
	}

	public static void alunoCadastrar(Funcionario funcionarioLogado){
		 Scanner sc = new Scanner(System.in);
		    String opcao;
		    String cpf;
		    String senha;

		    do {
		        System.out.println("Insira o nome do aluno: ");
		        String nome = sc.nextLine();

		        do {
		            System.out.println("Insira o CPF (apenas números, 11 dígitos): ");
		            cpf = sc.nextLine();

		            if (!validarCpf(cpf)) {
		                System.out.println("CPF inválido. Deve conter exatamente 11 dígitos numéricos.");
		            }
		        } while (!validarCpf(cpf));

		        for (Pessoa pessoa : BancoDeDados.listaTodasAsPessoas()) {
		            if (pessoa.getCpf().equals(cpf)) {
		                try {
		                    throw new CpfDuplicadosExcecao(cpf);
		                } catch (CpfDuplicadosExcecao e) {
		                    System.out.println("Erro: " + e.getMessage());
		                    return;
		                }
		            }
		        }

		        do {
			        System.out.println("Insira a senha: ");
			        senha = sc.nextLine();
			        if (!validarSenha(senha)) {
			        	System.out.println("Senha inválida. Deve conter exatamente no mínimo 6 dígitos.");
			        	}
			        } while (!validarSenha(senha));
			

		        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        System.out.println("Data de matrícula (dd/MM/yyyy): ");
		        String data = sc.nextLine();
		        LocalDate dataMatricula = LocalDate.parse(data, formatador);

		        PlanoMetodos.listarPlanos();
		        System.out.println("Digite o número do plano desejado:");
		        int numPlano = sc.nextInt();
		        sc.nextLine(); 

		        if (numPlano < 1 || numPlano > BancoDeDados.planos.size()) {
		            System.out.println("Opção Inválida. Digite um número da lista.");
		            return;
		        }

		        Plano planoEscolhido = BancoDeDados.planos.get(numPlano - 1);

		        funcionarioLogado.cadastrarAluno(nome, cpf, senha, dataMatricula, planoEscolhido);
		        
		        System.out.println("\nDeseja cadastrar outro aluno? (S/N)");
		        opcao = sc.nextLine().trim().toUpperCase();
		    } while (opcao.equals("S"));

		    System.out.println("Retornando ao menu...");
		}


	public static void personalCadastrar(Funcionario funcionarioLogado){
		 Scanner sc = new Scanner(System.in);
		    String opcao = null;
		    String cpf;
		    String senha;

		    do {
		        System.out.println("Insira o nome do personal: ");
		        String nome = sc.nextLine();

		        do {
		            System.out.println("Insira o CPF (apenas números, 11 dígitos): ");
		            cpf = sc.nextLine();

		            if (!validarCpf(cpf)) {
		                System.out.println("CPF inválido. Deve conter exatamente 11 dígitos numéricos.");
		            }
		        } while (!validarCpf(cpf));

		        for (Pessoa pessoa : BancoDeDados.listaTodasAsPessoas()) {
		            if (pessoa.getCpf().equals(cpf)) {
		                try {
		                    throw new CpfDuplicadosExcecao(cpf);
		                } catch (CpfDuplicadosExcecao e) {
		                    System.out.println("Erro: " + e.getMessage());
		                    return;
		                }
		            }
		        }
		        do {
		        System.out.println("Insira a senha: ");
		        senha = sc.nextLine();
		        if (!validarSenha(senha)) {
		        	System.out.println("Senha inválida. Deve conter exatamente no mínimo 6 dígitos.");
		        	}
		        } while (!validarSenha(senha));
		
		        System.out.println("Insira a CREF: ");
		        String cref = sc.nextLine();

		        System.out.println("Insira a especialidade: ");
		        String especialidade = sc.nextLine().toUpperCase();

		        try {
		            Especialidades espec = Especialidades.valueOf(especialidade);
		            funcionarioLogado.cadastrarPersonal(nome, cpf, senha, cref, espec);
		        } catch (IllegalArgumentException e) {
		            System.out.println("Especialidade inválida. Tente novamente: ");
		            continue;
		        }

		        System.out.println("\nDeseja cadastrar outro personal? (S/N)");
		        opcao = sc.nextLine().trim().toUpperCase();
		    } while (opcao.equals("S"));

		    System.out.println("== Retornando ao menu ==");
		}

	public static void retornoLogin() throws ValorInvalidoException {
		Login();
	}
	
	private static boolean validarCpf(String cpf) {
	    return cpf != null && cpf.matches("\\d{11}");
	}
	
	private static boolean validarSenha(String senha){
		  return senha != null && senha.length() >= 6;
	}
	
}

