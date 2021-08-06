package bibli.controle;

import java.util.HashMap;

import bibli.excecoes.ExcecaoLivro;
import bibli.modelo.AcervoLivro;
import bibli.modelo.Livro;

public class ControladorLivro {

	public static HashMap<String, Livro> getLivros() {
		
		return AcervoLivro.getLivros();
	}

	public static int getNumeroLivros() {
		
		return AcervoLivro.getNumeroLivros();
	}

	public static void exibirLivros() throws ExcecaoLivro {

		if(AcervoLivro.getNumeroLivros() == 0)
			throw new ExcecaoLivro("Não há livros cadastrados.");
		else {
			
			System.out.println("\n----------------- Livros -----------------");
			System.out.println("Quantidade: " + ControladorLivro.getNumeroLivros());	
			System.out.println("------------------------------------------");
			for(Livro livro : AcervoLivro.getLivros().values())
				System.out.println(livro+"\n");
			System.out.println("------------------------------------------");
		}
	}

	public static void adicionarLivro(Livro livro) throws ExcecaoLivro {

		if(livro == null)
			throw new ExcecaoLivro("Livro nulo.");
		
		if(AcervoLivro.buscarLivro(livro.getIsbn()) != null)
			throw new ExcecaoLivro("Livro já cadastrado.");

		AcervoLivro.adicionarLivro(livro);
	}
	
	public static void removerLivro(Livro livro) throws ExcecaoLivro {

		if(livro == null)
			throw new ExcecaoLivro("Livro nulo.");
		
		Livro l= AcervoLivro.removerLivro(livro.getIsbn());
		
		if(l == null) 
			throw new ExcecaoLivro("Livro não cadastrado.");
	}
	
	public static void removerLivro(String isbn) throws ExcecaoLivro {

		if(isbn == null)
			throw new ExcecaoLivro("ISBN nulo.");
		
		Livro l= AcervoLivro.removerLivro(isbn);
		
		if(l == null) 
			throw new ExcecaoLivro("Livro não cadastrado.");
	}
	
	public static void editarLivro(Livro livroAtualizado) throws ExcecaoLivro {

		if(livroAtualizado == null)
			throw new ExcecaoLivro("O conteúdo de atualização do livro está nulo.");
				
		Livro l= AcervoLivro.editarLivro(livroAtualizado);		
		
		if(l == null)
			throw new ExcecaoLivro("Livro não cadastrado.");			
	}
	
	public static Livro buscarLivro(String isbn) throws ExcecaoLivro{
		
		Livro livro= AcervoLivro.buscarLivro(isbn);
		
		if(livro == null)
			throw new ExcecaoLivro("Livro não encontrado.");

		return livro;
	}
	
	public static HashMap<String, Livro> buscarLivrosPorAutor(String autor) throws ExcecaoLivro{

		HashMap<String, Livro> livrosEncontrados= AcervoLivro.buscarLivrosPorAutor(autor);

		if(livrosEncontrados.size() == 0)
			throw new ExcecaoLivro("Nenhum livro encontrado.");

		return livrosEncontrados;
	}
	
	public static HashMap<String, Livro> buscarLivrosPorTitulo(String titulo) throws ExcecaoLivro{

		HashMap<String, Livro> livrosEncontrados= AcervoLivro.buscarLivrosPorTitulo(titulo);	
				
		if(livrosEncontrados.size() == 0)
			throw new ExcecaoLivro("Nenhum livro encontrado.");

		return livrosEncontrados;
	}
}
