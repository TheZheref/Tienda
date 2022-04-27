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
import java.awt.Scrollbar;
import java.awt.List;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;

public class Interfaz_cuarta_venta extends JFrame {

	private JPanel contentPane;
	private JTextField txtcedula_cliente;
	private JTextField txtcedula_empleado;
	private JTextField txtid_producto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz_cuarta_venta frame = new Interfaz_cuarta_venta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interfaz_cuarta_venta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cedula del cliente");
		lblNewLabel.setBounds(10, 72, 110, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("Ficha de venta");
		lblNewLabel_4.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(114, 11, 298, 32);
		contentPane.add(lblNewLabel_4);
		
		txtcedula_cliente = new JTextField();
		txtcedula_cliente.setBounds(142, 69, 86, 20);
		contentPane.add(txtcedula_cliente);
		txtcedula_cliente.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Cedula del empleado");
		lblNewLabel_1.setBounds(10, 130, 123, 14);
		contentPane.add(lblNewLabel_1);
		
		txtcedula_empleado = new JTextField();
		txtcedula_empleado.setBounds(142, 127, 86, 20);
		contentPane.add(txtcedula_empleado);
		txtcedula_empleado.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("ID del producto");
		lblNewLabel_2.setBounds(10, 187, 134, 14);
		contentPane.add(lblNewLabel_2);
		
		txtid_producto = new JTextField();
		txtid_producto.setBounds(142, 184, 86, 20);
		contentPane.add(txtid_producto);
		txtid_producto.setColumns(10);
		
		JList list = new JList();
		list.setBounds(258, 54, 204, 43);
		contentPane.add(list);
		
		JList list_1 = new JList();
		list_1.setValueIsAdjusting(true);
		list_1.setBounds(257, 115, 205, 43);
		contentPane.add(list_1);
		
		JList list_2 = new JList();
		list_2.setBounds(258, 169, 203, 43);
		contentPane.add(list_2);
		
		JList list_3 = new JList();
		list_3.setBounds(143, 262, 319, 121);
		contentPane.add(list_3);
		
		DefaultListModel ltProducto = new DefaultListModel();
		DefaultListModel ltProducto2 = new DefaultListModel();
		DefaultListModel ltProducto3 = new DefaultListModel();
		DefaultListModel ltProducto4 = new DefaultListModel();
		JButton btnNewButton = new JButton("Mostrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 mysql conexion = new mysql();
					
					
					Connection cn = null;
					Statement stm = null;
					ResultSet rs = null;
					
					
					
					try {
						cn = conexion.conectar();
						stm = cn.createStatement();
						
						ltProducto.clear();
						
						rs = stm.executeQuery("SELECT * FROM cliente");
						
						while (rs.next()) {
							
							int cedula = rs.getInt(1);
							String nombre = rs.getString(2);
							
							
							
							
							ltProducto.addElement("Cedula "+cedula);
							
							list.setModel(ltProducto);
							
							}
						ltProducto2.clear();
						rs = stm.executeQuery("SELECT * FROM empleado");
						
						while (rs.next()) {
							
							int cedula = rs.getInt(1);
							String nombre = rs.getString(2);
							String cargo = rs.getString(3);
							
							
							ltProducto2.addElement("Cedula: "+cedula);
							
							list_1.setModel(ltProducto2);
							
							
							
							}
						ltProducto3.clear();
						rs = stm.executeQuery("SELECT * FROM producto");
						
						while (rs.next()) {
							
							int id_producto = rs.getInt(1);
							String nombre = rs.getString(2);
							double precio = rs.getDouble(3);
							double cantidad = rs.getDouble(4);
							double iva = rs.getDouble(5);
							double total = rs.getDouble(6);
							
							ltProducto3.addElement("ID: "+id_producto);
							
							list_2.setModel(ltProducto3);
							
							
							
							}
						
							cn.close();
						
					} catch (SQLException eroor) {
						// TODO: handle exception
					}
			}
		});
		btnNewButton.setBounds(311, 223, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Agregar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mysql conexion = new mysql();
				
				
				Connection cn = null;
				Statement stm = null;
				ResultSet rs = null;
				
				
				
				try {
					cn = conexion.conectar();
					stm = cn.createStatement();
					
					ltProducto.clear();
					
					stm.executeUpdate("INSERT INTO venta_total (Cc_Cliente, Cc_empleado, Numero_producto)" 
							+ "VALUES ("+txtcedula_cliente.getText()+",'"
							+ txtcedula_empleado.getText()+"','"+txtid_producto.getText()+"');" );
					
					rs = stm.executeQuery("SELECT * FROM venta_total");
					
					while (rs.next()) {
						
						int cedula_cliente = rs.getInt(1);
						int cedula_empleado = rs.getInt(2);
						int id_producto = rs.getInt(3);
						
						
						
						
						ltProducto4.addElement("Cedula del cliente: "+cedula_cliente+ " || Cedula del empleado: "+cedula_empleado+" || ID del producto: "+id_producto);
						
						list_3.setModel(ltProducto4);
						
						}
						cn.close();
					
				} catch (SQLException eroor) {
					// TODO: handle exception
				}
				
			}
		});
		btnNewButton_1.setBounds(229, 413, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Volver al inicio");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Interfaz_principal_tienda primer_siguiente = new Interfaz_principal_tienda ();
					primer_siguiente.setLocationRelativeTo(null);
					primer_siguiente.setVisible(true);
					Interfaz_cuarta_venta.this.setVisible(false);
					}catch (Exception error) {
						JOptionPane.showMessageDialog(null, "Error, el programa no puede mostrar la ventana.");
					}
			}
		});
		btnNewButton_2.setBounds(391, 413, 144, 23);
		contentPane.add(btnNewButton_2);
		
		Scrollbar scrollbar_1 = new Scrollbar();
		scrollbar_1.setValue(1);
		scrollbar_1.setBounds(445, 115, 17, 43);
		contentPane.add(scrollbar_1);
		
	
	}
}
