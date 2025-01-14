import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class paginaprincipalmec extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					paginaprincipalmec frame = new paginaprincipalmec();
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
	public paginaprincipalmec() {
		setTitle("Derrap Mecanico | Pagina principal");
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
		
		JLabel lblMecanico = new JLabel("MECANICO");
		lblMecanico.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMecanico.setBounds(594, 11, 132, 45);
		contentPane.add(lblMecanico);
		
		JLabel lblDerrap = new JLabel("DERRAP");
		lblDerrap.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDerrap.setBounds(10, 20, 112, 27);
		contentPane.add(lblDerrap);
		
		JLabel lbldashboard = new JLabel("¿Qué quieres hacer?");
		lbldashboard.setBounds(10, 45, 143, 23);
		contentPane.add(lbldashboard);
		
		JButton btnbuscarvehiculo = new JButton("Buscar vehiculo");
		btnbuscarvehiculo.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnbuscarvehiculo.setBounds(10, 79, 169, 23);
		contentPane.add(btnbuscarvehiculo);
		
		JButton btnnuevaorden = new JButton("Añadir orden reparacion");
		btnnuevaorden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaadgestiontaller paginaadgestiontaller = new paginaadgestiontaller();
				paginaadgestiontaller.setVisible(true);		
				dispose();
				}
		});
		btnnuevaorden.setBounds(10, 123, 169, 23);
		contentPane.add(btnnuevaorden);
		
		JButton btnhistorialveh = new JButton("Consultar historial vehiculo");
		btnhistorialveh.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnhistorialveh.setBounds(10, 168, 169, 23);
		contentPane.add(btnhistorialveh);
		
		JButton btnmodificarorden = new JButton("Modificar orden reparacion");
		btnmodificarorden.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnmodificarorden.setBounds(10, 217, 169, 23);
		contentPane.add(btnmodificarorden);
		
		JButton btnVerListaOrdenes = new JButton("Ver lista ordenes reparacion");
		btnVerListaOrdenes.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnVerListaOrdenes.setBounds(10, 372, 169, 23);
		contentPane.add(btnVerListaOrdenes);
		
		JButton btnConsultarPiezasStock = new JButton("Consultar piezas stock");
		btnConsultarPiezasStock.setBounds(10, 315, 169, 23);
		contentPane.add(btnConsultarPiezasStock);
		
		JButton tbnsolicitarpieza = new JButton("Solicitar pieza sustitucion");
		tbnsolicitarpieza.setFont(new Font("Tahoma", Font.PLAIN, 9));
		tbnsolicitarpieza.setBounds(10, 266, 169, 23);
		contentPane.add(tbnsolicitarpieza);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(288, 83, 81, 14);
		contentPane.add(lblNombre);
		
		JLabel lblirnombre = new JLabel("Nombre de usuario");
		lblirnombre.setBounds(288, 108, 106, 14);
		contentPane.add(lblirnombre);
		
		JLabel lblIdUsuario = new JLabel("Id usuario:");
		lblIdUsuario.setBounds(288, 133, 81, 14);
		contentPane.add(lblIdUsuario);
		
		JLabel lbliridusuario = new JLabel("Id de usuario");
		lbliridusuario.setBounds(288, 158, 106, 14);
		contentPane.add(lbliridusuario);
		
		JLabel lblclave = new JLabel("Contraseña:");
		lblclave.setBounds(288, 188, 81, 14);
		contentPane.add(lblclave);
		
		JLabel lblirclave = new JLabel("Clave de usuario");
		lblirclave.setBounds(288, 213, 106, 14);
		contentPane.add(lblirclave);
		
		JLabel lblemail = new JLabel("Email:");
		lblemail.setBounds(288, 238, 81, 14);
		contentPane.add(lblemail);
		
		JLabel lbliremail = new JLabel("email de usuario");
		lbliremail.setBounds(288, 263, 106, 14);
		contentPane.add(lbliremail);
		
		JLabel lblordenesmecanico = new JLabel("Ordenes de reparacion:");
		lblordenesmecanico.setBounds(531, 83, 169, 14);
		contentPane.add(lblordenesmecanico);
	}
}
