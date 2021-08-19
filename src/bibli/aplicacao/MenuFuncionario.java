package bibli.aplicacao;

import bibli.controle.ControladorFuncionario;

public class MenuFuncionario {
	
	public static void apresentarOpcoes() {

		System.out.println("\n----------------------------------------------------");
		System.out.println("-------------------- BIBLIOTECA --------------------");
		System.out.println("  ________________ menu funcionários ________________  ");
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
			prepararFuncionario(true);
			break;
		case 3:
			prepararFuncionario(false);
			break;
		case 4:
			removerFuncionario();
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
		System.out.println("   _____________ menu funcionários (exibir) _____________   ");
		System.out.println("Escolha uma opção:");
		System.out.println("1. Exibir um funcionário");
		System.out.println("2. Exibir vários funcionários");
		System.out.println("0. Voltar");	
		System.out.println("----------------------------------------------------");

		int opcao= Principal.lerInteiroTeclado();

		switch(opcao) {

		case 1:
			exibirFuncionario();
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
		System.out.println("   ______ menu funcionários (exibir vários funcionários) ______   ");
		System.out.println("Escolha uma opção:");
		System.out.println("1. Exibir todos os funcionários");
		System.out.println("2. Exibir funcionários por cargo");
		System.out.println("0. Voltar");	
		System.out.println("----------------------------------------------------");

		int opcao= Principal.lerInteiroTeclado();

		switch(opcao) {

		case 1:
			exibirFuncionariosTotal();
			break;
		case 2:
			exibirFuncionariosPorCargo();
			break;
		case 0:
			apresentarOpcoesExibir();
			break;
		default:
			System.err.println("\nOpção inválida! Tente novamente.\n");		
			apresentarOpcoesExibirVarios();
		}
	}
	
	private static void exibirFuncionario (){

		System.out.println("\n  ________________ opção EXIBIR FUNCIONÀRIO ________________  ");
		System.out.print("Informe a matrícula do funcionário para sua a exibição ");

		String matricula= Principal.lerStringTeclado();

		ControladorFuncionario.exibirFuncionario(matricula);
	}
	
	private static void exibirFuncionariosTotal (){

		ControladorFuncionario.exibirFuncionarios();
	}
	
	private static void exibirFuncionariosPorCargo (){

		System.out.println("\n  ________________ opção EXIBIR FUNCIONÁRIOS POR CARGO ________________  ");
		System.out.print("Informe o cargo para a exibição dos funcionários ");

		String cargo= Principal.lerStringTeclado();

		ControladorFuncionario.exibirFuncionariosPorCargo(cargo);
	}
	
	private static void prepararFuncionario(boolean novo) {
		
		String operacao= null;
		String resultadoOperacao= null;
		String matricula= null;

		if(novo) {
			operacao= "adicionado";

			System.out.println("\n  ________________ opção ADICIONAR FUNCIONÁRIO ________________  ");
			System.out.println("Informe os dados a seguir para o cadastro de um novo funcionário:");

		}else {
			operacao= "editado";

			System.out.println("\n  ________________ opção EDITAR FUNCIONÁRIO ________________  ");
			System.out.println("Informe os dados a seguir para a edição de um novo funcionário:");
			
			System.out.print("Matrícula ");
			matricula= Principal.lerStringTeclado();
		}
			
		System.out.print("Nome ");
		String nome= Principal.lerStringTeclado();

		System.out.print("Endereço ");
		String endereco= Principal.lerStringTeclado();

		System.out.print("Telefone ");
		String telefone= Principal.lerStringTeclado();

		System.out.print("E-mail ");
		String email= Principal.lerStringTeclado();
		
		System.out.print("Salário ");
		double salario= Principal.lerRealTeclado();
		
		System.out.print("Cargo ");
		String cargo= Principal.lerStringTeclado();
		
		
		if(novo)
			resultadoOperacao= ControladorFuncionario.adicionarFuncionario(nome, endereco, 
					telefone, email, salario, cargo);		
		else 
			resultadoOperacao= ControladorFuncionario.editarFuncionario(matricula, nome, endereco, 
					telefone, email, salario, cargo);

		if(resultadoOperacao != null) 
			System.out.println("\nFuncionário com matrícula \"" + resultadoOperacao + "\" " + operacao + ".");
		else 
			System.err.println("\nOperação não realizada.");
	}

	private static void removerFuncionario() {		

		System.out.println("\n  ________________ opção REMOVER FUNCIONÁRIO ________________  ");
		System.out.print("Informe a matrícula do funcionário para realizar a remoção ");

		String matricula= Principal.lerStringTeclado();
		String nome= ControladorFuncionario.removerFuncionario(matricula);

		if(nome != null) 			
			System.out.println("\nFuncionário \"" + nome + "\" removido.");			
		else 
			System.err.println("\nOperação não realizada.");
	}

}
