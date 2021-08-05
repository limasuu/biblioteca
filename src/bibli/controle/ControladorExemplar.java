package bibli.controle;

import java.util.HashMap;

import bibli.modelo.Exemplar;
import bibli.modelo.Livro;

public class ControladorExemplar {
	
	private static HashMap<String, Exemplar> exemplares= new HashMap<String, Exemplar>();
	
	public static HashMap<String, Exemplar> getExemplares() {
		
		return exemplares;
	}
	
	public static int getNumeroExemplares() {
		
		return exemplares.size();
	}

	public static void exibirExemplares() {

		System.out.println("----------------- Exemplares -----------------");
		for(Exemplar exemplar : exemplares.values())
			System.out.println(exemplar+"\n");
	}
	
	public static void adicionarExemplar(Exemplar exemplar) throws Exception {

		if(exemplar == null)
			throw new Exception("Exemplar nulo.");
		
		if(exemplares.containsValue(exemplar)) 
			throw new Exception("Exemplar já cadastrado.");

		exemplares.put(exemplar.getCodigo(), exemplar);
	}
	
	public static void removerExemplar(Exemplar exemplar) throws Exception {

		if(exemplar == null)
			throw new Exception("Exemplar nulo.");	
		
		Exemplar e= exemplares.remove(exemplar.getCodigo());
		
		if(e == null) 
			throw new Exception("Exemplar não cadastrado.");
	}

	public static void removerExemplar(String codigo) throws Exception {

		if(codigo == null)
			throw new Exception("Código nulo.");	
		
		Exemplar e= exemplares.remove(codigo);
		
		if(e == null) 
			throw new Exception("Não foi encontrado exemplar com o código informado.");
	}

	public static void editarExemplar(Exemplar exemplar, Exemplar exemplarAtualizado) throws Exception {

		if(exemplar == null || exemplarAtualizado == null)
			throw new Exception("Exemplar nulo.");

		Exemplar e= exemplares.replace(exemplar.getCodigo(), exemplarAtualizado);		
		
		if(e == null)
			throw new Exception("Exemplar não cadastrado.");		
	}
	
	public static Exemplar buscarExemplar(String codigo) throws Exception{

		Exemplar exemplar=  exemplares.get(codigo);
		
		if(exemplar == null)
			throw new Exception("Exemplar não encontrado.");

		return exemplar;
	}
	
	public static HashMap<String, Exemplar> buscarExemplares(Livro livro) throws Exception{

		HashMap<String, Exemplar> exemplaresEncontrados= new HashMap<String, Exemplar>();

		for(Exemplar exemplar : exemplares.values()) 
			if(exemplar.getLivro().equals(livro)) 
				exemplaresEncontrados.put(exemplar.getCodigo(), exemplar);	

		if(exemplaresEncontrados.size() == 0)
			throw new Exception("Nenhum exemplar encontrado.");

		return exemplaresEncontrados;
	}	
}
