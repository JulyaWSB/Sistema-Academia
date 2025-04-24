package org.serratec.trabalho.modelos;

import org.serratec.trabalho.excecoes.CampoObrigatorioException;

public abstract class Pessoa {
	protected String nome;
    protected String cpf;
    protected String senha;

    public Pessoa(String nome, String cpf, String senha) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new CampoObrigatorioException("nome");
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new CampoObrigatorioException("CPF");
        }
        if (senha == null || senha.trim().isEmpty()) {
            throw new CampoObrigatorioException("senha");
        }

        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }
     

    public String getNome() {
		return nome;
	}


	public String getCpf() {
        return cpf;
    }

	public String getSenha() {
		return senha;
	}
    
    
}
