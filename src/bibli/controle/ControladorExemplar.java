package bibli.controle;

import java.util.HashMap;

import bibli.modelo.AcervoExemplar;
import bibli.modelo.Exemplar;
import bibli.modelo.Livro;

public class ControladorExemplar {

	public static HashMap<String, Exemplar> getExemplares() {

		return AcervoExemplar.getExemplares();
	}

	public static int getNumeroExemplares() {

		return AcervoExemplar.getNumeroExemplares();
	}

	public static boolean verificarExistenciaExemplar(String codigo) {

		return AcervoExemplar.getExemplares().containsKey(codigo);		
	}

	public static void exibirExemplares() {

		if(AcervoExemplar.getNumeroExemplares() == 0)
			System.out.println("Não há exemplares cadastrados.");
		else {
			
			System.out.println("\n----------------- Exemplares -----------------");
			System.out.println("Quantidade: " + getNumeroExemplares());	
			System.out.println("------------------------------------------");
			for(Exemplar exemplar : AcervoExemplar.getExemplares().values())
				System.out.println(exemplar+"\n");
			System.out.println("------------------------------------------");
		}
	}

	public static void adicionarExemplar(Exemplar exemplar) {

		AcervoExemplar.adicionarExemplar(exemplar);
	}

	public static void removerExemplar(Exemplar exemplar) {

		AcervoExemplar.removerExemplar(exemplar.getCodigo());
	}

	public static void removerExemplar(String codigo) {

		AcervoExemplar.removerExemplar(codigo);
	}

	public static void editarExemplar(Exemplar exemplarAtualizado) {

		AcervoExemplar.editarExemplar(exemplarAtualizado);		
	}

	public static void editarExemplares(Livro livroAtualizado)  {

		HashMap<String, Exemplar> conjuntoExemplares= ControladorExemplar.buscarExemplares(livroAtualizado.getIsbn());

		if(!conjuntoExemplares.isEmpty()) {

			System.err.println("Será efetuada a edição do(s) " + conjuntoExemplares.size() + " exemplar(es) vinculado(s) a este livro!\n");
			for(Exemplar e : conjuntoExemplares.values()) 
				e.setLivro(livroAtualizado);	
			//editarExemplar(new Exemplar(e.getCodigo(), livroAtualizado)); atualizou mesmo? se nao tentar este codigo
		}	
	}

	public static Exemplar buscarExemplar(String codigo) {

		return AcervoExemplar.buscarExemplar(codigo);
	}

	public static HashMap<String, Exemplar> buscarExemplares(Livro livro){

		return AcervoExemplar.buscarExemplares(livro);

	}	

	public static HashMap<String, Exemplar> buscarExemplares(String isbn){

		return AcervoExemplar.buscarExemplares(isbn);

	}	
}
