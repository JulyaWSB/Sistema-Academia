package org.serratec.trabalho.modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.serratec.trabalho.metodos.BancoDeDados;


public class Aluno extends Pessoa implements GerarRelatorio{
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
		}}

/*	public void removerPersonal() {
		this.personalContratado = null;
	}*/

	public boolean possuiPersonal() {
		return personalContratado != null;
	}


	public void exibirDados() { // usar esse como visualizarDadosPessoais 
		System.out.println("Nome: " + nome);
		System.out.println("\nCPF: " + cpf);
		System.out.println("\nData de matricula: " + formatador.format(dataMatricula));
		System.out.println("\nPlano: "); plano.exibirPlano();
		System.out.println(personalContratado != null? "\nPersonal contratado: "  + personalContratado : "");
	}

	public void visualizarAvaliacoes(Aluno aluno){
		if (BancoDeDados.listaAvaliacoesPorAluno(aluno).isEmpty()) {
			System.out.println("Nenhuma avaliação do(a) aluno(a) " + aluno.getNome() + " registrada.");
		} else {
			System.out.println("---Avaliações de " + aluno.getNome() + "---");
			for (Avaliacao av : BancoDeDados.listaAvaliacoesPorAluno(aluno)) {
				System.out.println("Data: "+ formatador.format(av.getData()));
				System.out.println("Personal: " ); av.getPersonal().exibirPersonal();
				System.out.println("Descrição: " + av.getDescricao());
			}
		}
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
	public void gerar() {
		//método obrigatório

	}

	@Override
	public String toString() {
		return nome;
	}
}