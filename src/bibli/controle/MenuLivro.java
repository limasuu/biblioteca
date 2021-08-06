package bibli.controle;

import java.util.HashMap;

import bibli.excecoes.ExcecaoExemplar;
import bibli.excecoes.ExcecaoLivro;
import bibli.modelo.Exemplar;
import bibli.modelo.Livro;

public class MenuLivro {

	public static void exibirLivro (String isbn){

		try {
			System.out.println(ControladorLivro.buscarLivro(isbn));
			
		} catch (ExcecaoLivro e) {			
			System.err.println(e.getMessage());
		}
	}
	
	public static void exibirLivrosTotal (){

		try {
			ControladorLivro.exibirLivros();
			
		} catch (ExcecaoLivro e) {
			System.err.println(e.getMessage());
		}		
	}
	
	public static void exibirLivrosPorAutor (String autor){
		
		try {			
			HashMap<String, Livro> livros= ControladorLivro.buscarLivrosPorAutor(autor);
			
			System.out.println("\n----------- Livros de \"" + autor + "\" -----------");
			System.out.println("Quantidade: " + livros.size());	
			System.out.println("------------------------------------------");
			
			for(Livro livro: livros.values())
				System.out.println("\n" + livro);	
			
			System.out.println("------------------------------------------");
			
		} catch (ExcecaoLivro e) {
			System.err.println(e.getMessage());
		}			
	}
	
	public static void exibirLivrosPorTitulo (String titulo){
		
		try {
			HashMap<String, Livro> livros= ControladorLivro.buscarLivrosPorTitulo(titulo);
			
			System.out.println("\n-------- Livros com o título \"" + titulo + "\" --------");
			System.out.println("Quantidade: " + livros.size());	
			System.out.println("------------------------------------------");
			
			for(Livro livro: livros.values())
				System.out.println("\n" + livro);			
			
			System.out.println("------------------------------------------");
			
		} catch (ExcecaoLivro e) {
			System.err.println(e.getMessage());
		}	
	}
	
	public static boolean adicionarLivro (String titulo, String autor, 
			int edicao, String editora, int numeroPaginas,
			String isbn, String categoria){

		if(titulo == null || autor == null || 
				edicao < 1 || editora == null ||
				numeroPaginas < 1 || isbn == null ||
				categoria == null) {
			System.err.println("Há campos nulos.");
			return false;
			
		}else {
			Livro livro= new Livro(titulo, autor, edicao, editora, numeroPaginas, isbn, categoria);

			try {
				ControladorLivro.adicionarLivro(livro);

			} catch (ExcecaoLivro e) {			
				System.err.println(e.getMessage());
				return false;
			}
		}
		return true;
	}
	
	public static boolean editarLivro (String novoTitulo, String novoAutor, 
			int novaEdicao, String novaEditora, int novoNumeroPaginas,
			String isbn, String novaCategoria){

		try {
			Livro livro = ControladorLivro.buscarLivro(isbn);
			
			boolean mudancas= false;
			
			if(!livro.getTitulo().equals(novoTitulo)) {
				livro.setTitulo(novoTitulo);
				mudancas= true;
			}
			if(!livro.getAutor().equals(novoAutor)) {
				livro.setAutor(novoAutor);
				mudancas= true;
			}
			if(!livro.getEditora().equals(novaEditora)) {
				livro.setEditora(novaEditora);
				mudancas= true;
			}
			if(!livro.getCategoria().equals(novaCategoria)) {
				livro.setCategoria(novaCategoria);
				mudancas= true;
			}
			if(livro.getEdicao() != novaEdicao) {
				livro.setEdicao(novaEdicao);
				mudancas= true;
			}
			if(livro.getNumeroPaginas() != novoNumeroPaginas) {
				livro.setNumeroPaginas(novoNumeroPaginas);
				mudancas= true;
			}
			
			if(mudancas) {					
				try {
					HashMap<String, Exemplar> conjuntoExemplares= ControladorExemplar.buscarExemplares(livro);
					
					ControladorLivro.editarLivro(livro);				
					for(Exemplar e : conjuntoExemplares.values())
						ControladorExemplar.editarExemplar(new Exemplar(e.getCodigo(), livro));
					
				} catch (ExcecaoExemplar e) {
					
					ControladorLivro.editarLivro(livro);
					
				}	
				return true;
			}else
				System.err.println("Não há mudanças para realizar.");
			
		} catch (ExcecaoLivro e) {
			System.err.println(e.getMessage());
		}
		return false;
	}	
}
