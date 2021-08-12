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

	public static boolean verificarExistenciaLivro(String isbn) {

		return livros.containsKey(isbn);		
	}
	
	public static Livro buscarLivro(String isbn) {

		return livros.get(isbn);
	}

	public static HashMap<String, Livro> buscarLivrosPorAutor(String autor){

		HashMap<String, Livro> livrosEncontrados= new HashMap<String, Livro>();

		for(Livro livro : livros.values()) 
			if(livro.getAutor().toLowerCase().contains(autor.toLowerCase())) 
				livrosEncontrados.put(livro.getIsbn(), livro);	

		return livrosEncontrados;
	}

	public static HashMap<String, Livro> buscarLivrosPorTitulo(String titulo){

		HashMap<String, Livro> livrosEncontrados= new HashMap<String, Livro>();

		for(Livro livro : livros.values()) 
			if(livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) 
				livrosEncontrados.put(livro.getIsbn(), livro);	

		return livrosEncontrados;
	}

	public static void adicionarLivro(Livro livro) {

		livros.put(livro.getIsbn(), livro);
	}
	
	public static Livro editarLivro(Livro livroAtualizado) {

		return livros.replace(livroAtualizado.getIsbn(), livroAtualizado);
	}


	public static Livro removerLivro(String isbn) {

		return livros.remove(isbn);
	}	
}