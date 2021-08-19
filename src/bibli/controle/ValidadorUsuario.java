package bibli.controle;

import java.util.ArrayList;

import bibli.modelo.AcervoFuncionario;
import bibli.modelo.AcervoUsuario;
import bibli.modelo.Funcionario;
import bibli.modelo.Usuario;

public class ValidadorUsuario extends Validador{

	public static boolean validarCamposUsuario(String nome, String endereco, 
			String telefone, String email) {

		ArrayList<String> strings= new ArrayList<String>();
		strings.add(nome);
		strings.add(endereco);
		strings.add(telefone);
		strings.add(email);

		return validarStrings(strings);
	}		

	public static boolean validarCamposFuncionario(String nome, String endereco, 
			String telefone, String email, 
			double salario, String cargo) {

		ArrayList<String> strings= new ArrayList<String>();
		strings.add(nome);
		strings.add(endereco);
		strings.add(telefone);
		strings.add(email);
		strings.add(cargo);

		if(!validarStrings(strings))
			return false;

		if(salario <= 0)
			return false;

		return true;	
	}

	public static Usuario validarAtualizacaoUsuario(String codigo, String nome, String endereco, 
			String telefone, String email) {

		Usuario usuario= AcervoUsuario.buscarUsuario(codigo);
		Usuario usuarioAtualizado= new Usuario(codigo, nome, endereco, telefone, email);

		if(usuario.equals(usuarioAtualizado))
			return null;

		usuario.setNome(nome);
		usuario.setEndereco(endereco);
		usuario.setTelefone(telefone);
		usuario.setEmail(email);			

		return usuario;		
	}

	public static Funcionario validarAtualizacaoFuncionario(String matricula, String nome, String endereco, 
			String telefone, String email, double salario, String cargo) {

		Funcionario funcionario= AcervoFuncionario.buscarFuncionario(matricula);
		Funcionario funcionarioAtualizado= new Funcionario(matricula, nome, endereco, telefone, email, salario, cargo);

		if(funcionario.equals(funcionarioAtualizado))
			return null;

		funcionario.setNome(nome);
		funcionario.setEndereco(endereco);
		funcionario.setTelefone(telefone);
		funcionario.setEmail(email);
		funcionario.setSalario(salario);
		funcionario.setCargo(cargo);

		return funcionario;		
	}
}
