package org.serratec.trabalho.modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.serratec.trabalho.metodos.BancoDeDados;


public class Aluno extends Pessoa {
	private LocalDate dataMatricula;
	private Plano plano;
	private Personal personalContratado;

	public Aluno(String nome, String cpf, String senha, LocalDate dataMatricula, Plano plano) {
		super(nome, cpf, senha);
		this.dataMatricula = dataMatricula;
		this.plano = plano;
	}
	
	private DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public void contratarPersonal(Personal personal) {
		if (possuiPersonal()) {
			System.out.println("Aluno já possui um personal contratado.");
		} 
		else {
			this.personalContratado = personal;
			System.out.println("Personal contratado com sucesso!");
		}
	}


	public boolean possuiPersonal() {
		return personalContratado != null;
	}


	public void exibirDados() { // usar esse como visualizarDadosPessoais 
		System.out.println("Nome: " + nome);
		System.out.println("CPF: " + formatarCpf(cpf));
		System.out.println("Data de matricula: " + formatador.format(dataMatricula));
		System.out.println("Plano: "); plano.exibirPlano();
		if (personalContratado != null) {
	        System.out.println("\nPersonal contratado: " + personalContratado.getNome());
	        System.out.println("Especialidade: " + personalContratado.getEspecialidade());
	    } else {
	        System.out.println("\nNenhum personal contratado.");
	    }
	}

	public void visualizarAvaliacoes(Aluno aluno){
		if (BancoDeDados.listaAvaliacoesPorAluno(aluno).isEmpty()) {
			System.out.println("Nenhuma avaliação do(a) aluno(a) " + aluno.getNome() + " registrada.");
		} else {
			System.out.println("--- Avaliações de " + aluno.getNome() + " ---");
			for (Avaliacao av : BancoDeDados.listaAvaliacoesPorAluno(aluno)) {
				System.out.println("Data: "+ formatador.format(av.getData()));
				System.out.println("Personal: " ); av.getPersonal().exibirPersonal();
				System.out.println("Descrição: " + av.getDescricao());
			}
		}
	}
	
	private static String formatarCpf(String cpf) {
	    return cpf.substring(0, 3) + "." +
	           cpf.substring(3, 6) + "." +
	           cpf.substring(6, 9) + "-" +
	           cpf.substring(9, 11);
	}

	public Plano getPlano() {
		return plano;
	}
	public Personal getPersonalContratado() {
		return personalContratado;
	}

	public LocalDate getDataMatricula() {
		return dataMatricula;
	}

	@Override
	public String toString() { // usar nos arquivos
		return  " -------------------- " +
		"\nNome: " + nome +
		"\nCPF: " + cpf + 
		"\nSenha: " + senha + 
		"\nData de matricula: " + dataMatricula +
		"\nPlano: " + plano ;
	}
}