package org.serratec.trabalho.modelos;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.serratec.trabalho.enums.CargoFuncionario;
import org.serratec.trabalho.enums.Especialidades;
import org.serratec.trabalho.enums.PlanoEnum;
import org.serratec.trabalho.excecoes.PlanoExcecao;
import org.serratec.trabalho.excecoes.ValorInvalidoException;
import org.serratec.trabalho.metodos.BancoDeDados;
import org.serratec.trabalho.metodos.PlanoMetodos;
import org.serratec.trabalho.metodos.UsuarioMetodos;
import org.serratec.trabalho.relatorios.RelatorioAvaliacaoMensal;
import org.serratec.trabalho.relatorios.RelatorioPessoas;
import org.serratec.trabalho.relatorios.RelatorioPlanos;

public class Funcionario extends Pessoa{
	private CargoFuncionario cargo;

    public Funcionario(String nome, String cpf, String senha, CargoFuncionario cargo) {
        super(nome, cpf, senha);
        this.cargo = cargo;
    }

    public CargoFuncionario getCargo() {
        return cargo;
    }
    
    public void cadastrarPlano(PlanoEnum plano, String descricao, double valor) throws PlanoExcecao, ValorInvalidoException {
        if (PlanoMetodos.planoExistente(plano, descricao, valor)) {
            throw new PlanoExcecao("Plano já cadastrado.");
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

		Pessoa novoAluno = new Aluno(nome, cpf, senha, dataMatricula, plano);
		BancoDeDados.adicionarPessoaNaListaCorreta(novoAluno);
		System.out.println("Aluno cadastrado com sucesso!");
	}

	public void cadastrarPersonal(String nome, String cpf, String senha, String cref, Especialidades especialidade){
		if (UsuarioMetodos.cpfExistente(cpf)) {
			System.out.println("Falha no cadastro: CPF já cadastrado.");// trocar por exceções? 
			return;
		} 
		Pessoa novoPersonal = new Personal(nome, cpf, senha, cref, especialidade);
		BancoDeDados.adicionarPessoaNaListaCorreta(novoPersonal);
		System.out.println("Personal cadastrado com sucesso!"); 
	}
	
	public void emitirRelatorios() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            String menu = """
                    \n____MENU DE RELATÓRIOS____
                    1. Relatório de Avaliações
                    2. Relatório de Pessoas
                    3. Relatório de Planos
                    4. Sair
                    """;
            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> new RelatorioAvaliacaoMensal().gerar(); // Relatório de Avaliações
                case 2 -> new RelatorioPessoas().gerar();   
                case 3 -> new RelatorioPlanos().gerar();
                   
                default ->
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 4);
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

	@Override
	public String toString() { // usado só nos arquivos
		return " ---------------------------------------------------------------------------------------- " +
			"\nNome: " + nome +
			"\nCPF: " + cpf + 
			"\nSenha: " + senha + 
			"\nCargo: " + cargo;
	}
	
	
    
}