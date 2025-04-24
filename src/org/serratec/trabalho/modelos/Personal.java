package org.serratec.trabalho.modelos;

import java.time.LocalDate;

import org.serratec.trabalho.enums.Especialidades;
import org.serratec.trabalho.metodos.BancoDeDados;

public class Personal extends Pessoa{
	private String cref;
	private Especialidades especialidade;

	public Personal(String nome, String cpf, String senha, String cref, Especialidades especialidade) {
		super(nome, cpf, senha);
		this.cref = cref;
		this.especialidade = especialidade;
	}

	public void visualizarAlunos() {
		boolean encontrouAluno = false;
		for (Aluno al : BancoDeDados.listaAlunos()) {
			if (this.equals(al.getPersonalContratado())) {
				al.exibirDados();  
				encontrouAluno = true;
			}
		}
		if (!encontrouAluno) {
			System.out.println("Nenhum aluno vinculado.");
		}
	}

	public void registrarAvaliacao(Aluno aluno, LocalDate data,  Personal personal,  String descricao) {
		Avaliacao novaAvaliacao = new Avaliacao(aluno, data, personal, descricao);
		BancoDeDados.adicionarAvaliacao(novaAvaliacao);
		System.out.println("Avaliação registrada com sucesso!");
	} 

	public void visualizarAvaliacoes() {
		boolean encontrouAvaliacao = false;
		for (Avaliacao av : BancoDeDados.listaAvaliacoes()) {
			if (av.getPersonal().equals(this)) {
				av.exibirDados();
				encontrouAvaliacao = true;
			}}
		if (!encontrouAvaliacao) {
			System.out.println("Nenhuma avaliação registrada."); //podemos adicionar tratamento nesse e no de visualizarAlunos para retornar ao menu se quiserem
		}


	}

	public String getCref() {
		return cref;
	}

	public Especialidades getEspecialidade() {
		return especialidade;
	}
	
	public void exibirPersonal() {
		System.out.println("Nome:" + nome);
		System.out.println("Especialidade:" + especialidade);
	}

	@Override
	public String toString() {
		return nome  + "\nEspecialidade: " + especialidade;
	}
	
	
	
}