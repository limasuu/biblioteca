package bibli.aplicacao;

import bibli.controle.ControladorPessoa;

public class MenuPessoa {
	
	public static void apresentarOpcoes() {

		System.out.println("\n----------------------------------------------------");
		System.out.println("-------------------- BIBLIOTECA --------------------");
		System.out.println("  ________________ menu pessoas ________________  ");
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
			prepararPessoa(true);
			break;
		case 3:
			prepararPessoa(false);
			break;
		case 4:
			removerPessoa();
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
		System.out.println("   _____________ menu pessoas (exibir) _____________   ");
		System.out.println("Escolha uma opção:");
		System.out.println("1. Exibir uma pessoa");
		System.out.println("2. Exibir todas as pessoas");
		System.out.println("0. Voltar");	
		System.out.println("----------------------------------------------------");

		int opcao= Principal.lerInteiroTeclado();

		switch(opcao) {

		case 1:
			exibirPessoa();
			break;
		case 2:
			exibirPessoasTotal();
			break;
		case 0:
			apresentarOpcoes();
			break;
		default:
			System.err.println("\nOpção inválida! Tente novamente.\n");		
			apresentarOpcoesExibir();
		}
	}
	
	private static void exibirPessoa (){

		System.out.println("\n  ________________ opção EXIBIR PESSOA ________________  ");
		System.out.print("Informe o código da pessoa para sua a exibição ");

		String codigo= Principal.lerStringTeclado();

		ControladorPessoa.exibirPessoa(codigo);
	}
	
	private static void exibirPessoasTotal (){

		ControladorPessoa.exibirPessoas();
	}

	private static void prepararPessoa(boolean novo) {
		
		String operacao= null;
		String resultadoOperacao= null;
		String codigo= null;

		if(novo) {
			operacao= "adicionado";

			System.out.println("\n  ________________ opção ADICIONAR PESSOA ________________  ");
			System.out.println("Informe os dados a seguir para o cadastro de uma nova pessoa:");

		}else {
			operacao= "editado";

			System.out.println("\n  ________________ opção EDITAR PESSOA ________________  ");
			System.out.println("Informe os dados a seguir para a edição de uma pessoa:");
			
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
			resultadoOperacao= ControladorPessoa.adicionarPessoa(nome, endereco, telefone, email);		
		else 
			resultadoOperacao= ControladorPessoa.editarPessoa(codigo, nome, endereco, telefone, email);

		if(resultadoOperacao != null) 
			System.out.println("\nPessoa com código \"" + resultadoOperacao + "\" " + operacao + ".");
		else 
			System.err.println("\nOperação não realizada.");
	}
	
	private static void removerPessoa() {		

		System.out.println("\n  ________________ opção REMOVER PESSOA ________________  ");
		System.out.print("Informe o código da pessoa para realizar a remoção ");

		String codigo= Principal.lerStringTeclado();
		String nome= ControladorPessoa.removerPessoa(codigo);

		if(nome != null) 			
			System.out.println("\nPessoa \"" + nome + "\" removida.");			
		else 
			System.err.println("\nOperação não realizada.");
	}
}
