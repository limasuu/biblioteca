package bibli.aplicacao;

import java.util.ArrayList;
import java.util.Collections;

import bibli.controle.ControladorExemplar;
import bibli.controle.ControladorLivro;
import bibli.modelo.Exemplar;
import bibli.modelo.Livro;

public class Principal {
		
	public static void main(String[] args) {
	
		Livro livro1= new Livro("Polianna", "Eleanor Porter", 1, "Pé de Letra", 184, "978-8595201170", "Clássico");
		Livro livro2= new Livro("O Mundo de Sofia", "Jostein Gaarder", 1, "Seguinte", 568, "9788535921892", "Romance");
		Livro livro3= new Livro("A Seleção", "Kiera Cass", 1, "Seguinte", 368, "9788565765015", "Young adult");
		Livro livro4= new Livro("A Elite", "Kiera Cass", 1, "Seguinte", 360, "9788565765121", "Young adult");
		Livro livro5= new Livro("O Menino do Pijama Listrado", "John Boine", 1, "Seguinte", 192, "9788535911121", "Ficção infantil");
		
//		Livro livro6= new Livro("O Menino do Pijama Listrado", "John Boine", 1, "Seguinte", 192, "9788535911121", "Ficção infantil");	
		Livro livroNaoCadastrado= new Livro("A Costureira", "Kate Alcott", 1, "Geração", 376, "9788581301310", "Romance");	
						
		try {
			ControladorLivro.adicionarLivro(livro1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ControladorLivro.adicionarLivro(livro2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ControladorLivro.adicionarLivro(livro3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ControladorLivro.adicionarLivro(livro4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ControladorLivro.adicionarLivro(livro5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		System.out.println("Há " + ControladorLivro.getNumeroLivros() + " livros cadastrados.");		
		ControladorLivro.exibirLivros();
				
		for(Livro livro : ControladorLivro.getLivros()) {
			try {
				ControladorExemplar.adicionarExemplar(new Exemplar(livro));
				ControladorExemplar.adicionarExemplar(new Exemplar(livro));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Há " + ControladorExemplar.getNumeroExemplares() + " exemplares cadastrados.");	
		ControladorExemplar.exibirExemplares();
				
		System.out.println("\nBuscando livro pelo ISBN \"9788565765015\"");
		try {
			Livro l = ControladorLivro.buscarLivro("9788565765015");
			System.out.println(l+"\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		
		System.out.println("\nBuscando livros pelo autor \"Kiera Cass\"");
		ArrayList<Livro> ls;
		try {
			ls = ControladorLivro.buscarLivrosPorAutor("Kiera Cass");			
			for(Livro livro : ls) 
				System.out.println(livro + "\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		System.out.println("\nBuscando livros pelo título \"O Mundo de Sofia\"");
		try {
			ls= ControladorLivro.buscarLivrosPorTitulo("O Mundo de Sofia");
			for(Livro livro : ls) 
				System.out.println(livro + "\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		System.out.println("\nEditando livro \"Pollyana\"");
		try {
			Livro l= new Livro("Pollyana", "Eleanor Porter", 1, "Pé de Letra", 184, "978-8595201170", "Clássico");
			ArrayList<Exemplar> es= ControladorExemplar.buscarExemplares(livro1);
			
			if(es.isEmpty())
				ControladorLivro.editarLivro(livro1, l);			
			else {
				
				ControladorLivro.editarLivro(livro1, l);				
				for(Exemplar e : es)
					ControladorExemplar.editarExemplar(e, new Exemplar(e.getCodigo(), l));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		ControladorLivro.exibirLivros();
		ControladorExemplar.exibirExemplares();
		
		
		System.out.println("\nExcluindo livro e exemplares de \"O Mundo de Sofia\"");
		try {
			ArrayList<Exemplar> es= ControladorExemplar.buscarExemplares(livro2);
			
			if(es.isEmpty())
				ControladorLivro.removerLivro(livro2);
			else {
				System.err.println("Os " + es.size() + " exemplares vinculados a este livro serão apagados.\n");
				for(Exemplar e : es)
					ControladorExemplar.removerExemplar(e);
				
				ControladorLivro.removerLivro(livro2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		System.out.println("Há " + ControladorLivro.getNumeroLivros() + " livros cadastrados.");		
		ControladorLivro.ordenarLivros();	
		ControladorLivro.exibirLivros();	
		System.out.println("Há " + ControladorExemplar.getNumeroExemplares() + " exemplares cadastrados.");	
		ControladorExemplar.exibirExemplares();
				
		System.out.println("Removendo exemplares do livro5= \"O Menino do Pijama Listrado\"");
		try {
			
			for(Exemplar e : ControladorExemplar.buscarExemplares(livro5))
				ControladorExemplar.removerExemplar(e);			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		System.out.println("Removendo exemplar 1 (é do livro \"Pollyana\")");
		try {
			ControladorExemplar.removerExemplar("1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Editando exemplar8 (colocando como o livro \"A Seleção\")");
		try {
			Exemplar e= ControladorExemplar.buscarExemplar("8");
			ControladorExemplar.editarExemplar(e, new Exemplar(e.getCodigo(), livro3));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Há " + ControladorLivro.getNumeroLivros() + " livros cadastrados.");		
		ControladorLivro.ordenarLivros();	
		ControladorLivro.exibirLivros();	
		System.out.println("Há " + ControladorExemplar.getNumeroExemplares() + " exemplares cadastrados.");
		ControladorExemplar.exibirExemplares();
	}

}