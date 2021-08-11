package bibli.controle;

import bibli.aplicacao.Principal;

public class MenuLivro {

	public static void apresentarOpcoes() {

		System.out.println("\n----------------------------------------------------");
		System.out.println("-------------------- BIBLIOTECA --------------------");
		System.out.println("  ________________ menu livros ________________  ");
		System.out.println("Escolha uma opção:");
		System.out.println("1. Exibir");
		System.out.println("2. Cadastrar");
		System.out.println("3. Editar");
		System.out.println("4. Excluir");	
		System.out.println("0. Voltar");
		System.out.println("----------------------------------------------------");

		int opcao= Principal.lerNumeroTeclado();

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
			System.err.println("\nOpção inválida! Tente novamente.\n");	
			apresentarOpcoes();
		}	
	}	

	private static void apresentarOpcoesExibir() {

		System.out.println("\n----------------------------------------------------");
		System.out.println("-------------------- BIBLIOTECA --------------------");
		System.out.println("   _____________ menu livros (exibir) _____________   ");
		System.out.println("Escolha uma opção:");
		System.out.println("1. Exibir um livro");
		System.out.println("2. Exibir vários livros");
		System.out.println("0. Voltar");	
		System.out.println("----------------------------------------------------");

		int opcao= Principal.lerNumeroTeclado();

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
			System.err.println("\nOpção inválida! Tente novamente.\n");		
			apresentarOpcoesExibir();
		}
	}

	private static void apresentarOpcoesExibirVarios() {

		System.out.println("\n----------------------------------------------------");
		System.out.println("-------------------- BIBLIOTECA --------------------");
		System.out.println("   ______ menu livros (exibir vários livros) ______   ");
		System.out.println("Escolha uma opção:");
		System.out.println("1. Exibir todos os livros");
		System.out.println("2. Exibir livros por autor");
		System.out.println("3. Exibir livros por título");
		System.out.println("0. Voltar");	
		System.out.println("----------------------------------------------------");

		int opcao= Principal.lerNumeroTeclado();

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
			System.err.println("\nOpção inválida! Tente novamente.\n");		
			apresentarOpcoesExibirVarios();
		}
	}

	private static void exibirLivro (){

		System.out.println("\n  ________________ opção EXIBIR LIVRO ________________  ");
		System.out.print("Informe o ISBN do livro para sua a exibição ");

		String isbn= Principal.lerStringTeclado();

		ControladorLivro.exibirLivro(isbn);
	}

	private static void exibirLivrosTotal (){

		ControladorLivro.exibirLivros();
	}

	private static void exibirLivrosPorAutor (){

		System.out.println("\n  ________________ opção EXIBIR LIVROS POR AUTOR ________________  ");
		System.out.print("Informe o nome do autor para a exibição dos livros ");

		String autor= Principal.lerStringTeclado();

		ControladorLivro.buscarLivrosPorAutor(autor);
	}

	private static void exibirLivrosPorTitulo (){

		System.out.println("\n  ________________ opção EXIBIR LIVROS POR TÍTULO ________________  ");
		System.out.print("Informe o título para a exibição dos livros ");

		String titulo= Principal.lerStringTeclado();

		ControladorLivro.exibirLivrosPorTitulo(titulo);	
	}

	private static void prepararLivro(boolean novo) {

		String operacao= null;
		boolean resultadoOperacao= false;

		if(novo) {
			operacao= "adicionado";

			System.out.println("\n  ________________ opção ADICIONAR LIVRO ________________  ");
			System.out.println("Informe os dados a seguir para o cadastro de um novo livro:");

			System.out.print("\nISBN ");

		}else {

			operacao= "editado";

			System.out.println("\n  ________________ opção EDITAR LIVRO ________________  ");
			System.out.println("Informe os dados a seguir para a edição de um livro:");

			System.out.print("\nISBN do livro a ser editado ");
		}

		String isbn= Principal.lerStringTeclado();

		System.out.print("Título ");
		String titulo= Principal.lerStringTeclado();

		System.out.print("Autor ");
		String autor= Principal.lerStringTeclado();

		System.out.print("Edição ");
		int edicao= Principal.lerNumeroTeclado();

		System.out.print("Editora ");
		String editora= Principal.lerStringTeclado();

		System.out.print("Número de páginas ");
		int numeroPaginas= Principal.lerNumeroTeclado();

		System.out.print("Categoria ");
		String categoria= Principal.lerStringTeclado();	

		if(novo)
			resultadoOperacao= ControladorLivro.adicionarLivro(titulo, autor, edicao, 
					editora, numeroPaginas, isbn, categoria);			
		else 
			resultadoOperacao= ControladorLivro.editarLivro(titulo, autor, edicao, 
					editora, numeroPaginas, isbn, categoria);

		if(resultadoOperacao) 
			System.out.println("\nLivro \"" + titulo + "\" " + operacao + ".");
		else 
			System.err.println("\nOperação não realizada.");		
	}	

	private static void removerLivro() {		

		System.out.println("\n  ________________ opção REMOVER LIVRO ________________  ");
		System.out.print("Informe o ISBN do livro para realizar a remoção ");

		String isbn= Principal.lerStringTeclado();

		String tituloLivro= ControladorLivro.removerLivro(isbn);

		if(tituloLivro != null) 
			System.out.println("\nLivro \"" + tituloLivro + "\" removido.");
		else 
			System.err.println("\nOperação não realizada.");
	}
}
