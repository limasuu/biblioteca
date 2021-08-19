package bibli.modelo;

import java.util.HashMap;

public class AcervoLivro {

	private static HashMap<String, Livro> livros= new HashMap<String, Livro>();

	public static HashMap<String, Livro> getLivros() {

		return livros;
	}

	public static int getNumeroLivros() {

		return livros.size();
	}

	public static boolean verificarExistenciaLivro(String codigo) {

		return livros.containsKey(codigo);		
	}

	public static boolean verificarExistenciaIsbn(String isbn) {

		for(Livro livro : livros.values()) 
			if(livro.getIsbn().equals(isbn)) 
				return true;

		return false;		
	}

	public static Livro buscarLivro(String codigo) {

		return livros.get(codigo);
	}

	public static HashMap<String, Livro> buscarLivrosPorAutor(String autor){

		HashMap<String, Livro> livrosEncontrados= new HashMap<String, Livro>();

		for(Livro livro : livros.values()) 
			if(livro.getAutor().toLowerCase().contains(autor.toLowerCase())) 
				livrosEncontrados.put(livro.getCodigo(), livro);	

		return livrosEncontrados;
	}

	public static HashMap<String, Livro> buscarLivrosPorTitulo(String titulo){

		HashMap<String, Livro> livrosEncontrados= new HashMap<String, Livro>();

		for(Livro livro : livros.values()) 
			if(livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) 
				livrosEncontrados.put(livro.getCodigo(), livro);	

		return livrosEncontrados;
	}

	public static void adicionarLivro(Livro livro) {

		livros.put(livro.getCodigo(), livro);
	}

	public static Livro editarLivro(Livro livroAtualizado) {

		return livros.replace(livroAtualizado.getCodigo(), livroAtualizado);
	}

	public static Livro removerLivro(String codigo) {

		return livros.remove(codigo);
	}	
}