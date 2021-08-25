package bibli.controle;

import java.time.LocalDateTime;
import java.util.HashMap;

import bibli.aplicacao.Principal;
import bibli.dados.AcervoEmprestimo;
import bibli.dados.AcervoExemplar;
import bibli.dados.AcervoFuncionario;
import bibli.dados.AcervoUsuario;
import bibli.modelo.Emprestimo;
import bibli.modelo.Exemplar;
import bibli.modelo.Funcionario;
import bibli.modelo.Usuario;

public class ControladorEmprestimo {

	public static boolean exibirEmprestimo (String codigo){

		if(!ValidadorEmprestimo.validarCampo(codigo)) {
			System.err.println( Principal.getMensagem("erro.codigoInvalido") );
			return false;
		}

		if(!AcervoEmprestimo.verificarExistencia(codigo)){
			System.err.println( Principal.getMensagem("erro.emprestimo.naoEncontrado") );
			return false;
		}	

		System.out.println("\n" + AcervoEmprestimo.buscarEmprestimo(codigo));

		return true;
	}

	public static void exibirEmprestimos() {

		if(AcervoEmprestimo.getNumeroEmprestimos() == 0)
			System.out.println( Principal.getMensagem("erro.emprestimo.vazio") );
		else {

			System.out.println( Principal.getMensagem("emprestimo.exibir.topo") );
			System.out.println( Principal.getMensagem("exibir.quantidade") + AcervoEmprestimo.getNumeroEmprestimos());	
			System.out.println( Principal.getMensagem("menu.base") );

			for(Emprestimo emprestimo : AcervoEmprestimo.getEmprestimos().values())
				System.out.println(emprestimo+"\n");

			System.out.println( Principal.getMensagem("menu.base") );
		}
	}

	public static boolean exibirEmprestimosPorLivro(String codigo){

		if(!ValidadorEmprestimo.validarCampo(codigo)) {
			System.err.println( Principal.getMensagem("erro.codigoInvalido") );
			return false;
		}

		HashMap<String, Emprestimo> emprestimosEncontrados= AcervoEmprestimo.buscarEmprestimosPorLivro(codigo);

		if(emprestimosEncontrados.size() == 0) 
			System.out.println( Principal.getMensagem("erro.emprestimo.vazio") );
		else {

			System.out.println("\n----------- Empréstimos do livro \"" + codigo + "\" -----------");
			System.out.println( Principal.getMensagem("exibir.quantidade") + emprestimosEncontrados.size());	
			System.out.println( Principal.getMensagem("menu.base") );

			for(Emprestimo emprestimo: emprestimosEncontrados.values())
				System.out.println("\n" + emprestimo);	

			System.out.println( Principal.getMensagem("menu.base") );
		}

		return true;		
	}

	public static boolean exibirEmprestimosPorUsuario(String codigo) {

		if(!ValidadorEmprestimo.validarCampo(codigo)) {
			System.err.println( Principal.getMensagem("erro.codigoInvalido") );
			return false;
		}

		HashMap<String, Emprestimo> emprestimosEncontrados= AcervoEmprestimo.buscarEmprestimosPorUsuario(codigo);

		if(emprestimosEncontrados.size() == 0) 
			System.out.println( Principal.getMensagem("erro.emprestimo.vazio") );
		else{

			System.out.println("\n----------- Empréstimos do usuário \"" + codigo + "\" -----------");
			System.out.println( Principal.getMensagem("exibir.quantidade") + emprestimosEncontrados.size());	
			System.out.println( Principal.getMensagem("menu.base") );

			for(Emprestimo emprestimo: emprestimosEncontrados.values())
				System.out.println("\n" + emprestimo);	

			System.out.println( Principal.getMensagem("menu.base") );
		}

		return true;		
	}

	public static boolean adicionarEmprestimo(String matriculaFuncionario, String codigoUsuario, 
			String codigoExemplar) {

		if(!ValidadorEmprestimo.validarCamposEmprestimo(matriculaFuncionario, codigoUsuario, codigoExemplar)){
			System.err.println( Principal.getMensagem("erro.invalido.campos") );
			return false;
		}

		if(!AcervoFuncionario.verificarExistencia(matriculaFuncionario)){
			System.err.println( Principal.getMensagem("erro.funcionario.naoEncontrado") );
			return false;
		}

		if(!AcervoUsuario.verificarExistencia(codigoUsuario)){
			System.err.println( Principal.getMensagem("erro.usuario.naoEncontrado") );
			return false;
		}	

		if(AcervoUsuario.verificarBloqueio(codigoUsuario)){
			System.err.println( Principal.getMensagem("erro.usuario.bloqueado") );
			return false;
		}	

		if(!AcervoExemplar.verificarExistencia(codigoExemplar)){
			System.err.println( Principal.getMensagem("erro.exemplar.naoEncontrado") );
			return false;
		}	
		
		if(!AcervoExemplar.verificarDisponibilidade(codigoExemplar)){
			System.err.println( Principal.getMensagem("erro.exemplar.indisponivel") );
			return false;
		}	

		Funcionario funcionario= AcervoFuncionario.buscarFuncionario(matriculaFuncionario);
		Usuario usuario= AcervoUsuario.buscarUsuario(codigoUsuario);
		Exemplar exemplar= AcervoExemplar.buscarExemplar(codigoExemplar);

		Emprestimo emprestimo= new Emprestimo(funcionario, usuario, exemplar);		
		AcervoEmprestimo.adicionarEmprestimo(emprestimo);

		exemplar.setDisponivel(false);
		
		System.out.println("\nEmpréstimo \"" + emprestimo.getCodigo() + "\" cadastrado.");

		return true;
	}	

	public static boolean renovarEmprestimo(String codigo) {

		if(!ValidadorEmprestimo.validarCampo(codigo)) {
			System.err.println( Principal.getMensagem("erro.codigoInvalido") );
			return false;
		}

		if(!AcervoEmprestimo.verificarExistencia(codigo)){
			System.err.println( Principal.getMensagem("erro.emprestimo.naoEncontrado") );
			return false;
		}	

		Emprestimo emprestimoAtualizado= AcervoEmprestimo.buscarEmprestimo(codigo);

		if(emprestimoAtualizado.getRenocacoes() >= 3){
			System.err.println( Principal.getMensagem("erro.emprestimo.limite.renovacao") );
			return false;
		}	
		
		if(AcervoUsuario.verificarBloqueio(emprestimoAtualizado.getUsuario().getCodigo())){
			System.err.println( Principal.getMensagem("erro.usuario.bloqueado") );
			return false;
		}	

		emprestimoAtualizado.setRenocacoes();
		System.out.println( Principal.getMensagem("menu.emprestimo.renovar.concluido") );

		return true;
	}

	public static boolean encerrarEmprestimo(String codigo) {

		if(!ValidadorEmprestimo.validarCampo(codigo)) {
			System.err.println( Principal.getMensagem("erro.codigoInvalido") );
			return false;
		}

		if(!AcervoEmprestimo.verificarExistencia(codigo)){
			System.err.println( Principal.getMensagem("erro.emprestimo.naoEncontrado") );
			return false;
		}	

		Emprestimo emprestimoAtualizado= AcervoEmprestimo.buscarEmprestimo(codigo);

		emprestimoAtualizado.getExemplar().setDisponivel(true);

		Usuario usuarioAtualizado= emprestimoAtualizado.getUsuario();
		if(usuarioAtualizado.isBloqueado()) {
			
			if(usuarioAtualizado.getDataFimBloqueio() == null)
				usuarioAtualizado.setDataFimBloqueio(LocalDateTime.now().plusDays(7));
			else {
				LocalDateTime dataFimAtual= usuarioAtualizado.getDataFimBloqueio();
				usuarioAtualizado.setDataFimBloqueio(dataFimAtual.plusDays(7));				
			}
		}
		
		emprestimoAtualizado.setAtivo(false);
		emprestimoAtualizado.setDataFim();
		
		System.out.println( Principal.getMensagem("menu.emprestimo.encerrar.concluido") );	

		return true;
	}

	public static boolean removerEmprestimo(String codigo){

		if(!ValidadorEmprestimo.validarCampo(codigo)) {
			System.err.println( Principal.getMensagem("erro.codigoInvalido") );
			return false;
		}

		if(!AcervoEmprestimo.verificarExistencia(codigo)){
			System.err.println( Principal.getMensagem("erro.emprestimo.naoEncontrado") );
			return false;
		}	
		
		if(AcervoEmprestimo.verificarAtividade(codigo)){
			System.err.println( Principal.getMensagem("erro.emprestimo.remover.ativo") );
			return false;
		}			

		AcervoEmprestimo.removerEmprestimo(codigo);
		System.out.println( Principal.getMensagem("menu.emprestimo.remover.concluido") );		

		return true;
	}	

	public static void atualizarInadimplencias(){

		HashMap<String, Usuario> usuariosEncontrados= AcervoUsuario.buscarUsuariosBloqueados();
		for(Usuario usuario : usuariosEncontrados.values()) 
			if(usuario.getDataFimBloqueio().isBefore(LocalDateTime.now())) {
				usuario.setBloqueado(false);
				usuario.setDataFimBloqueio(null);
			}

		HashMap<String, Emprestimo> emprestimosEncontrados= AcervoEmprestimo.buscarEmprestimosVencidos();
		for(Emprestimo emprestimo : emprestimosEncontrados.values()) 
			emprestimo.getUsuario().setBloqueado(true);
	}
}
