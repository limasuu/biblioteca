package bibli.controle;

import java.time.LocalDateTime;
import java.util.HashMap;

import bibli.modelo.AcervoEmprestimo;
import bibli.modelo.AcervoExemplar;
import bibli.modelo.AcervoFuncionario;
import bibli.modelo.AcervoUsuario;
import bibli.modelo.Emprestimo;
import bibli.modelo.Exemplar;
import bibli.modelo.Funcionario;
import bibli.modelo.Usuario;

public class ControladorEmprestimo {

	public static boolean exibirEmprestimo (String codigo){

		if(!ValidadorEmprestimo.validarCampo(codigo)) {
			System.err.println("Código inválido.");
			return false;
		}

		if(!AcervoEmprestimo.verificarExistenciaEmprestimo(codigo)){
			System.err.println("\nNão foi encontrado empréstimo com o código informado.");
			return false;
		}	

		System.out.println("\n" + AcervoEmprestimo.buscarEmprestimo(codigo));

		return true;
	}

	public static void exibirEmprestimos() {

		if(AcervoEmprestimo.getNumeroEmprestimos() == 0)
			System.out.println("Não há empréstimos cadastrados.");
		else {

			System.out.println("\n----------------- Empréstimos -----------------");
			System.out.println("Quantidade: " + AcervoEmprestimo.getNumeroEmprestimos());	
			System.out.println("------------------------------------------");

			for(Emprestimo emprestimo : AcervoEmprestimo.getEmprestimos().values())
				System.out.println(emprestimo+"\n");

			System.out.println("------------------------------------------");
		}
	}

	public static boolean exibirEmprestimosPorLivro(String codigo){

		if(!ValidadorEmprestimo.validarCampo(codigo)) {
			System.err.println("Código inválido.");
			return false;
		}

		HashMap<String, Emprestimo> emprestimosEncontrados= AcervoEmprestimo.buscarEmprestimosPorLivro(codigo);

		if(emprestimosEncontrados.size() == 0) 
			System.err.println("Nenhum empréstimo encontrado.");			
		else {

			System.out.println("\n----------- Empréstimos do livro \"" + codigo + "\" -----------");
			System.out.println("Quantidade: " + emprestimosEncontrados.size());	
			System.out.println("-------------------------------------------------------------");

			for(Emprestimo emprestimo: emprestimosEncontrados.values())
				System.out.println("\n" + emprestimo);	

			System.out.println("-------------------------------------------------------------");
		}

		return true;		
	}

	public static boolean exibirEmprestimosPorUsuario(String codigo) {

		if(!ValidadorEmprestimo.validarCampo(codigo)) {
			System.err.println("Código inválido.");
			return false;
		}

		HashMap<String, Emprestimo> emprestimosEncontrados= AcervoEmprestimo.buscarEmprestimosPorUsuario(codigo);

		if(emprestimosEncontrados.size() == 0) 
			System.err.println("Nenhum empréstimo encontrado.");
		else{

			System.out.println("\n----------- Empréstimos do usuário \"" + codigo + "\" -----------");
			System.out.println("Quantidade: " + emprestimosEncontrados.size());	
			System.out.println("-------------------------------------------------------------");

			for(Emprestimo emprestimo: emprestimosEncontrados.values())
				System.out.println("\n" + emprestimo);	

			System.out.println("-------------------------------------------------------------");
		}

		return true;		
	}

	public static boolean adicionarEmprestimo(String matriculaFuncionario, String codigoUsuario, 
			String codigoExemplar) {

		if(!ValidadorEmprestimo.validarCamposEmprestimo(matriculaFuncionario, codigoUsuario, codigoExemplar)){
			System.err.println("Há campos inválidos.");
			return false;
		}

		if(!AcervoFuncionario.verificarExistenciaFuncionario(matriculaFuncionario)){
			System.err.println("Não foi encontrado funcionário com a matrícula informada.");
			return false;
		}

		if(!AcervoUsuario.verificarExistenciaUsuario(codigoUsuario)){
			System.err.println("Não foi encontrado usuário com o código informado.");
			return false;
		}	

		if(AcervoUsuario.verificarSituacaoUsuario(codigoUsuario)){
			System.err.println("O usuário encontra-se bloqueado para operações.");
			return false;
		}	

		if(!AcervoExemplar.verificarExistenciaExemplar(codigoExemplar)){
			System.err.println("Não foi encontrado exemplar com o código informado.");
			return false;
		}	
		if(!AcervoExemplar.verificarSituacaExemplar(codigoExemplar)){
			System.err.println("O exemplar encontra-se indisponível.");
			return false;
		}	

		Funcionario funcionario= AcervoFuncionario.buscarFuncionario(matriculaFuncionario);
		Usuario usuario= AcervoUsuario.buscarUsuario(codigoUsuario);
		Exemplar exemplar= AcervoExemplar.buscarExemplar(codigoExemplar);

		Emprestimo emprestimo= new Emprestimo(funcionario, usuario, exemplar);		
		AcervoEmprestimo.adicionarEmprestimo(emprestimo);

		exemplar.setDisponivel(false);
		
		System.out.println("Código do empréstimo: " + emprestimo.getCodigo());

		return true;
	}	

	public static boolean renovarEmprestimo(String codigo) {

		if(!ValidadorEmprestimo.validarCampo(codigo)) {
			System.err.println("Código inválido.");
			return false;
		}

		if(!AcervoEmprestimo.verificarExistenciaEmprestimo(codigo)){
			System.err.println("\nNão foi encontrado empréstimo com o código informado.");
			return false;
		}	

		Emprestimo emprestimoAtualizado= AcervoEmprestimo.buscarEmprestimo(codigo);

		if(emprestimoAtualizado.getRenocacoes() >= 3){
			System.err.println("\nO limite máximo de renovações foi alcançado.\nO exemplar precisa ser devolvido!");
			return false;
		}	
		
		if(emprestimoAtualizado.getUsuario().isBloqueado()) {
			System.err.println("\nEste usuário está bloqueado para operações.");
			return false;
		}

		emprestimoAtualizado.setRenocacoes();

		return true;
	}

	public static boolean encerrarEmprestimo(String codigo) {

		if(!ValidadorEmprestimo.validarCampo(codigo)) {
			System.err.println("Código inválido.");
			return false;
		}

		if(!AcervoEmprestimo.verificarExistenciaEmprestimo(codigo)){
			System.err.println("\nNão foi encontrado empréstimo com o código informado.");
			return false;
		}	

		Emprestimo emprestimoAtualizado= AcervoEmprestimo.buscarEmprestimo(codigo);

		emprestimoAtualizado.getExemplar().setDisponivel(true);

		Usuario usuarioAtualizado= emprestimoAtualizado.getUsuario();
		if(usuarioAtualizado.isBloqueado()) 
			usuarioAtualizado.setDataFimBloqueio(7);
		
		emprestimoAtualizado.setAtivo(false);
		emprestimoAtualizado.setDataFim();

		return true;
	}

	public static boolean removerEmprestimo(String codigo){

		if(!ValidadorEmprestimo.validarCampo(codigo)) {
			System.err.println("\nCódigo inválido.");
			return false;
		}

		if(!AcervoEmprestimo.verificarExistenciaEmprestimo(codigo)){
			System.err.println("\nNão foi encontrado empréstimo com o código informado.");
			return false;
		}	

		AcervoEmprestimo.removerEmprestimo(codigo);

		return true;
	}	

	public static void atualizarInadimplencias(){

		HashMap<String, Usuario> usuariosEncontrados= AcervoUsuario.buscarUsuariosBloqueados();
		for(Usuario usuario : usuariosEncontrados.values()) 
			if(usuario.getDataFimBloqueio().isBefore(LocalDateTime.now())) {
				usuario.setBloqueado(false);
				usuario.setDataFimBloqueio(0);
			}

		HashMap<String, Emprestimo> emprestimosEncontrados= AcervoEmprestimo.buscarEmprestimosVencidos();
		for(Emprestimo emprestimo : emprestimosEncontrados.values()) 
			emprestimo.getUsuario().setBloqueado(true);
	}
}
