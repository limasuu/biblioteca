package bibli.aplicacao;

import bibli.controle.ControladorExemplar;

public class MenuExemplar {

	public static void apresentarOpcoes() {

		System.out.println( Principal.getMensagem("menu.topo") );
		System.out.println( Principal.getMensagem("menu.titulo") );
		System.out.println( Principal.getMensagem("menu.exemplar.titulo") );
		System.out.println( Principal.getMensagem("menu.escolha") );
		System.out.println( Principal.getMensagem("menu.exibir") );
		System.out.println( Principal.getMensagem("menu.cadastrar") );
		System.out.println( Principal.getMensagem("menu.3excluir") );
		System.out.println( Principal.getMensagem("menu.voltar"));
		System.out.println( Principal.getMensagem("menu.base") );

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
			System.err.println( Principal.getMensagem("menu.opcaoInvalida") );
			apresentarOpcoes();
		}	
	}	

	private static void apresentarOpcoesExibir() {

		System.out.println( Principal.getMensagem("menu.titulo") );
		System.out.println( Principal.getMensagem("menu.exemplar.exibir.titulo") );
		System.out.println( Principal.getMensagem("menu.escolha") );
		System.out.println( Principal.getMensagem("menu.exemplar.exibir.um") );
		System.out.println( Principal.getMensagem("menu.exemplar.exibir.varios") );
		System.out.println( Principal.getMensagem("menu.voltar") );
		System.out.println( Principal.getMensagem("menu.base") );

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
			System.err.println( Principal.getMensagem("menu.opcaoInvalida") );
			apresentarOpcoesExibir();
		}	
	}

	private static void apresentarOpcoesExibirVarios() {

		System.out.println( Principal.getMensagem("menu.titulo") );
		System.out.println( Principal.getMensagem("menu.exemplar.exibir.varios.titulo") );
		System.out.println( Principal.getMensagem("menu.escolha") );
		System.out.println( Principal.getMensagem("menu.exemplar.exibir.todos") );
		System.out.println( Principal.getMensagem("menu.exemplar.exibir.varios.livro") );
		System.out.println( Principal.getMensagem("menu.voltar"));
		System.out.println( Principal.getMensagem("menu.base"));

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
			System.err.println( Principal.getMensagem("menu.opcaoInvalida") );
			apresentarOpcoesExibirVarios();
		}	
	}

	private static void apresentarOpcoesCadastrar() {

		System.out.println( Principal.getMensagem("menu.titulo") );
		System.out.println( Principal.getMensagem("menu.exemplar.cadastrar.titulo") );
		System.out.println( Principal.getMensagem("menu.escolha") );
		System.out.println( Principal.getMensagem("menu.exemplar.cadastrar.um") );
		System.out.println( Principal.getMensagem("menu.exemplar.cadastrar.varios") );
		System.out.println( Principal.getMensagem("menu.voltar"));
		System.out.println( Principal.getMensagem("menu.base"));

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
			System.err.println( Principal.getMensagem("menu.opcaoInvalida") );
			apresentarOpcoesCadastrar();
		}	
	}

	private static void apresentarOpcoesExcluir() {

		System.out.println( Principal.getMensagem("menu.titulo") );
		System.out.println( Principal.getMensagem("menu.exemplar.excluir.titulo") );
		System.out.println( Principal.getMensagem("menu.escolha") );
		System.out.println( Principal.getMensagem("menu.exemplar.excluir.um") );
		System.out.println( Principal.getMensagem("menu.exemplar.excluir.varios") );
		System.out.println( Principal.getMensagem("menu.voltar"));
		System.out.println( Principal.getMensagem("menu.base"));

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
			System.err.println( Principal.getMensagem("menu.opcaoInvalida") );
			apresentarOpcoesExcluir();
		}	
	}

	private static void exibirExemplar (){

		System.out.println( Principal.getMensagem("menu.exemplar.exibir.um.titulo") );
		System.out.println( Principal.getMensagem("menu.exemplar.exibir.um.solicita") );

		String codigo= Principal.lerStringTeclado();

		ControladorExemplar.exibirExemplar(codigo);
	}

	public static void exibirExemplaresTotal (){

		ControladorExemplar.exibirExemplares();
	}

	private static void exibirExemplaresPorLivro() {

		System.out.println( Principal.getMensagem("menu.exemplar.exibir.varios.livro.titulo") );
		System.out.println( Principal.getMensagem("menu.exemplar.exibir.varios.livro.solicita") );

		String codigo= Principal.lerStringTeclado();

		ControladorExemplar.exibirExemplaresPorLivro(codigo);
	}

	private static void cadastrarExemplar(boolean varios) {

		boolean resultadoOperacao= false;
		int quantidade= 1;

		if(varios) {
			System.out.println( Principal.getMensagem("menu.exemplar.cadastrar.varios.titulo") );
			System.out.println( Principal.getMensagem("menu.exemplar.cadastrar.varios.solicita") );

		}else {
			System.out.println( Principal.getMensagem("menu.exemplar.cadastrar.um.titulo") );
			System.out.println( Principal.getMensagem("menu.exemplar.cadastrar.um.solicita") );
		}

		String codigo= Principal.lerStringTeclado();

		if(varios) {

			System.out.println( Principal.getMensagem("menu.exemplar.cadastrar.varios.quantidade") );
			quantidade= Principal.lerInteiroTeclado();
		}

		int qntd=0;
		for(int i=0; i<quantidade; i++) {
			resultadoOperacao= ControladorExemplar.adicionarExemplar(codigo);	

			if(!resultadoOperacao) 	{
				qntd= i;
				break;
			}
		}

		if(!resultadoOperacao) {
			System.err.println( Principal.getMensagem("menu.naoRealizado") );

			if(varios)
				System.err.println("Realizou-se o cadastro de " + qntd + " exemplar(es).");
		}	
	}	

	private static void removerExemplar() {

		System.out.println( Principal.getMensagem("menu.exemplar.remover.um.titulo") );
		System.out.println( Principal.getMensagem("menu.exemplar.remover.um.solicita") );		

		String codigo= Principal.lerStringTeclado();

		boolean resultadoOperacao= ControladorExemplar.removerExemplar(codigo);

		if(!resultadoOperacao)	
			System.err.println( Principal.getMensagem("menu.naoRealizado") );	
	}

	private static void removerExemplares() {

		System.out.println( Principal.getMensagem("menu.exemplar.remover.varios.titulo") );
		System.out.println( Principal.getMensagem("menu.exemplar.remover.varios.solicita") );

		String codigo= Principal.lerStringTeclado();
		boolean resultadoOperacao= ControladorExemplar.removerExemplares(codigo);

		if(!resultadoOperacao)	
			System.err.println( Principal.getMensagem("menu.naoRealizado") );	
	}
}
