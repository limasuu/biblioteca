package bibli.controle;

import java.util.ArrayList;
import java.util.Collections;

import bibli.modelo.Livro;

public class ControladorLivro {

	private static ArrayList<Livro> livros= new ArrayList<Livro>();

	public static ArrayList<Livro> getLivros() {
		
		return livros;
	}

	public static int getNumeroLivros() {
		
		return livros.size();
	}

	public static void exibirLivros() {

		if(livros.size() == 0)
			System.out.println("Não há livros cadastrados.");
		else {
			System.out.println("----------------- Livros -----------------");
			for(Livro livro : livros)
				System.out.println(livro+"\n");
		}
	}
	
	public static void ordenarLivros() {

		Collections.sort(livros);
	}

	public static void adicionarLivro(Livro livro) throws Exception {

		if(livro == null)
			throw new Exception("Livro nulo.");
		
		if(livros.contains(livro)) 
			throw new Exception("Livro já cadastrado.");

		livros.add(livro);
	}
	
	public static void removerLivro(Livro livro) throws Exception {

		if(livro == null)
			throw new Exception("Livro nulo.");
		
		if(!livros.contains(livro)) 
			throw new Exception("Livro não cadastrado.");

		livros.remove(livro);
	}
	
	public static void editarLivro(Livro livro, Livro livroAtualizado) throws Exception {

		if(livro == null)
			throw new Exception("Livro nulo.");

		int indice= livros.indexOf(livro);
		
		if(indice == -1)
			throw new Exception("Livro não cadastrado.");
		else
			livros.set(indice, livroAtualizado);			
	}
	
	public static Livro buscarLivro(String isbn) throws Exception{

		Livro livro= null;

		for(Livro l : livros) {
			if(l.getIsbn().equals(isbn)) {
				livro= l;
				break;
			}
		}

		if(livro == null)
			throw new Exception("Livro não encontrado.");

		return livro;
	}
	
	public static ArrayList<Livro> buscarLivrosPorAutor(String autor) throws Exception{

		ArrayList<Livro> livrosEncontrados= new ArrayList<Livro>();

		for(Livro livro : livros) 
			if(livro.getAutor().equals(autor)) 
				livrosEncontrados.add(livro);	

		if(livrosEncontrados.size() == 0)
			throw new Exception("Nenhum livro encontrado.");

		return livrosEncontrados;
	}
	
	public static ArrayList<Livro> buscarLivrosPorTitulo(String titulo) throws Exception{

		ArrayList<Livro> livrosEncontrados= new ArrayList<Livro>();

		for(Livro livro : livros)
			if(livro.getTitulo().equals(titulo)) 
				livrosEncontrados.add(livro);	
				
		if(livrosEncontrados.size() == 0)
			throw new Exception("Nenhum livro encontrado.");

		return livrosEncontrados;
	}
}
