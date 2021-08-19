package bibli.modelo;

public class Livro implements Comparable<Livro>{

	private static int totalLivrosJaCadastrados= 0;

	private String codigo;
	private String titulo;
	private String autor;
	private int edicao;
	private String editora;
	private int numeroPaginas;
	private String isbn;
	private String categoria;

	public Livro(String titulo, String autor, 
			int edicao, String editora, int numeroPaginas,
			String isbn, String categoria) {
		
		totalLivrosJaCadastrados++;
		this.codigo= "L" + String.valueOf(totalLivrosJaCadastrados);
		this.titulo = titulo;
		this.autor = autor;
		this.edicao = edicao;
		this.editora = editora;
		this.numeroPaginas = numeroPaginas;
		this.isbn = isbn;
		this.categoria = categoria;
	}	
	
	public Livro(String codigo, String titulo, String autor, 
			int edicao, String editora, int numeroPaginas,
			String isbn, String categoria) {
		
		this.codigo= codigo;
		this.titulo = titulo;
		this.autor = autor;
		this.edicao = edicao;
		this.editora = editora;
		this.numeroPaginas = numeroPaginas;
		this.isbn = isbn;
		this.categoria = categoria;
	}	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getEdicao() {
		return edicao;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null)
			return false;

		if (this == obj)
			return true;		

		if (obj instanceof Livro) {
			Livro outroLivro = (Livro) obj;

			if(outroLivro.getCodigo() == null || 
					outroLivro.getTitulo() == null || 
					outroLivro.getAutor() == null || 
					outroLivro.getEditora() == null ||
					outroLivro.getIsbn() == null ||
					outroLivro.getCategoria() == null)
				return false;

			if(outroLivro.getCodigo().equals(this.codigo) &&
					outroLivro.getTitulo().equals(this.titulo) &&
					outroLivro.getAutor().equals(this.autor) &&					
					(outroLivro.getEdicao() == this.edicao) &&
					outroLivro.getEditora().equals(this.editora) &&
					(outroLivro.getNumeroPaginas() == this.numeroPaginas) &&
					outroLivro.getIsbn().equals(this.isbn) &&
					outroLivro.getCategoria().equals(this.categoria))
				return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return "Código: " + codigo + " | Título: " + titulo +
				"\nAutor: " + autor + " | Edição: " + edicao + " | Editora: " + editora +
				"\nNúmero de páginas: " + numeroPaginas + " | ISBN: " + isbn + " | Categoria: " + categoria;
	}

	@Override
	public int compareTo(Livro arg0) { 		

		int comparacao= this.titulo.compareToIgnoreCase(arg0.getTitulo());

		if(comparacao == 0)
			comparacao= this.isbn.compareToIgnoreCase(arg0.getIsbn());

		return comparacao;
	}
}