package bibli.controle;

import java.util.HashMap;

import bibli.aplicacao.Principal;
import bibli.modelo.AcervoExemplar;
import bibli.modelo.AcervoLivro;
import bibli.modelo.Exemplar;
import bibli.modelo.Livro;

public class ControladorExemplar {

	public static boolean exibirExemplar(String codigo) {
		
		if(!ValidadorObra.validarCampo(codigo)) {
			System.err.println( Principal.getMensagem("erro.codigoInvalido") );
			return false;
		}

		if(!AcervoExemplar.verificarExistenciaExemplar(codigo)){
			System.err.println( Principal.getMensagem("erro.exemplar.naoEncontrado") );
			return false;
		}	

		System.out.println("\n" + AcervoExemplar.buscarExemplar(codigo));

		return true;
	}
	
	public static void exibirExemplares() {

		if(AcervoExemplar.getNumeroExemplares() == 0)
			System.out.println( Principal.getMensagem("erro.exemplar.vazio") );
		else {

			System.out.println( Principal.getMensagem("exemplar.exibir.topo") );
			System.out.println( Principal.getMensagem("exibir.quantidade") + AcervoExemplar.getNumeroExemplares());	
			System.out.println( Principal.getMensagem("menu.base") );
			
			for(Exemplar exemplar : AcervoExemplar.getExemplares().values())
				System.out.println(exemplar+"\n");
			
			System.out.println( Principal.getMensagem("menu.base") );
		}
	}	

	public static boolean exibirExemplaresPorLivro(String codigo){

		if(!ValidadorObra.validarCampo(codigo)) {
			System.err.println( Principal.getMensagem("erro.codigoInvalido") );
			return false;
		}

		HashMap<String, Exemplar> exemplaresEncontrados= AcervoExemplar.buscarExemplares(codigo);		

		if(exemplaresEncontrados.size() == 0) 
			System.out.println( Principal.getMensagem("erro.exemplar.vazio") );		
		else {

			System.out.println("\n----------- Exemplares com código \"" + codigo + "\" -----------");
			System.out.println( Principal.getMensagem("exibir.quantidade") + exemplaresEncontrados.size());	
			System.out.println( Principal.getMensagem("menu.base") );

			for(Exemplar exemplar: exemplaresEncontrados.values())
				System.out.println("\n" + exemplar);	

			System.out.println( Principal.getMensagem("menu.base") );
		}

		return true;
	}	

	public static boolean adicionarExemplar(String codigo, int quantidade) {

		if(!ValidadorObra.validarCampo(codigo)) {
			System.err.println( Principal.getMensagem("erro.codigoInvalido") );
			return false;
		}

		if(!AcervoLivro.verificarExistenciaLivro(codigo)){
			System.err.println( Principal.getMensagem("erro.livro.naoEncontrado") );
			return false;
		}	
		
		Livro livro= AcervoLivro.buscarLivro(codigo);
		
		for(int i=0; i<quantidade; i++) {			
			
			Exemplar exemplar= new Exemplar(livro);
			
			AcervoExemplar.adicionarExemplar(exemplar);
			System.out.println("\nExemplar \"" + exemplar.getCodigo() + "\" cadastrado.");			
		}

		return true;
	}
		
	public static boolean removerExemplar(String codigo) {

		if(!ValidadorObra.validarCampo(codigo)) {
			System.err.println( Principal.getMensagem("erro.codigoInvalido") );
			return false;
		}

		if(!AcervoExemplar.verificarExistenciaExemplar(codigo)){
			System.err.println( Principal.getMensagem("erro.exemplar.naoEncontrado") );
			return false;
		}				

		AcervoExemplar.removerExemplar(codigo);
		System.out.println( Principal.getMensagem("menu.exemplar.remover.concluido") );	

		return true;
	}
}
