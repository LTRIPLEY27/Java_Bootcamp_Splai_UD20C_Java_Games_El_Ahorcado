package com.game02.Ahorcado;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import Objetos.Ahorcado;
import View.Formulario;
import View.InsertarPalabras;
import View.InterfazJuego;

/**
 * 
 * @author JOAN
 *
 */
public class App 
{
	private static Formulario form;
	private static InterfazJuego interfaz;
	private static InsertarPalabras docker;
	
    public static void main( String[] args )
    {
    	Hashtable<String, ArrayList<String>> palabrasDoc = new Hashtable<String, ArrayList<String>>();
		palabrasDoc.put("Superhéroes", new ArrayList<String>(Arrays.asList("ironman","spiderman","hulk","superman","batman","aquaman","supergirl","flash","starlord","groot")));
		palabrasDoc.put("Misterio", new ArrayList<String>(Arrays.asList("agatha","allan","conan","john","dashiel","edogawa","raymond","josephine","truman","juan")));
		palabrasDoc.put("Deportes", new ArrayList<String>(Arrays.asList("futbol","tenis","baloncesto","badminton","volleyball","ciclismo","hockey","balomano","natacion","motociclismo")));
		palabrasDoc.put("Literatura", new ArrayList<String>(Arrays.asList("aventuras","terror","misterio","policiaca","cuento","humor","romantica","poesia","teatro","mitologia")));
		
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
				try 
				{
					form = new Formulario();
					
					form.getBtn().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) 
						{
							form.setVisible(false);
							interfaz.setVisible(true);
							
							int intentos;
							
							if(form.getFacil().isSelected())
							{
								intentos = 10;
							}
							else if(form.getNormal().isSelected())
							{
								intentos = 8;
							}
							else
							{
								intentos = 6;
							}
							
							interfaz.setJuego(new Ahorcado(intentos, (String)form.getComboBox().getSelectedItem(), palabrasDoc));
							interfaz.iniciarJuego();
						}
					});
					
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
				try 
				{					
					interfaz = new InterfazJuego();
					interfaz.getInicio().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) 
						{
							
							form.setVisible(true);
							interfaz.setVisible(false);
							
						}
					});

					interfaz.getNewGame().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) 
						{
							
							form.setVisible(true);
							interfaz.setVisible(false);
							
						}
					});

					interfaz.getAddDoc().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) 
						{
							
							docker.setVisible(true);
							interfaz.setVisible(false);
							docker.mostrarDocker(interfaz.getJuego().getPalabrasDoc());
						}
					});
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
				try 
				{
					docker = new InsertarPalabras();
					docker.getVolver().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) 
						{
							interfaz.setVisible(true);
							docker.setVisible(false);
						}
					});
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
    }
}
