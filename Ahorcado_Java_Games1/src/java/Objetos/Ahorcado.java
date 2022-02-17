package Objetos;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;

public class Ahorcado 
{
	private ArrayList<String> palabras;
	private Hashtable<String, ArrayList<String>> palabrasDoc = new Hashtable<String, ArrayList<String>>();
	private int lifeNumber = 5;
	private String palabraSecreta;
	private char[] palabraSecretaMostrar;
	private int intents = 0;
	private int intentsMax;
	private int contador;
	private Hashtable<Integer, String[]> imagenes = new Hashtable<Integer, String[]>();
	
	public Ahorcado(int intents, String opcion, Hashtable<String, ArrayList<String>> palabrasDoc) 
	{
		this.palabrasDoc = palabrasDoc;
		this.intentsMax = intents;
		setPalabras(opcion);
		setPalabraSecreta();
		setPalabraSecretaMostrar();
		imagenes.put(10, new String[]{"","../intento1.png", "../intento2.png", "../intento3.png", "../intento4.png", "../intento5.png", "../intento6.png", "../intento7.png", "../intento8.png", "../intento9.png", "../intento10.png"});
		imagenes.put(8, new String[]{"","../intento3.png", "../intento4.png", "../intento5.png", "../intento6.png", "../intento7.png", "../intento8.png", "../intento9.png", "../intento10.png"});
		imagenes.put(6, new String[]{"","../intento5.png", "../intento6.png", "../intento7.png", "../intento8.png", "../intento9.png", "../intento10.png"});
	}

	public int getContador() 
	{
		return contador;
	}

	public void setContador(int contador) 
	{
		this.contador = contador;
	}

	public int getLifeNumber() 
	{
		return lifeNumber;
	}
	
	public ArrayList<String> getPalabras() 
	{
		return palabras;
	}

	public void setPalabras(String opcion)  
	{
		this.palabras = palabrasDoc.get(opcion);
	}
	
	public String getPalabraSecreta() 
	{
		return palabraSecreta;
	}

	public void setPalabraSecreta() 
	{
		int numero = (int) (Math.random() * palabras.size());
		
		palabraSecreta = palabras.get(numero).toUpperCase();
	}

	public int getIntents() {
		return intents;
	}

	public String getPalabraSecretaMostrar() 
	{
		String str = "";
		
		for (int i = 0; i < palabraSecretaMostrar.length; i++) 
		{
			str += palabraSecretaMostrar[i] + " ";
		}
		return str;
	}

	public void setPalabraSecretaMostrar() 
	{
		char[] str = new char[palabraSecreta.length()];
		
		for (int i = 0; i < palabraSecreta.length(); i++) 
		{
			str[i] = '_';
		}
		this.palabraSecretaMostrar = str;
	}

	public int getIntentsMax() 
	{
		return intentsMax;
	}

	public boolean estaLetra(char letra) 
	{
		boolean letraEsta = false;
		
		for(int i = 0; i < palabraSecreta.length(); i++) 
		{
			if(palabraSecreta.charAt(i) == letra) 
			{
				palabraSecretaMostrar[i] = letra;
				letraEsta = true;
			} 
		}
		
		contador++;
		
		if(!letraEsta)
		{
			intents++;
		}
		
		return letraEsta;
	}

	public boolean palabraSecretaDesvelada() 
	{
		boolean guiones = true;
		for (int i = 0; i < palabraSecretaMostrar.length && guiones; i++) 
		{
			if(palabraSecretaMostrar[i] == '_')
			{
				guiones = false;
			}
		}
		
		return guiones;
	}

	public void intentoResolver(String showInputDialog) 
	{
		if(showInputDialog.equalsIgnoreCase(palabraSecreta))
		{
			for (int i = 0; i < palabraSecretaMostrar.length; i++) 
			{
				palabraSecretaMostrar[i] = palabraSecreta.charAt(i);
			}
		}
		else
		{
			intents++;
		}
	}

	public String[] getImagenes() 
	{
		return imagenes.get((Integer)intentsMax);
	}

	public void ejecutarPista(JButton[] array) 
	{
		lifeNumber--;
		boolean pistaValida;
		char letra;
		do 
		{
			pistaValida = true;
			letra = palabraSecreta.charAt((int)(Math.random()*palabraSecreta.length()));
			
			for (int i = 0; i < palabraSecretaMostrar.length && pistaValida; i++) 
			{
				if(palabraSecretaMostrar[i] == letra)
				{
					pistaValida = false;
				}
			}
		}
		while(!pistaValida);
		
		for(int i = 0; i < palabraSecreta.length(); i++) 
		{
			if(palabraSecreta.charAt(i) == letra) 
			{
				palabraSecretaMostrar[i] = letra;
			} 
		}
		
		for (int i = 0; i < array.length; i++) 
		{
			if(array[i].getText().charAt(0) == letra) 
			{
				array[i].setEnabled(false);
			} 
		}
	}

	public Hashtable<String, ArrayList<String>> getPalabrasDoc() {
		return palabrasDoc;
	}
}
