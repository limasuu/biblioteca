package bibli.controle;

import java.util.HashMap;

import bibli.aplicacao.Principal;
import bibli.dados.AcervoFuncionario;
import bibli.dados.AcervoUsuario;
import bibli.modelo.Funcionario;

public class ControladorFuncionario {

	public static boolean exibirFuncionario(String matricula) {

		if(!ValidadorUsuario.validarCampo(matricula)) {
			System.err.println( Principal.getMensagem("erro.matriculaInvalida") );
			return false;
		}

		if(!AcervoFuncionario.verificarExistencia(matricula)){
			System.err.println( Principal.getMensagem("erro.funcionario.naoEncontrado") );
			return false;
		}	

		System.out.println("\n" + AcervoFuncionario.buscarFuncionario(matricula));

		return true;
	}
	
	public static void exibirFuncionarios() {

		if(AcervoFuncionario.getNumeroFuncionarios() == 0)
			System.out.println( Principal.getMensagem("erro.funcionario.vazio") );
		else {

			System.out.println( Principal.getMensagem("funcionario.exibir.topo") );
			System.out.println( Principal.getMensagem("exibir.quantidade") + AcervoFuncionario.getNumeroFuncionarios());	
			System.out.println( Principal.getMensagem("menu.base") );
			
			for(Funcionario funcionario : AcervoFuncionario.getFuncionarios().values())
				System.out.println(funcionario+"\n");
			
			System.out.println( Principal.getMensagem("menu.base") );
		}
	}	

	public static boolean exibirFuncionariosPorCargo(String cargo){

		if(!ValidadorUsuario.validarCampo(cargo)) {
			System.err.println( Principal.getMensagem("erro.funcionario.invalido.cargo") );
			return false;
		}

		HashMap<String, Funcionario> funcionariosEncontrados= AcervoFuncionario.buscarFuncionariosPorCargo(cargo);		

		if(funcionariosEncontrados.size() == 0) 
			System.out.println( Principal.getMensagem("erro.funcionario.vazio") );		
		else {

			System.out.println("\n----------- Funcionários com o cargo \"" + cargo + "\" -----------");
			System.out.println( Principal.getMensagem("exibir.quantidade") + funcionariosEncontrados.size());	
			System.out.println( Principal.getMensagem("menu.base") );

			for(Funcionario funcionario: funcionariosEncontrados.values())
				System.out.println("\n" + funcionario);	

			System.out.println( Principal.getMensagem("menu.base") );
		}

		return true;
	}	
	
	public static boolean adicionarFuncionario(String nome, String endereco, 
			String telefone, String email, 
			double salario, String cargo) {

		if(!ValidadorUsuario.validarCamposFuncionario(nome, endereco, telefone, 
				email, salario, cargo)) {
			System.err.println( Principal.getMensagem("erro.invalido.campos") );
			return false;
		}
		
		Funcionario funcionario= new Funcionario(nome, endereco, telefone, email, salario, cargo);
		
		AcervoFuncionario.adicionarFuncionario(funcionario);
		AcervoUsuario.adicionarUsuario(funcionario);
		
		System.out.println("\nFuncionário \"" + funcionario.getMatricula() + "\" cadastrado.");

		return true;
	}
	
	public static boolean editarFuncionario(String matricula, String novoNome, String novoEndereco, 
			String novoTelefone, String novoEmail, double novoSalario, String novoCargo) {

		if(!ValidadorUsuario.validarCamposFuncionario(novoNome, novoEndereco, novoTelefone, 
				novoEmail, novoSalario, novoCargo)){
			System.err.println( Principal.getMensagem("erro.invalido.campos") );
			return false;
		}

		if(!AcervoFuncionario.verificarExistencia(matricula)){
			System.err.println( Principal.getMensagem("erro.funcionario.naoEncontrado") );
			return false;
		}		

		boolean resultadoOperacao= ValidadorUsuario.validarAtualizacaoFuncionario(matricula, novoNome, novoEndereco, novoTelefone, novoEmail, novoSalario, novoCargo);

		if(!resultadoOperacao){
			System.err.println( Principal.getMensagem("editar.sem.mudanças") );
			return false;
		}	

		System.out.println("\nFuncionário \"" + matricula + "\" editado.");
		return true;
	}
	
	public static boolean removerFuncionario(String matricula) {

		if(!ValidadorUsuario.validarCampo(matricula)) {
			System.err.println( Principal.getMensagem("erro.matriculaInvalida") );	
			return false;
		}

		if(!AcervoFuncionario.verificarExistencia(matricula)){
			System.err.println( Principal.getMensagem("erro.funcionario.naoEncontrado") );
			return false;
		}		

		String codigo= AcervoFuncionario.buscarFuncionario(matricula).getCodigo();
		AcervoFuncionario.removerFuncionario(matricula);
		AcervoUsuario.removerUsuario(codigo);

		System.out.println( Principal.getMensagem("menu.funcionario.remover.concluido") );	

		return true;
	}	
}
