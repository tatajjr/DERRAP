import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Estilos.estilos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class crearcuenta extends JFrame {
	
	ConexionMySql conexion = new ConexionMySql();
	Connection cn = null;
	Statement stm = null;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtEmail;
	private JPasswordField pwClave;
	private JPasswordField pwConfirmarClave;
	private JTextField txtDNI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					crearcuenta frame = new crearcuenta();
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
	public crearcuenta() {
        setTitle("Crear Cuenta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 550);
        setResizable(false);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(estilos.obtenerColorFondo());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCrearUnaCuenta = new JLabel("Crear una cuenta");
		lblCrearUnaCuenta.setFont(estilos.obtenerFuenteSubtitulo());
		lblCrearUnaCuenta.setForeground(estilos.COLOR_TEXTO_ERROR);
		lblCrearUnaCuenta.setBounds(36, 98, 300, 30);
		contentPane.add(lblCrearUnaCuenta);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(estilos.obtenerFuenteCampos());
		lblNombre.setBounds(36, 154, 157, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setFont(estilos.obtenerFuenteCampos());
		txtNombre.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		txtNombre.setBounds(36, 179, 175, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(estilos.obtenerFuenteCampos());
		lblEmail.setBounds(221, 216, 157, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setFont(estilos.obtenerFuenteCampos());
		txtEmail.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		txtEmail.setBounds(221, 241, 175, 30);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblClave = new JLabel("Contraseña");
		lblClave.setFont(estilos.obtenerFuenteCampos());
		lblClave.setBounds(36, 216, 157, 14);
		contentPane.add(lblClave);
		
		pwClave = new JPasswordField();
		pwClave.setFont(estilos.obtenerFuenteCampos());
		pwClave.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		pwClave.setBounds(36, 241, 175, 30);
		contentPane.add(pwClave);
		
		JLabel lblConfirmarClave = new JLabel("Confirmar contraseña");
		lblConfirmarClave.setFont(estilos.obtenerFuenteCampos());
		lblConfirmarClave.setBounds(36, 282, 157, 14);
		contentPane.add(lblConfirmarClave);
		
		pwConfirmarClave = new JPasswordField();
		pwConfirmarClave.setFont(estilos.obtenerFuenteCampos());
		pwConfirmarClave.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		pwConfirmarClave.setBounds(36, 307, 175, 30);
		contentPane.add(pwConfirmarClave);
		
		JButton btncrearcuenta = new JButton("Crear cuenta");
		btncrearcuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearCuenta();
			}
		});
		btncrearcuenta.setFont(estilos.obtenerFuenteBoton());
		btncrearcuenta.setBackground(estilos.COLOR_BOTON_NORMAL);
		btncrearcuenta.setForeground(Color.WHITE);
		btncrearcuenta.setFocusPainted(false);
		btncrearcuenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btncrearcuenta.setBounds(221, 377, 175, 40);
		contentPane.add(btncrearcuenta);
		
		JButton btnInicio = new JButton("Volver a inicio");
		btnInicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirmacion = JOptionPane.showConfirmDialog(
                    null, 
                    "¿Estás seguro de volver a la pagina de inicio?", 
                    "Confirmación", 
                    JOptionPane.YES_NO_OPTION
                );

                if (confirmacion == JOptionPane.YES_OPTION) {
                    login login = new login();
                    login.setVisible(true);
                    dispose();
                }
                // Si se selecciona "NO", no hace nada.
            }
        });
	    btnInicio.setForeground(Color.WHITE);
	    btnInicio.setFont(new Font("Segoe UI", Font.BOLD, 14));
	    btnInicio.setBackground(Color.RED);
	    btnInicio.setBounds(36, 377, 175, 40);
	    contentPane.add(btnInicio);
		
		JLabel lblDNI = new JLabel("DNI ");
		lblDNI.setFont(estilos.obtenerFuenteCampos());
		lblDNI.setBounds(221, 154, 157, 14);
		contentPane.add(lblDNI);
		
		txtDNI = new JTextField();
		txtDNI.setFont(estilos.obtenerFuenteCampos());
		txtDNI.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		txtDNI.setBounds(221, 179, 175, 30);
		contentPane.add(txtDNI);
		txtDNI.setColumns(10);
        JLabel lblLogo1 = new JLabel("Talleres");
        lblLogo1.setFont(estilos.obtenerFuenteTitulo());
        lblLogo1.setForeground(estilos.COLOR_TEXTO_TITULO);
        lblLogo1.setBounds(36, 36, 150, 40);
        contentPane.add(lblLogo1);

        JLabel lblLogo2 = new JLabel("Derrap");
        lblLogo2.setFont(estilos.obtenerFuenteTitulo());
        lblLogo2.setForeground(estilos.COLOR_TEXTO_SECUNDARIO);
        lblLogo2.setBounds(136, 36, 150, 40);
        contentPane.add(lblLogo2);
		

		

        // Add the image to the right side
        Image img = new ImageIcon(getClass().getResource("/Imagenes/imagenmecanico.jpg")).getImage(); // Resize the image
        JLabel lblImage = new JLabel(new ImageIcon(img));
        lblImage.setBounds(483, 0, 351, 511); // Position and size of the image
        contentPane.add(lblImage);
	}

	protected void crearCuenta() {
		String nombre= txtNombre.getText();
        String dni = txtDNI.getText();
        String email = txtEmail.getText();
        String clave = new String(pwClave.getPassword());
        String confirmarclave = new String(pwConfirmarClave.getPassword());
        String rol = "Mecanico";

     // Crear array con etiquetas y valores de campos a verificar
        String[][] campos = {
            {"Nombre", nombre},
            {"DNI", dni},
            {"Email", email},
            {"Contraseña", clave},
            {"Confirmar contraseña", confirmarclave}};

        // Verificar si algún campo está vacío
        for (int i = 0; i < campos.length; i++) {
            if (campos[i][1].isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completa el campo '" + campos[i][0] + "'.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        //Verificar si el DNI es valido (8numeros y 1 letra)
        if(!conexion.DNIValido(dni)) {
        	JOptionPane.showMessageDialog(this,"El formato de DNI no es correcto. ej:12345678A","error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        // Verificar DNI único
        if (conexion.DNIAceptable(dni)) {
        	JOptionPane.showMessageDialog(this, "DNI ya en uso","error",JOptionPane.ERROR_MESSAGE);
        	return;
        }

        // Verificar patrón de email
        if (!conexion.EmailValido(email)) {
        	JOptionPane.showMessageDialog(this, "Formato de email no correcto \n nombre@gmail.com","error",JOptionPane.ERROR_MESSAGE);
        	return;
        }

        // Verificar patrón de contraseña
        if (!conexion.claveValida(clave)) {
        	JOptionPane.showMessageDialog(this, "La contraseña debe incluir mayusculas , minisculas , numeros y simbolos (@,#,$...)","error",JOptionPane.ERROR_MESSAGE);
        	return;
        }

        // Verificar coincidencia de contraseñas
        if (!clave.equals(confirmarclave)) {
        	JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden","error",JOptionPane.ERROR_MESSAGE);
        	return;
        }
        
        // Si todo es correcto, guardar el usuario
        if (conexion.guardarUsuario(dni, email, clave, rol, nombre)) {
        	JOptionPane.showMessageDialog(this,"Usuario creado con éxito");
			paginaprincipalmec paginaprincipalmec = new paginaprincipalmec();
			paginaprincipalmec.setVisible(true);
			dispose();											
			}
        
			else {
			JOptionPane.showMessageDialog(this, "Error al crear el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
			limpiarCampos();
		}
    }
		private void limpiarCampos() {
		    txtNombre.setText("");
		    txtDNI.setText("");
		    txtEmail.setText("");
		    pwClave.setText("");
		    pwConfirmarClave.setText("");
		}

	

}
