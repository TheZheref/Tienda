import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Interfaz extends JFrame {
	JTextArea texto;
	JLabel titulo;
	JButton btnpanel1,btnpanel2,btnpanel3,btnpanel4;
	JPanel panel1;
	static JPanel panel2;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Demo application");
		frame.setSize(387, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		placeComponents(panel);

		frame.setVisible(true);
	}
	
	


	private static void placeComponents(JPanel panel) {
		panel = new JPanel();
		
		panel2 = new JPanel();
		panel2.setBackground(Color.cyan);
		panel2.setBounds(5,112,370,200);
		panel2.setLayout(null);
		
		
		JLabel titulo = new JLabel("Ejemplo basico");
		titulo.setFont(new Font ("Yu Gothic UI Semilight",Font.PLAIN,30));
		titulo.setBounds(10,14,221,51);
		panel.add(titulo);
		
		JButton btnpanel1 = new JButton("Panel del Empleado");
		btnpanel1.setBounds(10,76,89,23);
		panel.add(btnpanel1);
		
		JButton btnpanel2 = new JButton("Panel del Cliente");
		btnpanel2.setBounds(50,76,89,23);
		panel.add(btnpanel2);
		
		JButton btnpanel3 = new JButton("Panel del Producto");
		btnpanel3.setBounds(100,76,80,23);
		panel.add(btnpanel3);

		JButton btnpanel4 = new JButton("Panel de venta");
		btnpanel4.setBounds(150,76,80,23);
		panel.add(btnpanel4);
		
		cargarComponentesPanel1(panel);
		
	}




	
	public void Interfaz_principal_tienda2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 460);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cedula del empleado");
		lblNewLabel.setBounds(30, 137, 134, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre completo");
		lblNewLabel_1.setBounds(30, 196, 111, 14);
		contentPane.add(lblNewLabel_1);
		
		JTextField txtnombre_empleado = new JTextField();
		txtnombre_empleado.setBounds(151, 193, 86, 20);
		contentPane.add(txtnombre_empleado);
		txtnombre_empleado.setColumns(10);
		
		JTextField txtcedula_empleado = new JTextField();
		txtcedula_empleado.setBounds(151, 134, 86, 20);
		contentPane.add(txtcedula_empleado);
		txtcedula_empleado.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Lista de empleados");
		lblNewLabel_3.setBounds(356, 86, 121, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Ficha del empleado");
		lblNewLabel_4.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(179, 23, 298, 32);
		contentPane.add(lblNewLabel_4);
		
		JList list = new JList();
		list.setBounds(310, 114, 204, 112);
		contentPane.add(list);
		
		JList list_1 = new JList();
		list_1.setBounds(307, 269, 207, 46);
		contentPane.add(list_1);
		
		JLabel lblNewLabel_6 = new JLabel("Numero de cargo:");
		lblNewLabel_6.setBounds(307, 244, 134, 14);
		contentPane.add(lblNewLabel_6);
		
		JTextField txtcargo = new JTextField();
		txtcargo.setBounds(428, 241, 86, 20);
		contentPane.add(txtcargo);
		txtcargo.setColumns(10);
		
		
		
		DefaultListModel ltProducto2 = new DefaultListModel();
		DefaultListModel ltProducto = new DefaultListModel();
		JButton btnNewButton = new JButton("Mostrar");
		btnNewButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent arg0) {
				 
				 mysql conexion = new mysql();
					
					
					Connection cn = null;
					Statement stm = null;
					ResultSet rs = null;
					
					
					
					try {
						cn = conexion.conectar();
						stm = cn.createStatement();
						
						ltProducto.clear();
						
						rs = stm.executeQuery("SELECT * FROM empleado");
						
						while (rs.next()) {
							
							int cedula = rs.getInt(1);
							String nombre = rs.getString(2);
							String cargo = rs.getString(3);
							
							
							
							
							ltProducto.addElement("Nombre: "+nombre);
							
							list.setModel(ltProducto);
							
							}
						ltProducto2.clear();
						rs = stm.executeQuery("SELECT * FROM cargo");
						
						while (rs.next()) {
							
							int id_cargo = rs.getInt(1);
							String tipo = rs.getString(2);
							
							
							ltProducto2.addElement("ID: "+id_cargo+ " || Tipo: "+tipo);
							
							list_1.setModel(ltProducto2);
							
							
							
							}
							cn.close();
						
					} catch (SQLException eroor) {
						// TODO: handle exception
					}
				}
				 
			
		});
		btnNewButton.setBounds(369, 326, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Agregar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				mysql conexion = new mysql();
				
				
				Connection cn = null;
				Statement stm = null;
				ResultSet rs = null;
				
				
				
				try {
					cn = conexion.conectar();
					stm = cn.createStatement();
					
					ltProducto.clear();
					
					stm.executeUpdate("INSERT INTO empleado (Cedula, nombre_empleado, tipo_de_cargo)" 
							+ "VALUES ("+txtcedula_empleado.getText()+",'"+ txtnombre_empleado.getText()+"',"+ txtcargo.getText()+");" );
					
					rs = stm.executeQuery("SELECT * FROM empleado");
					
					while (rs.next()) {
						
						int cedula = rs.getInt(1);
						String nombre = rs.getString(2);
						String cargo = rs.getString(3);
						
						
						
						ltProducto.addElement("Nombre: "+nombre);
						
						list.setModel(ltProducto);
						
						}
						cn.close();
					
				} catch (SQLException eroor) {
					// TODO: handle exception
				}
			}
				
			
		});
		btnNewButton_1.setBounds(235, 372, 89, 23);
		contentPane.add(btnNewButton_1);
		
		
		
		
		
		
	}
	private static void cargarComponentesPanel1(JPanel panel) {
		Interfaz_principal_tienda deah = new Interfaz_principal_tienda();
		panel2.add(deah);
		panel.add(panel2);
	}



	private void iniciarComponentes() {
		panel1 = new JPanel();
		panel1.setBackground(Color.cyan);
		panel1.setBounds(5,112,370,200);
		panel1.setLayout(null);
		
		titulo = new JLabel("Ejemplo basico");
		titulo.setFont(new Font ("Yu Gothic UI Semilight",Font.PLAIN,30));
		titulo.setBounds(10,14,221,51);
		
		btnpanel1 = new JButton("Panel del Empleado");
		btnpanel1.setBounds(10,76,89,23);

		btnpanel2 = new JButton("Panel del Cliente");
		btnpanel2.setBounds(50,76,89,23);

		btnpanel3 = new JButton("Panel del Producto");
		btnpanel3.setBounds(100,76,80,23);

		btnpanel4 = new JButton("Panel de venta");
		btnpanel4.setBounds(150,76,80,23);
		
		Interfaz_principal_tienda p1 = new Interfaz_principal_tienda();
		this.add(titulo);
		this.add(btnpanel1);
		this.add(btnpanel2);
		this.add(btnpanel3);
		this.add(btnpanel4);
		panel1.add(p1);
		this.add(panel1);
	}

}
