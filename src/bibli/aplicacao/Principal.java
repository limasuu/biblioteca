package bibli.aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import bibli.controle.ControladorEmprestimo;
import bibli.controle.ControladorExemplar;
import bibli.controle.ControladorFuncionario;
import bibli.controle.ControladorLivro;
import bibli.controle.ControladorPessoa;
import bibli.modelo.AcervoEmprestimo;
import bibli.modelo.Emprestimo;
import bibli.modelo.Exemplar;
import bibli.modelo.Funcionario;
import bibli.modelo.Livro;
import bibli.modelo.Pessoa;

public class Principal {

	private static Scanner entrada;

	public static void main(String[] args) {

	//	exibirMenu();
		
		ControladorPessoa.adicionarPessoa("Carlos Nogueira", "Estrada Campo Novo, 1021. Bairro Novo", "911223344", "nogueira.carlos@gmail.com");
		ControladorPessoa.adicionarPessoa("Ana de Melo", "Rua das Flores, 2. Bairro Novo.", "912345678", "contato-ana@gmail.com");		
		
		ControladorFuncionario.adicionarFuncionario("Eliza Aguiar", "Rua das Pedras, 50. Bairro Central.", "900998877", "eliza2@gmail.com", 1500.35, "Atendente");
	
		ControladorLivro.adicionarLivro("Polianna", "Eleanor Porter", 1, "Pé de Letra", 184, "978-8595201170", "Clássico");
		ControladorLivro.adicionarLivro("O Menino do Pijama Listrado", "John Boine", 1, "Seguinte", 192, "9788535911121", "Ficção infantil");
		
		ControladorExemplar.adicionarExemplar("978-8595201170");
		ControladorExemplar.adicionarExemplar("978-8595201170");
		ControladorExemplar.adicionarExemplar("9788535911121");
		
		ControladorEmprestimo.adicionarEmprestimo("F1", "P1", "EX1");
		ControladorEmprestimo.adicionarEmprestimo("F1", "P2", "EX2");
		ControladorEmprestimo.adicionarEmprestimo("F1", "P2", "EX3");
		
		ControladorEmprestimo.exibirEmprestimos();
		
		ControladorEmprestimo.exibirEmprestimo("EM2");
		
		ControladorEmprestimo.exibirEmprestimosPorLivro("978-8595201170");
		
		ControladorEmprestimo.exibirEmprestimosPorUsuario("P2");
		
	//	ControladorEmprestimo.renovarEmprestimo();
		
	//	ControladorEmprestimo.encerrarEmprestimo();
		
		ControladorEmprestimo.removerEmprestimo("EM1");
		
		ControladorEmprestimo.exibirEmprestimos();		
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
			System.out.println("3. Menu Usuários");
			System.out.println("4. Menu Funcionários");
			System.out.println("5. Menu Empréstimos");
			System.out.println("0. Sair");
			System.out.println("----------------------------------------------------");

			int opcao= lerInteiroTeclado();

			switch(opcao) {

			case 1:
				MenuLivro.apresentarOpcoes();
				break;
			case 2:
				MenuExemplar.apresentarOpcoes();
				break;
			case 3:
				MenuPessoa.apresentarOpcoes();
				break;
			case 4:
				MenuFuncionario.apresentarOpcoes();
				break;
			case 5:
//				MenuEmprestimo.apresentarOpcoes();
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

	public static int lerInteiroTeclado() {

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
	
	public static double lerRealTeclado() {

		double numero= 0;
		boolean leitura= true;

		while(leitura) {
			try {
				System.out.print("|> ");
				numero= entrada.nextDouble(); 
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