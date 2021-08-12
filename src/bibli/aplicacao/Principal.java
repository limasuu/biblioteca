package bibli.aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	private static Scanner entrada;

	public static void main(String[] args) {

		exibirMenu();
	}
	
	private static void exibirMenu() {
		
		boolean uso= true;
		habilitarTeclado();
		
		while(uso) {

			System.out.println("\n----------------------------------------------------");
			System.out.println("-------------------- BIBLIOTECA --------------------");
			System.out.println("  ________________ menu principal ________________  ");
			System.out.println("Escolha uma opção:");
			System.out.println("1. Menu Livros");
			System.out.println("2. Menu Exemplares");
			System.out.println("0. Sair");
			System.out.println("\n----------------------------------------------------");

			int opcao= lerNumeroTeclado();

			switch(opcao) {

			case 1:
				MenuLivro.apresentarOpcoes();
				break;
			case 2:
				MenuExemplar.apresentarOpcoes();
				break;
			case 0:
				System.out.println("Agradecemos a visita!");					
				uso= false;
				desabilitarTeclado();
				break;
			default:
				System.err.println("\nOpção inválida! Tente novamente.\n");	
			}	
		}
	}

	public static String lerStringTeclado() {

		System.out.print("|> ");

		return entrada.nextLine();
	}

	public static int lerNumeroTeclado() {

		int numero= 0;
		boolean leitura= true;

		while(leitura) {
			try {
				System.out.print("|> ");
				numero= entrada.nextInt(); 
				leitura= false;

			}catch (InputMismatchException e) {
				System.err.print("Informe um valor válido!\n");

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
	
	public static void habilitarTeclado() {

		entrada= new Scanner(System.in);
	}
	
	public static void desabilitarTeclado() {

		entrada.close();
	}
}