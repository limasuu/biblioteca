package bibli.aplicacao;

import bibli.controle.ControladorExemplar;

public class MenuExemplar {

	public static void apresentarOpcoes() {

		System.out.println("\n----------------------------------------------------");
		System.out.println("-------------------- BIBLIOTECA --------------------");
		System.out.println("  ________________ menu exemplares ________________  ");
		System.out.println("Escolha uma opção:");
		System.out.println("1. Exibir");
		System.out.println("2. Cadastrar");
		System.out.println("3. Excluir");	
		System.out.println("0. Voltar");
		System.out.println("----------------------------------------------------");

		int opcao= Principal.lerNumeroTeclado();

		switch(opcao) {

		case 1:
			apresentarOpcoesExibir();
			break;
		case 2:
			adicionarExemplar();
			break;
		case 3:
			apresentarOpcoesExcluir();
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
		System.out.println("   ___________ menu exemplares (exibir) ___________   ");
		System.out.println("Escolha uma opção:");
		System.out.println("1. Exibir um exemplar");
		System.out.println("2. Exibir vários exemplares");
		System.out.println("0. Voltar");	
		System.out.println("----------------------------------------------------");
		
		int opcao= Principal.lerNumeroTeclado();

		switch(opcao) {

		case 1:
			exibirExemplar();
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
		System.out.println("   ____ menu livros (exibir vários exemplares) ____   ");
		System.out.println("Escolha uma opção:");
		System.out.println("1. Exibir todos os exemplares");
		System.out.println("2. Exibir exemplares por livro");
		System.out.println("0. Voltar");	
		System.out.println("----------------------------------------------------");
		
		int opcao= Principal.lerNumeroTeclado();

		switch(opcao) {

		case 1:
			exibirExemplaresTotal();
			break;
		case 2:
			exibirExemplaresPorLivro();
			break;
		case 0:
			apresentarOpcoesExibir();
			break;
		default:
			System.err.println("\nOpção inválida! Tente novamente.\n");	
			apresentarOpcoesExibirVarios();
		}	
	}

	private static void apresentarOpcoesExcluir() {

		System.out.println("\n-------------------- BIBLIOTECA --------------------");
		System.out.println("   ___________ menu exemplares (excluir) ___________   ");
		System.out.println("Escolha uma opção:");
		System.out.println("1. Excluir um exemplar");
		System.out.println("2. Excluir vários exemplares ");
		System.out.println("0. Voltar");	
		System.out.println("----------------------------------------------------");

		int opcao= Principal.lerNumeroTeclado();

		switch(opcao) {

		case 1:
			removerExemplar();
			break;
		case 2:
			removerExemplares();
			break;
		case 0:
			apresentarOpcoes();
			break;
		default:
			System.err.println("\nOpção inválida! Tente novamente.\n");	
			apresentarOpcoesExcluir();
		}	
	}

	private static void exibirExemplar (){

		System.out.println("\n  ________________ opção EXIBIR EXEMPLAR ________________  ");
		System.out.print("Informe o código do exemplar para sua a exibição ");

		String codigo= Principal.lerStringTeclado();

		ControladorExemplar.exibirExemplar(codigo);
	}

	public static void exibirExemplaresTotal (){

		ControladorExemplar.exibirExemplares();
	}

	private static void exibirExemplaresPorLivro() {

		System.out.println("\n  ________________ opção EXIBIR EXEMPLARES POR LIVRO ________________  ");
		System.out.print("Informe o código do livro para a exibição de seus exemplares ");

		String codigo= Principal.lerStringTeclado();

		ControladorExemplar.exibirExemplaresPorLivro(codigo);
	}

	private static void adicionarExemplar() {

		boolean resultadoOperacao= false;

		System.out.println("\n  ________________ opção ADICIONAR EXEMPLAR ________________  ");
		System.out.print("Informe o ISBN do livro para o cadastro de um novo exemplar ");

		String isbn= Principal.lerStringTeclado();

		resultadoOperacao= ControladorExemplar.adicionarExemplar(isbn);	

		if(resultadoOperacao) 
			System.out.println("Exemplar adicionado.");
		else 
			System.err.println("Operação não realizada.");		
	}	

	private static void removerExemplar() {

		System.out.println("\n  ________________ opção EXCLUIR EXEMPLAR ________________  ");
		System.out.print("Informe o código do exemplar para realizar a exclusão ");

		String codigo= Principal.lerStringTeclado();

		boolean resultadoOperacao= ControladorExemplar.removerExemplar(codigo);

		if(resultadoOperacao)			
			System.out.println("\nExemplar excluído.");	
		else 
			System.err.println("\nOperação não realizada.");
	}

	private static void removerExemplares() {

		System.out.println("\n  ________________ opção EXCLUIR EXEMPLARES POR LIVRO________________  ");
		System.out.print("Informe o ISBN do livro para realizar as exclusões ");

		String isbn= Principal.lerStringTeclado();
		int numeroExemplares= ControladorExemplar.getNumeroExemplares(isbn);
		boolean resultadoOperacao= ControladorExemplar.removerExemplares(isbn);

		if(resultadoOperacao)			
			System.out.println("\nFoi efetuada a exclusão do(s) " + numeroExemplares + " exemplar(es) vinculado(s) a este livro!");
		else 
			System.err.println("\nOperação não realizada.");
	}
}
