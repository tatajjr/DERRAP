import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JRadioButton rbAdministrador = new JRadioButton("Administrador");
	private JRadioButton rbMecanico = new JRadioButton("Mecanico");
	private JTextField txtDNI;
	private ButtonGroup radioGroup;

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
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCrearUnaCuenta = new JLabel("Crear una cuenta");
		lblCrearUnaCuenta.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCrearUnaCuenta.setBounds(61, 11, 189, 38);
		contentPane.add(lblCrearUnaCuenta);
		
		JLabel lblRol = new JLabel("Rol en Derrap");
		lblRol.setBounds(61, 52, 157, 14);
		contentPane.add(lblRol);
		
		
		rbAdministrador.setBounds(61, 79, 111, 23);
		contentPane.add(rbAdministrador);
		
		
		rbMecanico.setBounds(174, 79, 111, 23);
		contentPane.add(rbMecanico);
		
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(rbAdministrador);
		radioGroup.add(rbMecanico);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(61, 109, 157, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(61, 134, 157, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(61, 219, 157, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(61, 244, 157, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblClave = new JLabel("Contraseña");
		lblClave.setBounds(61, 274, 157, 14);
		contentPane.add(lblClave);
		
		pwClave = new JPasswordField();
		pwClave.setBounds(61, 299, 157, 20);
		contentPane.add(pwClave);
		
		JLabel lblConfirmarClave = new JLabel("Confirmar contraseña");
		lblConfirmarClave.setBounds(61, 329, 157, 14);
		contentPane.add(lblConfirmarClave);
		
		pwConfirmarClave = new JPasswordField();
		pwConfirmarClave.setBounds(61, 354, 157, 20);
		contentPane.add(pwConfirmarClave);
		
		JButton btnNewButton = new JButton("Crear cuenta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearCuenta();
			}
		});
		btnNewButton.setBounds(61, 401, 157, 23);
		contentPane.add(btnNewButton);
		
		JButton btnInicio = new JButton("Volver a inicio");
	    btnInicio.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		login login = new login();
				login.setVisible(true);
				dispose();
	    	}
	    });
	    btnInicio.setBounds(557, 401, 157, 23);
	    contentPane.add(btnInicio);
		
		JLabel lblDNI = new JLabel("DNI ");
		lblDNI.setBounds(61, 164, 157, 14);
		contentPane.add(lblDNI);
		
		txtDNI = new JTextField();
		txtDNI.setBounds(61, 189, 157, 20);
		contentPane.add(txtDNI);
		txtDNI.setColumns(10);
		
		JLabel lblLogo1 = new JLabel("Talleres");
		lblLogo1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLogo1.setBounds(612, 0, 124, 38);
		contentPane.add(lblLogo1);
		
		JLabel lblLogo2 = new JLabel("Derrap");
		lblLogo2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLogo2.setBounds(647, 23, 119, 38);
		contentPane.add(lblLogo2);
		
		JLabel lblAdministrador = new JLabel("Administrador");
		lblAdministrador.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblAdministrador.setBounds(350, 81, 169, 20);
		contentPane.add(lblAdministrador);
		lblAdministrador.setVisible(false);		
		JLabel lblInfoad = new JLabel("<html>\r\n¿Qué hace un administrador?<br>\r\n-Gestión de clientes (crear, modificar, consultar).<br>\r\n-Gestión de órdenes de reparación.<br>\r\n-Gestión de stock y proveedores.<br>\r\n-Gestión de serviciosy categorías.<br>\r\n-Control económico y generación de facturas.<br>\r\n-Reportes e historial de pagos y servicios de clientes.<br>\r\n</html>");
		lblInfoad.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInfoad.setBounds(342, 107, 372, 204);
		contentPane.add(lblInfoad);
		lblInfoad.setVisible(false);
		JLabel lblMecanico = new JLabel("Mecánico");
		lblMecanico.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblMecanico.setBounds(350, 81, 169, 20);
		contentPane.add(lblMecanico);
		lblMecanico.setVisible(false);		
		JLabel lblInfomec = new JLabel("<html>\r\n¿Qué hace un mecánico?<br>\r\n-Consultar órdenes de reparación pendientes.<br>\r\n-Historial de reparaciones de los vehículos asignados.<br>\r\n-Opción de modificación y solicitud de piezas.<br>\r\n-Consultar stock de piezas disponibles.<br>\r\n-Buscar vehículos en el sistema.<br>\r\n</html>");
		lblInfomec.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInfomec.setBounds(342, 107, 372, 204);
		contentPane.add(lblInfomec);
		lblInfomec.setVisible(false);
		
		rbAdministrador.addActionListener(e -> {
		    conexion.seleccionarperfil(rbAdministrador, rbMecanico, lblAdministrador, lblMecanico, lblInfoad, lblInfomec);
		});
		rbMecanico.addActionListener(e -> {
		    conexion.seleccionarperfil(rbAdministrador, rbMecanico, lblAdministrador, lblMecanico, lblInfoad, lblInfomec);
		    
		    
		});
	}

	protected void crearCuenta() {
		String nombre= txtNombre.getText();
        String dni = txtDNI.getText();
        String email = txtEmail.getText();
        String clave = new String(pwClave.getPassword());
        String confirmarclave = new String(pwConfirmarClave.getPassword());
        String rol = "";
        if (rbAdministrador.isSelected()) {
			rol = "Administrador";
		}
        else {
        	rol = "Mecanico";
        }
        //Controlar que el usuario que este creando cuenta elija su rol dentro de la empresa
        if (!rbAdministrador.isSelected() && !rbMecanico.isSelected()) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un rol (Administrador o Mecánico).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
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
        	//Controlamos que tipo de usuario acabamos de crear para enviarlo a la pagina correspondiente
        	if (conexion.tipousarioad(rbAdministrador,dni)) {
				paginaadgestionclienteyveh paginaprincipalad = new paginaadgestionclienteyveh();
				paginaprincipalad.setVisible(true);
				dispose();
				
			} else {					
					if (conexion.tipousuariomec(rbMecanico,dni)) {
						paginaprincipalmec paginaprincipalmec = new paginaprincipalmec();
						paginaprincipalmec.setVisible(true);
						dispose();								
					}
			}
		}else {
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
		    rbAdministrador.setSelected(false);
		    rbMecanico.setSelected(false);
		    radioGroup.clearSelection();
		}

	

}
