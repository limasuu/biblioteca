package bibli.modelo;

public class Exemplar implements Comparable<Exemplar>{
	
	private static int totalExemplaresJaCadastrados= 0;
	private String codigo;
	private Livro livro;
	private boolean disponivel;
	
	public Exemplar(Livro livro) {
		
		totalExemplaresJaCadastrados++;
		this.codigo= "EX" + String.valueOf(totalExemplaresJaCadastrados);
		this.livro= livro;
		this.disponivel= true;
	}

	public String getCodigo() {
		return codigo;
	}	
	
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
		
	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null)
			return false;
		
		if (this == obj)
			return true;
		
		if (obj instanceof Exemplar) {
			Exemplar outroExemplar= (Exemplar) obj;
			
			if(outroExemplar.getCodigo() == null ||
					outroExemplar.getLivro() == null)
				return false;
			
			
			if(outroExemplar.getCodigo().equals(this.codigo) &&
					outroExemplar.getLivro().equals(this.livro))
				return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return "Código do exemplar: " + codigo + " | " + (disponivel ? "Disponível" : "Indisponível")
				+ "\n" + livro;
	}

	@Override
	public int compareTo(Exemplar arg0) {
		
		return this.codigo.compareTo(arg0.getCodigo());
	}	
}