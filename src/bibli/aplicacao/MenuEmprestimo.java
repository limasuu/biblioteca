package bibli.aplicacao;

import bibli.controle.ControladorEmprestimo;

public class MenuEmprestimo {

	public static void apresentarOpcoes() {

		System.out.println("\n----------------------------------------------------");
		System.out.println("-------------------- BIBLIOTECA --------------------");
		System.out.println("  ________________ menu empréstimos ________________  ");
		System.out.println("Escolha uma opção:");
		System.out.println("1. Exibir");
		System.out.println("2. Cadastrar");
		System.out.println("3. Renovar");
		System.out.println("4. Encerrar");	
		System.out.println("5. Excluir");	
		System.out.println("0. Voltar");
		System.out.println("----------------------------------------------------");

		int opcao= Principal.lerInteiroTeclado();

		switch(opcao) {

		case 1:
			apresentarOpcoesExibir();
			break;
		case 2:
			adicionarEmprestimo();
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
			System.err.println("\nOpção inválida! Tente novamente.\n");	
			apresentarOpcoes();
		}	
	}	

	private static void apresentarOpcoesExibir() {

		System.out.println("\n-------------------- BIBLIOTECA --------------------");
		System.out.println("   _____________ menu empréstimos (exibir) _____________   ");
		System.out.println("Escolha uma opção:");
		System.out.println("1. Exibir um empréstimo");
		System.out.println("2. Exibir vários empréstimos");
		System.out.println("0. Voltar");	
		System.out.println("----------------------------------------------------");

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
			System.err.println("\nOpção inválida! Tente novamente.\n");		
			apresentarOpcoesExibir();
		}
	}

	private static void apresentarOpcoesExibirVarios() {

		System.out.println("\n-------------------- BIBLIOTECA --------------------");
		System.out.println("   ______ menu empréstimos (exibir vários empréstimos) ______   ");
		System.out.println("Escolha uma opção:");
		System.out.println("1. Exibir todos os empréstimos");
		System.out.println("2. Exibir empréstimos por livro");
		System.out.println("3. Exibir empréstimos por usuário");
		System.out.println("0. Voltar");	
		System.out.println("----------------------------------------------------");

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
			System.err.println("\nOpção inválida! Tente novamente.\n");		
			apresentarOpcoesExibirVarios();
		}
	}

	private static void exibirEmprestimo (){

		System.out.println("\n  ________________ opção EXIBIR EMPRÉSTIMO ________________  ");
		System.out.print("Informe o código do empréstimo para sua a exibição ");

		String codigo= Principal.lerStringTeclado();

		ControladorEmprestimo.exibirEmprestimo(codigo);
	}

	private static void exibirEmprestimosTotal (){

		ControladorEmprestimo.exibirEmprestimos();
	}

	private static void exibirEmprestimosPorLivro (){

		System.out.println("\n  ________________ opção EXIBIR EMPRÉSTIMOS POR LIVRO ________________  ");
		System.out.print("Informe o ISBN do livro para a exibição dos empréstimos ");

		String isbn= Principal.lerStringTeclado();

		ControladorEmprestimo.exibirEmprestimosPorLivro(isbn);
	}

	private static void exibirEmprestimosPorUsuario (){

		System.out.println("\n  ________________ opção EXIBIR EMPRÉSTIMOS POR USUÁRIO ________________  ");
		System.out.print("Informe o código do usuário para a exibição dos empréstimos ");

		String codigo= Principal.lerStringTeclado();

		ControladorEmprestimo.exibirEmprestimosPorUsuario(codigo);	
	}

	private static void adicionarEmprestimo() {

		System.out.println("\n  ________________ opção CADASTRAR EMPRÉSTIMO ________________  ");
		System.out.println("Informe os dados a seguir para o cadastro de um empréstimo:");

		System.out.print("Matrícula do funcionário ");
		String matriculaFuncionario= Principal.lerStringTeclado();

		System.out.print("Código da pessoa ");
		String codigoPessoa= Principal.lerStringTeclado();

		System.out.print("Código do exemplar ");
		String codigoExemplar= Principal.lerStringTeclado();

		boolean resultadoOperacao= ControladorEmprestimo.adicionarEmprestimo(matriculaFuncionario, codigoPessoa, codigoExemplar);	

		if(resultadoOperacao) {
			System.out.println("Empréstimo cadastrado.");
		}else 
			System.err.println("Operação não realizada.");	
	}	

	private static void renovarEmprestimo() {	

		System.out.println("\n  ________________ opção RENOVAR EMPRÉSTIMO ________________  ");
		System.out.print("Informe o código do empréstimo para realizar a renovação ");

		String codigo= Principal.lerStringTeclado();

		boolean resultadoOperacao= ControladorEmprestimo.renovarEmprestimo(codigo);

		if(resultadoOperacao) 			
			System.out.println("\nEmpréstimo \"" + codigo + "\" renovado.");			
		else 
			System.err.println("\nOperação não realizada.");		
	}

	private static void encerrarEmprestimo() {	

		System.out.println("\n  ________________ opção ENCERRAR EMPRÉSTIMO ________________  ");
		System.out.print("Informe o código do empréstimo para realizar o encerramento ");

		String codigo= Principal.lerStringTeclado();

		boolean resultadoOperacao= ControladorEmprestimo.encerrarEmprestimo(codigo);

		if(resultadoOperacao) 			
			System.out.println("\nEmpréstimo \"" + codigo + "\" encerrado.");			
		else 
			System.err.println("\nOperação não realizada.");
	}

	private static void removerEmprestimo() {		

		System.out.println("\n  ________________ opção REMOVER EMPRÉSTIMO ________________  ");
		System.out.print("Informe o código do empréstimo para realizar a remoção ");

		String codigo= Principal.lerStringTeclado();

		boolean resultadoOperacao= ControladorEmprestimo.removerEmprestimo(codigo);

		if(resultadoOperacao) 			
			System.out.println("\nEmpréstimo \"" + codigo + "\" removido.");			
		else 
			System.err.println("\nOperação não realizada.");
	}

}
