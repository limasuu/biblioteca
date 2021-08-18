package bibli.modelo;

import java.time.LocalDateTime;
import java.util.HashMap;

public class AcervoEmprestimo {

	private static HashMap<String, Emprestimo> emprestimos= new HashMap<String, Emprestimo>();

	public static HashMap<String, Emprestimo> getEmprestimos() {

		return emprestimos;
	}

	public static int getNumeroEmprestimos() {

		return emprestimos.size();
	}

	public static boolean verificarExistenciaEmprestimo(String codigo) {

		return emprestimos.containsKey(codigo);		
	}

	public static Emprestimo buscarEmprestimo(String codigo) {

		return emprestimos.get(codigo);
	}

	public static HashMap<String, Emprestimo> buscarEmprestimosPorLivro(String isbn){

		HashMap<String, Emprestimo> emprestimosEncontrados= new HashMap<String, Emprestimo>();

		for(Emprestimo emprestimo : emprestimos.values()) 
			if(emprestimo.getExemplar().getLivro().getIsbn().toLowerCase().equals(isbn.toLowerCase())) 
				emprestimosEncontrados.put(emprestimo.getCodigo(), emprestimo);	

		return emprestimosEncontrados;
	}

	public static HashMap<String, Emprestimo> buscarEmprestimosPorUsuario(String codigo){

		HashMap<String, Emprestimo> emprestimosEncontrados= new HashMap<String, Emprestimo>();

		for(Emprestimo emprestimo : emprestimos.values()) 
			if(emprestimo.getPessoa().getCodigo().toLowerCase().equals(codigo.toLowerCase())) 
				emprestimosEncontrados.put(emprestimo.getCodigo(), emprestimo);	

		return emprestimosEncontrados;
	}

	public static void adicionarEmprestimo(Emprestimo emprestimo) {

		emprestimos.put(emprestimo.getCodigo(), emprestimo);
	}

	public static Emprestimo editarEmprestimo(Emprestimo emprestimoAtualizado) {

		return emprestimos.replace(emprestimoAtualizado.getCodigo(), emprestimoAtualizado);
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
}
