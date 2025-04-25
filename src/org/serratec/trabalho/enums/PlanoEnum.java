package org.serratec.trabalho.enums;

public enum PlanoEnum {
	 MENSAL_1_MODALIDADE("Mensal - 1 Modalidade"),
	    MENSAL_2_MODALIDADES("Mensal - 2 Modalidades"),
	    MENSAL_TOTAL("Mensal - Total"),
	    TRIMESTRAL_TOTAL("Trimestral - Total"),
	    SEMESTRAL_TOTAL("Semestral - Total"),
	    ANUAL_TOTAL("Anual - Total");

	    private final String descricao;

	    PlanoEnum(String descricao) {
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
