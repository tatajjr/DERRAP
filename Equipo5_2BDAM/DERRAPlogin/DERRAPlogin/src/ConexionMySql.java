
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ConexionMySql {
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/derrap";
	private static final String USUARIO = "root";
	private static final String CLAVE = "1234";
	
	Connection cn = null;
	Statement stm = null;
	ResultSet resultado = null;

	public Connection conectar() {
	try {
	cn = DriverManager.getConnection(URL, USUARIO, CLAVE);
	stm = cn.createStatement();
	System.out.println("Conexion realizada correctamente");
	} catch (SQLException e) {
	System.out.println("Error en la conexion");
	e.printStackTrace();
	}
	return cn;
	}

	public boolean buscarusuario(String nombre_usuario,String clave_usuario) {
		boolean usuariovalido = false;
		this.conectar();		
		try {
			resultado = stm.executeQuery("Select * from usuarios where ID_Usuario = '"+nombre_usuario+"'");
			if (resultado.next()) {
				
				
					String usuarioEncontrado = resultado.getString("nombre");
					String claveEncontrado = resultado.getString("clave");
					if(clave_usuario.equalsIgnoreCase(claveEncontrado)) {
						usuariovalido = true;
					
					System.out.println(usuarioEncontrado+","+claveEncontrado);
				}
				resultado.close();
				stm.close();
				cn.close();
			}
			else {
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuariovalido;		
	}

	public boolean DNIAceptable(String dni ) {
        // 	Verificar que el DNI no esté en la base de datos
    	boolean dniusado = false;
    	this.conectar();
        try {
            resultado = stm.executeQuery("SELECT COUNT(*) FROM usuarios WHERE ID_Usuario ='"+dni+"'");
            if (resultado.next()) {
				int count = resultado.getInt(1);
				if (count == 0) {
	                dniusado = false; 
	            } else {
	                dniusado = true;
	            }
			}
                        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dniusado;
    }
	public boolean DNIValido (String DNI) {
		//Creamos el patron de dni valido 8 numeros y 1 Letra
		String DNIpatron ="^[0-9]{8}[A-Za-z]$";
		return Pattern.matches(DNIpatron, DNI);
	}
	public boolean EmailValido(String email) {
		//Creamos un patrón para comprobar que el formato del correo electronico introducido es correcto
        String emailpatron = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailpatron, email);
    }
	//Creamos un patrón para obligar al usuario que su clave tenga minusculas, mayusculas, numeros y algún simbolo de @#$%^&+= para garantizar su seguridad
    public boolean claveValida(String clave) {
    	String patronclave = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{4,}$";
        return Pattern.matches(patronclave, clave);
    }
    public boolean guardarUsuario(String dni, String email, String clave, String rol, String nombre) {
	    // Insertar el nuevo usuario en la base de datos
    	this.conectar();
	    try {
	        stm = cn.createStatement();
	        stm.executeUpdate("INSERT INTO usuarios (ID_Usuario, clave, email, tipo, nombre) VALUES ('" + dni + "','" + clave + "','" + email + "','" + rol + "','" + nombre + "')");	        	        
	        return true; // Usuario creado correctamente
	    } catch (SQLException e) {
	        e.printStackTrace();	        
	        return false; // Ocurrió un error
	    }
	}
	

	public void seleccionarperfil(JRadioButton rbAdministrador, JRadioButton rbMecanico, JLabel lblAdministrador,
            JLabel lblMecanico, JLabel lblInfoad, JLabel lblInfomec) {

			if (rbAdministrador.isSelected()) {
			lblAdministrador.setVisible(true);
			lblInfoad.setVisible(true);
			
			lblMecanico.setVisible(false);
			lblInfomec.setVisible(false);
			} 
			else if (rbMecanico.isSelected()) {
			lblMecanico.setVisible(true);
			lblInfomec.setVisible(true);
			
			lblAdministrador.setVisible(false);
			lblInfoad.setVisible(false);
			}
	}

	public boolean tipousarioad(JRadioButton rbAdministrador, String id_Usuario) {
		this.conectar();
		boolean boleano = false;
		if(rbAdministrador.isSelected()) {
			try {
				resultado = stm.executeQuery("SELECT COUNT(*) FROM usuarios WHERE id_Usuario='"+id_Usuario+"' AND tipo = 'Administrador'");
				if (resultado.next()) {
					int count = resultado.getInt(1);
					if (count == 1) {
		                boleano = true;
		            } else {
		                boleano = false;
		                JOptionPane.showMessageDialog(null,"No hay ningun administrador correspondiente a los datos introducidos","error",JOptionPane.ERROR_MESSAGE);
		            }
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return boleano;
	}
	
	public boolean tipousuariomec(JRadioButton rbMecanico, String id_Usuario) {
		this.conectar();
		boolean boleano = false;
		if(rbMecanico.isSelected()) {
			try {
				resultado = stm.executeQuery("SELECT COUNT(*) FROM usuarios WHERE id_Usuario='"+id_Usuario+"' AND tipo = 'Mecanico'");
				if (resultado.next()) {
					int count = resultado.getInt(1);
					if (count == 1) {
		                boleano = true;
		            } else {
		                boleano = false;
		                JOptionPane.showMessageDialog(null,"No hay ningun mecánico correspondiente a los datos introducidos","error",JOptionPane.ERROR_MESSAGE);
		            }
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return boleano;
	}
	public String obtenerTipoUsuario(String id_usuario) {
		this.conectar();
	    String tipo = "";
	    try{
	    	resultado = stm.executeQuery("SELECT tipo FROM usuarios WHERE id_Usuario = '"+id_usuario+"'");
	        if (resultado.next()) {
	            tipo = resultado.getString("tipo");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return tipo;
	}

	public void dardealta(JTextField txtNombre, JTextField txtDNI, JTextField txtclave, JTextField txtemail) {
		this.conectar();
	    // Verificar que todos los campos están rellenos
	    if (txtNombre.getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo Nombre está vacío.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (txtDNI.getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo DNI está vacío.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (txtclave.getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo Clave está vacío.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (txtemail.getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo Email está vacío.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    // Verificar formato de DNI
	    if (!DNIValido(txtDNI.getText())) {
	        JOptionPane.showMessageDialog(null, "El formato del DNI es incorrecto ej:12345678A.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (DNIAceptable(txtDNI.getText())) {
	    	JOptionPane.showMessageDialog(null, "El DNI ingresado ya existe en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    // Verificar formato de Email
	    if (!EmailValido(txtemail.getText())) {
	        JOptionPane.showMessageDialog(null, "El formato del Email es incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    // Verificar formato de Clave
	    if (!claveValida(txtclave.getText())) {
	        JOptionPane.showMessageDialog(null, "La contraseña debe incluir mayusculas , minisculas , numeros y simbolos (@,#,$...)", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Si todos los campos son válidos, insertar en la base de datos
	    try {
	    	stm = cn.createStatement();
	    	stm.executeUpdate("INSERT INTO usuarios (id_usuario, clave, email, tipo, nombre) VALUES ('" 
	                 + txtDNI.getText() + "', '" 
	                 + txtclave.getText() + "', '" 
	                 + txtemail.getText() + "',"
	                 + " 'Mecanico', '" 
	                 + txtNombre.getText()+ "')");
	    	JOptionPane.showMessageDialog(null, "El mecánico ha sido creado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
	    	txtDNI.setText("");
	    	txtclave.setText("");
	    	txtemail.setText("");
	    	txtNombre.setText("");
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al insertar usuario en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }	    
	}
}
