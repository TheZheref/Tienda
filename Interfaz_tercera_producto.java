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

public class Interfaz_tercera_producto extends JFrame {

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtnombre;
	private JTextField txtprecio;
	private JTextField txtiva;
	private JTextField txtcantidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz_tercera_producto frame = new Interfaz_tercera_producto();
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
	public Interfaz_tercera_producto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 619, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Ficha del producto");
		lblNewLabel_4.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(209, 11, 298, 32);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("Numero de producto:");
		lblNewLabel.setBounds(10, 66, 148, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 114, 62, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Precio:");
		lblNewLabel_2.setBounds(10, 160, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Iva:");
		lblNewLabel_3.setBounds(10, 204, 76, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Cantidad");
		lblNewLabel_5.setBounds(10, 245, 86, 14);
		contentPane.add(lblNewLabel_5);
		
		txtid = new JTextField();
		txtid.setBounds(160, 63, 86, 20);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(160, 111, 86, 20);
		contentPane.add(txtnombre);
		txtnombre.setColumns(10);
		
		txtprecio = new JTextField();
		txtprecio.setBounds(160, 157, 86, 20);
		contentPane.add(txtprecio);
		txtprecio.setColumns(10);
		
		txtiva = new JTextField();
		txtiva.setBounds(160, 198, 86, 20);
		contentPane.add(txtiva);
		txtiva.setColumns(10);
		
		txtcantidad = new JTextField();
		txtcantidad.setBounds(160, 242, 86, 20);
		contentPane.add(txtcantidad);
		txtcantidad.setColumns(10);
		
		JList list = new JList();
		list.setBounds(302, 54, 291, 205);
		contentPane.add(list);
		
		DefaultListModel ltProducto = new DefaultListModel();
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mysql conexion = new mysql();
				
				
				Connection cn = null;
				Statement stm = null;
				ResultSet rs = null;
				
				int id_producto2 = Integer.parseInt(txtid.getText());
				double precio2 = Double.parseDouble(txtprecio.getText());
				double iva2 = Double.parseDouble(txtiva.getText());
				int cant = Integer.parseInt(txtcantidad.getText());
				
				double total2 = (((precio2*iva2)/100)+precio2)*cant;
				
				try {
					cn = conexion.conectar();
					stm = cn.createStatement();
					
					ltProducto.clear();
					stm.executeUpdate("INSERT INTO producto (	id_producto, nombre, Precio_venta, Cantidad_de_productos, iva_producto, total )" + 
		                    "VALUES ('"+id_producto2+"','"+txtnombre.getText()+"','"+precio2+"','"
							+txtcantidad.getText()+"','"+iva2+"','"+total2+"');");
					
					rs = stm.executeQuery("SELECT * FROM producto");
					
					while (rs.next()) {
						
						int id_producto = rs.getInt(1);
						String nombre = rs.getString(2);
						double precio = rs.getDouble(3);
						double cantidad = rs.getDouble(4);
						double iva = rs.getDouble(5);
						double total = rs.getDouble(6);
						
						
						ltProducto.addElement("Num: "+id_producto+" || "+"Nombre: "+nombre+" || "+" Precio: "+precio+ " || "+" Cantidad: "+cantidad+" || "+
						"Iva: "+ iva+" || "+ "Total: "+total);
						
						list.setModel(ltProducto);
						
						}
						cn.close();
					
				} catch (SQLException eroor) {
					// TODO: handle exception
				}
				
			}
		});
		btnNewButton.setBounds(234, 278, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Siguiente");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Interfaz_cuarta_venta primer_siguiente = new Interfaz_cuarta_venta();
					primer_siguiente.setLocationRelativeTo(null);
					primer_siguiente.setVisible(true);
					Interfaz_tercera_producto.this.setVisible(false);
					}catch (Exception error) {
						JOptionPane.showMessageDialog(null, "Error, el programa no puede mostrar la ventana.");
					}
			}
		});
		btnNewButton_1.setBounds(468, 278, 89, 23);
		contentPane.add(btnNewButton_1);
	}

}
