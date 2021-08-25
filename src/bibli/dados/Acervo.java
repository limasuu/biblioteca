package bibli.dados;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import bibli.aplicacao.Principal;

public class Acervo {

	private static File file;
	private static FileReader fileReader;
	private static BufferedReader bufferedReader;
	private static FileWriter fileWriter;
	private static BufferedWriter bufferedWriter;

	private static void iniciarArquivo(String nomeArquivo) {

		file= new File(nomeArquivo);
		if(!file.exists()) {
			try {
				file.createNewFile();

			} catch (IOException e1) {				
				System.err.println( Principal.getMensagem("erro.arquivo.IOException") );
			}
		}
	}

	protected static void iniciarLeitor(String nomeArquivo) {

		iniciarArquivo(nomeArquivo);

		try {
			fileReader= new FileReader(file);
			bufferedReader= new BufferedReader(fileReader);

		} catch (FileNotFoundException e) {			
			System.err.println( Principal.getMensagem("erro.arquivo.FileNotFoundException") );
		}
	}

	protected static void iniciarEscritor(String nomeArquivo) {

		iniciarArquivo(nomeArquivo);

		try {
			fileWriter= new FileWriter(file, false);
			bufferedWriter= new BufferedWriter(fileWriter);

		} catch (IOException e) {			
			System.err.println( Principal.getMensagem("erro.arquivo.IOException") );
		}
	}

	protected static String ler() {

		String linha= null;

		try {
			linha= bufferedReader.readLine();			
		} catch (IOException e) {
			System.err.println( Principal.getMensagem("erro.arquivo.IOException") );
		}

		return linha;
	}

	protected static void escrever(String linha) {

		try {
			bufferedWriter.write(linha);
			bufferedWriter.newLine();
		} catch (IOException e) {			
			System.err.println( Principal.getMensagem("erro.arquivo.IOException") );
		}
	}

	protected static void fecharLeitor() {

		try {
			bufferedReader.close();

		} catch (IOException e) {			
			System.err.println( Principal.getMensagem("erro.arquivo.IOException") );
		}
	}

	protected static void fecharEscritor() {

		try {
			bufferedWriter.close();

		} catch (IOException e) {			
			System.err.println( Principal.getMensagem("erro.arquivo.IOException") );
		}
	}

	protected static LocalDateTime resgatarData(String linhaData) {

		LocalDateTime data= null;

		if(!linhaData.equals( Principal.getMensagem("dado.nulo") )) {

			String[] partesData= linhaData.split("_");
			int dia= Integer.parseInt(partesData[0]);
			int mes= Integer.parseInt(partesData[1]);
			int ano= Integer.parseInt(partesData[2]);
			int hora= Integer.parseInt(partesData[3]);
			int minuto= Integer.parseInt(partesData[4]);
			data= LocalDateTime.of(ano, mes, dia, hora, minuto);
		}

		return data;
	}

	protected static String prepararData(LocalDateTime data) {

		String linhaData= new String();
		if(data != null) {
			
			int[] partesData= new int[5];			

			partesData[0]= data.getDayOfMonth();
			partesData[1]= data.getMonthValue();
			partesData[2]= data.getYear();
			partesData[3]= data.getHour();
			partesData[4]= data.getMinute();		

			for(int i=0; i<partesData.length; i++) {
				linhaData= linhaData + partesData[i];
				if(i < (partesData.length-1))
					linhaData= linhaData + "_";
			}			
		}else
			linhaData= Principal.getMensagem("dado.nulo");			
			
		return linhaData;
	}
	
	protected static String prepararLinha(String[] atributos) {

		String linha= new String();

		for(int i=0; i< atributos.length; i++) {
			linha= linha + atributos[i];
			if(i < (atributos.length-1))
				linha= linha + "##";
		}
			
		return linha;
	}
}
