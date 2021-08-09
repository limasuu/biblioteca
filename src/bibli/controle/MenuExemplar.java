package bibli.controle;

import java.util.HashMap;

import bibli.excecoes.ExcecaoExemplar;
import bibli.modelo.Exemplar;
import bibli.modelo.Livro;

public class MenuExemplar {

	public static void exibirExemplar (String codigo){

		try {
			System.out.println(ControladorExemplar.buscarExemplar(codigo));

		} catch (ExcecaoExemplar e) {
			System.err.println(e.getMessage());
		}	
	}

	public static void exibirExemplaresTotal (){

		if(ControladorExemplar.getNumeroExemplares() == 0)
			System.out.println("Não há exemplares cadastrados.");
		else
			ControladorExemplar.exibirExemplares();
	}

	public static void exibirExemplaresPorLivro (Livro livro){

		HashMap<String, Exemplar> exemplares= ControladorExemplar.buscarExemplares(livro);

		System.out.println("\n----------- Exemplares de \"" + livro.getTitulo() + "\" -----------");
		System.out.println("Quantidade: " + exemplares.size());	
		System.out.println("------------------------------------------");

		for(Exemplar exemplar: exemplares.values())
			System.out.println("\n" + exemplar);	

		System.out.println("------------------------------------------");

	}

	public static boolean adicionarExemplar (Livro livro) {

		boolean saida= false;

		try {
			ControladorExemplar.adicionarExemplar(new Exemplar(livro));
			saida= true;

		} catch (ExcecaoExemplar e) {
			System.err.println(e.getMessage());
		}
		return saida;
	}

	public static boolean editarExemplar (String codigo, Livro livro){

		boolean saida= false;

		try {
			Exemplar e = ControladorExemplar.buscarExemplar(codigo);
			ControladorExemplar.editarExemplar(new Exemplar(e.getCodigo(), livro));

			saida= true;

		} catch (ExcecaoExemplar ee) {
			System.err.println(ee.getMessage());
		}

		return saida;
	}	

	public static void removerExemplares(Livro livro) {

		HashMap<String, Exemplar> conjuntoExemplares= ControladorExemplar.buscarExemplares(livro);

		removerExemplares(conjuntoExemplares);
	}	

	public static void removerExemplares(String isbn) {

		HashMap<String, Exemplar> conjuntoExemplares= ControladorExemplar.buscarExemplares(isbn);

		removerExemplares(conjuntoExemplares);
	}

	private static void removerExemplares(HashMap<String, Exemplar> conjuntoExemplares) {

		if(!conjuntoExemplares.isEmpty()) {

			System.err.println("Será efetuada a remoção do(s) " + conjuntoExemplares.size() + " exemplar(es) vinculado(s) a este livro!\n");
			for(Exemplar e : conjuntoExemplares.values()) {
				try {
					ControladorExemplar.removerExemplar(e);
				} catch (ExcecaoExemplar ee) {
					System.err.println(ee.getMessage());
				}
			}
		}else
			System.out.println("Não há exemplares vinculados a este livro.");		
	}

	public static void removerExemplar(String codigo) {

		try {
			ControladorExemplar.removerExemplar(codigo);

		} catch (ExcecaoExemplar ee) {				
			System.err.println(ee.getMessage());
		}		
	}
}
