package bibli.dados;

import java.time.LocalDateTime;
import java.util.HashMap;

import bibli.aplicacao.Principal;
import bibli.modelo.Funcionario;
import bibli.modelo.Usuario;

public class AcervoUsuario extends Acervo{

	private static HashMap<String, Usuario> usuarios= new HashMap<String, Usuario>();

	public static HashMap<String, Usuario> getUsuarios() {

		return usuarios;
	}

	public static int getNumeroUsuarios() {

		return usuarios.size();
	}

	public static boolean verificarExistencia(String codigo) {

		return usuarios.containsKey(codigo);		
	}

	public static boolean verificarBloqueio(String codigoUsuario) {

		return usuarios.get(codigoUsuario).isBloqueado();
	}

	public static boolean verificarSeEhFuncionario(String codigo) {

		return usuarios.get(codigo) instanceof Funcionario;
	}	

	public static HashMap<String, Usuario> buscarUsuariosBloqueados() {

		HashMap<String, Usuario> usuariosEncontrados= new HashMap<String, Usuario>();

		for(Usuario usuario : usuarios.values()) 
			if(usuario.isBloqueado() && usuario.getDataFimBloqueio()!=null)
				usuariosEncontrados.put(usuario.getCodigo(), usuario);	

		return usuariosEncontrados;		
	}

	public static Usuario buscarUsuario(String codigo) {

		return usuarios.get(codigo);
	}

	public static void adicionarUsuario(Usuario usuario) {

		usuarios.put(usuario.getCodigo(), usuario);
	}

	public static Usuario removerUsuario(String codigo) {

		return usuarios.remove(codigo);
	}

	public static void iniciar() {

		iniciarLeitor( Principal.getMensagem("arquivo.usuario") );

		String linha= null;

		linha= ler();

		if(linha != null) {
			int totalUsuariosJaCadastrados= Integer.parseInt(linha);
			Usuario.setTotalUsuariosJaCadastrados(totalUsuariosJaCadastrados);
		}

		while(linha != null) {

			linha= ler();

			if(linha != null) {
				String[] atributosUsuario = linha.split("##");

				String codigo= atributosUsuario[0];
				String nome= atributosUsuario[1];
				String endereco= atributosUsuario[2];
				String telefone= atributosUsuario[3];
				String email= atributosUsuario[4];
				boolean bloqueado= Boolean.parseBoolean(atributosUsuario[5]);
				LocalDateTime dataFimBloqueio= resgatarData(atributosUsuario[6]);				
				
				Usuario usuario= new Usuario(codigo, nome, endereco, telefone, email, bloqueado, dataFimBloqueio);
				adicionarUsuario(usuario);
			}
		}

		fecharLeitor();
	}

	public static void encerrar() {

		iniciarEscritor( Principal.getMensagem("arquivo.usuario") );

		escrever(String.valueOf(Usuario.getTotalUsuariosJaCadastrados()));

		for(Usuario usuario : usuarios.values()) {

			if (!(usuario instanceof Funcionario)) {

				String[] atributosUsuario= new String[7];

				atributosUsuario[0]= usuario.getCodigo();
				atributosUsuario[1]= usuario.getNome();
				atributosUsuario[2]= usuario.getEndereco();
				atributosUsuario[3]= usuario.getTelefone();
				atributosUsuario[4]= usuario.getEmail();
				atributosUsuario[5]= String.valueOf(usuario.isBloqueado());
				atributosUsuario[6]= prepararData(usuario.getDataFimBloqueio());

				String linha= prepararLinha(atributosUsuario);
				escrever(linha);
			}
		}		

		fecharEscritor();
	}	
}
