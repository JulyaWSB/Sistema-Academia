package org.serratec.trabalho.relatorios;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.serratec.trabalho.metodos.BancoDeDados;
import org.serratec.trabalho.modelos.GerarRelatorio;
import org.serratec.trabalho.modelos.Plano;

public class RelatorioPlanos implements GerarRelatorio {
	@Override
	public void gerar() {
		List<Plano> planos = BancoDeDados.listaPlanos();

		try (FileWriter writer = new FileWriter("relatorio_planos.csv")) {
			writer.write("Nome,Descrição,Valor\n");
			for (Plano plano : planos) {
				writer.write(plano.getPlano() + "," + plano.getDescricao() + "," + plano.getValor() + "\n");
			}
			System.out.println("Relatório de planos gerado com sucesso!");
		} catch (IOException e) {
			System.out.println("Erro ao gerar relatório de planos: " + e.getMessage());
		}
	}
}
