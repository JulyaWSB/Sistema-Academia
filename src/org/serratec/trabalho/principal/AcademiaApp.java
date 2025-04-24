package org.serratec.trabalho.principal;

import java.time.LocalDate;

import org.serratec.trabalho.enums.CargoFuncionario;
import org.serratec.trabalho.enums.Especialidades;
import org.serratec.trabalho.enums.PlanoEnum;
import org.serratec.trabalho.excecoes.ValorInvalidoException;
import org.serratec.trabalho.modelos.Aluno;
import org.serratec.trabalho.modelos.Funcionario;
import org.serratec.trabalho.modelos.Personal;
import org.serratec.trabalho.modelos.Pessoa;
import org.serratec.trabalho.modelos.Plano;

public class AcademiaApp {

	public static void main(String[] args) throws ValorInvalidoException {
		Pessoa personal = new Personal("Rayca", "123456789-22", "1234", "12345-G-RJ", Especialidades.MUSCULACAO);
		Plano plano = new Plano(PlanoEnum.MENSAL_1_MODALIDADE, "Qualquer coisa", 200.00);
		Pessoa aluno = new Aluno("Leo", "789456123-22", "3216", LocalDate.parse("2025-04-10"), plano );
		Pessoa funcionario = new Funcionario("Paulo", "987654123-22", "9874", CargoFuncionario.DONO);
				
	
	}

}
