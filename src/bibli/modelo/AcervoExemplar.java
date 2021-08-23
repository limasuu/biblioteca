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

	public static boolean verificarExistencia(String codigo) {

		return exemplares.containsKey(codigo);		
	}

	public static boolean verificarDisponibilidade(String codigoExemplar) {
		
		return exemplares.get(codigoExemplar).isDisponivel();
	}
	
	public static boolean verificarExemplaresIndisponiveisLivro(String codigo){ 

		int quantidade= 0;

		for(Exemplar exemplar : exemplares.values()) 
			if(!exemplar.isDisponivel() &&
					exemplar.getLivro().getCodigo().equalsIgnoreCase(codigo)) 
				quantidade++;	

		return quantidade > 0;
	}

	public static Exemplar buscarExemplar(String codigo) {

		return exemplares.get(codigo);
	}

	public static HashMap<String, Exemplar> buscarExemplares(String codigo) {

		HashMap<String, Exemplar> exemplaresEncontrados= new HashMap<String, Exemplar>();

		for(Exemplar exemplar : exemplares.values()) 
			if(exemplar.getLivro().getCodigo().equals(codigo)) 
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

	public static int removerExemplares(String codigo) {

		HashMap<String, Exemplar> conjuntoExemplares= buscarExemplares(codigo);

		if(!conjuntoExemplares.isEmpty()) 
			for(Exemplar e : conjuntoExemplares.values()) 
				removerExemplar(e.getCodigo());

		return conjuntoExemplares.size();
	}
}
