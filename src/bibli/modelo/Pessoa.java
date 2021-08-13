package bibli.modelo;

public class Pessoa implements Comparable<Pessoa>{

	private static int totalPessoasJaCadastradas= 0;
	
	private String codigo;
	private String nome;
	private String endereco;
	private String telefone;
	private String email;

	public Pessoa(String nome, String endereco, 
			String telefone, String email) {

		totalPessoasJaCadastradas++;
		this.codigo= "P" + String.valueOf(totalPessoasJaCadastradas);
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
					outraPessoa.getEmail().equals(this.email))
				return true;
		}		
		return false;
	}
	
	@Override
	public String toString() {
		return "Código: " + codigo + "\n" +
				"Nome: " + nome + "\nEndereco: " + endereco + 
				"\nTelefone: " + telefone + " | E-mail: " + email;
	}
	
	@Override
	public int compareTo(Pessoa arg0) { 		
		
		return this.codigo.compareToIgnoreCase(arg0.getCodigo());
	}
}
