package bibli.modelo;

import java.util.HashMap;

public class AcervoExemplar {

	private static HashMap<String, Exemplar> exemplares= new HashMap<String, Exemplar>();

	public static HashMap<String, Exemplar> getExemplares() {

		return exemplares;
	}

	public static int getNumeroExemplares() {

		return exemplares.size();
	}

	public static boolean verificarExistenciaExemplar(String codigo) {

		return exemplares.containsKey(codigo);		
	}

	public static boolean verificarSituacaExemplar(String codigoExemplar) {
		
		return exemplares.get(codigoExemplar).isDisponivel();
	}

	public static Exemplar buscarExemplar(String codigo) {

		return exemplares.get(codigo);
	}

	public static HashMap<String, Exemplar> buscarExemplares(String isbn) {

		HashMap<String, Exemplar> exemplaresEncontrados= new HashMap<String, Exemplar>();

		for(Exemplar exemplar : exemplares.values()) 
			if(exemplar.getLivro().getIsbn().equals(isbn)) 
				exemplaresEncontrados.put(exemplar.getCodigo(), exemplar);	

		return exemplaresEncontrados;
	}	
	
	public static void adicionarExemplar(Exemplar exemplar) {

		exemplares.put(exemplar.getCodigo(), exemplar);
	}
	
	public static Exemplar editarExemplar(Exemplar exemplarAtualizado){

		return exemplares.replace(exemplarAtualizado.getCodigo(), exemplarAtualizado);	
	}
	
	public static Exemplar removerExemplar(String codigo){

		return exemplares.remove(codigo);
	}

	public static int removerExemplares(String isbn) {

		HashMap<String, Exemplar> conjuntoExemplares= buscarExemplares(isbn);

		if(!conjuntoExemplares.isEmpty()) 
			for(Exemplar e : conjuntoExemplares.values()) 
				removerExemplar(e.getCodigo());

		return conjuntoExemplares.size();
	}
}
