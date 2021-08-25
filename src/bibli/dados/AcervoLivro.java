package bibli.dados;

import java.util.HashMap;

import bibli.aplicacao.Principal;
import bibli.modelo.Livro;

public class AcervoLivro extends Acervo{

	private static HashMap<String, Livro> livros= new HashMap<String, Livro>();

	public static HashMap<String, Livro> getLivros() {

		return livros;
	}

	public static int getNumeroLivros() {

		return livros.size();
	}

	public static boolean verificarExistenciaLivro(String codigo) {

		return livros.containsKey(codigo);		
	}

	public static boolean verificarExistenciaIsbn(String isbn) {

		for(Livro livro : livros.values()) 
			if(livro.getIsbn().equalsIgnoreCase(isbn)) 
				return true;

		return false;		
	}

	public static Livro buscarLivro(String codigo) {

		return livros.get(codigo);
	}

	public static HashMap<String, Livro> buscarLivrosPorAutor(String autor){

		HashMap<String, Livro> livrosEncontrados= new HashMap<String, Livro>();

		for(Livro livro : livros.values()) 
			if(livro.getAutor().toLowerCase().contains(autor.toLowerCase())) 
				livrosEncontrados.put(livro.getCodigo(), livro);	

		return livrosEncontrados;
	}

	public static HashMap<String, Livro> buscarLivrosPorTitulo(String titulo){

		HashMap<String, Livro> livrosEncontrados= new HashMap<String, Livro>();

		for(Livro livro : livros.values()) 
			if(livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) 
				livrosEncontrados.put(livro.getCodigo(), livro);	

		return livrosEncontrados;
	}

	public static void adicionarLivro(Livro livro) {

		livros.put(livro.getCodigo(), livro);
	}

	public static Livro removerLivro(String codigo) {

		return livros.remove(codigo);
	}

	public static void iniciar() {

		iniciarLeitor( Principal.getMensagem("arquivo.livro") );

		String linha= null;

		linha= ler();

		if(linha != null) {
			int totalLivrosJaCadastrados= Integer.parseInt(linha);
			Livro.setTotalLivrosJaCadastrados(totalLivrosJaCadastrados);
		}
		
		while(linha != null) {

			linha= ler();

			if(linha != null) {
				String[] atributosLivro = linha.split("##");

				String codigo= atributosLivro[0];
				String titulo= atributosLivro[1];
				String autor= atributosLivro[2];
				int edicao= Integer.parseInt(atributosLivro[3]);
				String editora= atributosLivro[4];
				int numeroPaginas= Integer.parseInt(atributosLivro[5]);
				String isbn= atributosLivro[6];
				String categoria= atributosLivro[7];
				int quantidadeExemplares= Integer.parseInt(atributosLivro[8]);

				Livro livro= new Livro(codigo, titulo, autor, 
						edicao, editora, numeroPaginas, isbn, categoria, 
						quantidadeExemplares);
				adicionarLivro(livro);
			}
		}

		fecharLeitor();
	}

	public static void encerrar() {

		iniciarEscritor( Principal.getMensagem("arquivo.livro") );
		
		escrever(String.valueOf(Livro.getTotalLivrosJaCadastrados()));

		for(Livro livro : livros.values()) {

			String[] atributosLivro= new String[9];

			atributosLivro[0]= livro.getCodigo();
			atributosLivro[1]= livro.getTitulo();
			atributosLivro[2]= livro.getAutor();
			atributosLivro[3]= String.valueOf(livro.getEdicao());
			atributosLivro[4]= livro.getEditora();
			atributosLivro[5]= String.valueOf(livro.getNumeroPaginas());
			atributosLivro[6]= livro.getIsbn();
			atributosLivro[7]= livro.getCategoria();	
			atributosLivro[8]= String.valueOf(livro.getQuantidadeExemplares());

			String linha= prepararLinha(atributosLivro);
			escrever(linha);
		}		

		fecharEscritor();
	}	
}