package bibli.controle;

import java.util.HashMap;

import bibli.modelo.AcervoFuncionario;
import bibli.modelo.AcervoPessoa;
import bibli.modelo.Funcionario;

public class ControladorFuncionario {

	public static boolean exibirFuncionario(String matricula) {

		if(!ValidadorPessoa.validarCampo(matricula)) {
			System.err.println("Matrícula inválida.");
			return false;
		}

		if(!AcervoFuncionario.verificarExistenciaFuncionario(matricula)){
			System.err.println("Não foi encontrado funcionário com a matrícula informada.");
			return false;
		}	

		System.out.println("\n" + AcervoFuncionario.buscarFuncionario(matricula));

		return true;
	}
	
	public static void exibirFuncionarios() {

		if(AcervoFuncionario.getNumeroFuncionarios() == 0)
			System.out.println("Não há funcionários cadastrados.");
		else {

			System.out.println("\n----------------- Funcionários -----------------");
			System.out.println("Quantidade: " + AcervoFuncionario.getNumeroFuncionarios());	
			System.out.println("------------------------------------------");
			
			for(Funcionario funcionario : AcervoFuncionario.getFuncionarios().values())
				System.out.println(funcionario+"\n");
			
			System.out.println("------------------------------------------");
		}
	}	

	public static boolean exibirFuncionariosPorCargo(String cargo){

		if(!ValidadorPessoa.validarCampo(cargo)) {
			System.err.println("Nome de cargo inválido.");
			return false;
		}

		HashMap<String, Funcionario> funcionariosEncontrados= AcervoFuncionario.buscarFuncionariosPorCargo(cargo);		

		if(funcionariosEncontrados.size() == 0) 
			System.err.println("Não há funcionários registrados para o cargo informado.");			
		else {

			System.out.println("\n----------- Funcionários com o cargo \"" + cargo + "\" -----------");
			System.out.println("Quantidade: " + funcionariosEncontrados.size());	
			System.out.println("------------------------------------------");

			for(Funcionario funcionario: funcionariosEncontrados.values())
				System.out.println("\n" + funcionario);	

			System.out.println("------------------------------------------");
		}

		return true;
	}	
	
	public static boolean adicionarFuncionario(String nome, String endereco, 
			String telefone, String email, 
			double salario, String cargo) {

		if(!ValidadorPessoa.validarCamposFuncionario(nome, endereco, telefone, 
				email, salario, cargo)) {
			System.err.println("Há campos inválidos.");
			return false;
		}
		
		Funcionario funcionario= new Funcionario(nome, endereco, telefone, email, salario, cargo);
		
		AcervoFuncionario.adicionarFuncionario(funcionario);
		AcervoPessoa.adicionarPessoa(funcionario);

		return true;
	}
	
	public static boolean editarFuncionario(String matricula, String novoNome, String novoEndereco, 
			String novoTelefone, String novoEmail, double novoSalario, String novoCargo) {

		if(!ValidadorPessoa.validarCamposFuncionario(novoNome, novoEndereco, novoTelefone, 
				novoEmail, novoSalario, novoCargo)){
			System.err.println("Há campos inválidos.");
			return false;
		}

		if(!AcervoFuncionario.verificarExistenciaFuncionario(matricula)){
			System.err.println("Não foi encontrado funcionário com a matrícula informada.");
			return false;
		}		

		Funcionario funcionarioAtualizado= ValidadorPessoa.validarAtualizacaoFuncionario(matricula, novoNome, novoEndereco, novoTelefone, novoEmail, novoSalario, novoCargo);

		if(funcionarioAtualizado == null){
			System.err.println("\nNão há mudanças para realizar.");
			return false;
		}	

		AcervoFuncionario.editarFuncionario(funcionarioAtualizado);
		AcervoPessoa.editarPessoa(funcionarioAtualizado);

		return true;
	}
	
	public static String removerFuncionario(String matricula) {

		if(!ValidadorPessoa.validarCampo(matricula)) {
			System.err.println("Matrícula inválida.");
			return null;
		}

		if(!AcervoFuncionario.verificarExistenciaFuncionario(matricula)){
			System.err.println("Não foi encontrado funcionário com a matrícula informada.");
			return null;
		}		

		Funcionario funcionario= AcervoFuncionario.buscarFuncionario(matricula);
		AcervoFuncionario.removerFuncionario(matricula);
		AcervoPessoa.removerPessoa(funcionario.getCodigo());

		return funcionario.getNome();
	}	
}
