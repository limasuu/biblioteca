package bibli.aplicacao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import bibli.controle.ControladorEmprestimo;
import bibli.dados.AcervoEmprestimo;
import bibli.dados.AcervoExemplar;
import bibli.dados.AcervoFuncionario;
import bibli.dados.AcervoLivro;
import bibli.dados.AcervoUsuario;

public class Principal {

	private static Properties properties;
	private static Scanner entrada;

	public static void main(String[] args) {

		exibirMenu();
	}

	private static void exibirMenu() {

		boolean uso= true;
		iniciarProperties();
		habilitarTeclado();
		
		resgatarRegistros();
		ControladorEmprestimo.atualizarInadimplencias();

		while(uso) {

			System.out.println( getMensagem("menu.topo") );
			System.out.println( getMensagem("menu.titulo") );
			System.out.println( getMensagem("menu.principal.titulo") );
			System.out.println( getMensagem("menu.escolha") );
			System.out.println( getMensagem("menu.principal.opcao1") );
			System.out.println( getMensagem("menu.principal.opcao2") );
			System.out.println( getMensagem("menu.principal.opcao3") );
			System.out.println( getMensagem("menu.principal.opcao4") );
			System.out.println( getMensagem("menu.principal.opcao5") );
			System.out.println( getMensagem("menu.sair"));
			System.out.println( getMensagem("menu.base"));

			int opcao= lerInteiroTeclado();

			switch(opcao) {

			case 1:
				MenuLivro.apresentarOpcoes();
				break;
			case 2:
				MenuExemplar.apresentarOpcoes();
				break;
			case 3:
				MenuUsuario.apresentarOpcoes();
				break;
			case 4:
				MenuFuncionario.apresentarOpcoes();
				break;
			case 5:
				MenuEmprestimo.apresentarOpcoes();
				break;
			case 0:
				System.out.println( getMensagem("menu.agradecimento") );					
				uso= false;				
				break;
			default:
				System.err.println( getMensagem("menu.opcaoInvalida") );	
			}	
		}
		
		desabilitarTeclado();
		concluirRegistros();
	}

	private static void iniciarProperties(){

		properties= new Properties();
		FileInputStream file;

		try {
			file= new FileInputStream("./info/mensagens.properties");
			properties.load(file);

		} catch (IOException e) {
			System.err.print("Ocorreu um erro com o arquivo de mensagens do sistema.");
		} 
	}

	public static void habilitarTeclado() {

		entrada= new Scanner(System.in);
	}

	public static void desabilitarTeclado() {

		entrada.close();
	}

	private static void resgatarRegistros() {

		AcervoLivro.iniciar();
		AcervoExemplar.iniciar();
		AcervoUsuario.iniciar();
		AcervoFuncionario.iniciar();
		AcervoEmprestimo.iniciar();
	}

	private static void concluirRegistros() {

		AcervoLivro.encerrar();
		AcervoExemplar.encerrar();
		AcervoUsuario.encerrar();
		AcervoFuncionario.encerrar();
		AcervoEmprestimo.encerrar();
	}

	public static String getMensagem(String chave) {

		return properties.getProperty(chave);
	}

	public static String lerStringTeclado() {

		System.out.print( getMensagem("menu.entradaTeclado") );

		return entrada.nextLine();
	}

	public static int lerInteiroTeclado() {

		int numero= 0;
		boolean leitura= true;

		while(leitura) {
			try {
				System.out.print( getMensagem("menu.entradaTeclado") );
				numero= entrada.nextInt(); 

				if(numero >= 0)
					leitura= false;
				else
					System.err.print( getMensagem("menu.valorInvalido") );

			}catch (InputMismatchException e) {
				System.err.print( getMensagem("menu.valorInvalido") );

			}finally {
				limparBuffer();
			}
		}

		return numero;
	}

	public static double lerRealTeclado() {

		double numero= 0;
		boolean leitura= true;

		while(leitura) {
			try {
				System.out.print( getMensagem("menu.entradaTeclado") );
				numero= entrada.nextDouble(); 

				if(numero >= 0)
					leitura= false;
				else
					System.err.print( getMensagem("menu.valorInvalido") );

			}catch (InputMismatchException e) {
				System.err.print( getMensagem("menu.valorInvalido") );

			}finally {
				limparBuffer();
			}
		}

		return numero;
	}

	private static void limparBuffer() {

		if(entrada.hasNextLine())
			entrada.nextLine();	
	}	
}