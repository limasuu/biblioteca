package bibli.controle;

import java.util.HashMap;

import bibli.modelo.Livro;

public class MenuLivro {

	public static boolean exibirLivro (String isbn){

		if(!ValidadorObra.validarCampo(isbn)) {
			System.err.println("ISBN inválido.");
			return false;
		}
		
		if(!ControladorLivro.verificarExistenciaLivro(isbn)){
			System.err.println("Não foi encontrado livro com o ISBN informado.");
			return false;
		}	

		System.out.println(ControladorLivro.buscarLivro(isbn));

		return true;
	}

	public static void exibirLivrosTotal (){

		ControladorLivro.exibirLivros();
	}

	public static boolean exibirLivrosPorAutor (String autor){

		if(!ValidadorObra.validarCampo(autor)) {
			System.err.println("Nome de autor inválido.");
			return false;
		}

		HashMap<String, Livro> livrosEncontrados= ControladorLivro.buscarLivrosPorAutor(autor);

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

	public static boolean exibirLivrosPorTitulo (String titulo){

		if(!ValidadorObra.validarCampo(titulo)) {
			System.err.println("Nome de título inválido.");
			return false;
		}

		HashMap<String, Livro> livrosEncontrados= ControladorLivro.buscarLivrosPorTitulo(titulo);

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

	public static boolean adicionarLivro (String titulo, String autor, 
			int edicao, String editora, int numeroPaginas,
			String isbn, String categoria){

		if(!ValidadorObra.validarCamposLivro(titulo, autor, edicao, editora,
				numeroPaginas, isbn, categoria)){
			System.err.println("Há campos inválidos.");
			return false;
		}

		if(ControladorLivro.verificarExistenciaLivro(isbn)){
			System.err.println("Há um livro com o ISBN informado no sistema.");
			return false;
		}

		Livro livro= new Livro(titulo, autor, edicao, editora, numeroPaginas, isbn, categoria);
		ControladorLivro.adicionarLivro(livro);

		return true;
	}

	public static boolean editarLivro (String novoTitulo, String novoAutor, 
			int novaEdicao, String novaEditora, int novoNumeroPaginas,
			String isbn, String novaCategoria){

		if(!ValidadorObra.validarCamposLivro(novoTitulo, novoAutor, novaEdicao, novaEditora,
				novoNumeroPaginas, isbn, novaCategoria)) {
			System.err.println("Há campos inválidos.");
			return false;
		}

		if(!ControladorLivro.verificarExistenciaLivro(isbn)){
			System.err.println("Não foi encontrado livro com o ISBN informado.");
			return false;
		}		

		Livro livroAtualizado= ValidadorObra.validarAtualizacaoLivro(novoTitulo, novoAutor,
				novaEdicao, novaEditora, novoNumeroPaginas, isbn, novaCategoria);

		if(livroAtualizado == null){
			System.err.println("Não há mudanças para realizar.");
			return false;
		}	

		ControladorExemplar.editarExemplares(livroAtualizado);
		ControladorLivro.editarLivro(livroAtualizado);	

		return true;
	}	

	public static boolean removerLivro(String isbn) {

		if(!ValidadorObra.validarCampo(isbn)) {
			System.err.println("ISBN inválido.");
			return false;
		}

		if(!ControladorLivro.verificarExistenciaLivro(isbn)){
			System.err.println("Não foi encontrado livro com o ISBN informado.");
			return false;
		}		

		MenuExemplar.removerExemplares(isbn);
		ControladorLivro.removerLivro(isbn);

		return true;
	}
}
