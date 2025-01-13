import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class login<TextPrompt> extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField pwClave;
	
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
					login frame = new login();
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
	public login() {
		setTitle("Inicio Derrap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenido = new JLabel("Bienvenido de vuelta");
		lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBienvenido.setBounds(62, 96, 189, 38);
		contentPane.add(lblBienvenido);
		
		JLabel lblUsuario = new JLabel("Usuario (DNI)");
		lblUsuario.setBounds(62, 161, 189, 14);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(62, 186, 189, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setBounds(62, 220, 119, 14);
		contentPane.add(lblContrasena);
		
		
		pwClave = new JPasswordField();
		pwClave.setBounds(62, 245, 189, 20);
		contentPane.add(pwClave);
		pwClave.setColumns(10);
		
		JButton btnInicioSesion = new JButton("Iniciar sesión");
		btnInicioSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id_usuario = txtUsuario.getText();
				String clave_usuario = pwClave.getText();
				if (conexion.buscarusuario(id_usuario, clave_usuario)) {
					 // Obtener el tipo de usuario desde la base de datos
		            String tipoUsuario = conexion.obtenerTipoUsuario(id_usuario);
		            System.out.println(tipoUsuario);
		            
		            if ("Administrador".equalsIgnoreCase(tipoUsuario)) {
		                paginaadgestionclienteyveh paginaadgestionclienteyveh = new paginaadgestionclienteyveh();
		                paginaadgestionclienteyveh.setVisible(true);
		                dispose();
		            } else if ("Mecanico".equalsIgnoreCase(tipoUsuario)) {
		                paginaprincipalmec paginaprincipalmec = new paginaprincipalmec();
		                paginaprincipalmec.setVisible(true);
		                dispose();
		            } else {
		                JOptionPane.showMessageDialog(btnInicioSesion, "Tipo de usuario desconocido", "Error", JOptionPane.ERROR_MESSAGE);
		            }
				} else {
					JOptionPane.showMessageDialog(btnInicioSesion, "Usuario o contraseña incorrectos","error",JOptionPane.ERROR_MESSAGE);

				
				}
			}
		});
		
		btnInicioSesion.setBounds(62, 291, 189, 23);
		contentPane.add(btnInicioSesion);
		
		JLabel lblCrearCuenta = new JLabel("¿Todavía no tienes una cuenta?");
		lblCrearCuenta.setBounds(10, 345, 200, 19);
		contentPane.add(lblCrearCuenta);
		
		JButton btnNewButton = new JButton("Crear una");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearcuenta crearcuenta = new crearcuenta();
				crearcuenta.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(209, 343, 106, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblLogo1 = new JLabel("Talleres");
		lblLogo1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLogo1.setBounds(10, 11, 124, 38);
		contentPane.add(lblLogo1);
		
		JLabel lblLogo2 = new JLabel("Derrap");
		lblLogo2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLogo2.setBounds(47, 34, 119, 38);
		contentPane.add(lblLogo2);
		
		
	}
}
