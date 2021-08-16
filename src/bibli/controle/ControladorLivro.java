package bibli.controle;

import java.util.HashMap;

import bibli.modelo.AcervoExemplar;
import bibli.modelo.AcervoLivro;
import bibli.modelo.Livro;

public class ControladorLivro {
	
	public static boolean exibirLivro (String isbn){

		if(!ValidadorObra.validarCampo(isbn)) {
			System.err.println("ISBN inválido.");
			return false;
		}

		if(!AcervoLivro.verificarExistenciaLivro(isbn)){
			System.err.println("\nNão foi encontrado livro com o ISBN informado.");
			return false;
		}	

		System.out.println("\n" + AcervoLivro.buscarLivro(isbn));

		return true;
	}
	
	public static void exibirLivros() {

		if(AcervoLivro.getNumeroLivros() == 0)
			System.out.println("Não há livros cadastrados.");
		else {

			System.out.println("\n----------------- Livros -----------------");
			System.out.println("Quantidade: " + AcervoLivro.getNumeroLivros());	
			System.out.println("------------------------------------------");

			for(Livro livro : AcervoLivro.getLivros().values())
				System.out.println(livro+"\n");

			System.out.println("------------------------------------------");
		}
	}

	public static boolean exibirLivrosPorAutor(String autor){

		if(!ValidadorObra.validarCampo(autor)) {
			System.err.println("Nome de autor inválido.");
			return false;
		}

		HashMap<String, Livro> livrosEncontrados= AcervoLivro.buscarLivrosPorAutor(autor);

		if(livrosEncontrados.size() == 0) 
			System.err.println("Nenhum livro encontrado.");			
		else {

			System.out.println("\n----------- Livros de \"" + autor + "\" -----------");
			System.out.println("Quantidade: " + livrosEncontrados.size());	
			System.out.println("------------------------------------------");

			for(Livro livro: livrosEncontrados.values())
				System.out.println("\n" + livro);	

			System.out.println("------------------------------------------");
		}

		return true;		
	}

	public static boolean exibirLivrosPorTitulo(String titulo) {

		if(!ValidadorObra.validarCampo(titulo)) {
			System.err.println("Nome de título inválido.");
			return false;
		}

		HashMap<String, Livro> livrosEncontrados= AcervoLivro.buscarLivrosPorTitulo(titulo);

		if(livrosEncontrados.size() == 0) 
			System.out.println("Nenhum livro encontrado.");
		else{

			System.out.println("\n-------- Livros com o título \"" + titulo + "\" --------");
			System.out.println("Quantidade: " + livrosEncontrados.size());	
			System.out.println("------------------------------------------");

			for(Livro livro: livrosEncontrados.values())
				System.out.println("\n" + livro);			

			System.out.println("------------------------------------------");
		} 

		return true;
	}

	public static boolean adicionarLivro(String titulo, String autor, 
			int edicao, String editora, int numeroPaginas,
			String isbn, String categoria) {

		if(!ValidadorObra.validarCamposLivro(titulo, autor, edicao, editora,
				numeroPaginas, isbn, categoria)){
			System.err.println("Há campos inválidos.");
			return false;
		}

		if(AcervoLivro.verificarExistenciaLivro(isbn)){
			System.err.println("Há um livro com o ISBN informado no sistema.");
			return false;
		}

		Livro livro= new Livro(titulo, autor, edicao, editora, numeroPaginas, isbn, categoria);
		AcervoLivro.adicionarLivro(livro);

		return true;
	}	

	public static boolean editarLivro(String novoTitulo, String novoAutor, 
			int novaEdicao, String novaEditora, int novoNumeroPaginas,
			String isbn, String novaCategoria) {

		if(!ValidadorObra.validarCamposLivro(novoTitulo, novoAutor, novaEdicao, novaEditora,
				novoNumeroPaginas, isbn, novaCategoria)) {
			System.err.println("Há campos inválidos.");
			return false;
		}

		if(!AcervoLivro.verificarExistenciaLivro(isbn)){
			System.err.println("Não foi encontrado livro com o ISBN informado.");
			return false;
		}		

		Livro livroAtualizado= ValidadorObra.validarAtualizacaoLivro(novoTitulo, novoAutor,
				novaEdicao, novaEditora, novoNumeroPaginas, isbn, novaCategoria);

		if(livroAtualizado == null){
			System.err.println("\nNão há mudanças para realizar.");
			return false;
		}	

		ControladorExemplar.editarExemplares(livroAtualizado);
		AcervoLivro.editarLivro(livroAtualizado);	

		return true;
	}

	public static String removerLivro(String isbn) {

		if(!ValidadorObra.validarCampo(isbn)) {
			System.err.println("ISBN inválido.");
			return null;
		}

		if(!AcervoLivro.verificarExistenciaLivro(isbn)){
			System.err.println("Não foi encontrado livro com o ISBN informado.");
			return null;
		}		

		String titulo= AcervoLivro.buscarLivro(isbn).getTitulo();
		AcervoExemplar.removerExemplares(isbn);
		AcervoLivro.removerLivro(isbn);

		return titulo;
	}	
}
