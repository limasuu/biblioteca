package bibli.controle;

import java.util.HashMap;

import bibli.modelo.Exemplar;
import bibli.modelo.Livro;

public class MenuExemplar {

	public static boolean exibirExemplar (String codigo){

		if(!ValidadorObra.validarCampo(codigo)) {
			System.err.println("Código inválido.");
			return false;
		}

		if(!ControladorExemplar.verificarExistenciaExemplar(codigo)){
			System.err.println("Não foi encontrado exemplar com o código informado.");
			return false;
		}	

		System.out.println(ControladorExemplar.buscarExemplar(codigo));

		return true;
	}

	public static void exibirExemplaresTotal (){

		ControladorExemplar.exibirExemplares();
	}

	public static boolean exibirExemplaresPorLivro (Livro livro){

		if(!ValidadorObra.validarLivro(livro)) {
			System.err.println("Livro inválido.");
			return false;
		}

		HashMap<String, Exemplar> exemplaresEncontrados= ControladorExemplar.buscarExemplares(livro);		

		if(exemplaresEncontrados.size() == 0) 
			System.err.println("Não há exemplares registrados para o livro informado.");			
		else {

			System.out.println("\n----------- Exemplares de \"" + livro.getTitulo() + "\" -----------");
			System.out.println("Quantidade: " + exemplaresEncontrados.size());	
			System.out.println("------------------------------------------");

			for(Exemplar exemplar: exemplaresEncontrados.values())
				System.out.println("\n" + exemplar);	

			System.out.println("------------------------------------------");
		}

		return true;
	}

	public static boolean adicionarExemplar (Livro livro) {

		if(!ValidadorObra.validarLivro(livro)) {
			System.err.println("Livro inválido.");
			return false;
		}

		ControladorExemplar.adicionarExemplar(new Exemplar(livro));

		return true;
	}

	public static boolean editarExemplar (String codigo, Livro livro){

		if(!ValidadorObra.validarCampo(codigo)) {
			System.err.println("Código inválido.");
			return false;
		}

		if(!ControladorExemplar.verificarExistenciaExemplar(codigo)){
			System.err.println("Não foi encontrado exemplar com o código informado.");
			return false;
		}	

		if(!ValidadorObra.validarLivro(livro)) {
			System.err.println("Livro inválido.");
			return false;
		}

		Exemplar exemplarAtualizado= ValidadorObra.validarAtualizacaoExemplar(codigo, livro);

		if(exemplarAtualizado == null){
			System.err.println("Não há mudanças para realizar.");
			return false;
		}	

		ControladorExemplar.editarExemplar(exemplarAtualizado);

		return true;
	}	

	public static boolean removerExemplares(Livro livro) {
		
		if(!ValidadorObra.validarLivro(livro)) {
			System.err.println("Livro inválido.");
			return false;
		}

		HashMap<String, Exemplar> conjuntoExemplares= ControladorExemplar.buscarExemplares(livro);

		removerExemplares(conjuntoExemplares);
		
		return true;
	}	

	public static boolean removerExemplares(String isbn) {
		
		if(!ValidadorObra.validarCampo(isbn)) {
			System.err.println("ISBN inválido.");
			return false;
		}

		if(!ControladorLivro.verificarExistenciaLivro(isbn)){
			System.err.println("Não foi encontrado livro com o ISBN informado.");
			return false;
		}		

		HashMap<String, Exemplar> conjuntoExemplares= ControladorExemplar.buscarExemplares(isbn);

		removerExemplares(conjuntoExemplares);
		
		return true;
	}

	private static void removerExemplares(HashMap<String, Exemplar> conjuntoExemplares) {

		if(!conjuntoExemplares.isEmpty()) {

			System.err.println("Será efetuada a remoção do(s) " + conjuntoExemplares.size() + " exemplar(es) vinculado(s) a este livro!\n");
			for(Exemplar e : conjuntoExemplares.values()) 
				ControladorExemplar.removerExemplar(e);
		}	
	}

	public static boolean removerExemplar(String codigo) {

		if(!ValidadorObra.validarCampo(codigo)) {
			System.err.println("Código inválido.");
			return false;
		}

		if(!ControladorExemplar.verificarExistenciaExemplar(codigo)){
			System.err.println("Não foi encontrado exemplar com o código informado.");
			return false;
		}	

		ControladorExemplar.removerExemplar(codigo);

		return true;
	}
}
