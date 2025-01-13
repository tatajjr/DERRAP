import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class paginaadgestionclienteyveh extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					paginaadgestionclienteyveh frame = new paginaadgestionclienteyveh();
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
	public paginaadgestionclienteyveh() {
		setTitle("Derrap Administrador | Gestion clientes y vehículos");
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
		lblDerrap.setBounds(10, 20, 112, 27);
		contentPane.add(lblDerrap);
		
		JLabel lbldashboard = new JLabel("¿Qué quieres hacer?");
		lbldashboard.setBounds(10, 45, 143, 23);
		contentPane.add(lbldashboard);
		
		JButton btngestioncliyveh = new JButton("Gestion clientes y vehículos");
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
	}
}
