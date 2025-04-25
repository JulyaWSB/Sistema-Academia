package org.serratec.trabalho.relatorios;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.serratec.trabalho.metodos.BancoDeDados;
import org.serratec.trabalho.modelos.Aluno;
import org.serratec.trabalho.modelos.Funcionario;
import org.serratec.trabalho.modelos.GerarRelatorio;
import org.serratec.trabalho.modelos.Personal;

	public class RelatorioPessoas implements GerarRelatorio {
		@Override
		public void gerar() {
			List<Aluno> alunos = BancoDeDados.listaAlunos();
			List<Personal> personals = BancoDeDados.listaPersonal();
			List<Funcionario> funcionarios = BancoDeDados.listaFuncionarios();

			try (FileWriter writer = new FileWriter("relatorio_pessoas.csv")) {
				writer.write("Tipo,Nome,CPF,Telefone\n");

				for (Aluno aluno : alunos) {
					writer.write("Aluno," + aluno.getNome() + "," + aluno.getCpf() + "\n");
				}

				for (Personal personal : personals) {
					writer.write("Personal," + personal.getNome() + "," + personal.getCpf() + "\n");
				}

				for (Funcionario funcionario : funcionarios) {
					writer.write("Funcionário," + funcionario.getNome() + "," + funcionario.getCpf() + "\n");
				}

				System.out.println("Relatório de pessoas gerado com sucesso!");

			} catch (IOException e) {
				System.out.println("Erro ao gerar relatório de pessoas: " + e.getMessage());
			}
		}
	}