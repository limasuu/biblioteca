package bibli.dados;

import java.time.LocalDateTime;
import java.util.HashMap;

import bibli.aplicacao.Principal;
import bibli.modelo.Funcionario;

public class AcervoFuncionario extends Acervo{

	private static HashMap<String, Funcionario> funcionarios= new HashMap<String, Funcionario>();

	public static HashMap<String, Funcionario> getFuncionarios() {

		return funcionarios;
	}

	public static int getNumeroFuncionarios() {

		return funcionarios.size();
	}

	public static boolean verificarExistencia(String matricula) {

		return funcionarios.containsKey(matricula);		
	}

	public static Funcionario buscarFuncionario(String matricula) {

		return funcionarios.get(matricula);
	}

	public static HashMap<String, Funcionario> buscarFuncionariosPorCargo(String cargo) {

		HashMap<String, Funcionario> funcionariosEncontrados= new HashMap<String, Funcionario>();

		for(Funcionario funcionario : funcionarios.values()) 
			if(funcionario.getCargo().equalsIgnoreCase(cargo)) 
				funcionariosEncontrados.put(funcionario.getMatricula(), funcionario);	

		return funcionariosEncontrados;
	}	

	public static void adicionarFuncionario(Funcionario funcionario) {

		funcionarios.put(funcionario.getMatricula(), funcionario);
		AcervoUsuario.adicionarUsuario(funcionario);
	}

	public static Funcionario removerFuncionario(String matricula) {

		AcervoUsuario.removerUsuario(funcionarios.get(matricula).getCodigo());
		return funcionarios.remove(matricula);
	}	

	public static void iniciar() {

		iniciarLeitor( Principal.getMensagem("arquivo.funcionario") );

		String linha= null;

		linha= ler();

		if(linha != null) {
			int totalFuncionariosJaCadastrados= Integer.parseInt(linha);
			Funcionario.setTotalFuncionariosJaCadastrados(totalFuncionariosJaCadastrados);
		}

		while(linha != null) {

			linha= ler();

			if(linha != null) {
				String[] atributosFuncionario = linha.split("##");

				String matricula= atributosFuncionario[0];
				String codigo= atributosFuncionario[1];
				String nome= atributosFuncionario[2];
				String endereco= atributosFuncionario[3];
				String telefone= atributosFuncionario[4];
				String email= atributosFuncionario[5];
				boolean bloqueado= Boolean.parseBoolean(atributosFuncionario[6]);
				LocalDateTime dataFimBloqueio= resgatarData(atributosFuncionario[7]);
				double salario= Double.parseDouble(atributosFuncionario[8]);
				String cargo= atributosFuncionario[9];

				Funcionario funcionario= new Funcionario(matricula, codigo, nome, endereco, telefone, email,
						bloqueado, dataFimBloqueio, salario, cargo);
				adicionarFuncionario(funcionario);
			}
		}

		fecharLeitor();
	}

	public static void encerrar() {

		iniciarEscritor( Principal.getMensagem("arquivo.funcionario") );

		escrever(String.valueOf(Funcionario.getTotalFuncionariosJaCadastrados()));

		for(Funcionario funcionario : funcionarios.values()) {

			String[] atributosFuncionario= new String[10];

			atributosFuncionario[0]= funcionario.getMatricula();
			atributosFuncionario[1]= funcionario.getCodigo();
			atributosFuncionario[2]= funcionario.getNome();
			atributosFuncionario[3]= funcionario.getEndereco();
			atributosFuncionario[4]= funcionario.getTelefone();
			atributosFuncionario[5]= funcionario.getEmail();
			atributosFuncionario[6]= String.valueOf(funcionario.isBloqueado());
			atributosFuncionario[7]= prepararData(funcionario.getDataFimBloqueio());
			atributosFuncionario[8]= String.valueOf(funcionario.getSalario());
			atributosFuncionario[9]= funcionario.getCargo();	

			String linha= prepararLinha(atributosFuncionario);				
			escrever(linha);
		}		

		fecharEscritor();
	}	
}
