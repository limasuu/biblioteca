package bibli.controle;

import java.util.ArrayList;

public class Validador {

	public static boolean validarCampo(String campo) {

		if(campo == null)
			return false;		

		if(campo.isBlank())
			return false;

		return true;		
	}

	public static boolean validarStrings(ArrayList<String> strings) {

		for(String string : strings)
			if(string == null)
				return false;

		for(String string : strings)
			if(string.isBlank())
				return false;

		return true;		
	}	
}
