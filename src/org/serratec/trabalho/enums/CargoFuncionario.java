package org.serratec.trabalho.enums;

public enum CargoFuncionario {
	SOCIO("Socio"),
	DONO("Dono"),
	ATENDENTE("Atendente"),
	LIMPEZA("Limpeza");
	
	private final String descricao;
	
	CargoFuncionario(String descricao) {
        this.descricao = descricao;
    }
	
	@Override
    public String toString() {
        return descricao;
    }
	
	public String getDescricao() {
        return descricao;
    }
}
