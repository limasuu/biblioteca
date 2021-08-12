package bibli.controle;

import java.util.HashMap;

import bibli.modelo.AcervoExemplar;
import bibli.modelo.AcervoLivro;
import bibli.modelo.Exemplar;
import bibli.modelo.Livro;

public class ControladorExemplar {

	public static HashMap<String, Exemplar> getExemplares() {

		return AcervoExemplar.getExemplares();
	}

	public static int getNumeroExemplares() {

		return AcervoExemplar.getNumeroExemplares();
	}

	public static int getNumeroExemplares(String isbn) {

		return AcervoExemplar.buscarExemplares(isbn).size();
	}

	public static boolean exibirExemplar(String codigo) {
		
		if(!ValidadorObra.validarCampo(codigo)) {
			System.err.println("Código inválido.");
			return false;
		}

		if(!AcervoExemplar.verificarExistenciaExemplar(codigo)){
			System.err.println("Não foi encontrado exemplar com o código informado.");
			return false;
		}	

		System.out.println(AcervoExemplar.buscarExemplar(codigo));

		return true;
	}
	
	public static void exibirExemplares() {

		if(AcervoExemplar.getNumeroExemplares() == 0)
			System.out.println("Não há exemplares cadastrados.");
		else {

			System.out.println("\n----------------- Exemplares -----------------");
			System.out.println("Quantidade: " + AcervoExemplar.getNumeroExemplares());	
			System.out.println("------------------------------------------");
			
			for(Exemplar exemplar : AcervoExemplar.getExemplares().values())
				System.out.println(exemplar+"\n");
			
			System.out.println("------------------------------------------");
		}
	}	

	public static boolean exibirExemplaresPorLivro(String isbn){

		if(!ValidadorObra.validarCampo(isbn)) {
			System.err.println("ISBN inválido.");
			return false;
		}

		HashMap<String, Exemplar> exemplaresEncontrados= AcervoExemplar.buscarExemplares(isbn);		

		if(exemplaresEncontrados.size() == 0) 
			System.err.println("Não há exemplares registrados para o livro informado.");			
		else {

			System.out.println("\n----------- Exemplares com ISBN \"" + isbn + "\" -----------");
			System.out.println("Quantidade: " + exemplaresEncontrados.size());	
			System.out.println("------------------------------------------");

			for(Exemplar exemplar: exemplaresEncontrados.values())
				System.out.println("\n" + exemplar);	

			System.out.println("------------------------------------------");
		}

		return true;
	}	

	public static boolean adicionarExemplar(String isbn) {

		if(!ValidadorObra.validarCampo(isbn)) {
			System.err.println("ISBN inválido.");
			return false;
		}

		if(!AcervoLivro.verificarExistenciaLivro(isbn)){
			System.err.println("Não foi encontrado livro com o ISBN informado.");
			return false;
		}	
		
		Livro livro= AcervoLivro.buscarLivro(isbn);
		Exemplar exemplar= new Exemplar(livro);
		
		AcervoExemplar.adicionarExemplar(exemplar);
		System.out.println("\nCódigo do exemplar: " + exemplar.getCodigo());

		return true;
	}
	
	public static boolean editarExemplares(Livro livroAtualizado)  {
		
		if(!ValidadorObra.validarLivro(livroAtualizado))
			return false;

		HashMap<String, Exemplar> conjuntoExemplares= AcervoExemplar.buscarExemplares(livroAtualizado.getIsbn());

		if(!conjuntoExemplares.isEmpty()) {

			System.err.print("\nSerá efetuada a edição do(s) " + conjuntoExemplares.size() + " exemplar(es) vinculado(s) a este livro!");
			for(Exemplar e : conjuntoExemplares.values()) 
				e.setLivro(livroAtualizado);				
		}	
		
		return true;
	}
	
	public static boolean removerExemplar(String codigo) {

		if(!ValidadorObra.validarCampo(codigo)) {
			System.err.println("Código inválido.");
			return false;
		}

		if(!AcervoExemplar.verificarExistenciaExemplar(codigo)){
			System.err.println("Não foi encontrado exemplar com o código informado.");
			return false;
		}		

		AcervoExemplar.removerExemplar(codigo);

		return true;
	}

	public static boolean removerExemplares(String isbn) {

		if(!ValidadorObra.validarCampo(isbn)) {
			System.err.println("ISBN inválido.");
			return false;
		}

		if(!AcervoLivro.verificarExistenciaLivro(isbn)){
			System.err.println("Não foi encontrado livro com o ISBN informado.");
			return false;
		}		

		int numeroExemplares= AcervoExemplar.removerExemplares(isbn);

		if(numeroExemplares == 0) {
			System.out.println("Não há exemplares vinculados a este livro.");
			return false;
		}

		return true;
	}
}
