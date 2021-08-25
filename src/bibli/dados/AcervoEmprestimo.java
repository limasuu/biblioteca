package bibli.dados;

import java.time.LocalDateTime;
import java.util.HashMap;

import bibli.aplicacao.Principal;
import bibli.modelo.Emprestimo;
import bibli.modelo.Exemplar;
import bibli.modelo.Funcionario;
import bibli.modelo.Usuario;

public class AcervoEmprestimo extends Acervo{

	private static HashMap<String, Emprestimo> emprestimos= new HashMap<String, Emprestimo>();

	public static HashMap<String, Emprestimo> getEmprestimos() {

		return emprestimos;
	}

	public static int getNumeroEmprestimos() {

		return emprestimos.size();
	}

	public static boolean verificarExistencia(String codigo) {

		return emprestimos.containsKey(codigo);		
	}

	public static boolean verificarAtividade(String codigo) {

		return emprestimos.get(codigo).isAtivo();		
	}

	public static boolean verificarEmprestimosAtivosPorUsuario(String codigo){ 

		int quantidade= 0;

		for(Emprestimo emprestimo : emprestimos.values()) 
			if(emprestimo.isAtivo() &&
					emprestimo.getUsuario().getCodigo().equalsIgnoreCase(codigo)) 
				quantidade++;	

		return quantidade > 0;
	}

	public static Emprestimo buscarEmprestimo(String codigo) {

		return emprestimos.get(codigo);
	}

	public static HashMap<String, Emprestimo> buscarEmprestimosPorLivro(String codigoLivro){

		HashMap<String, Emprestimo> emprestimosEncontrados= new HashMap<String, Emprestimo>();

		for(Emprestimo emprestimo : emprestimos.values()) 
			if(emprestimo.getExemplar().getLivro().getCodigo().equalsIgnoreCase(codigoLivro)) 
				emprestimosEncontrados.put(emprestimo.getCodigo(), emprestimo);	

		return emprestimosEncontrados;
	}

	public static HashMap<String, Emprestimo> buscarEmprestimosPorUsuario(String codigoUsuario){

		HashMap<String, Emprestimo> emprestimosEncontrados= new HashMap<String, Emprestimo>();

		for(Emprestimo emprestimo : emprestimos.values()) 
			if(emprestimo.getUsuario().getCodigo().equalsIgnoreCase(codigoUsuario)) 
				emprestimosEncontrados.put(emprestimo.getCodigo(), emprestimo);	

		return emprestimosEncontrados;
	}

	public static void adicionarEmprestimo(Emprestimo emprestimo) {

		emprestimos.put(emprestimo.getCodigo(), emprestimo);
	}

	public static Emprestimo removerEmprestimo(String codigo) {

		return emprestimos.remove(codigo);
	}

	public static HashMap<String, Emprestimo> buscarEmprestimosVencidos() {

		HashMap<String, Emprestimo> emprestimosEncontrados= new HashMap<String, Emprestimo>();

		for(Emprestimo emprestimo : emprestimos.values()) 
			if(emprestimo.getDataLimite().isBefore(LocalDateTime.now()))
				emprestimosEncontrados.put(emprestimo.getCodigo(), emprestimo);	

		return emprestimosEncontrados;
	}	

	public static void iniciar() {

		iniciarLeitor( Principal.getMensagem("arquivo.emprestimo") );

		String linha= null;

		linha= ler();

		if(linha != null) {
			int totalEmprestimosJaEfetuados= Integer.parseInt(linha);
			Emprestimo.setTotalEmprestimosJaEfetuados(totalEmprestimosJaEfetuados);
		}

		while(linha != null) {

			linha= ler();

			if(linha != null) {
				String[] atributosEmprestimo= linha.split("##");

				String codigo= atributosEmprestimo[0];
				String matriculaFuncionario= atributosEmprestimo[1];
				Funcionario funcionario= AcervoFuncionario.buscarFuncionario(matriculaFuncionario);
				String codigoUsuario= atributosEmprestimo[2];
				Usuario usuario= AcervoUsuario.buscarUsuario(codigoUsuario);
				String codigoExemplar= atributosEmprestimo[3];
				Exemplar exemplar= AcervoExemplar.buscarExemplar(codigoExemplar);
				boolean ativo= Boolean.parseBoolean(atributosEmprestimo[4]); 
				int renocacoes= Integer.parseInt(atributosEmprestimo[5]);							
				LocalDateTime dataInicio= resgatarData(atributosEmprestimo[6]);
				LocalDateTime dataLimite= resgatarData(atributosEmprestimo[7]);
				LocalDateTime dataFim= resgatarData(atributosEmprestimo[8]);

				Emprestimo emprestimo= new Emprestimo(codigo, funcionario, usuario, exemplar,
						ativo, renocacoes, dataInicio, dataLimite, dataFim);
				adicionarEmprestimo(emprestimo);
			}
		}

		fecharLeitor();
	}

	public static void encerrar() {

		iniciarEscritor( Principal.getMensagem("arquivo.emprestimo") );

		escrever(String.valueOf(Emprestimo.getTotalEmprestimosJaEfetuados()));

		for(Emprestimo emprestimo : emprestimos.values()) {

			String[] atributosEmprestimo= new String[9];

			atributosEmprestimo[0]= emprestimo.getCodigo();
			atributosEmprestimo[1]= emprestimo.getFuncionario().getMatricula();
			atributosEmprestimo[2]= emprestimo.getUsuario().getCodigo();
			atributosEmprestimo[3]= emprestimo.getExemplar().getCodigo();
			atributosEmprestimo[4]= String.valueOf(emprestimo.isAtivo());
			atributosEmprestimo[5]= String.valueOf(emprestimo.getRenocacoes());			
			atributosEmprestimo[6]= prepararData(emprestimo.getDataInicio());
			atributosEmprestimo[7]= prepararData(emprestimo.getDataLimite());
			atributosEmprestimo[8]= prepararData(emprestimo.getDataFim());

			String linha= prepararLinha(atributosEmprestimo);			
			escrever(linha);
		}		

		fecharEscritor();
	}	
}
