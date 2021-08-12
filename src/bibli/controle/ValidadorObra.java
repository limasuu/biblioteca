package bibli.controle;

import bibli.modelo.AcervoLivro;
import bibli.modelo.Livro;

public class ValidadorObra {

	public static boolean validarCampo(String campo) {

		if(campo == null)
			return false;		

		if(campo.isBlank())
			return false;

		return true;		
	}

	public static boolean validarCamposLivro(String titulo, String autor, 
			int edicao, String editora, int numeroPaginas,
			String isbn, String categoria) {

		if(titulo == null || autor == null || 
				edicao < 1 || editora == null ||
				numeroPaginas < 1 || isbn == null ||
				categoria == null)
			return false;

		if(titulo.isBlank() || autor.isBlank() || 
				editora.isBlank() || isbn.isBlank() ||
				categoria.isBlank())
			return false;

		return true;		
	}	

	public static Livro validarAtualizacaoLivro(String titulo, String autor, 
			int edicao, String editora, int numeroPaginas,
			String isbn, String categoria) {

		Livro livro= AcervoLivro.buscarLivro(isbn);
		Livro livroAtualizado= new Livro(titulo, autor, edicao, editora, 
				numeroPaginas, isbn, categoria);
		
		if(livro.equals(livroAtualizado))
			return null;
		else
			return livroAtualizado;		
	}

	public static boolean validarLivro(Livro livro) {

		if(livro == null) 
			return false;

		return AcervoLivro.verificarExistenciaLivro(livro.getIsbn());
	}
}
