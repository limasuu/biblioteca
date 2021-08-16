package bibli.aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import bibli.controle.ControladorFuncionario;
import bibli.controle.ControladorPessoa;
import bibli.modelo.AcervoFuncionario;
import bibli.modelo.AcervoPessoa;

public class Principal {

	private static Scanner entrada;

	public static void main(String[] args) {

		//exibirMenu();
		
		ControladorPessoa.adicionarPessoa("Ana de Melo", "Rua das Flores, 2. Bairro Novo.", "912345678", "contato-ana@gmail.com");
		ControladorPessoa.adicionarPessoa("Bruno Souza", "Avenida Primeiro de Janeiro, 100. Bairro Central.", "988881111", "brunosouza@yahoo.com.br");
		ControladorPessoa.adicionarPessoa("Carlos Nogueira", "Estrada Campo Novo, 1021. Bairro Novo", "911223344", "nogueira.carlos@gmail.com");
		ControladorFuncionario.adicionarFuncionario("Eliza Aguiar", "Rua das Pedras, 50. Bairro Central.", "900998877", "eliza2@gmail.com", 1500.35, "Atendente");
		ControladorFuncionario.adicionarFuncionario("Marta da Silva", "Travessa dos Pinheiros, 16. Bairro Central.", "910203040", "martasilva14@gmail.com", 1500.35, "Atendente");
		ControladorFuncionario.adicionarFuncionario("Ulisses Neves", "Rua da Liberdade, 88. Bairro Central.", "911110000", "ulissesn@gmail.com", 1099.99, "Auxiliar de Limpeza");
		
		
		System.out.println("Há " + AcervoPessoa.getNumeroPessoas() + " pessoas cadastradas na biblioteca.");
		ControladorPessoa.exibirPessoas();
		
		System.out.println("\nHá " + AcervoFuncionario.getNumeroFuncionarios() + " funcionários cadastrados na biblioteca.");
		ControladorFuncionario.exibirFuncionarios();
		
		System.out.println("\nAtendentes da biblioteca:");
		ControladorFuncionario.exibirFuncionariosPorCargo("Atendente");
		
		System.out.println("A pessoa P2 está no cadastro da biblioteca? " + AcervoPessoa.verificarExistenciaPessoa("P2"));
		System.out.println("O funcionário F1 está no cadastro da biblioteca? " + AcervoFuncionario.verificarExistenciaFuncionario("F1"));
		
		ControladorPessoa.editarPessoa("P1", "Mariana de Melo", "Rua das Flores, 22. Bairro Novo.", "912345678", "mari-ana@gmail.com");
		ControladorPessoa.exibirPessoa("P1");
				
		ControladorFuncionario.editarFuncionario("F3", "Ulysses Neves", "Rua da Liberdade, 88. Bairro Central.", "911210022", "ulissesn@gmail.com", 1299.99, "Auxiliar de Limpeza");
		ControladorFuncionario.exibirFuncionario("F3");
				
		ControladorFuncionario.removerFuncionario("F1");
		
		System.out.println("\nHá " + AcervoPessoa.getNumeroPessoas() + " pessoas cadastradas na biblioteca.\n");
		ControladorPessoa.exibirPessoas();
		
		System.out.println("O funcionário F1 está no cadastro da biblioteca? " + AcervoFuncionario.verificarExistenciaFuncionario("F1"));
		
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