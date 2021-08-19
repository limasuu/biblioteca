package bibli.controle;

import java.util.ArrayList;

public class ValidadorEmprestimo extends Validador{	
	
	public static boolean validarCamposEmprestimo(String codigoFuncionario, 
			String codigoUsuario, 
			String codigoExemplar) {
		
		ArrayList<String> strings= new ArrayList<String>();
		strings.add(codigoFuncionario);
		strings.add(codigoUsuario);
		strings.add(codigoExemplar);
	
		return validarStrings(strings);		
	}	

}
