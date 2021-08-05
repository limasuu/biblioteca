package bibli.controle;

import java.util.ArrayList;
import java.util.Collections;

import bibli.modelo.Exemplar;
import bibli.modelo.Livro;

public class ControladorExemplar {
	
	private static ArrayList<Exemplar> exemplares= new ArrayList<Exemplar>();
	
	public static ArrayList<Exemplar> getExemplares() {
		
		return exemplares;
	}
	
	public static int getNumeroExemplares() {
		
		return exemplares.size();
	}

	public static void exibirExemplares() {

		System.out.println("----------------- Exemplares -----------------");
		for(Exemplar exemplar : exemplares)
			System.out.println(exemplar+"\n");
	}
	
	public static void ordenarExemplares() {

		Collections.sort(exemplares);
	}
	
	public static void adicionarExemplar(Exemplar exemplar) throws Exception {

		if(exemplar == null)
			throw new Exception("Exemplar nulo.");
		
		if(exemplares.contains(exemplar)) 
			throw new Exception("Exemplar já cadastrado.");

		exemplares.add(exemplar);
	}
	
	public static void removerExemplar(Exemplar exemplar) throws Exception {

		if(exemplar == null)
			throw new Exception("Exemplar nulo.");		
		
		if(!exemplares.contains(exemplar)) 
			throw new Exception("Exemplar não cadastrado.");

		exemplares.remove(exemplar);
	}

	public static void removerExemplar(String codigo) throws Exception {

		Exemplar exemplar= null;

		for(Exemplar e : exemplares) {
			if(e.getCodigo().equals(codigo)) {				
				exemplar= e;	
				exemplares.remove(exemplar);
				break;
			}
		}

		if(exemplar == null)
			throw new Exception("Não foi encontrado exemplar com o código informado.");
	}

	public static void editarExemplar(Exemplar exemplar, Exemplar exemplarAtualizado) throws Exception {

		if(exemplar == null || exemplarAtualizado == null)
			throw new Exception("Exemplar nulo.");

		int indice= exemplares.indexOf(exemplar);
		
		if(indice == -1)
			throw new Exception("Exemplar não cadastrado.");
		else
			exemplares.set(indice, exemplarAtualizado);	
		
	}
	
	public static Exemplar buscarExemplar(String codigo) throws Exception{

		Exemplar exemplar= null;

		for(Exemplar e : exemplares) {
			if(e.getCodigo().equals(codigo)) {
				exemplar= e;
				break;
			}
		}

		if(exemplar == null)
			throw new Exception("Exemplar não encontrado.");

		return exemplar;
	}
	
	public static ArrayList<Exemplar> buscarExemplares(Livro livro) throws Exception{

		ArrayList<Exemplar> exemplaresEncontrados= new ArrayList<Exemplar>();

		for(Exemplar exemplar : exemplares)
			if(exemplar.getLivro().equals(livro))
				exemplaresEncontrados.add(exemplar);	

		if(exemplaresEncontrados.size() == 0)
			throw new Exception("Nenhum exemplar encontrado.");

		return exemplaresEncontrados;
	}

	
}
