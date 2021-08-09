package bibli.controle;

import java.util.HashMap;

import bibli.excecoes.ExcecaoExemplar;
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

	public static void adicionarExemplar(Exemplar exemplar) throws ExcecaoExemplar {

		if(exemplar == null)
			throw new ExcecaoExemplar("Exemplar nulo.");

		if(AcervoExemplar.buscarExemplar(exemplar.getCodigo()) != null)
			throw new ExcecaoExemplar("Exemplar já cadastrado.");

		AcervoExemplar.adicionarExemplar(exemplar);
	}

	public static void removerExemplar(Exemplar exemplar) throws ExcecaoExemplar {

		if(exemplar == null)
			throw new ExcecaoExemplar("Exemplar nulo.");	

		Exemplar e= AcervoExemplar.removerExemplar(exemplar.getCodigo());

		if(e == null) 
			throw new ExcecaoExemplar("Exemplar não cadastrado.");
	}

	public static void removerExemplar(String codigo) throws ExcecaoExemplar {

		if(codigo == null)
			throw new ExcecaoExemplar("Código nulo.");	

		Exemplar e= AcervoExemplar.removerExemplar(codigo);

		if(e == null) 
			throw new ExcecaoExemplar("Não foi encontrado exemplar com o código informado.");
	}

	public static void editarExemplar(Exemplar exemplarAtualizado) throws ExcecaoExemplar {

		if(exemplarAtualizado == null)
			throw new ExcecaoExemplar("O conteúdo de atualização do exemplar está nulo.");

		Exemplar e= AcervoExemplar.editarExemplar(exemplarAtualizado);		

		if(e == null)
			throw new ExcecaoExemplar("Exemplar não cadastrado.");	
	}

	public static Exemplar buscarExemplar(String codigo) throws ExcecaoExemplar{

		Exemplar exemplar= AcervoExemplar.buscarExemplar(codigo);

		if(exemplar == null)
			throw new ExcecaoExemplar("Exemplar não encontrado.");

		return exemplar;
	}

	public static HashMap<String, Exemplar> buscarExemplares(Livro livro){

		return AcervoExemplar.buscarExemplares(livro);

	}	
	
	public static HashMap<String, Exemplar> buscarExemplares(String isbn){

		return AcervoExemplar.buscarExemplares(isbn);

	}	
}
