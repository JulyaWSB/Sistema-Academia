package org.serratec.trabalho.relatorios;

import java.io.FileWriter;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.serratec.trabalho.metodos.BancoDeDados;
import org.serratec.trabalho.modelos.Avaliacao;
import org.serratec.trabalho.modelos.GerarRelatorio;

public class RelatorioAvaliacaoMensal implements GerarRelatorio {

    @Override
    public void gerar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o mês e ano para gerar o relatório (MM/yyyy): ");
        String input = sc.nextLine();

        YearMonth mesAno;
        try {
            mesAno = YearMonth.parse(input, DateTimeFormatter.ofPattern("MM/yyyy"));
        } catch (Exception e) {
            System.out.println("Formato inválido. Use MM/yyyy.");
            return;
        }

        List<Avaliacao> avaliacoes = BancoDeDados.listaAvaliacoes();
        StringBuilder sb = new StringBuilder();
        sb.append("Aluno,Data,Personal,Descrição\n");

        for (Avaliacao a : avaliacoes) {
            if (YearMonth.from(a.getData()).equals(mesAno)) {
                sb.append(a.getAluno().getNome()).append(",");
                sb.append(a.getData()).append(",");
                sb.append(a.getPersonal().getNome()).append(",");
                sb.append(a.getDescricao().replace(",", " ")).append("\n");
            }
        }

        try (FileWriter writer = new FileWriter("relatorio_avaliacoes_" + input.replace("/", "-") + ".csv")) {
            writer.write(sb.toString());
            System.out.println("Relatório gerado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

	}

