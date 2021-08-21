package bibli.aplicacao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import bibli.controle.ControladorEmprestimo;

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

		while(uso) {

			ControladorEmprestimo.atualizarInadimplencias();

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
				desabilitarTeclado();
				break;
			default:
				System.err.println( getMensagem("menu.opcaoInvalida") );	
			}	
		}
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
				leitura= false;

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
				leitura= false;

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

	private static void iniciarProperties(){

		properties= new Properties();
		FileInputStream file;

		try {
			file= new FileInputStream("./info/mensagens.properties");
			properties.load(file);

		} catch (IOException e) {
			System.err.print( getMensagem("menu.erroArquivo") );
		} 
	}

	public static void habilitarTeclado() {

		entrada= new Scanner(System.in);
	}

	public static void desabilitarTeclado() {

		entrada.close();
	}
}