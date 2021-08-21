package bibli.aplicacao;

import bibli.controle.ControladorLivro;

public class MenuLivro {

	public static void apresentarOpcoes() {

		System.out.println( Principal.getMensagem("menu.topo") );
		System.out.println( Principal.getMensagem("menu.titulo") );
		System.out.println( Principal.getMensagem("menu.livro.titulo") );
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
			prepararLivro(true);
			break;
		case 3:
			prepararLivro(false);
			break;
		case 4:
			removerLivro();
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
		System.out.println( Principal.getMensagem("menu.livro.exibir.titulo") );
		System.out.println( Principal.getMensagem("menu.escolha") );
		System.out.println( Principal.getMensagem("menu.livro.exibir.um") );
		System.out.println( Principal.getMensagem("menu.livro.exibir.varios") );
		System.out.println( Principal.getMensagem("menu.voltar") );
		System.out.println( Principal.getMensagem("menu.base") );

		int opcao= Principal.lerInteiroTeclado();

		switch(opcao) {

		case 1:
			exibirLivro();
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
		System.out.println( Principal.getMensagem("menu.livro.exibir.varios.titulo") );
		System.out.println( Principal.getMensagem("menu.escolha") );
		System.out.println( Principal.getMensagem("menu.livro.exibir.todos") );
		System.out.println( Principal.getMensagem("menu.livro.exibir.varios.autor") );
		System.out.println( Principal.getMensagem("menu.livro.exibir.varios.opcao.titulo") );
		System.out.println( Principal.getMensagem("menu.voltar"));
		System.out.println( Principal.getMensagem("menu.base"));

		int opcao= Principal.lerInteiroTeclado();

		switch(opcao) {

		case 1:
			exibirLivrosTotal();
			break;
		case 2:
			exibirLivrosPorAutor();
			break;
		case 3:
			exibirLivrosPorTitulo();
			break;
		case 0:
			apresentarOpcoesExibir();
			break;
		default:
			System.err.println( Principal.getMensagem("menu.opcaoInvalida") );	
			apresentarOpcoesExibirVarios();
		}
	}

	private static void exibirLivro (){

		System.out.println( Principal.getMensagem("menu.livro.exibir.um.titulo") );
		System.out.println( Principal.getMensagem("menu.livro.exibir.um.solicita") );

		String codigo= Principal.lerStringTeclado();

		ControladorLivro.exibirLivro(codigo);
	}

	private static void exibirLivrosTotal (){

		ControladorLivro.exibirLivros();
	}

	private static void exibirLivrosPorAutor (){

		System.out.println( Principal.getMensagem("menu.livro.exibir.varios.autor.titulo") );
		System.out.println( Principal.getMensagem("menu.livro.exibir.varios.autor.solicita") );

		String autor= Principal.lerStringTeclado();

		ControladorLivro.exibirLivrosPorAutor(autor);
	}

	private static void exibirLivrosPorTitulo (){

		System.out.println( Principal.getMensagem("menu.livro.exibir.varios.titulo.titulo") );
		System.out.println( Principal.getMensagem("menu.livro.exibir.varios.titulo.solicita") );				

		String titulo= Principal.lerStringTeclado();

		ControladorLivro.exibirLivrosPorTitulo(titulo);	
	}

	private static void prepararLivro(boolean novo) {

		boolean resultadoOperacao= false;
		String codigo= null;

		if(novo) {
			System.out.println( Principal.getMensagem("menu.livro.cadastrar.titulo") );
			System.out.println( Principal.getMensagem("menu.livro.cadastrar.solicita") );

		}else {
			System.out.println( Principal.getMensagem("menu.livro.editar.titulo") );
			System.out.println( Principal.getMensagem("menu.livro.editar.solicita") );

			System.out.println( Principal.getMensagem("menu.livro.codigo") );
			codigo= Principal.lerStringTeclado();
		}

		System.out.println( Principal.getMensagem("menu.livro.atributo.titulo") );
		String titulo= Principal.lerStringTeclado();

		System.out.println( Principal.getMensagem("menu.livro.autor") );
		String autor= Principal.lerStringTeclado();

		System.out.println( Principal.getMensagem("menu.livro.edicao") );
		int edicao= Principal.lerInteiroTeclado();

		System.out.println( Principal.getMensagem("menu.livro.editora") );
		String editora= Principal.lerStringTeclado();

		System.out.println( Principal.getMensagem("menu.livro.numeroPaginas") );
		int numeroPaginas= Principal.lerInteiroTeclado();
		
		System.out.println( Principal.getMensagem("menu.livro.isbn") );
		String isbn= Principal.lerStringTeclado();	

		System.out.println( Principal.getMensagem("menu.livro.categoria") );
		String categoria= Principal.lerStringTeclado();	

		if(novo)
			resultadoOperacao= ControladorLivro.adicionarLivro(titulo, autor, edicao, 
					editora, numeroPaginas, isbn, categoria);			
		else 
			resultadoOperacao= ControladorLivro.editarLivro(codigo, titulo, autor, edicao, 
					editora, numeroPaginas, isbn, categoria);

		if(!resultadoOperacao)
			System.err.println( Principal.getMensagem("menu.naoRealizado") );	
	}	

	private static void removerLivro() {		

		System.out.println( Principal.getMensagem("menu.livro.remover.titulo") );
		System.out.println( Principal.getMensagem("menu.livro.remover.solicita") );		

		String codigo= Principal.lerStringTeclado();
		
		boolean resultadoOperacao= ControladorLivro.removerLivro(codigo);

		if(!resultadoOperacao) 
			System.err.println( Principal.getMensagem("menu.naoRealizado") );	
	}
}
