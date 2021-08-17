package bibli.controle;

import java.util.HashMap;

import bibli.modelo.AcervoEmprestimo;
import bibli.modelo.AcervoExemplar;
import bibli.modelo.AcervoFuncionario;
import bibli.modelo.AcervoPessoa;
import bibli.modelo.Emprestimo;
import bibli.modelo.Exemplar;
import bibli.modelo.Funcionario;
import bibli.modelo.Pessoa;

public class ControladorEmprestimo {

	public static boolean exibirEmprestimo (String codigo){

		if(!ValidadorEmprestimo.validarCampo(codigo)) {
			System.err.println("Código inválido.");
			return false;
		}

		if(!AcervoEmprestimo.verificarExistenciaEmprestimo(codigo)){
			System.err.println("\nNão foi encontrado empréstimo com o código informado.");
			return false;
		}	

		System.out.println("\n" + AcervoEmprestimo.buscarEmprestimo(codigo));

		return true;
	}

	public static void exibirEmprestimos() {

		if(AcervoEmprestimo.getNumeroEmprestimos() == 0)
			System.out.println("Não há empréstimos cadastrados.");
		else {

			System.out.println("\n----------------- Empréstimos -----------------");
			System.out.println("Quantidade: " + AcervoEmprestimo.getNumeroEmprestimos());	
			System.out.println("------------------------------------------");

			for(Emprestimo emprestimo : AcervoEmprestimo.getEmprestimos().values())
				System.out.println(emprestimo+"\n");

			System.out.println("------------------------------------------");
		}
	}

	public static boolean exibirEmprestimosPorLivro(String isbn){

		if(!ValidadorEmprestimo.validarCampo(isbn)) {
			System.err.println("ISBN inválido.");
			return false;
		}

		HashMap<String, Emprestimo> emprestimosEncontrados= AcervoEmprestimo.buscarEmprestimosPorLivro(isbn);

		if(emprestimosEncontrados.size() == 0) 
			System.err.println("Nenhum empréstimo encontrado.");			
		else {

			System.out.println("\n----------- Empréstimos do livro \"" + isbn + "\" -----------");
			System.out.println("Quantidade: " + emprestimosEncontrados.size());	
			System.out.println("-------------------------------------------------------------");

			for(Emprestimo emprestimo: emprestimosEncontrados.values())
				System.out.println("\n" + emprestimo);	

			System.out.println("-------------------------------------------------------------");
		}

		return true;		
	}

	public static boolean exibirEmprestimosPorUsuario(String codigo) {

		if(!ValidadorEmprestimo.validarCampo(codigo)) {
			System.err.println("Código inválido.");
			return false;
		}

		HashMap<String, Emprestimo> emprestimosEncontrados= AcervoEmprestimo.buscarEmprestimosPorUsuario(codigo);

		if(emprestimosEncontrados.size() == 0) 
			System.err.println("Nenhum empréstimo encontrado.");
		else{

			System.out.println("\n----------- Empréstimos do usuário \"" + codigo + "\" -----------");
			System.out.println("Quantidade: " + emprestimosEncontrados.size());	
			System.out.println("-------------------------------------------------------------");

			for(Emprestimo emprestimo: emprestimosEncontrados.values())
				System.out.println("\n" + emprestimo);	

			System.out.println("-------------------------------------------------------------");
		}

		return true;		
	}

	public static boolean adicionarEmprestimo(String matriculaFuncionario, String codigoPessoa, 
			String codigoExemplar) {

		if(!ValidadorEmprestimo.validarCamposEmprestimo(matriculaFuncionario, codigoPessoa, codigoExemplar)){
			System.err.println("Há campos inválidos.");
			return false;
		}

		if(!AcervoFuncionario.verificarExistenciaFuncionario(matriculaFuncionario)){
			System.err.println("Não foi encontrado funcionário com a matrícula informada.");
			return false;
		}

		if(!AcervoPessoa.verificarExistenciaPessoa(codigoPessoa)){
			System.err.println("Não foi encontrada pessoa com o código informado.");
			return false;
		}	

		if(!AcervoExemplar.verificarExistenciaExemplar(codigoExemplar)){
			System.err.println("Não foi encontrado exemplar com o código informado.");
			return false;
		}	

		Funcionario funcionario= AcervoFuncionario.buscarFuncionario(matriculaFuncionario);
		Pessoa pessoa= AcervoPessoa.buscarPessoa(codigoPessoa);
		Exemplar exemplar= AcervoExemplar.buscarExemplar(codigoExemplar);

		Emprestimo emprestimo= new Emprestimo(funcionario, pessoa, exemplar);
		AcervoEmprestimo.adicionarEmprestimo(emprestimo);

		return true;
	}	

	public static boolean renovarEmprestimo(String codigo) {

	

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return true;
	}

	public static boolean encerrarEmprestimo(String codigo) {

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		return true;
	}

	public static boolean removerEmprestimo(String codigo){

		if(!ValidadorEmprestimo.validarCampo(codigo)) {
			System.err.println("\nCódigo inválido.");
			return false;
		}

		if(!AcervoEmprestimo.verificarExistenciaEmprestimo(codigo)){
			System.err.println("\nNão foi encontrado empréstimo com o código informado.");
			return false;
		}	

		AcervoEmprestimo.removerEmprestimo(codigo);

		return true;
	}	
}
