package bibli.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Emprestimo implements Comparable<Emprestimo>{
	
	private static int totalEmprestimosJaEfetuados= 0;
	
	private String codigo;
	private Funcionario funcionario;
	
	private Usuario usuario;
	private Exemplar exemplar;
	
	private boolean ativo;
	private int renocacoes;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
	private LocalDateTime dataInicio;
	private LocalDateTime dataLimite;	
	private LocalDateTime dataFim;	
	
	public Emprestimo(Funcionario funcionario, Usuario usuario, Exemplar exemplar) {

		totalEmprestimosJaEfetuados++;
		this.codigo= "EM" + String.valueOf(totalEmprestimosJaEfetuados);
		this.funcionario= funcionario;
		this.usuario= usuario;
		this.exemplar= exemplar;
		this.ativo= true;
		this.renocacoes= 0;
		this.dataInicio= LocalDateTime.now();
		this.dataLimite= dataInicio.plusDays(7);
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		this.dataLimite= dataLimite.plusDays(7);
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public LocalDateTime getDataLimite() {
		return dataLimite;
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
					outroEmprestimo.getUsuario() == null ||
					outroEmprestimo.getExemplar() == null || 					
					outroEmprestimo.getRenocacoes() < 0 || 
					outroEmprestimo.getDataInicio() == null)
				return false;
			
			if(outroEmprestimo.getCodigo().equals(this.codigo) &&
					outroEmprestimo.getFuncionario().equals(this.funcionario) &&
					outroEmprestimo.getUsuario().equals(this.usuario) &&
					outroEmprestimo.getExemplar().equals(this.exemplar) &&
					outroEmprestimo.isAtivo() == this.ativo &&
					outroEmprestimo.getRenocacoes() == this.renocacoes && 
					outroEmprestimo.getDataInicio().equals(this.dataInicio) &&
					outroEmprestimo.getDataLimite().equals(this.dataLimite) &&
					outroEmprestimo.getDataFim().equals(this.dataFim))
				return true;
		}		
		return false;
	}
	
	@Override
	public String toString() {
				
		return "Código: " + codigo + " | " + (ativo ? "ativo" : "finalizado") + 
				" | Funcionário(a): " + funcionario.getNome() +				
				"\nUsuário: " + usuario.getNome() + ((renocacoes>0) ? (" | Renovações: " + renocacoes) : "") +
				"\nLivro \"" + exemplar.getLivro().getTitulo() + "\" | Exemplar \"" + exemplar.getCodigo() + 
				"\"\nInício: " + dataInicio.format(formatter)  + ((dataFim!=null) ? (" | Fim: " + dataFim.format(formatter)) : 
					" | Prazo: " + dataLimite.format(formatter));
	}
	
	@Override
	public int compareTo(Emprestimo arg0) { 		
		
		return this.codigo.compareToIgnoreCase(arg0.getCodigo());
	}
}
