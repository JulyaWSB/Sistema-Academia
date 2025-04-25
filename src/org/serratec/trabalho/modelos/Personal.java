package org.serratec.trabalho.modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

	public boolean visualizarAlunos() {
	    boolean encontrouAluno = false;
	    int contador = 1;  
	    
	    for (Aluno al : BancoDeDados.listaAlunos()) {
	        if (this.equals(al.getPersonalContratado())) {
	            System.out.println(contador + " - Nome: " + al.getNome() + 
	                               ", CPF: " + formatarCpf(al.getCpf()) + 
	                               ", Plano: " + al.getPlano().getDescricao());
	            contador++;
	            encontrouAluno = true;
	        }
	    }

	    if (!encontrouAluno) {
	        System.out.println("Nenhum aluno vinculado.");
	        
	    } return encontrouAluno;
	}
	
	public void registrarAvaliacao(Aluno aluno, LocalDate data,  Personal personal,  String descricao) {
		Avaliacao novaAvaliacao = new Avaliacao(aluno, data, personal, descricao);
		BancoDeDados.adicionarAvaliacao(novaAvaliacao);
		System.out.println("Avaliação registrada com sucesso!");
	} 

	public void visualizarAvaliacoes() {
	    boolean encontrouAvaliacao = false;

	    DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	    for (Avaliacao av : BancoDeDados.listaAvaliacoes()) {
	        if (av.getPersonal().equals(this)) {
	            System.out.println("Data: " + av.getData().format(formatoData));
	            System.out.println("Aluno: " + av.getAluno().getNome() + 
	                               ", CPF: " + formatarCpf(av.getAluno().getCpf()));
	            System.out.println("Personal: " + av.getPersonal().getNome());
	            System.out.println("Descrição da avaliação: " + av.getDescricao());
	            System.out.print("\n");
	            encontrouAvaliacao = true;
	        }
	    }
	   
	    if (!encontrouAvaliacao) {
	        System.out.println("Nenhuma avaliação registrada.");
	        return;
	    }
	}
	
	private static String formatarCpf(String cpf) {
	    return cpf.substring(0, 3) + "." +
	           cpf.substring(3, 6) + "." +
	           cpf.substring(6, 9) + "-" +
	           cpf.substring(9, 11);
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
	public String toString() { // usado só nos arquivos
		return  " -------------------- " +
			"\nNome: " + nome +
			"\nCPF: " + cpf + 
			"\nSenha: " + senha + 
			"\nCREF: " + cref +
			"\nEspecialidade: " + especialidade;
	}
	
	
	
}