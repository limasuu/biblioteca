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

		boolean mudancas= false;

		if(!livro.getTitulo().equals(titulo)) {
			livro.setTitulo(titulo);
			mudancas= true;
		}
		if(!livro.getAutor().equals(autor)) {
			livro.setAutor(autor);
			mudancas= true;
		}
		if(!livro.getEditora().equals(editora)) {
			livro.setEditora(editora);
			mudancas= true;
		}
		if(!livro.getCategoria().equals(categoria)) {
			livro.setCategoria(categoria);
			mudancas= true;
		}
		if(livro.getEdicao() != edicao) {
			livro.setEdicao(edicao);
			mudancas= true;
		}
		if(livro.getNumeroPaginas() != numeroPaginas) {
			livro.setNumeroPaginas(numeroPaginas);
			mudancas= true;
		}


		if(!mudancas)
			return null;

		return livro;		
	}

	public static boolean validarLivro(Livro livro) {

		if(livro == null) 
			return false;

		return AcervoLivro.verificarExistenciaLivro(livro.getIsbn());
	}
}
