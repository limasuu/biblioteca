package bibli.controle;

import bibli.aplicacao.Principal;
import bibli.modelo.AcervoEmprestimo;
import bibli.modelo.AcervoUsuario;
import bibli.modelo.Usuario;

public class ControladorUsuario {

	public static boolean exibirUsuario (String codigo){

		if(!ValidadorUsuario.validarCampo(codigo)) {
			System.err.println( Principal.getMensagem("erro.codigoInvalido") );
			return false;
		}

		if(!AcervoUsuario.verificarExistencia(codigo)){
			System.err.println( Principal.getMensagem("erro.usuario.naoEncontrado") );
			return false;
		}	

		System.out.println("\n" + AcervoUsuario.buscarUsuario(codigo));

		return true;
	}

	public static void exibirUsuarios() {

		if(AcervoUsuario.getNumeroUsuarios() == 0)
			System.out.println( Principal.getMensagem("erro.usuario.vazio") );
		else {

			System.out.println( Principal.getMensagem("usuario.exibir.topo") );
			System.out.println( Principal.getMensagem("exibir.quantidade") + AcervoUsuario.getNumeroUsuarios());	
			System.out.println( Principal.getMensagem("menu.base") );

			for(Usuario usuario : AcervoUsuario.getUsuarios().values())
				System.out.println(usuario+"\n");

			System.out.println( Principal.getMensagem("menu.base") );
		}
	}

	public static boolean adicionarUsuario(String nome, String endereco, 
			String telefone, String email) {

		if(!ValidadorUsuario.validarCamposUsuario(nome, endereco, telefone, email)){
			System.err.println( Principal.getMensagem("erro.invalido.campos") );
			return false;
		}

		Usuario usuario= new Usuario(nome, endereco, telefone, email);
		AcervoUsuario.adicionarUsuario(usuario);
		System.out.println("\nUsuário \"" + usuario.getCodigo() + "\" cadastrado.");

		return true;
	}	

	public static boolean editarUsuario(String codigo, String novoNome, String novoEndereco, 
			String novoTelefone, String novoEmail) {
		
		if(!ValidadorUsuario.validarCamposUsuario(novoNome, novoEndereco, novoTelefone, novoEmail)){
			System.err.println( Principal.getMensagem("erro.invalido.campos") );
			return false;
		}		
		
		if(!AcervoUsuario.verificarExistencia(codigo)){
			System.err.println( Principal.getMensagem("erro.usuario.naoEncontrado") );
			return false;
		}		

		if(AcervoUsuario.verificarSeEhFuncionario(codigo)){
			System.err.println( Principal.getMensagem("erro.usuario.editar.funcionario") );
			return false;
		}

		boolean resultadoOperacao= ValidadorUsuario.validarAtualizacaoUsuario(codigo, novoNome, novoEndereco, novoTelefone, novoEmail);

		if(!resultadoOperacao){
			System.err.println( Principal.getMensagem("editar.sem.mudanças") );
			return false;
		}	
		
		System.out.println("\nUsuário \"" + codigo + "\" editado.");
		return true;
	}

	public static boolean removerUsuario(String codigo) {

		if(!ValidadorObra.validarCampo(codigo)) {
			System.err.println( Principal.getMensagem("erro.codigoInvalido") );
			return false;
		}

		if(!AcervoUsuario.verificarExistencia(codigo)){
			System.err.println( Principal.getMensagem("erro.usuario.naoEncontrado") );
			return false;
		}		
		
		if(AcervoUsuario.verificarSeEhFuncionario(codigo)){
			System.err.println( Principal.getMensagem("erro.usuario.remover.funcionario") );
			return false;
		}
		
		if(AcervoUsuario.verificarBloqueio(codigo)){
			System.err.println( Principal.getMensagem("erro.usuario.remover.bloqueado") );
			return false;
		}
		
		if(AcervoEmprestimo.verificarEmprestimosAtivosPorUsuario(codigo)){
			System.err.println( Principal.getMensagem("erro.usuario.remover.emprestimo.ativo") );
			return false;
		}

		AcervoUsuario.removerUsuario(codigo);
		System.out.println( Principal.getMensagem("menu.usuario.remover.concluido") );

		return true;
	}	
}
