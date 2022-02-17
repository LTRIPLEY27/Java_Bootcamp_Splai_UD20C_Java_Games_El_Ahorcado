package View;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Objetos.Ahorcado;

import java.awt.FlowLayout;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author JOAN
 *
 */
public class InterfazJuego extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, menu, palabra, vidas, palabra_secreta, botones, imagenes;
	private JButton[] array = new JButton[27];
	private JTextField textPalabra;
	private JLabel pistas = new JLabel(), colgao = new JLabel();
	
	private Ahorcado juego;
	
	private JButton inicio, resolver, pista;
	
	private JMenuItem newGame, numIntentos, addDoc;
	
	public InterfazJuego() 
	{
		setResizable(false);
		setTitle("Ahorcado");
		setBounds(410, 200, 750, 610);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar barraMenu = new JMenuBar();
        setJMenuBar(barraMenu);
		JMenu menuArchivo = new JMenu("Archivo");
		barraMenu.add(menuArchivo);
		JMenu ayuda = new JMenu("Ayuda");
		barraMenu.add(ayuda);
		JMenuItem comoJugar = new JMenuItem("Como jugar");
		ayuda.add(comoJugar);
		JMenuItem acercaDe = new JMenuItem("Acerca de");
		ayuda.add(acercaDe);
		
		comoJugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(null, "Tienes 5 pistas.\nPulsando las teclas de abajo indicas la letra que quieres, si fallas cuenta como error si aciertas se muestra la letra.\nEn todo momento tienes el boton resolver si sabes la palabra.");
			}
		});
		
		acercaDe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(null, "Aplicacion desarrollada por los alumnos:\nAdrián Rodriguez\nIsabel Calzadilla\nJoan Rofes");
			}
		});
		
		newGame = new JMenuItem("Nuevo juego");
		menuArchivo.add(newGame);
		
		addDoc = new JMenuItem("Añadir Palabras");
		menuArchivo.add(addDoc);
		
		JMenuItem salir = new JMenuItem("Salir");
		menuArchivo.add(salir);
		salir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
				
			}
		});
		
		numIntentos = new JMenuItem("Intentos: 0");
		menuArchivo.add(numIntentos);
		
		menu = new JPanel();
		palabra = new JPanel();
		vidas = new JPanel();
		botones = new JPanel();
		imagenes = new JPanel();
		palabra_secreta = new JPanel();
		inicio = new JButton("Iniciar juego");
		pista = new JButton("Pista!");
		pista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(JOptionPane.showConfirmDialog(contentPane, "Estas seguro de perder una pista? (pistas restantes " + juego.getLifeNumber() + ")") == 0)
				{
					juego.ejecutarPista(array);
					textPalabra.setText(juego.getPalabraSecretaMostrar());

					if(juego.getLifeNumber() != 0)
					{
						pistas.setIcon(new ImageIcon(new ImageIcon("../pista"+juego.getLifeNumber()+".png").getImage().getScaledInstance(350, 55, Image.SCALE_SMOOTH)));
					}
					else
					{
						pistas.setIcon(new ImageIcon(new ImageIcon("../sinpistas.png").getImage().getScaledInstance(350, 55, Image.SCALE_SMOOTH)));
						pista.setEnabled(false);
					}

					
					fin();
				}
			}
		});
		
		resolver = new JButton("Resolver");
		resolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				juego.intentoResolver(JOptionPane.showInputDialog("La palabra es: "));
				textPalabra.setText(juego.getPalabraSecretaMostrar());

				colgao.setIcon(new ImageIcon(new ImageIcon(juego.getImagenes()[juego.getIntents()]).getImage().getScaledInstance(310, 525, Image.SCALE_SMOOTH)));
				fin();
			}
		});
		textPalabra = new JTextField();
		
		menu.setBounds(10, 10, 378, 161);
		menu.setLayout(null);
		inicio.setBounds(10, 16, 358, 57);
		resolver.setBounds(10, 84, 358, 66);
		menu.add(inicio);
		menu.add(resolver);
		menu.setBorder(new TitledBorder(null, "Menu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		palabra.setBounds(10, 182, 378, 189);
		palabra.setBorder(new LineBorder(new Color(0, 0, 0)));
		palabra.setLayout(null);
		vidas.setBounds(10, 11, 358, 64);
		vidas.setBorder(new EmptyBorder(0, 0, 0, 0));
		vidas.add(pistas);
		pista.setBounds(137, 80, 105, 23);
		palabra.add(pista);
		palabra.add(vidas);
		palabra_secreta.setBounds(10, 114, 358, 64);
		palabra_secreta.setLayout(null);
		textPalabra.setBounds(21, 21, 314, 32);
		textPalabra.setEditable(false);
		textPalabra.setBackground(Color.BLACK);
		textPalabra.setForeground(Color.WHITE);
		textPalabra.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPalabra.setHorizontalAlignment(SwingConstants.CENTER);
		palabra_secreta.add(textPalabra);
		palabra_secreta.setBorder(new TitledBorder(null, "Palabra Secreta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		palabra.add(palabra_secreta);
		
		botones.setBounds(10, 382, 378, 168);
		botones.setBorder(new TitledBorder(null, "Teclado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		botones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		addBotones();
		
		imagenes.setBounds(398, 10, 326, 540);
		imagenes.setBorder(new EmptyBorder(0, 0, 0, 0));
		imagenes.setBorder(new LineBorder(new Color(0, 0, 0)));
		imagenes.add(colgao);

		contentPane.add(menu);
		contentPane.add(palabra);
		contentPane.add(botones);
		contentPane.add(imagenes);
	}
	
	private void addBotones()
	{
		int aux = 0;
		for (int i = 0; i < array.length; i++) 
		{
			if(i != 14)
			{
				array[i] = new JButton("" + (char)(65+aux));
				aux++;
			}
			else
			{
				array[i] = new JButton("Ñ");
			}
			
			array[i].setEnabled(false);

			array[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					JButton btn = (JButton)e.getSource();
					btn.setEnabled(false);
					
					if(juego.estaLetra(btn.getText().charAt(0)))
					{
						textPalabra.setText(juego.getPalabraSecretaMostrar());
					}
					colgao.setIcon(new ImageIcon(new ImageIcon(juego.getImagenes()[juego.getIntents()]).getImage().getScaledInstance(310, 525, Image.SCALE_SMOOTH)));
					
					fin();
				}
			});
			
			botones.add(array[i]);
		}
	}
	
	public void iniciarJuego()
	{
		textPalabra.setText(juego.getPalabraSecretaMostrar());
		resolver.setEnabled(true);
		pista.setEnabled(true);
		
		pistas.setIcon(new ImageIcon(new ImageIcon("../pista5.png").getImage().getScaledInstance(350, 55, Image.SCALE_SMOOTH)));
		colgao.setIcon(null);
		
		for (int i = 0; i < array.length; i++) 
		{
			array[i].setEnabled(true);
		}
	}

	public Ahorcado getJuego() {
		return juego;
	}

	public void setJuego(Ahorcado juego) {
		this.juego = juego;
	}

	public JButton getInicio() {
		return inicio;
	}

	private void fin() 
	{
		numIntentos.setText("Intentos: " + juego.getIntents());
		if(juego.palabraSecretaDesvelada())
		{
			JOptionPane.showMessageDialog(null, "Has ganado!!");
			deshabilitarBotones();
		}
		else if(juego.getIntents() == juego.getIntentsMax())
		{
			JOptionPane.showMessageDialog(null, "Has perdido!!");
			deshabilitarBotones();
		}
	}

	private void deshabilitarBotones() 
	{
		resolver.setEnabled(false);
		pista.setEnabled(false);
		for (int i = 0; i < array.length; i++) 
		{
			array[i].setEnabled(false);
		}
	}

	public JMenuItem getNewGame() {
		return newGame;
	}

	public JMenuItem getAddDoc() {
		return addDoc;
	}
}
