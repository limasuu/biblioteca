package bibli.controle;

import java.util.HashMap;

import bibli.modelo.AcervoLivro;
import bibli.modelo.Livro;

public class ControladorLivro {

	public static HashMap<String, Livro> getLivros() { 
		
		return AcervoLivro.getLivros();
	}
	
	public static boolean verificarExistenciaLivro(String isbn) {
		
		return AcervoLivro.getLivros().containsKey(isbn);		
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

	public static void adicionarLivro(Livro livro) {

		AcervoLivro.adicionarLivro(livro);
	}	
		
	public static void removerLivro(String isbn) {
	
		AcervoLivro.removerLivro(isbn);
	}
	
	public static void editarLivro(Livro livroAtualizado) {
		
		AcervoLivro.editarLivro(livroAtualizado);	
	}
	
	public static Livro buscarLivro(String isbn){
		
		return AcervoLivro.buscarLivro(isbn);
	}
	
	public static HashMap<String, Livro> buscarLivrosPorAutor(String autor){

		return AcervoLivro.buscarLivrosPorAutor(autor);
	}
	
	public static HashMap<String, Livro> buscarLivrosPorTitulo(String titulo) {

		return AcervoLivro.buscarLivrosPorTitulo(titulo);
	}
}
