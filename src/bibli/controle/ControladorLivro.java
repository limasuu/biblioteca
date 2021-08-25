package bibli.controle;

import java.util.HashMap;

import bibli.aplicacao.Principal;
import bibli.dados.AcervoExemplar;
import bibli.dados.AcervoLivro;
import bibli.modelo.Livro;

public class ControladorLivro {
	
	public static boolean exibirLivro (String codigo){

		if(!ValidadorObra.validarCampo(codigo)) {
			System.err.println( Principal.getMensagem("erro.codigoInvalido") );
			return false;
		}

		if(!AcervoLivro.verificarExistenciaLivro(codigo)){
			System.err.println( Principal.getMensagem("erro.livro.naoEncontrado") );
			return false;
		}	

		System.out.println("\n" + AcervoLivro.buscarLivro(codigo));

		return true;
	}
	
	public static void exibirLivros() {

		if(AcervoLivro.getNumeroLivros() == 0)
			System.out.println( Principal.getMensagem("erro.livro.vazio") );
		else {

			System.out.println( Principal.getMensagem("livro.exibir.topo") );
			System.out.println( Principal.getMensagem("exibir.quantidade") + AcervoLivro.getNumeroLivros());	
			System.out.println( Principal.getMensagem("menu.base") );

			for(Livro livro : AcervoLivro.getLivros().values())
				System.out.println(livro+"\n");

			System.out.println( Principal.getMensagem("menu.base") );
		}
	}

	public static boolean exibirLivrosPorAutor(String autor){

		if(!ValidadorObra.validarCampo(autor)) {
			System.err.println( Principal.getMensagem("erro.livro.invalido.autor") );
			return false;
		}

		HashMap<String, Livro> livrosEncontrados= AcervoLivro.buscarLivrosPorAutor(autor);

		if(livrosEncontrados.size() == 0) 
			System.out.println( Principal.getMensagem("erro.livro.vazio") );
		else {

			System.out.println("\n----------- Livros de \"" + autor + "\" -----------");
			System.out.println( Principal.getMensagem("exibir.quantidade") + livrosEncontrados.size());	
			System.out.println( Principal.getMensagem("menu.base") );

			for(Livro livro: livrosEncontrados.values())
				System.out.println("\n" + livro);	

			System.out.println( Principal.getMensagem("menu.base") );
		}

		return true;		
	}

	public static boolean exibirLivrosPorTitulo(String titulo) {

		if(!ValidadorObra.validarCampo(titulo)) {			
			System.err.println( Principal.getMensagem("erro.livro.invalido.titulo") );
			return false;
		}

		HashMap<String, Livro> livrosEncontrados= AcervoLivro.buscarLivrosPorTitulo(titulo);

		if(livrosEncontrados.size() == 0) 
			System.out.println( Principal.getMensagem("erro.livro.vazio") );
		else{

			System.out.println("\n-------- Livros com o título \"" + titulo + "\" --------");
			System.out.println( Principal.getMensagem("exibir.quantidade") + livrosEncontrados.size());	
			System.out.println( Principal.getMensagem("menu.base") );

			for(Livro livro: livrosEncontrados.values())
				System.out.println("\n" + livro);			

			System.out.println( Principal.getMensagem("menu.base") );
		} 

		return true;
	}

	public static boolean adicionarLivro(String titulo, String autor, 
			int edicao, String editora, int numeroPaginas,
			String isbn, String categoria) {

		if(!ValidadorObra.validarCamposLivro(titulo, autor, edicao, editora,
				numeroPaginas, isbn, categoria)){
			System.err.println( Principal.getMensagem("erro.invalido.campos") );
			return false;
		}

		if(AcervoLivro.verificarExistenciaIsbn(isbn)){
			System.err.println( Principal.getMensagem("erro.livro.invalido.isbn") );
			return false;
		}

		Livro livro= new Livro(titulo, autor, edicao, editora, numeroPaginas, isbn, categoria);
		AcervoLivro.adicionarLivro(livro);
		System.out.println("\nLivro \"" + livro.getCodigo() + "\" cadastrado.");

		return true;
	}	

	public static boolean editarLivro(String codigo, String novoTitulo, String novoAutor, 
			int novaEdicao, String novaEditora, int novoNumeroPaginas,
			String novoIsbn, String novaCategoria) {
		
		if(!AcervoLivro.verificarExistenciaLivro(codigo)){
			System.err.println( Principal.getMensagem("erro.livro.naoEncontrado") );
			return false;
		}	

		if(!ValidadorObra.validarCamposLivro(novoTitulo, novoAutor, novaEdicao, novaEditora,
				novoNumeroPaginas, novoIsbn, novaCategoria)) {
			System.err.println( Principal.getMensagem("erro.invalido.campos") );
			return false;
		}			

		boolean resultadoOperacao= ValidadorObra.validarAtualizacaoLivro(codigo, novoTitulo, novoAutor,
				novaEdicao, novaEditora, novoNumeroPaginas, novoIsbn, novaCategoria);

		if(!resultadoOperacao){
			System.err.println( Principal.getMensagem("editar.sem.mudanças") );
			return false;
		}	
		
		System.out.println("\nLivro \"" + codigo + "\" editado.");
		return true;
	}

	public static boolean removerLivro(String codigo) {

		if(!ValidadorObra.validarCampo(codigo)) {
			System.err.println( Principal.getMensagem("erro.codigoInvalido") );
			return false;
		}

		if(!AcervoLivro.verificarExistenciaLivro(codigo)){
			System.err.println( Principal.getMensagem("erro.livro.naoEncontrado") );
			return false;
		}		
		
		if(AcervoExemplar.verificarExemplaresIndisponiveisPorLivro(codigo)){
			System.err.println( Principal.getMensagem("erro.livro.remover.exemplar.indisponivel") );
			return false;
		}
		
		int numeroExemplares= AcervoExemplar.removerExemplares(codigo);
		AcervoLivro.removerLivro(codigo);
		
		System.out.println( Principal.getMensagem("menu.livro.remover.concluido") );	
		if(numeroExemplares > 0)
			System.err.println("Foi efetuada a remoção do(s) " + numeroExemplares + " exemplar(es) vinculado(s) a este livro!");
		
		return true;
	}	
}
