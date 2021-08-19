package bibli.controle;

import bibli.modelo.AcervoUsuario;
import bibli.modelo.Usuario;

public class ControladorUsuario {

	public static boolean exibirUsuario (String codigo){

		if(!ValidadorUsuario.validarCampo(codigo)) {
			System.err.println("Código inválido.");
			return false;
		}

		if(!AcervoUsuario.verificarExistenciaUsuario(codigo)){
			System.err.println("\nNão foi encontrado usuário com o código informado.");
			return false;
		}	

		System.out.println("\n" + AcervoUsuario.buscarUsuario(codigo));

		return true;
	}

	public static void exibirUsuarios() {

		if(AcervoUsuario.getNumeroUsuarios() == 0)
			System.out.println("Não há usuários cadastrados.");
		else {

			System.out.println("\n----------------- Usuários -----------------");
			System.out.println("Quantidade: " + AcervoUsuario.getNumeroUsuarios());	
			System.out.println("------------------------------------------");

			for(Usuario usuario : AcervoUsuario.getUsuarios().values())
				System.out.println(usuario+"\n");

			System.out.println("------------------------------------------");
		}
	}

	public static String adicionarUsuario(String nome, String endereco, 
			String telefone, String email) {

		if(!ValidadorUsuario.validarCamposUsuario(nome, endereco, telefone, email)){
			System.err.println("Há campos inválidos.");
			return null;
		}

		Usuario usuario= new Usuario(nome, endereco, telefone, email);
		AcervoUsuario.adicionarUsuario(usuario);

		return usuario.getCodigo();
	}	

	public static String editarUsuario(String codigo, String novoNome, String novoEndereco, 
			String novoTelefone, String novoEmail) {
		
		if(!AcervoUsuario.verificarExistenciaUsuario(codigo)){
			System.err.println("Não foi encontrado usuário com o código informado.");
			return null;
		}		

		if(AcervoUsuario.verificarSeEhFuncionario(codigo)){
			System.err.println("A edição de funcionários deve ser realizada em sua respectiva seção.");
			return null;
		}

		if(!ValidadorUsuario.validarCamposUsuario(novoNome, novoEndereco, novoTelefone, novoEmail)){
			System.err.println("Há campos inválidos.");
			return null;
		}		
		
		Usuario usuarioAtualizado= ValidadorUsuario.validarAtualizacaoUsuario(codigo, novoNome, novoEndereco, novoTelefone, novoEmail);

		if(usuarioAtualizado == null){
			System.err.println("\nNão há mudanças para realizar.");
			return null;
		}	

		return usuarioAtualizado.getCodigo();
	}

	public static String removerUsuario(String codigo) {

		if(!ValidadorObra.validarCampo(codigo)) {
			System.err.println("Código inválido.");
			return null;
		}

		if(!AcervoUsuario.verificarExistenciaUsuario(codigo)){
			System.err.println("Não foi encontrada usuário com o código informado.");
			return null;
		}		
		
		if(AcervoUsuario.verificarSeEhFuncionario(codigo)){
			System.err.println("A edição de funcionários deve ser realizada em sua respectiva seção.");
			return null;
		}

		String nome= AcervoUsuario.buscarUsuario(codigo).getNome();
		AcervoUsuario.removerUsuario(codigo);

		return nome;
	}	
}
