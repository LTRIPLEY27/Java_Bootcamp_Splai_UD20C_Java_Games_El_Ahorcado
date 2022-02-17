package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class InsertarPalabras extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panelLista, insertarDatos;

	private String str;
	private ArrayList<String> categorys = new ArrayList<String>();
	private JTextField textField;
	private JButton volver;
	private JComboBox<Object> select;
	
	public InsertarPalabras() 
	{
		setResizable(false);
		setTitle("Ahorcado");
		setBounds(400, 200, 750, 610);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelLista = new JPanel();
		panelLista.setBounds(10, 11, 714, 226);
		contentPane.add(panelLista);
		
		insertarDatos = new JPanel();
		insertarDatos.setBounds(10, 248, 714, 312);
		contentPane.add(insertarDatos);
		insertarDatos.setLayout(null);
		
		volver = new JButton("Volver");
		volver.setBounds(400, 150, 85, 25);
		insertarDatos.add(volver);
	}
	
	public void mostrarDocker(Hashtable<String, ArrayList<String>> docker)
	{
		docker.forEach((categoria, lista) -> {
			
			str = "";
			lista.forEach((palabra) -> {str += palabra + ", ";});
			
			str = str.substring(0, str.length()-2);
			
			JLabel categ = new JLabel("<html><body style=\"width: 100%; text-align: center;\"><u><i>" + categoria + "</i></u><div style=\"width: 500px;\">" + str + "</div></body></html>");

			panelLista.add(categ);
		});
		


		JLabel categ = new JLabel("Categoria: ");
		select = new JComboBox<Object>();
		JLabel texto = new JLabel("Palabra a añadir: ");
		textField = new JTextField();
		JButton addBtn = new JButton("Añadir");
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				docker.get(select.getSelectedItem()).add(textField.getText());
				
				panelLista.removeAll();
				
				docker.forEach((categoria, lista) -> {
					
					str = "";
					lista.forEach((palabra) -> {str += palabra + ", ";});
					
					str = str.substring(0, str.length()-2);
					
					JLabel categ = new JLabel("<html><body style=\"width: 100%; text-align: center;\"><u><i>" + categoria + "</i></u><div style=\"width: 500px;\">" + str + "</div></body></html>");

					panelLista.add(categ);
				});
				
				validate();
				repaint();
			}
		});
		
		docker.forEach((categoria, lista) -> {
			categorys.add(categoria);
		});
		
		select.setModel(new DefaultComboBoxModel<Object>(categorys.toArray()));
		
		categ.setBounds(80, 53, 80, 15);
		select.setBounds(170, 49, 120, 20);
		texto.setBounds(320, 53, 120, 15);
		textField.setBounds(441, 50, 145, 20);
		textField.setColumns(10);
		addBtn.setBounds(250, 150, 85, 25);
		
		insertarDatos.add(categ);
		insertarDatos.add(select);
		insertarDatos.add(texto);
		insertarDatos.add(textField);
		insertarDatos.add(addBtn);
	}

	public JButton getVolver() {
		return volver;
	}
}
