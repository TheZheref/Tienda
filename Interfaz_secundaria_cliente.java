import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Interfaz_secundaria_cliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtcedula;
	private JTextField txtnombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interfaz_secundaria_cliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cedula ");
		lblNewLabel.setBounds(10, 83, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre completo");
		lblNewLabel_1.setBounds(10, 144, 122, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("Ficha del Cliente");
		lblNewLabel_4.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(156, 11, 298, 32);
		contentPane.add(lblNewLabel_4);
		
		txtcedula = new JTextField();
		txtcedula.setBounds(136, 80, 86, 20);
		contentPane.add(txtcedula);
		txtcedula.setColumns(10);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(136, 141, 86, 20);
		contentPane.add(txtnombre);
		txtnombre.setColumns(10);
		
		JList list = new JList();
		list.setBounds(257, 65, 247, 113);
		contentPane.add(list);
		DefaultListModel ltProducto = new DefaultListModel();
		JButton btnNewButton = new JButton("Agregar");
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
					
					stm.executeUpdate("INSERT INTO cliente (Cc_Cliente, Nombre_De_cliente)" 
							+ "VALUES ("+txtcedula.getText()+",'"+ txtnombre.getText()+"');" );
					
					rs = stm.executeQuery("SELECT * FROM cliente");
					
					while (rs.next()) {
						
						int cedula = rs.getInt(1);
						String nombre = rs.getString(2);
						
						
						
						
						ltProducto.addElement("Cedula: "+cedula+ " || Nombre: "+nombre);
						
						list.setModel(ltProducto);
						
						}
						cn.close();
					
				} catch (SQLException eroor) {
					// TODO: handle exception
				}
				
			}
		});
		btnNewButton.setBounds(195, 213, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Siguiente");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Interfaz_tercera_producto primer_siguiente = new Interfaz_tercera_producto();
					primer_siguiente.setLocationRelativeTo(null);
					primer_siguiente.setVisible(true);
					Interfaz_secundaria_cliente.this.setVisible(false);
					}catch (Exception error) {
						JOptionPane.showMessageDialog(null, "Error, el programa no puede mostrar la ventana.");
					}
			}
		});
		btnNewButton_1.setBounds(415, 213, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Eliminar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mysql conexion = new mysql();
				Connection cn = null;
				Statement stm = null;
				ResultSet rs = null;
				
				
				
				try {
					cn = conexion.conectar();
					stm = cn.createStatement();
					
						stm.executeUpdate("DELETE FROM cliente WHERE Cc_Cliente = '"+txtcedula.getText()+"'");
			
						
						
						rs = stm.executeQuery("SELECT * FROM cliente");
						ltProducto.clear();
						while (rs.next()) {
							
							int cedula = rs.getInt(1);
							String nombre = rs.getString(2);
							ltProducto.addElement("Cedula: "+cedula+ " || Nombre: "+nombre);
							
							list.setModel(ltProducto);
							
							
						}
						
						cn.close();
					
				} catch (SQLException eroor) {
					// TODO: handle exception
				}
			}
		});
		btnNewButton_2.setBounds(66, 213, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("Introduzca la cedula que desea eliminar.");
		lblNewLabel_2.setBounds(10, 55, 237, 14);
		contentPane.add(lblNewLabel_2);
	}
}
