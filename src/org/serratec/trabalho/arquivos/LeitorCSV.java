package org.serratec.trabalho.arquivos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.serratec.trabalho.enums.CargoFuncionario;
import org.serratec.trabalho.enums.Especialidades;
import org.serratec.trabalho.enums.PlanoEnum;
import org.serratec.trabalho.excecoes.ValorInvalidoException;
import org.serratec.trabalho.metodos.BancoDeDados;
import org.serratec.trabalho.modelos.Aluno;
import org.serratec.trabalho.modelos.Avaliacao;
import org.serratec.trabalho.modelos.Funcionario;
import org.serratec.trabalho.modelos.Personal;
import org.serratec.trabalho.modelos.Pessoa;
import org.serratec.trabalho.modelos.Plano;

public class LeitorCSV {

	public static void inicializador(){
//		Ler Planos	

		Path path1 = Paths.get("C:\\Users\\SERRAE\\Desktop\\Trabalho POO\\Sistema-Academia\\plano.csv");

		try (BufferedReader br = Files.newBufferedReader(path1)) {
			String linha = br.readLine();

			while ((linha = br.readLine()) != null) {
				String[] campos = linha.split(",");

				String descricao = campos[1];
				double valor = Double.parseDouble(campos[2]);
				try {
					PlanoEnum plano = PlanoEnum.valueOf(campos[0].toUpperCase());
					try {
						BancoDeDados.adicionarPlano(new Plano(plano,descricao,valor));
					} catch (ValorInvalidoException e) {
						 // tem que testar isso aqui, ou retirar esse try/catch
						e.printStackTrace();
					}
				} catch (IllegalArgumentException e) {
					System.out.println("Periodicidade inválida: " + campos[0]);
				}
			}

			System.out.println("\nPlanos Lidos do Arquivo CSV: ");
			BancoDeDados.listaPlanos().forEach(System.out::println);

		} catch (IOException e) {
			e.printStackTrace();
		}

// Ler Personal
		
				Path path2 = Paths.get("C:\\Users\\SERRAE\\Desktop\\Trabalho POO\\Sistema-Academia\\personal.csv");

				try (BufferedReader br = Files.newBufferedReader(path2)) {
					String linha = br.readLine();

					while ((linha = br.readLine()) != null) {
						String[] campos = linha.split(",");
						String nome = campos[0];
						String cpf = campos[1];
						String senha = campos[2];
						String cref = campos[3];
						try {
							Especialidades especialidade = Especialidades.valueOf(campos[4].toUpperCase());
							Pessoa personal = new Personal(nome,cpf,senha,cref,especialidade);
							BancoDeDados.adicionarPessoaNaListaCorreta(personal);
						}catch (IllegalArgumentException e) {
							System.out.println("Especialidade inválida para o personal " + nome + ": " + campos[4]);
						}

					}

					System.out.println("\nPersonal Lidos do Arquivo CSV: ");
					BancoDeDados.listaPersonal().forEach(System.out::println);

				} catch (IOException e) {
					e.printStackTrace();
				}
// Ler Alunos
		
		Path path3 = Paths.get("C:\\Users\\SERRAE\\Desktop\\Trabalho POO\\Sistema-Academia\\aluno.csv");

		try (BufferedReader reader = Files.newBufferedReader(path3)){
			String linha = reader.readLine(); //vai ignorar o cabeçalho igual a prof ensinou

			while((linha = reader.readLine()) != null) {
				String[] campos = linha.split(",");
				String nome  = campos[0];
				String cpf = campos[1];
				String senha = campos[2];
				String dataMatricula = campos[3];
				int indicePlano = Integer.parseInt(campos[4]);

				DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate dataRegistro = LocalDate.parse(dataMatricula, formatador);
				
				List<Plano> planos = BancoDeDados.listaPlanos();
				if (indicePlano < 0 || indicePlano >= planos.size()){
					System.out.println("Índice de plano inválido.");
				}
				Plano plano = planos.get(indicePlano);
				
			Pessoa aluno = new Aluno(nome, cpf, senha, dataRegistro, plano);
			BancoDeDados.adicionarPessoaNaListaCorreta(aluno);
			}

			System.out.println("\nAlunos Lidos do Arquivo CSV: ");
			BancoDeDados.listaAlunos().forEach(System.out::println);

		} catch(IOException e) {
			e.printStackTrace();
		}


//// Ler Funcionarios

		Path path4 = Paths.get("C:\\Users\\SERRAE\\Desktop\\Trabalho POO\\Sistema-Academia\\funcionario.csv");

		try (BufferedReader br = Files.newBufferedReader(path4)) {
			String linha = br.readLine();

			while ((linha = br.readLine()) != null) {
				String[] campos = linha.split(",");
				String nome = campos[0];
				String cpf = campos[1];
				String senha = campos[2];
				try {
					CargoFuncionario cargo = CargoFuncionario.valueOf(campos[3].toUpperCase());
					Pessoa funcionario = new Funcionario(nome,cpf,senha,cargo);
					BancoDeDados.adicionarPessoaNaListaCorreta(funcionario);
				} catch (IllegalArgumentException e) {
					System.out.println("Cargo inválido para o funcionário " + nome + ": " + campos[3]);
				}

			}

			System.out.println("\nFuncionários Lidos do Arquivo CSV: ");
			BancoDeDados.listaFuncionarios().forEach(System.out::println);

		} catch (IOException e) {
			e.printStackTrace();
		}

	
		

//// Ler Avaliações

		Path path5 = Paths.get("C:\\Users\\SERRAE\\Desktop\\Trabalho POO\\Sistema-Academia\\avaliacao.csv");

		try (BufferedReader br = Files.newBufferedReader(path5)) {
			String linha = br.readLine();

			while ((linha = br.readLine()) != null) {
				String[] campos = linha.split(",");
				String cpfAluno = campos[0];
				String dataString = campos[1];
				String cpfPersonal = campos[2];
				String descricao = campos[3];

				Pessoa pessoaAluno = BancoDeDados.buscarPessoaPorCpf(cpfAluno);
				if (!(pessoaAluno instanceof Aluno)) {
					System.out.println("CPF informado não está vinculado a um aluno.");
					continue;
				}
				Aluno aluno = (Aluno) pessoaAluno;

				Pessoa pessoaPersonal = BancoDeDados.buscarPessoaPorCpf(cpfPersonal);
				if (!(pessoaPersonal instanceof Personal)) {
					System.out.println("CPF informado não está vinculado a um personal.");
					continue;
				}
				Personal personal = (Personal) pessoaPersonal;

				DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate dataRegistro = LocalDate.parse(dataString, formatador);

				Avaliacao avaliacao = new Avaliacao(aluno,dataRegistro,personal,descricao);
				BancoDeDados.adicionarAvaliacao(avaliacao);			}
			System.out.println("\nAvaliações Lidas do Arquivo CSV: ");
			BancoDeDados.listaAvaliacoes().forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

}


