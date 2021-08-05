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

	public static void adicionarExemplar(Exemplar exemplar) {

		exemplares.put(exemplar.getCodigo(), exemplar);
	}

	public static Exemplar removerExemplar(String codigo){

		return exemplares.remove(codigo);
	}

	public static Exemplar editarExemplar(Exemplar exemplarAtualizado){

		return exemplares.replace(exemplarAtualizado.getCodigo(), exemplarAtualizado);	
	}

	public static Exemplar buscarExemplar(String codigo) {

		return exemplares.get(codigo);
	}

	public static HashMap<String, Exemplar> buscarExemplares(Livro livro) {

		HashMap<String, Exemplar> exemplaresEncontrados= new HashMap<String, Exemplar>();

		for(Exemplar exemplar : exemplares.values()) 
			if(exemplar.getLivro().equals(livro)) 
				exemplaresEncontrados.put(exemplar.getCodigo(), exemplar);	

		return exemplaresEncontrados;
	}	

}
