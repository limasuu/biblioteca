package bibli.controle;

import bibli.modelo.AcervoPessoa;
import bibli.modelo.Pessoa;

public class ControladorPessoa {

	public static boolean exibirPessoa (String codigo){

		if(!ValidadorPessoa.validarCampo(codigo)) {
			System.err.println("Código inválido.");
			return false;
		}

		if(!AcervoPessoa.verificarExistenciaPessoa(codigo)){
			System.err.println("\nNão foi encontrada pessoa com o código informado.");
			return false;
		}	

		System.out.println("\n" + AcervoPessoa.buscarPessoa(codigo));

		return true;
	}

	public static void exibirPessoas() {

		if(AcervoPessoa.getNumeroPessoas() == 0)
			System.out.println("Não há pessoas cadastradas.");
		else {

			System.out.println("\n----------------- Pessoas -----------------");
			System.out.println("Quantidade: " + AcervoPessoa.getNumeroPessoas());	
			System.out.println("------------------------------------------");

			for(Pessoa pessoa : AcervoPessoa.getPessoas().values())
				System.out.println(pessoa+"\n");

			System.out.println("------------------------------------------");
		}
	}

	public static String adicionarPessoa(String nome, String endereco, 
			String telefone, String email) {

		if(!ValidadorPessoa.validarCamposPessoa(nome, endereco, telefone, email)){
			System.err.println("Há campos inválidos.");
			return null;
		}

		Pessoa pessoa= new Pessoa(nome, endereco, telefone, email);
		AcervoPessoa.adicionarPessoa(pessoa);

		return pessoa.getCodigo();
	}	

	public static String editarPessoa(String codigo, String novoNome, String novoEndereco, 
			String novoTelefone, String novoEmail) {
		
		if(!AcervoPessoa.verificarExistenciaPessoa(codigo)){
			System.err.println("Não foi encontrada pessoa com o código informado.");
			return null;
		}		

		if(AcervoPessoa.verificarSeEhFuncionario(codigo)){
			System.err.println("A edição de funcionários deve ser realizada em sua respectiva seção.");
			return null;
		}

		if(!ValidadorPessoa.validarCamposPessoa(novoNome, novoEndereco, novoTelefone, novoEmail)){
			System.err.println("Há campos inválidos.");
			return null;
		}		
		
		Pessoa pessoaAtualizada= ValidadorPessoa.validarAtualizacaoPessoa(codigo, novoNome, novoEndereco, novoTelefone, novoEmail);

		if(pessoaAtualizada == null){
			System.err.println("\nNão há mudanças para realizar.");
			return null;
		}	

		AcervoPessoa.editarPessoa(pessoaAtualizada);	

		return pessoaAtualizada.getCodigo();
	}

	public static String removerPessoa(String codigo) {

		if(!ValidadorObra.validarCampo(codigo)) {
			System.err.println("Código inválido.");
			return null;
		}

		if(!AcervoPessoa.verificarExistenciaPessoa(codigo)){
			System.err.println("Não foi encontrada pessoa com o código informado.");
			return null;
		}		
		
		if(AcervoPessoa.verificarSeEhFuncionario(codigo)){
			System.err.println("A edição de funcionários deve ser realizada em sua respectiva seção.");
			return null;
		}

		String nome= AcervoPessoa.buscarPessoa(codigo).getNome();
		AcervoPessoa.removerPessoa(codigo);

		return nome;
	}	
}
