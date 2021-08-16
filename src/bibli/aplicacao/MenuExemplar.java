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

		int opcao= Principal.lerInteiroTeclado();

		switch(opcao) {

		case 1:
			apresentarOpcoesExibir();
			break;
		case 2:
			apresentarOpcoesCadastrar();
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

		int opcao= Principal.lerInteiroTeclado();

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

		int opcao= Principal.lerInteiroTeclado();

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

	private static void apresentarOpcoesCadastrar() {

		System.out.println("\n-------------------- BIBLIOTECA --------------------");
		System.out.println("   ___________ menu exemplares (cadastrar) ___________   ");
		System.out.println("Escolha uma opção:");
		System.out.println("1. Cadastrar um exemplar");
		System.out.println("2. Cadastrar vários exemplares");
		System.out.println("0. Voltar");	
		System.out.println("----------------------------------------------------");

		int opcao= Principal.lerInteiroTeclado();

		switch(opcao) {

		case 1:
			cadastrarExemplar(false);
			break;
		case 2:
			cadastrarExemplar(true);
			break;
		case 0:
			apresentarOpcoes();
			break;
		default:
			System.err.println("\nOpção inválida! Tente novamente.\n");	
			apresentarOpcoesCadastrar();
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

		int opcao= Principal.lerInteiroTeclado();

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

	private static void cadastrarExemplar(boolean varios) {

		boolean resultadoOperacao= false;
		int quantidade= 1;

		if(varios) {
			System.out.println("\n  ________________ opção CADASTRAR EXEMPLARES ________________  ");
			System.out.print("Informe o ISBN do livro para o cadastro de novos exemplares ");	

		}else {
			System.out.println("\n  ________________ opção CADASTRAR EXEMPLAR ________________  ");
			System.out.print("Informe o ISBN do livro para o cadastro de um novo exemplar ");
		}

		String isbn= Principal.lerStringTeclado();

		if(varios) {

			System.out.print("\nQuantidade ");
			quantidade= Principal.lerInteiroTeclado();
		}

		for(int i=0; i<quantidade; i++) {
			resultadoOperacao= ControladorExemplar.adicionarExemplar(isbn);	

			if(!resultadoOperacao) 	
				break;
		}

		if(resultadoOperacao) {
			if(varios)
				System.out.println("Exemplares cadastrados.");
			else
				System.out.println("Exemplar cadastrado.");
		}else 
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
