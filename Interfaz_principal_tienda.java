
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Scrollbar;



public class Interfaz_principal_tienda extends JFrame {

	private JPanel contentPane;
	private JTextField txtnombre_empleado;
	private JTextField txtcedula_empleado;
	private JTextField txtcargo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Interfaz_principal_tienda frame = new Interfaz_principal_tienda();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public Interfaz_principal_tienda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cedula del empleado");
		lblNewLabel.setBounds(30, 137, 134, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre completo");
		lblNewLabel_1.setBounds(30, 196, 111, 14);
		contentPane.add(lblNewLabel_1);
		
		txtnombre_empleado = new JTextField();
		txtnombre_empleado.setBounds(151, 193, 86, 20);
		contentPane.add(txtnombre_empleado);
		txtnombre_empleado.setColumns(10);
		
		txtcedula_empleado = new JTextField();
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
		
		txtcargo = new JTextField();
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
		
		JButton btnNewButton_2 = new JButton("Siguiente");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Interfaz_secundaria_cliente primer_siguiente = new Interfaz_secundaria_cliente();
					primer_siguiente.setLocationRelativeTo(null);
					primer_siguiente.setVisible(true);
					
					Interfaz_principal_tienda.this.setVisible(false);
					}catch (Exception error) {
						JOptionPane.showMessageDialog(null, "Error, el programa no puede mostrar la ventana.");
					}
			}
		});
		btnNewButton_2.setBounds(450, 372, 89, 23);
		contentPane.add(btnNewButton_2);
		
		Scrollbar scrollbar = new Scrollbar();
		scrollbar.setBounds(497, 114, 17, 112);
		contentPane.add(scrollbar);
		
		
		
		
		
		
	}
}
