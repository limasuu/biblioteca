package bibli.aplicacao;

import bibli.controle.ControladorFuncionario;

public class MenuFuncionario {
	
	public static void apresentarOpcoes() {

		System.out.println( Principal.getMensagem("menu.topo") );
		System.out.println( Principal.getMensagem("menu.titulo") );
		System.out.println( Principal.getMensagem("menu.funcionario.titulo") );
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
			System.err.println( Principal.getMensagem("menu.opcaoInvalida") );
			apresentarOpcoes();
		}	
	}	
	
	private static void apresentarOpcoesExibir() {

		System.out.println( Principal.getMensagem("menu.titulo") );
		System.out.println( Principal.getMensagem("menu.funcionario.exibir.titulo") );
		System.out.println( Principal.getMensagem("menu.escolha") );
		System.out.println( Principal.getMensagem("menu.funcionario.exibir.um") );
		System.out.println( Principal.getMensagem("menu.funcionario.exibir.varios") );
		System.out.println( Principal.getMensagem("menu.voltar") );
		System.out.println( Principal.getMensagem("menu.base") );

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
			System.err.println( Principal.getMensagem("menu.opcaoInvalida") );
			apresentarOpcoesExibir();
		}
	}
	
	private static void apresentarOpcoesExibirVarios() {

		System.out.println( Principal.getMensagem("menu.titulo") );
		System.out.println( Principal.getMensagem("menu.funcionario.exibir.varios.titulo") );
		System.out.println( Principal.getMensagem("menu.escolha") );
		System.out.println( Principal.getMensagem("menu.funcionario.exibir.todos") );
		System.out.println( Principal.getMensagem("menu.funcionario.exibir.varios.cargo") );
		System.out.println( Principal.getMensagem("menu.voltar"));
		System.out.println( Principal.getMensagem("menu.base"));

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
			System.err.println( Principal.getMensagem("menu.opcaoInvalida") );	
			apresentarOpcoesExibirVarios();
		}
	}
	
	private static void exibirFuncionario (){

		System.out.println( Principal.getMensagem("menu.funcionario.exibir.um.titulo") );
		System.out.println( Principal.getMensagem("menu.funcionario.exibir.um.solicita") );

		String matricula= Principal.lerStringTeclado();

		ControladorFuncionario.exibirFuncionario(matricula);
	}
	
	private static void exibirFuncionariosTotal (){

		ControladorFuncionario.exibirFuncionarios();
	}
	
	private static void exibirFuncionariosPorCargo (){

		System.out.println( Principal.getMensagem("menu.funcionario.exibir.varios.cargo.titulo") );
		System.out.println( Principal.getMensagem("menu.funcionario.exibir.varios.cargo.solicita") );

		String cargo= Principal.lerStringTeclado();

		ControladorFuncionario.exibirFuncionariosPorCargo(cargo);
	}
	
	private static void prepararFuncionario(boolean novo) {
		
		boolean resultadoOperacao= false;
		String matricula= null;

		if(novo) {
			System.out.println( Principal.getMensagem("menu.funcionario.cadastrar.titulo") );
			System.out.println( Principal.getMensagem("menu.funcionario.cadastrar.solicita") );

		}else {
			System.out.println( Principal.getMensagem("menu.funcionario.editar.titulo") );
			System.out.println( Principal.getMensagem("menu.funcionario.editar.solicita") );
			
			System.out.println( Principal.getMensagem("menu.funcionario.matricula") );
			matricula= Principal.lerStringTeclado();
		}
			
		System.out.println( Principal.getMensagem("menu.funcionario.nome") );
		String nome= Principal.lerStringTeclado();

		System.out.println( Principal.getMensagem("menu.funcionario.endereco") );
		String endereco= Principal.lerStringTeclado();

		System.out.println( Principal.getMensagem("menu.funcionario.telefone") );
		String telefone= Principal.lerStringTeclado();

		System.out.println( Principal.getMensagem("menu.funcionario.email") );
		String email= Principal.lerStringTeclado();
		
		System.out.println( Principal.getMensagem("menu.funcionario.salario") );
		double salario= Principal.lerRealTeclado();
		
		System.out.println( Principal.getMensagem("menu.funcionario.cargo") );
		String cargo= Principal.lerStringTeclado();
		
		
		if(novo)
			resultadoOperacao= ControladorFuncionario.adicionarFuncionario(nome, endereco, 
					telefone, email, salario, cargo);		
		else 
			resultadoOperacao= ControladorFuncionario.editarFuncionario(matricula, nome, endereco, 
					telefone, email, salario, cargo);

		if(!resultadoOperacao)
			System.err.println( Principal.getMensagem("menu.naoRealizado") );	
	}

	private static void removerFuncionario() {		

		System.out.println( Principal.getMensagem("menu.funcionario.remover.titulo") );
		System.out.println( Principal.getMensagem("menu.funcionario.remover.solicita") );		

		String matricula= Principal.lerStringTeclado();
		
		boolean resultadoOperacao= ControladorFuncionario.removerFuncionario(matricula);

		if(!resultadoOperacao) 
			System.err.println( Principal.getMensagem("menu.naoRealizado") );
	}
}
