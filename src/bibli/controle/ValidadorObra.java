package bibli.controle;

import java.util.ArrayList;

import bibli.modelo.AcervoLivro;
import bibli.modelo.Livro;

public class ValidadorObra extends Validador{	

	public static boolean validarCamposLivro(String titulo, String autor, 
			int edicao, String editora, int numeroPaginas,
			String isbn, String categoria) {
		
		ArrayList<String> strings= new ArrayList<String>();
		strings.add(titulo);
		strings.add(autor);
		strings.add(editora);
		strings.add(isbn);
		strings.add(categoria);
	
		if(!validarStrings(strings))
			return false;
		
		if(edicao < 1 || numeroPaginas < 1)
			return false;

		return true;		
	}	

	public static Livro validarAtualizacaoLivro(String codigo, String titulo, String autor, 
			int edicao, String editora, int numeroPaginas,	String isbn, String categoria) {

		Livro livro= AcervoLivro.buscarLivro(isbn);
		Livro livroAtualizado= new Livro(codigo, titulo, autor, edicao, editora, 
				numeroPaginas, isbn, categoria);
		
		if(livro.equals(livroAtualizado))
			return null;

		livro.setTitulo(titulo);
		livro.setAutor(autor);
		livro.setEdicao(edicao);
		livro.setEditora(editora);		
		livro.setNumeroPaginas(numeroPaginas);
		livro.setIsbn(isbn);
		livro.setCategoria(categoria);

		return livro;	
	}	

	public static boolean validarLivro(Livro livro) {

		if(livro == null) 
			return false;

		return AcervoLivro.verificarExistenciaIsbn(livro.getIsbn());
	}
}
