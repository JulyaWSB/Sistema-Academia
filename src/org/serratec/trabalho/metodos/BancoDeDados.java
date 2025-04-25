package org.serratec.trabalho.metodos;

import java.util.ArrayList;
import java.util.List;

import org.serratec.trabalho.modelos.Aluno;
import org.serratec.trabalho.modelos.Avaliacao;
import org.serratec.trabalho.modelos.Funcionario;
import org.serratec.trabalho.modelos.Personal;
import org.serratec.trabalho.modelos.Pessoa;
import org.serratec.trabalho.modelos.Plano;

public class BancoDeDados {

	public static List<Aluno> alunos = new ArrayList<>();
	public static List<Personal> personals = new ArrayList<>();
	public static List<Funcionario> funcionarios = new ArrayList<>();
	public static List<Plano> planos = new ArrayList<>();
	public static List<Avaliacao> avaliacoes = new ArrayList<>();

	public static void adicionarAluno(Aluno aluno){
		alunos.add(aluno);
	}

	public static void adicionarPersonal(Personal personal) {
		personals.add(personal);
	}

	public static void adicionarFuncionario(Funcionario funcionario){ 
		funcionarios.add(funcionario);
	}

	public static void adicionarPlano(Plano plano) {
		planos.add(plano);
	}

	public static void adicionarAvaliacao(Avaliacao avaliacao) {
		avaliacoes.add(avaliacao);
	}


	public static List<Aluno> listaAlunos() {
		return alunos;
	} 

	public static List<Personal> listaPersonal() {
		return personals;
	} 

	public static List<Funcionario> listaFuncionarios() {
		return funcionarios;
	} 

	public static List<Plano> listaPlanos(){
		return planos;
	}

	public static List<Avaliacao> listaAvaliacoes(){
		return avaliacoes;
	}

	public static List<Avaliacao> listaAvaliacoesPorAluno(Aluno aluno) {
		List<Avaliacao> porAluno = new ArrayList<>();
		for (Avaliacao avaliacao : listaAvaliacoes()) {
			if (avaliacao.getAluno().equals(aluno)) {
				porAluno.add(avaliacao);
			}
		} return porAluno;
	}

	/*public static List<Avaliacao> listaAvaliacoesPorPeriodo(LocalDate inicio, LocalDate fim){
		List <Avaliacao> porPeriodo = new ArrayList<>();
		for (Avaliacao avaliacao : listaAvaliacoes()) {
			if ( (avaliacao.getData().isEqual(inicio) || avaliacao.getData().isAfter(inicio)) &&  
					(avaliacao.getData().isEqual(fim) || avaliacao.getData().isBefore(fim))) {
				porPeriodo.add(avaliacao);
			}
		} return porPeriodo;
	}*/
	
	public static void adicionarPessoaNaListaCorreta(Pessoa pessoa) {
	    if (pessoa instanceof Aluno) {
	        BancoDeDados.adicionarAluno((Aluno) pessoa);
	    } else if (pessoa instanceof Personal) {
	        BancoDeDados.adicionarPersonal((Personal) pessoa);
	    } else if (pessoa instanceof Funcionario) {
	        BancoDeDados.adicionarFuncionario((Funcionario) pessoa);
	    }
	}

	public static List<Pessoa> listaTodasAsPessoas() {
		List<Pessoa> todos = new ArrayList<>();
		todos.addAll(alunos);
		todos.addAll(personals);
		todos.addAll(funcionarios);
		return todos;
	}
	
	public static Pessoa buscarPessoaPorCpf(String cpf){
		for (Pessoa p : listaTodasAsPessoas()) {
			if (p.getCpf().equals(cpf)) {
				return p;
			}
		} return null; //substituir por exceção: UsuarioNaoEncontradoException(); 
	}
}




