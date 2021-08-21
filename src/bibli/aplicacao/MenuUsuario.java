package bibli.aplicacao;

import bibli.controle.ControladorUsuario;

public class MenuUsuario {
	
	public static void apresentarOpcoes() {

		System.out.println( Principal.getMensagem("menu.topo") );
		System.out.println( Principal.getMensagem("menu.titulo") );
		System.out.println( Principal.getMensagem("menu.usuario.titulo") );
		System.out.println( Principal.getMensagem("menu.escolha") );
		System.out.println( Principal.getMensagem("menu.exibir") );
		System.out.println( Principal.getMensagem("menu.cadastrar") );
		System.out.println( Principal.getMensagem("menu.3editar") );
		System.out.println( Principal.getMensagem("menu.4excluir") );	
		System.out.println( Principal.getMensagem("menu.voltar"));
		System.out.println( Principal.getMensagem("menu.base") );

		int opcao= Principal.lerInteiroTeclado();

		switch(opcao) {

		case 1:
			apresentarOpcoesExibir();
			break;
		case 2:
			prepararUsuario(true);
			break;
		case 3:
			prepararUsuario(false);
			break;
		case 4:
			removerUsuario();
			break;
		case 0:			
			break;
		default:
			System.err.println( Principal.getMensagem("menu.opcaoInvalida") );
			apresentarOpcoes();
		}	
	}	
	
	private static void apresentarOpcoesExibir() {

		System.out.println( Principal.getMensagem("menu.titulo") );
		System.out.println( Principal.getMensagem("menu.usuario.exibir.titulo") );
		System.out.println( Principal.getMensagem("menu.escolha") );
		System.out.println( Principal.getMensagem("menu.usuario.exibir.um") );
		System.out.println( Principal.getMensagem("menu.usuario.exibir.todos") );
		System.out.println( Principal.getMensagem("menu.voltar") );
		System.out.println( Principal.getMensagem("menu.base") );

		int opcao= Principal.lerInteiroTeclado();

		switch(opcao) {

		case 1:
			exibirUsuario();
			break;
		case 2:
			exibirUsuariosTotal();
			break;
		case 0:
			apresentarOpcoes();
			break;
		default:
			System.err.println( Principal.getMensagem("menu.opcaoInvalida") );
			apresentarOpcoesExibir();
		}
	}
	
	private static void exibirUsuario (){

		System.out.println( Principal.getMensagem("menu.usuario.exibir.um.titulo") );
		System.out.println( Principal.getMensagem("menu.usuario.exibir.um.solicita") );

		String codigo= Principal.lerStringTeclado();

		ControladorUsuario.exibirUsuario(codigo);
	}
	
	private static void exibirUsuariosTotal (){

		ControladorUsuario.exibirUsuarios();
	}

	private static void prepararUsuario(boolean novo) {
		
		boolean resultadoOperacao= false;
		String codigo= null;

		if(novo) {
			System.out.println( Principal.getMensagem("menu.usuario.cadastrar.titulo") );
			System.out.println( Principal.getMensagem("menu.usuario.cadastrar.solicita") );

		}else {
			System.out.println( Principal.getMensagem("menu.usuario.editar.titulo") );
			System.out.println( Principal.getMensagem("menu.usuario.editar.solicita") );
			
			System.out.println( Principal.getMensagem("menu.usuario.codigo") );
			codigo= Principal.lerStringTeclado();
		}
			
		System.out.println( Principal.getMensagem("menu.usuario.nome") );
		String nome= Principal.lerStringTeclado();

		System.out.println( Principal.getMensagem("menu.usuario.endereco") );
		String endereco= Principal.lerStringTeclado();

		System.out.println( Principal.getMensagem("menu.usuario.telefone") );
		String telefone= Principal.lerStringTeclado();

		System.out.println( Principal.getMensagem("menu.usuario.email") );
		String email= Principal.lerStringTeclado();
		
		if(novo)
			resultadoOperacao= ControladorUsuario.adicionarUsuario(nome, endereco, telefone, email);		
		else 
			resultadoOperacao= ControladorUsuario.editarUsuario(codigo, nome, endereco, telefone, email);

		if(!resultadoOperacao)
			System.err.println( Principal.getMensagem("menu.naoRealizado") );	
	}
	
	private static void removerUsuario() {		

		System.out.println( Principal.getMensagem("menu.usuario.remover.titulo") );
		System.out.println( Principal.getMensagem("menu.usuario.remover.solicita") );		

		String codigo= Principal.lerStringTeclado();
		
		boolean resultadoOperacao= ControladorUsuario.removerUsuario(codigo);

		if(!resultadoOperacao) 
			System.err.println( Principal.getMensagem("menu.naoRealizado") );		
	}
}