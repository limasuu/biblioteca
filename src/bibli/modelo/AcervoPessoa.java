package bibli.modelo;

import java.util.HashMap;

public class AcervoPessoa {

	private static HashMap<String, Pessoa> pessoas= new HashMap<String, Pessoa>();

	public static HashMap<String, Pessoa> getPessoas() {

		return pessoas;
	}

	public static HashMap<String, Funcionario> getFuncionarios(){

		HashMap<String, Funcionario> funcionariosEncontrados= new HashMap<String, Funcionario>();

		for(Pessoa pessoa : pessoas.values()) 
			if(pessoa instanceof Funcionario) {
				Funcionario funcionario= (Funcionario) pessoa;
				funcionariosEncontrados.put(funcionario.getMatricula(), funcionario);	
			}
		return funcionariosEncontrados;
	}

	public static int getNumeroPessoas() {

		return pessoas.size();
	}

	public static int getNumeroFuncionarios(){

		int total= 0;

		for(Pessoa pessoa : pessoas.values()) 
			if(pessoa instanceof Funcionario)
				total++;

		return total;
	}

	public static boolean verificarExistenciaPessoa(String codigo) {

		return pessoas.containsKey(codigo);		
	}

	public static boolean verificarExistenciaFuncionario(String matricula) {

		for(Pessoa pessoa : pessoas.values()) 
			if(pessoa instanceof Funcionario) 
				if(((Funcionario) pessoa).getMatricula().equals(matricula))
					return true;

		return false;
	}

	public static Pessoa buscarPessoa(String codigo) {

		return pessoas.get(codigo);
	}

	public static Funcionario buscarFuncionario(String matricula) {

		Funcionario funcionario= null;

		for(Pessoa pessoa : pessoas.values()) 
			if(pessoa instanceof Funcionario) 
				if(((Funcionario) pessoa).getMatricula().equals(matricula))
					funcionario= (Funcionario) pessoa;

		return funcionario;
	}	

	public static HashMap<String, Funcionario> buscarFuncionariosPorCargo(String cargo) {

		HashMap<String, Funcionario> funcionariosEncontrados= new HashMap<String, Funcionario>();

		for(Pessoa pessoa : pessoas.values()) 
			if(pessoa instanceof Funcionario) {
				Funcionario funcionario= (Funcionario) pessoa;
				if(funcionario.getCargo().equals(cargo)) 
					funcionariosEncontrados.put(funcionario.getMatricula(), funcionario);	
			}
		return funcionariosEncontrados;
	}	
	
	public static void adicionarPessoa(Pessoa pessoa) {

		pessoas.put(pessoa.getCodigo(), pessoa);
	}
	
	public static Pessoa editarPessoa(Pessoa pessoaAtualizada) {

		return pessoas.replace(pessoaAtualizada.getCodigo(), pessoaAtualizada);
	}

	public static Pessoa removerPessoa(String codigo) {

		return pessoas.remove(codigo);
	}	
}
