package bibli.modelo;

import java.util.HashMap;

public class AcervoUsuario {

	private static HashMap<String, Usuario> usuarios= new HashMap<String, Usuario>();

	public static HashMap<String, Usuario> getUsuarios() {

		return usuarios;
	}

	public static int getNumeroUsuarios() {

		return usuarios.size();
	}

	public static boolean verificarExistenciaUsuario(String codigo) {

		return usuarios.containsKey(codigo);		
	}

	public static boolean verificarSituacaoUsuario(String codigoUsuario) {
		
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
	
	public static Usuario editarUsuario(Usuario usuarioAtualizado) {

		return usuarios.replace(usuarioAtualizado.getCodigo(), usuarioAtualizado);
	}

	public static Usuario removerUsuario(String codigo) {

		return usuarios.remove(codigo);
	}
}
