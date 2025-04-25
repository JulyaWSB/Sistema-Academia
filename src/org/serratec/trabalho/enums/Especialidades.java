package org.serratec.trabalho.enums;

public enum Especialidades {
    MUSCULACAO("Musculação"),
    TREINAMENTO_FUNCIONAL("Treinamento Funcional"),
    EMAGRECIMENTO("Treinamento para Emagrecimento"),
    HIPERTROFIA("Hipertrofia Muscular"),
    CONDICIONAMENTO("Condicionamento Físico"),
    PREPARACAO_ESPORTIVA("Preparação Física Esportiva"),
    REABILITACAO("Reabilitação Metabólica"),
    TREINAMENTO_PARA_IDOSOS("Treinamento para Idosos"),
    TREINAMENTO_PARA_GESTANTES("Treinamento para Gestantes"),
    AVALIACAO_FISICA("Avaliação Física"),
    ALONGAMENTO("Alongamento e Mobilidade"),
    GINASTICA_LABORAL("Ginástica Laboral"),
    PSICOMOTRICIDADE("Psicomotricidade"),
    ATIVIDADE_ADAPTADA("Atividade Física Adaptada"),
    INFANTIL("Educação Corporal Infantil");

    private final String descricao;

	Especialidades(String descricao) {
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
