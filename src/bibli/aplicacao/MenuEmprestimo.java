package bibli.aplicacao;

import bibli.controle.ControladorEmprestimo;

public class MenuEmprestimo {

	public static void apresentarOpcoes() {

		System.out.println( Principal.getMensagem("menu.topo") );
		System.out.println( Principal.getMensagem("menu.titulo") );
		System.out.println( Principal.getMensagem("menu.emprestimo.titulo") );
		System.out.println( Principal.getMensagem("menu.escolha") );
		System.out.println( Principal.getMensagem("menu.exibir") );
		System.out.println( Principal.getMensagem("menu.cadastrar") );
		System.out.println( Principal.getMensagem("menu.3renovar") );
		System.out.println( Principal.getMensagem("menu.4encerrar") );
		System.out.println( Principal.getMensagem("menu.5excluir") );
		System.out.println( Principal.getMensagem("menu.voltar") );
		System.out.println( Principal.getMensagem("menu.base") );

		int opcao= Principal.lerInteiroTeclado();

		switch(opcao) {

		case 1:
			apresentarOpcoesExibir();
			break;
		case 2:
			cadastrarEmprestimo();
			break;
		case 3:
			renovarEmprestimo();
			break;
		case 4:
			encerrarEmprestimo();
			break;
		case 5:
			removerEmprestimo();
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
		System.out.println( Principal.getMensagem("menu.emprestimo.exibir.titulo") );
		System.out.println( Principal.getMensagem("menu.escolha") );
		System.out.println( Principal.getMensagem("menu.emprestimo.exibir.um") );
		System.out.println( Principal.getMensagem("menu.emprestimo.exibir.varios") );
		System.out.println( Principal.getMensagem("menu.voltar") );
		System.out.println( Principal.getMensagem("menu.base") );

		int opcao= Principal.lerInteiroTeclado();

		switch(opcao) {

		case 1:
			exibirEmprestimo();
			break;
		case 2:
			apresentarOpcoesExibirVarios();
			break;
		case 0:
			apresentarOpcoes();
			break;
		default:
			System.err.println( Principal.getMensagem("menu.opcaoInvalida") );
			apresentarOpcoesExibir();
		}
	}

	private static void apresentarOpcoesExibirVarios() {

		System.out.println( Principal.getMensagem("menu.titulo") );
		System.out.println( Principal.getMensagem("menu.emprestimo.exibir.varios.titulo") );
		System.out.println( Principal.getMensagem("menu.escolha") );
		System.out.println( Principal.getMensagem("menu.emprestimo.exibir.todos") );
		System.out.println( Principal.getMensagem("menu.emprestimo.exibir.varios.livro") );
		System.out.println( Principal.getMensagem("menu.emprestimo.exibir.varios.usuario") );
		System.out.println( Principal.getMensagem("menu.voltar"));
		System.out.println( Principal.getMensagem("menu.base"));

		int opcao= Principal.lerInteiroTeclado();

		switch(opcao) {

		case 1:
			exibirEmprestimosTotal();
			break;
		case 2:
			exibirEmprestimosPorLivro();
			break;
		case 3:
			exibirEmprestimosPorUsuario();
			break;
		case 0:
			apresentarOpcoesExibir();
			break;
		default:
			System.err.println( Principal.getMensagem("menu.opcaoInvalida") );	
			apresentarOpcoesExibirVarios();
		}
	}

	private static void exibirEmprestimo (){

		System.out.println( Principal.getMensagem("menu.emprestimo.exibir.um.titulo") );
		System.out.println( Principal.getMensagem("menu.emprestimo.exibir.um.solicita") );

		String codigo= Principal.lerStringTeclado();

		ControladorEmprestimo.exibirEmprestimo(codigo);
	}

	private static void exibirEmprestimosTotal (){

		ControladorEmprestimo.exibirEmprestimos();
	}

	private static void exibirEmprestimosPorLivro (){

		System.out.println( Principal.getMensagem("menu.emprestimo.exibir.varios.livro.titulo") );
		System.out.println( Principal.getMensagem("menu.emprestimo.exibir.varios.livro.solicita") );

		String codigo= Principal.lerStringTeclado();

		ControladorEmprestimo.exibirEmprestimosPorLivro(codigo);
	}

	private static void exibirEmprestimosPorUsuario (){

		System.out.println( Principal.getMensagem("menu.emprestimo.exibir.varios.usuario.titulo") );
		System.out.println( Principal.getMensagem("menu.emprestimo.exibir.varios.usuario.solicita") );

		String codigo= Principal.lerStringTeclado();

		ControladorEmprestimo.exibirEmprestimosPorUsuario(codigo);	
	}

	private static void cadastrarEmprestimo() {

		System.out.println( Principal.getMensagem("menu.emprestimo.cadastrar.titulo") );
		System.out.println( Principal.getMensagem("menu.emprestimo.cadastrar.solicita") );

		System.out.println( Principal.getMensagem("menu.emprestimo.cadastrar.matriculaFuncionario") );
		String matriculaFuncionario= Principal.lerStringTeclado();

		System.out.println( Principal.getMensagem("menu.emprestimo.cadastrar.codigoUsuario") );
		String codigoUsuario= Principal.lerStringTeclado();

		System.out.println( Principal.getMensagem("menu.emprestimo.cadastrar.codigoExemplar") );
		String codigoExemplar= Principal.lerStringTeclado();

		boolean resultadoOperacao= ControladorEmprestimo.adicionarEmprestimo(matriculaFuncionario, codigoUsuario, codigoExemplar);	

		if(!resultadoOperacao)
			System.err.println( Principal.getMensagem("menu.naoRealizado") );
	}	

	private static void renovarEmprestimo() {	

		System.out.println( Principal.getMensagem("menu.emprestimo.renovar.titulo") );
		System.out.println( Principal.getMensagem("menu.emprestimo.renovar.solicita") );		

		String codigo= Principal.lerStringTeclado();

		boolean resultadoOperacao= ControladorEmprestimo.renovarEmprestimo(codigo);

		if(!resultadoOperacao)
			System.err.println( Principal.getMensagem("menu.naoRealizado") );
	}

	private static void encerrarEmprestimo() {	

		System.out.println( Principal.getMensagem("menu.emprestimo.encerrar.titulo") );
		System.out.println( Principal.getMensagem("menu.emprestimo.encerrar.solicita") );		

		String codigo= Principal.lerStringTeclado();

		boolean resultadoOperacao= ControladorEmprestimo.encerrarEmprestimo(codigo);

		if(!resultadoOperacao)
			System.err.println( Principal.getMensagem("menu.naoRealizado") );
	}

	private static void removerEmprestimo() {		

		System.out.println( Principal.getMensagem("menu.emprestimo.remover.titulo") );
		System.out.println( Principal.getMensagem("menu.emprestimo.remover.solicita") );		

		String codigo= Principal.lerStringTeclado();

		boolean resultadoOperacao= ControladorEmprestimo.removerEmprestimo(codigo);

		if(!resultadoOperacao) 
			System.err.println( Principal.getMensagem("menu.naoRealizado") );
	}
}
