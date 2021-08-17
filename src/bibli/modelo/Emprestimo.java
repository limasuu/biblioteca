package bibli.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Emprestimo implements Comparable<Emprestimo>{
	
	private static int totalEmprestimosJaEfetuados= 0;
	
	private String codigo;
	private Funcionario funcionario;
	
	private Pessoa pessoa;
	private Exemplar exemplar;
	
	private boolean ativo;
	private int renocacoes;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;	
	
	public Emprestimo(Funcionario funcionario, Pessoa pessoa, Exemplar exemplar) {

		totalEmprestimosJaEfetuados++;
		this.codigo= "EM" + String.valueOf(totalEmprestimosJaEfetuados);
		this.funcionario= funcionario;
		this.pessoa= pessoa;
		this.exemplar= exemplar;
		this.ativo= true;
		this.renocacoes= 0;
		this.dataInicio= LocalDateTime.now();
		this.dataFim= null;
	}

	public static int getTotalEmprestimosJaEfetuados() {
		return totalEmprestimosJaEfetuados;
	}

	public String getCodigo() {
		return codigo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Exemplar getExemplar() {
		return exemplar;
	}

	public void setExemplar(Exemplar exemplar) {
		this.exemplar = exemplar;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getRenocacoes() {
		return renocacoes;
	}

	public void setRenocacoes() {
		this.renocacoes++;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim() {
		this.dataFim= LocalDateTime.now();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null)
			return false;

		if (this == obj)
			return true;

		if (obj instanceof Emprestimo) {
			Emprestimo outroEmprestimo= (Emprestimo) obj;	
			
			if(outroEmprestimo.getCodigo() == null || 
					outroEmprestimo.getFuncionario() == null ||
					outroEmprestimo.getPessoa() == null ||
					outroEmprestimo.getExemplar() == null || 					
					outroEmprestimo.getRenocacoes() < 0 || 
					outroEmprestimo.getDataInicio() == null)
				return false;
			
			if(outroEmprestimo.getCodigo().equals(this.codigo) &&
					outroEmprestimo.getFuncionario().equals(this.funcionario) &&
					outroEmprestimo.getPessoa().equals(this.pessoa) &&
					outroEmprestimo.getExemplar().equals(this.exemplar) &&
					outroEmprestimo.isAtivo() == this.ativo &&
					outroEmprestimo.getRenocacoes() == this.renocacoes && 
					outroEmprestimo.getDataInicio().equals(this.dataInicio) &&
					outroEmprestimo.getDataFim().equals(this.dataFim))
				return true;
		}		
		return false;
	}
	
	@Override
	public String toString() {
				
		return "Código: " + codigo + " | " + (ativo ? "ativo" : "finalizado") + 
				" | Funcionário(a): " + funcionario.getNome() +				
				"\nUsuário: " + pessoa.getNome() + ((renocacoes>0) ? (" | Renovações: " + renocacoes) : "") +
				"\nLivro \"" + exemplar.getLivro().getTitulo() + "\" | Exemplar \"" + exemplar.getCodigo() + 
				"\"\nInício: " + dataInicio.format(formatter)  + ((dataFim!=null) ? (" | Fim: " + dataFim.format(formatter)) : "");
	}
	
	@Override
	public int compareTo(Emprestimo arg0) { 		
		
		return this.codigo.compareToIgnoreCase(arg0.getCodigo());
	}
}
