import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class administrador_alta_mecanico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDNI;
	private JTextField txtclave;
	private JTextField txtemail;
	ConexionMySql conexion = new ConexionMySql();
	Connection cn = null;
	Statement stm = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					administrador_alta_mecanico frame = new administrador_alta_mecanico();
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
	public administrador_alta_mecanico() {
		setTitle("Derrap Administrador | Gestion taller | Dar alta mecanico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnprovisional = new JButton("Volver a inicio");
		btnprovisional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login login = new login();
				login.setVisible(true);
				dispose();
			}
		});
		btnprovisional.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnprovisional.setBounds(10, 429, 220, 23);
		contentPane.add(btnprovisional);
		
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		lblAdministrador.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAdministrador.setBounds(545, 11, 191, 45);
		contentPane.add(lblAdministrador);
		
		JLabel lblDerrap = new JLabel("DERRAP");
		lblDerrap.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDerrap.setBounds(10, 20, 158, 27);
		contentPane.add(lblDerrap);
		
		JLabel lbldashboard = new JLabel("¿Qué quieres hacer?");
		lbldashboard.setBounds(10, 45, 169, 23);
		contentPane.add(lbldashboard);
		
		JButton btngestioncliyveh = new JButton("Gestion clientes y vehículos");
		btngestioncliyveh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaadgestionclienteyveh paginaadgestionclienteyveh = new paginaadgestionclienteyveh();
				paginaadgestionclienteyveh.setVisible(true);
				dispose();
			}
		});
		btngestioncliyveh.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btngestioncliyveh.setBounds(10, 79, 169, 23);
		contentPane.add(btngestioncliyveh);
		
		JButton btngestiontaller = new JButton("Gestion de taller");
		btngestiontaller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaadgestiontaller paginaadgestiontaller = new paginaadgestiontaller();
				paginaadgestiontaller.setVisible(true);
				dispose();
			}
		});
		btngestiontaller.setBounds(10, 157, 169, 23);
		contentPane.add(btngestiontaller);
		
		JButton btngestionfacturasypedidos = new JButton("Gestion facturas y pedidos");
		btngestionfacturasypedidos.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btngestionfacturasypedidos.setBounds(10, 236, 169, 23);
		contentPane.add(btngestionfacturasypedidos);
		
		JButton btnadmisistema = new JButton("Administracion sistema");
		btnadmisistema.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnadmisistema.setBounds(10, 328, 169, 23);
		contentPane.add(btnadmisistema);
		
		JLabel lblmecanico = new JLabel("Dar alta mecánico");
		lblmecanico.setBounds(223, 79, 244, 23);
		contentPane.add(lblmecanico);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(223, 125, 116, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(223, 150, 116, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setBounds(223, 214, 116, 14);
		contentPane.add(lblDNI);
		
		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(223, 239, 116, 20);
		contentPane.add(txtDNI);
		
		txtclave = new JTextField();
		txtclave.setColumns(10);
		txtclave.setBounds(445, 239, 116, 20);
		contentPane.add(txtclave);
		
		JLabel lblclave = new JLabel("Contraseña");
		lblclave.setBounds(445, 214, 126, 14);
		contentPane.add(lblclave);
		
		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(445, 150, 116, 20);
		contentPane.add(txtemail);
		
		JLabel lblemail = new JLabel("Email:");
		lblemail.setBounds(445, 125, 116, 14);
		contentPane.add(lblemail);
		
		JButton btnaltamecanico = new JButton("Dar de alta");
		btnaltamecanico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexion.dardealta(txtNombre,txtDNI,txtclave,txtemail);
			}
		});
		btnaltamecanico.setBounds(445, 328, 116, 23);
		contentPane.add(btnaltamecanico);
		
		JButton btnvovler = new JButton("<-");
		btnvovler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaadgestiontaller paginaadgestiontaller = new paginaadgestiontaller();
				paginaadgestiontaller.setVisible(true);
				dispose();
			}
		});
		btnvovler.setBounds(677, 429, 49, 23);
		contentPane.add(btnvovler);
	}
}