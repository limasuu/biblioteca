package bibli.modelo;

import java.util.HashMap;

public class AcervoFuncionario {
	
	private static HashMap<String, Funcionario> funcionarios= new HashMap<String, Funcionario>();

	public static HashMap<String, Funcionario> getFuncionarios() {

		return funcionarios;
	}

	public static int getNumeroFuncionarios() {

		return funcionarios.size();
	}

	public static boolean verificarExistenciaFuncionario(String matricula) {

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
	
	public static Funcionario editarFuncionario(Funcionario funcionarioAtualizado) {

		AcervoUsuario.editarUsuario(funcionarioAtualizado);
		return funcionarios.replace(funcionarioAtualizado.getMatricula(), funcionarioAtualizado);
	}

	public static Funcionario removerFuncionario(String matricula) {

		AcervoUsuario.removerUsuario(funcionarios.get(matricula).getCodigo());
		return funcionarios.remove(matricula);
	}	
}
