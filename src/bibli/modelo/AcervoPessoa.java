package bibli.modelo;

import java.util.HashMap;

public class AcervoPessoa {

	private static HashMap<String, Pessoa> pessoas= new HashMap<String, Pessoa>();

	public static HashMap<String, Pessoa> getPessoas() {

		return pessoas;
	}

	public static int getNumeroPessoas() {

		return pessoas.size();
	}

	public static boolean verificarExistenciaPessoa(String codigo) {

		return pessoas.containsKey(codigo);		
	}

	public static Pessoa buscarPessoa(String codigo) {

		return pessoas.get(codigo);
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
