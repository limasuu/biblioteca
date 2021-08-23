package bibli.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Usuario implements Comparable<Usuario>{

	private static int totalUsuariosJaCadastrados= 0;
	
	private String codigo;
	private String nome;
	private String endereco;
	private String telefone;
	private String email;
	
	private boolean bloqueado;
	private LocalDateTime dataFimBloqueio;	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");

	public Usuario(String nome, String endereco, 
			String telefone, String email) {

		totalUsuariosJaCadastrados++;
		this.codigo= "U" + String.valueOf(totalUsuariosJaCadastrados);
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email= email;
		this.bloqueado= false;
		this.dataFimBloqueio= null;
	}
	
	public Usuario(String codigo, String nome, String endereco, 
			String telefone, String email) {
		
		this.codigo= codigo;
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email= email;
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

	public void setDataFimBloqueio(LocalDateTime dataFimBloqueio) {
		
		this.dataFimBloqueio= dataFimBloqueio;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null)
			return false;

		if (this == obj)
			return true;

		if (obj instanceof Usuario) {
			Usuario outroUsuario= (Usuario) obj;
			
			if(outroUsuario.getCodigo() == null || 
					outroUsuario.getNome() == null ||
					outroUsuario.getEndereco() == null ||
					outroUsuario.getTelefone() == null || 
					outroUsuario.getEmail() == null)
				return false;
			
			if(outroUsuario.getCodigo().equals(this.codigo) &&
					outroUsuario.getNome().equals(this.nome) &&
					outroUsuario.getEndereco().equals(this.endereco) &&
					outroUsuario.getTelefone().equals(this.telefone) &&
					outroUsuario.getEmail().equals(this.email))
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
	public int compareTo(Usuario arg0) { 		
		
		return this.codigo.compareToIgnoreCase(arg0.getCodigo());
	}
}
