package bibli.controle;

import bibli.modelo.AcervoPessoa;
import bibli.modelo.Pessoa;

public class ValidadorPessoa {
	
	public static boolean validarCampo(String campo) {

		if(campo == null)
			return false;		

		if(campo.isBlank())
			return false;

		return true;		
	}
	
	public static boolean validarCamposPessoa(String nome, String endereco, 
			String telefone, String email) {
		
		if(nome == null || endereco == null || 
				telefone == null || email == null)
			return false;

		if(nome.isBlank() || endereco.isBlank() || 
				telefone.isBlank() || email.isBlank())
			return false;

		return true;	
	}
	
	public static Pessoa validarAtualizacaoPessoa(String codigo, String nome, String endereco, 
			String telefone, String email) {

		Pessoa pessoa= AcervoPessoa.buscarPessoa(codigo);
		
		if(pessoa.getNome().equals(nome) &&
				pessoa.getEndereco().equals(endereco) &&
				pessoa.getTelefone().equals(telefone) &&
				pessoa.getEmail().equals(email))
			return null;
		else {
			pessoa.setNome(nome);
			pessoa.setEndereco(endereco);
			pessoa.setTelefone(telefone);
			pessoa.setEmail(email);			
		}
			return pessoa;		
	}

}
