import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class paginaadgestiontaller extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					paginaadgestiontaller frame = new paginaadgestiontaller();
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
	public paginaadgestiontaller() {
		setTitle("Derrap Administrador | Gestion taller");
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
		btngestioncliyveh.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btngestioncliyveh.setBounds(10, 79, 169, 23);
		contentPane.add(btngestioncliyveh);
		
		JButton btngestiontaller = new JButton("Gestion de taller");
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
		
		JLabel lblmecanico = new JLabel("Mecánico");
		lblmecanico.setBounds(223, 79, 98, 23);
		contentPane.add(lblmecanico);
		
		JButton btnaltamecanico = new JButton("Dar de alta");
		btnaltamecanico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				administrador_alta_mecanico administrador_alta_mecanico = new administrador_alta_mecanico();
				dispose();
				administrador_alta_mecanico.setVisible(true);
			}
		});
		btnaltamecanico.setBounds(223, 112, 104, 23);
		contentPane.add(btnaltamecanico);
		
		JButton tbnmodificarmecanico = new JButton("Modificar ");
		tbnmodificarmecanico.setBounds(223, 157, 104, 23);
		contentPane.add(tbnmodificarmecanico);
		
		JButton btnconsultarmecanico = new JButton("Consultar");
		btnconsultarmecanico.setBounds(223, 209, 104, 23);
		contentPane.add(btnconsultarmecanico);
		
		JButton btnbajamecanico = new JButton("Dar de baja");
		btnbajamecanico.setBounds(223, 263, 104, 23);
		contentPane.add(btnbajamecanico);
	}
}
