package bibli.aplicacao;

import bibli.controle.ControladorExemplar;
import bibli.controle.ControladorLivro;

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
			System.err.println("\nOpção inválida! Tente novamente.\n");	
			apresentarOpcoes();
		}	
	}	

	private static void apresentarOpcoesExibir() {

		System.out.println("\n-------------------- BIBLIOTECA --------------------");
		System.out.println("   _____________ menu livros (exibir) _____________   ");
		System.out.println("Escolha uma opção:");
		System.out.println("1. Exibir um livro");
		System.out.println("2. Exibir vários livros");
		System.out.println("0. Voltar");	
		System.out.println("----------------------------------------------------");

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
			System.err.println("\nOpção inválida! Tente novamente.\n");		
			apresentarOpcoesExibir();
		}
	}

	private static void apresentarOpcoesExibirVarios() {

		System.out.println("\n-------------------- BIBLIOTECA --------------------");
		System.out.println("   ______ menu livros (exibir vários livros) ______   ");
		System.out.println("Escolha uma opção:");
		System.out.println("1. Exibir todos os livros");
		System.out.println("2. Exibir livros por autor");
		System.out.println("3. Exibir livros por título");
		System.out.println("0. Voltar");	
		System.out.println("----------------------------------------------------");

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
			System.err.println("\nOpção inválida! Tente novamente.\n");		
			apresentarOpcoesExibirVarios();
		}
	}

	private static void exibirLivro (){

		System.out.println("\n  ________________ opção EXIBIR LIVRO ________________  ");
		System.out.print("Informe o código do livro para sua a exibição ");

		String codigo= Principal.lerStringTeclado();

		ControladorLivro.exibirLivro(codigo);
	}

	private static void exibirLivrosTotal (){

		ControladorLivro.exibirLivros();
	}

	private static void exibirLivrosPorAutor (){

		System.out.println("\n  ________________ opção EXIBIR LIVROS POR AUTOR ________________  ");
		System.out.print("Informe o nome do autor para a exibição dos livros ");

		String autor= Principal.lerStringTeclado();

		ControladorLivro.exibirLivrosPorAutor(autor);
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
		String codigo= null;

		if(novo) {
			operacao= "adicionado";

			System.out.println("\n  ________________ opção ADICIONAR LIVRO ________________  ");
			System.out.println("Informe os dados a seguir para o cadastro de um novo livro:\n");

		}else {

			operacao= "editado";

			System.out.println("\n  ________________ opção EDITAR LIVRO ________________  ");
			System.out.println("Informe os dados a seguir para a edição de um livro:");

			System.out.print("\nCódigo do livro a ser editado ");
			codigo= Principal.lerStringTeclado();
		}

		System.out.print("Título ");
		String titulo= Principal.lerStringTeclado();

		System.out.print("Autor ");
		String autor= Principal.lerStringTeclado();

		System.out.print("Edição ");
		int edicao= Principal.lerInteiroTeclado();

		System.out.print("Editora ");
		String editora= Principal.lerStringTeclado();

		System.out.print("Número de páginas ");
		int numeroPaginas= Principal.lerInteiroTeclado();
		
		System.out.print("ISBN ");
		String isbn= Principal.lerStringTeclado();	

		System.out.print("Categoria ");
		String categoria= Principal.lerStringTeclado();	

		if(novo)
			resultadoOperacao= ControladorLivro.adicionarLivro(titulo, autor, edicao, 
					editora, numeroPaginas, isbn, categoria);			
		else 
			resultadoOperacao= ControladorLivro.editarLivro(codigo, titulo, autor, edicao, 
					editora, numeroPaginas, isbn, categoria);

		if(resultadoOperacao) 
			System.out.println("\nLivro \"" + titulo + "\" " + operacao + ".");
		else 
			System.err.println("\nOperação não realizada.");		
	}	

	private static void removerLivro() {		

		System.out.println("\n  ________________ opção REMOVER LIVRO ________________  ");
		System.out.print("Informe o código do livro para realizar a remoção ");

		String codigo= Principal.lerStringTeclado();
		int numeroExemplares= ControladorExemplar.getNumeroExemplares(codigo);
		String tituloLivro= ControladorLivro.removerLivro(codigo);

		if(tituloLivro != null) {
			
			System.out.println("\nLivro \"" + tituloLivro + "\" removido.");			
			if(numeroExemplares != 0)
				System.out.println("Foi efetuada a remoção do(s) " + numeroExemplares + " exemplar(es) vinculado(s) a este livro!");
		
		}else 
			System.err.println("\nOperação não realizada.");
	}
}
