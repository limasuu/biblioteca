package bibli.controle;

import java.util.ArrayList;

import bibli.modelo.AcervoFuncionario;
import bibli.modelo.AcervoPessoa;
import bibli.modelo.Funcionario;
import bibli.modelo.Pessoa;

public class ValidadorPessoa extends Validador{

	public static boolean validarCamposPessoa(String nome, String endereco, 
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

	public static Funcionario validarAtualizacaoFuncionario(String matricula, String nome, String endereco, 
			String telefone, String email, double salario, String cargo) {

		Funcionario funcionario= AcervoFuncionario.buscarFuncionario(matricula);

		if(funcionario.getNome().equals(nome) &&
				funcionario.getEndereco().equals(endereco) &&
				funcionario.getTelefone().equals(telefone) &&
				funcionario.getEmail().equals(email) &&
				funcionario.getSalario() == salario &&
				funcionario.getCargo().equals(cargo))			
			return null;
		else {
			funcionario.setNome(nome);
			funcionario.setEndereco(endereco);
			funcionario.setTelefone(telefone);
			funcionario.setEmail(email);
			funcionario.setSalario(salario);
			funcionario.setCargo(cargo);
		}
		return funcionario;		
	}
}
