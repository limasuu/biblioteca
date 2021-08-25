package bibli.dados;

import java.util.HashMap;

import bibli.aplicacao.Principal;
import bibli.modelo.Exemplar;
import bibli.modelo.Livro;

public class AcervoExemplar extends Acervo{

	private static HashMap<String, Exemplar> exemplares= new HashMap<String, Exemplar>();

	public static HashMap<String, Exemplar> getExemplares() {

		return exemplares;
	}

	public static int getNumeroExemplares() {

		return exemplares.size();
	}

	public static boolean verificarExistencia(String codigo) {

		return exemplares.containsKey(codigo);		
	}

	public static boolean verificarDisponibilidade(String codigo) {
		
		return exemplares.get(codigo).isDisponivel();
	}
	
	public static boolean verificarExemplaresIndisponiveisPorLivro(String codigoLivro){ 

		int quantidade= 0;

		for(Exemplar exemplar : exemplares.values()) 
			if(!exemplar.isDisponivel() &&
					exemplar.getLivro().getCodigo().equalsIgnoreCase(codigoLivro)) 
				quantidade++;	

		return quantidade > 0;
	}

	public static Exemplar buscarExemplar(String codigo) {

		return exemplares.get(codigo);
	}

	public static HashMap<String, Exemplar> buscarExemplaresPorLivro(String codigoLivro) {

		HashMap<String, Exemplar> exemplaresEncontrados= new HashMap<String, Exemplar>();

		for(Exemplar exemplar : exemplares.values()) 
			if(exemplar.getLivro().getCodigo().equalsIgnoreCase(codigoLivro)) 
				exemplaresEncontrados.put(exemplar.getCodigo(), exemplar);	

		return exemplaresEncontrados;
	}	
	
	public static void adicionarExemplar(Exemplar exemplar) {

		exemplares.put(exemplar.getCodigo(), exemplar);
	}
	
	public static Exemplar removerExemplar(String codigo){

		return exemplares.remove(codigo);
	}

	public static int removerExemplares(String codigoLivro) {

		HashMap<String, Exemplar> conjuntoExemplares= buscarExemplaresPorLivro(codigoLivro);

		if(!conjuntoExemplares.isEmpty()) 
			for(Exemplar e : conjuntoExemplares.values()) 
				removerExemplar(e.getCodigo());

		return conjuntoExemplares.size();
	}
	
	public static void iniciar() {

		iniciarLeitor( Principal.getMensagem("arquivo.exemplar") );

		String linha= null;

		linha= ler();

		if(linha != null) {
			int totalExemplaresJaCadastrados= Integer.parseInt(linha);
			Exemplar.setTotalExemplaresJaCadastrados(totalExemplaresJaCadastrados);
		}
		
		while(linha != null) {

			linha= ler();

			if(linha != null) {
				String[] atributosExemplar = linha.split("##");

				String codigo= atributosExemplar[0];
				String codigoLivro= atributosExemplar[1];
				Livro livro= AcervoLivro.buscarLivro(codigoLivro);
				boolean disponivel= Boolean.parseBoolean(atributosExemplar[2]);					
				
				Exemplar exemplar= new Exemplar(codigo, livro, disponivel);
				adicionarExemplar(exemplar);
			}
		}

		fecharLeitor();
	}

	public static void encerrar() {

		iniciarEscritor( Principal.getMensagem("arquivo.exemplar") );
		
		escrever(String.valueOf(Exemplar.getTotalExemplaresJaCadastrados()));

		for(Exemplar exemplar : exemplares.values()) {

			String[] atributosExemplar= new String[3];

			atributosExemplar[0]= exemplar.getCodigo();
			atributosExemplar[1]= exemplar.getLivro().getCodigo();
			atributosExemplar[2]= String.valueOf(exemplar.isDisponivel());

			String linha= prepararLinha(atributosExemplar);
			escrever(linha);
		}		

		fecharEscritor();
	}	
}
