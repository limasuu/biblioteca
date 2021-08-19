package bibli.aplicacao;

import bibli.controle.ControladorUsuario;

public class MenuUsuario {
	
	public static void apresentarOpcoes() {

		System.out.println("\n----------------------------------------------------");
		System.out.println("-------------------- BIBLIOTECA --------------------");
		System.out.println("  ________________ menu usuários ________________  ");
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
			System.err.println("\nOpção inválida! Tente novamente.\n");	
			apresentarOpcoes();
		}	
	}	
	
	private static void apresentarOpcoesExibir() {

		System.out.println("\n-------------------- BIBLIOTECA --------------------");
		System.out.println("   _____________ menu usuários (exibir) _____________   ");
		System.out.println("Escolha uma opção:");
		System.out.println("1. Exibir um usuário");
		System.out.println("2. Exibir todos os usuários");
		System.out.println("0. Voltar");	
		System.out.println("----------------------------------------------------");

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
			System.err.println("\nOpção inválida! Tente novamente.\n");		
			apresentarOpcoesExibir();
		}
	}
	
	private static void exibirUsuario (){

		System.out.println("\n  ________________ opção EXIBIR USUÁRIO ________________  ");
		System.out.print("Informe o código do usuário para sua a exibição ");

		String codigo= Principal.lerStringTeclado();

		ControladorUsuario.exibirUsuario(codigo);
	}
	
	private static void exibirUsuariosTotal (){

		ControladorUsuario.exibirUsuarios();
	}

	private static void prepararUsuario(boolean novo) {
		
		String operacao= null;
		String resultadoOperacao= null;
		String codigo= null;

		if(novo) {
			operacao= "adicionado";

			System.out.println("\n  ________________ opção ADICIONAR USUÁRIO ________________  ");
			System.out.println("Informe os dados a seguir para o cadastro de um novo usuário:");

		}else {
			operacao= "editado";

			System.out.println("\n  ________________ opção EDITAR USUÁRIO ________________  ");
			System.out.println("Informe os dados a seguir para a edição de um usuário:");
			
			System.out.print("Código ");
			codigo= Principal.lerStringTeclado();
		}
			
		System.out.print("Nome ");
		String nome= Principal.lerStringTeclado();

		System.out.print("Endereço ");
		String endereco= Principal.lerStringTeclado();

		System.out.print("Telefone ");
		String telefone= Principal.lerStringTeclado();

		System.out.print("E-mail ");
		String email= Principal.lerStringTeclado();
		
		if(novo)
			resultadoOperacao= ControladorUsuario.adicionarUsuario(nome, endereco, telefone, email);		
		else 
			resultadoOperacao= ControladorUsuario.editarUsuario(codigo, nome, endereco, telefone, email);

		if(resultadoOperacao != null) 
			System.out.println("\nUsuário com código \"" + resultadoOperacao + "\" " + operacao + ".");
		else 
			System.err.println("\nOperação não realizada.");
	}
	
	private static void removerUsuario() {		

		System.out.println("\n  ________________ opção REMOVER USUÁRIO ________________  ");
		System.out.print("Informe o código do usuário para realizar a remoção ");

		String codigo= Principal.lerStringTeclado();
		String nome= ControladorUsuario.removerUsuario(codigo);

		if(nome != null) 			
			System.out.println("\nUsuário \"" + nome + "\" removida.");			
		else 
			System.err.println("\nOperação não realizada.");
	}
}
