package bibli.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pessoa implements Comparable<Pessoa>{

	private static int totalPessoasJaCadastradas= 0;
	
	private String codigo;
	private String nome;
	private String endereco;
	private String telefone;
	private String email;
	
	private boolean bloqueado;
	private LocalDateTime dataFimBloqueio;	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");

	public Pessoa(String nome, String endereco, 
			String telefone, String email) {

		totalPessoasJaCadastradas++;
		this.codigo= "P" + String.valueOf(totalPessoasJaCadastradas);
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email= email;
		this.bloqueado= false;
		this.dataFimBloqueio= null;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public LocalDateTime getDataFimBloqueio() {
		return dataFimBloqueio;
	}

	public void setDataFimBloqueio(int dias) {
		
		if(dias == 0)
			this.dataFimBloqueio= null;
		
		this.dataFimBloqueio= LocalDateTime.now().plusDays(dias);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null)
			return false;

		if (this == obj)
			return true;

		if (obj instanceof Pessoa) {
			Pessoa outraPessoa= (Pessoa) obj;
			
			if(outraPessoa.getCodigo() == null || 
					outraPessoa.getNome() == null ||
					outraPessoa.getEndereco() == null ||
					outraPessoa.getTelefone() == null || 
					outraPessoa.getEmail() == null)
				return false;
			
			if(outraPessoa.getCodigo().equals(this.codigo) &&
					outraPessoa.getNome().equals(this.nome) &&
					outraPessoa.getEndereco().equals(this.endereco) &&
					outraPessoa.getTelefone().equals(this.telefone) &&
					outraPessoa.getEmail().equals(this.email) &&
					outraPessoa.isBloqueado() == this.bloqueado &&
					outraPessoa.getDataFimBloqueio().equals(this.dataFimBloqueio))
				return true;
		}		
		return false;
	}
	
	@Override
	public String toString() {
		return "Código: " + codigo + (bloqueado ? " | BLOQUEADO" : "") +
				((dataFimBloqueio!=null) ? "\nFim do bloqueio: " + dataFimBloqueio.format(formatter) : "") +						
				"\nNome: " + nome + "\nEndereco: " + endereco + 
				"\nTelefone: " + telefone + " | E-mail: " + email;
	}
	
	@Override
	public int compareTo(Pessoa arg0) { 		
		
		return this.codigo.compareToIgnoreCase(arg0.getCodigo());
	}
}
