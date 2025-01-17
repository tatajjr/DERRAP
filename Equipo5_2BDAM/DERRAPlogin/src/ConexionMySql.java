
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
	//System.out.println("Conexion realizada correctamente");
	} catch (SQLException e) {
	System.out.println("Error en la conexion");
	e.printStackTrace();
	}
	return cn;
	}
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+METODO PARA BUSCAR USUARIOS EN LA BASE DE DATOS++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+METODO PARA BUSCAR DNI DEL USUARIO EN LA BASE DE DATOS+++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+METODO PARA PATRONES DNI, EMAIL Y CONTRASEÑAS++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+METODO PARA GUARDAR USUARIO EN LA BASE DE DATOS++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+METODO PARA CUANDO SE INICIE SESION, SE DIRIJA A MECANICO O ADMINISTRADOR+
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
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
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+METODOS PARA CREAR E INSERTAR UN NUEVO USUARIO EN LA BASE DE DATOS+
	//+CON TODAS SUS RESTRICCIONES CONTROLADAS++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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
	    
	    
	    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    //+METODOS PARA OBTENER DATOS Y RELLENAR LAS TABLAS POSTERIORMENTE+
	    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    public ArrayList<String[]> obtenerClientes() {
	    	this.conectar();
	        ArrayList<String[]> clientes = new ArrayList<>();
	        try (Statement stm = cn.createStatement()) {
	            ResultSet resultado = stm.executeQuery("SELECT * FROM clientes");
	            while (resultado.next()) {
	                String[] cliente = {
	                    resultado.getString("DNI"),
	                    resultado.getString("Nombre"),
	                    resultado.getString("Apellidos"),
	                    resultado.getString("Direccion"),
	                    resultado.getString("Telefono"),
	                    resultado.getString("email")
	                };
	                clientes.add(cliente);
	            }
	            resultado.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return clientes;
	    }

	    public ArrayList<String[]> obtenerMecanicos() {
	    	this.conectar();
	        ArrayList<String[]> mecanicos = new ArrayList<>();
	        try (Statement stm = cn.createStatement()) {
	            ResultSet resultado = stm.executeQuery("SELECT ID_Usuario, nombre, email FROM usuarios WHERE tipo = 'Mecanico'");
	            while (resultado.next()) {
	                String[] mecanico = {
	                    resultado.getString("ID_Usuario"),
	                    resultado.getString("nombre"),
	                    resultado.getString("email")
	                };
	                mecanicos.add(mecanico);
	            }
	            resultado.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return mecanicos;
	    }

	    public ArrayList<String[]> obtenerVehiculos() {
	    	this.conectar();
	        ArrayList<String[]> vehiculos = new ArrayList<>();
	        try (Statement stm = cn.createStatement()) {
	            ResultSet resultado = stm.executeQuery("SELECT * FROM vehiculos");
	            while (resultado.next()) {
	                String[] vehiculo = {
	                    resultado.getString("Matricula"),
	                    resultado.getString("Modelo"),
	                    resultado.getString("Marca"),
	                    String.valueOf(resultado.getInt("anio")),
	                    String.valueOf(resultado.getInt("kmtotales")),
	                    String.valueOf(resultado.getInt("Clientes_DNI"))
	                };
	                vehiculos.add(vehiculo);
	            }
	            resultado.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return vehiculos;
	    }
	    
	    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    //+METODOS PARA INSERTAR CLIENTES++++++++++++++++++++++++++++++++++
	    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    public boolean insertarCliente(String dni, String nombre, String apellidos, String direccion, String telefono, String email) {
	        String sql = "INSERT INTO clientes (dni, nombre, apellidos, direccion, telefono, email) VALUES (?, ?, ?, ?, ?, ?)";
	        try (Connection conn = this.conectar();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, dni);
	            pstmt.setString(2, nombre);
	            pstmt.setString(3, apellidos);
	            pstmt.setString(4, direccion);
	            pstmt.setString(5, telefono);
	            pstmt.setString(6, email);
	            pstmt.executeUpdate();
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 // Método en la clase ConexionMySql para actualizar el cliente
	    public boolean actualizarCliente(String dni, String camposeleccionado, String nuevovalor) {
	        String sql = "UPDATE clientes SET " + camposeleccionado + " = ? WHERE DNI = ?";
	        try (Connection con = this.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, nuevovalor); // Asignar el nuevo valor
	            ps.setString(2, dni);        // Asignar el DNI
	            ps.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    public ArrayList<String[]> buscarClientePorDNI(String dni) {
	        this.conectar();
	        ArrayList<String[]> clientes = new ArrayList<>();   
	        String sql = "SELECT nombre, apellidos, direccion, telefono, email FROM clientes WHERE dni = ?";

	        try (PreparedStatement stm = cn.prepareStatement(sql)) {
	            // Establecer el parámetro del DNI en la consulta
	            stm.setString(1, dni);

	            // Ejecutar la consulta
	            try (ResultSet resultado = stm.executeQuery()) {
	                while (resultado.next()) {
	                    // Crear un array con los datos del cliente y agregarlo a la lista
	                    String[] cliente = new String[5];
	                    cliente[0] = resultado.getString("nombre");
	                    cliente[1] = resultado.getString("apellidos");
	                    cliente[2] = resultado.getString("direccion");
	                    cliente[3] = resultado.getString("telefono");
	                    cliente[4] = resultado.getString("email");

	                    clientes.add(cliente);
	                }
	            }
	        } catch (SQLException ex) {
	            System.err.println("Error al buscar el cliente por DNI: " + ex.getMessage());
	        }

	        return clientes; // Devuelve la lista de clientes (vacía si no se encontró ninguno)
	    }
	  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    //+METODOS PARA INSERTAR Mecanico++++++++++++++++++++++++++++++++++
	    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    public boolean insertarMecanico(String dni, String nombre, String clave, String email) {
	    	String tipo = "Mecanico";
	    
	        String sql = "INSERT INTO usuarios (ID_usuario, nombre, clave, tipo, email) VALUES (?, ?, ?, ?, ?)";
	        try (Connection conn = this.conectar();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, dni);
	            pstmt.setString(2, nombre);
	            pstmt.setString(3, clave);
	            pstmt.setString(4, tipo);
	            pstmt.setString(5, email);
	            pstmt.executeUpdate();
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 // Método en la clase ConexionMySql para actualizar el cliente
	    public boolean actualizarMecanico(String ID_usuario, String camposeleccionado, String nuevovalor) {
	        String sql = "UPDATE usuarios SET " + camposeleccionado + " = ? WHERE ID_Usuario = ?";
	        try (Connection con = this.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, nuevovalor); // Asignar el nuevo valor
	            ps.setString(2, ID_usuario);        // Asignar el ID_usuario
	            ps.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    public ArrayList<String[]> buscarMecanicoPorDNI(String dni) {
	        this.conectar();
	        ArrayList<String[]> usuarios = new ArrayList<>();   
	        String sql = "SELECT * FROM usuarios WHERE ID_usuario = ?";

	        try (PreparedStatement stm = cn.prepareStatement(sql)) {
	            // Establecer el parámetro del DNI en la consulta
	            stm.setString(1, dni);

	            // Ejecutar la consulta
	            try (ResultSet resultado = stm.executeQuery()) {
	                while (resultado.next()) {
	                    // Crear un array con los datos del cliente y agregarlo a la lista
	                    String[] usuario = new String[3];
	                    usuario[0] = resultado.getString("clave");
	                    usuario[1] = resultado.getString("nombre");
	                    usuario[2] = resultado.getString("email");
	                    usuarios.add(usuario);
	                }
	            }
	        } catch (SQLException ex) {
	            System.err.println("Error al buscar el cliente por DNI: " + ex.getMessage());
	        }

	        return usuarios; // Devuelve la lista de clientes (vacía si no se encontró ninguno)
	    }
	  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    //+METODOS PARA INSERTAR CLIENTES++++++++++++++++++++++++++++++++++
	    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    public boolean insertarVehiculo(String matricula, String modelo, String marca, String anio, String kmtotales, String dnicliente) {
	        String sql = "INSERT INTO vehiculos (matricula, modelo, marca, anio, kmtotales, clientes_dni) VALUES (?, ?, ?, ?, ?, ?)";
	        try (Connection conn = this.conectar();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, matricula);
	            pstmt.setString(2, modelo);
	            pstmt.setString(3, marca);
	            pstmt.setString(4, anio);
	            pstmt.setString(5, kmtotales);
	            pstmt.setString(6, dnicliente);
	            pstmt.executeUpdate();
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    public ArrayList<String[]> buscarVehiculoPorMatricula(String matricula) {
	        this.conectar();
	        ArrayList<String[]> vehiculos = new ArrayList<>();   
	        String sql = "SELECT * FROM vehiculos WHERE Matricula = ?";

	        try (PreparedStatement stm = cn.prepareStatement(sql)) {
	            // Establecer el parámetro del DNI en la consulta
	            stm.setString(1, matricula);

	            // Ejecutar la consulta
	            try (ResultSet resultado = stm.executeQuery()) {
	                while (resultado.next()) {
	                    // Crear un array con los datos del cliente +y agregarlo a la lista
	                    String[] vehiculo = new String[5];
	                    vehiculo[0] = resultado.getString("modelo");
	                    vehiculo[1] = resultado.getString("marca");
	                    vehiculo[2] = resultado.getString("anio");
	                    vehiculo[3] = resultado.getString("kmtotales");
	                    vehiculo[4] = resultado.getString("Clientes_DNI");
	                    vehiculos.add(vehiculo);
	                }
	            }
	        } catch (SQLException ex) {
	            System.err.println("Error al buscar el vehiculo por matricula: " + ex.getMessage());
	        }

	        return vehiculos; // Devuelve la lista de clientes (vacía si no se encontró ninguno)
	    }
	    public ArrayList<String[]> buscarVehiculoPorMatricula2(String matricula) {
	        ArrayList<String[]> vehiculos = new ArrayList<>();
	        String sql = "SELECT marca, matricula, modelo, anio, kmtotales, Clientes_DNI FROM vehiculos WHERE matricula = ?"; // Asegúrate de tener un ? en la consulta

	        try (Connection conn = this.conectar();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            
	            // Establecer la matrícula como parámetro de la consulta
	            pstmt.setString(1, matricula);  // El índice debe ser 1 ya que estamos usando un solo parámetro
	            
	            // Ejecutar la consulta
	            try (ResultSet rs = pstmt.executeQuery()) {
	                // Verificar si hay resultados
	                if (rs.next()) {
	                    // Crear un array con los detalles del vehículo
	                    String[] vehiculo = new String[6];
	                    vehiculo[0] = rs.getString("marca");     // Marca
	                    vehiculo[1] = rs.getString("matricula"); // Matrícula
	                    vehiculo[2] = rs.getString("modelo");    // Modelo
	                    vehiculo[3] = rs.getString("anio");      // Año
	                    vehiculo[4] = rs.getString("kmtotales");
	                    vehiculo[5] = rs.getString("Clientes_DNI");// Kilómetros totales

	                    // Añadir el vehículo a la lista
	                    vehiculos.add(vehiculo);
	                }
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }

	        return vehiculos; // Devuelve la lista de vehículos encontrados (vacía si no se encontró ninguno)
	    }

	    public boolean actualizarVehiculo(String Matricula, String camposeleccionado, String nuevovalor) {
	        String sql = "UPDATE vehiculos SET " + camposeleccionado + " = ? WHERE Matricula = ?";
	        try (Connection con = this.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, nuevovalor); // Asignar el nuevo valor
	            ps.setString(2, Matricula);        // Asignar el ID_usuario
	            ps.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    
	}
	    public boolean eliminarVehiculo(String matricula) {
	        String sql = "DELETE FROM vehiculos WHERE matricula = ?";
	        try (Connection conn = this.conectar(); 
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            
	            pstmt.setString(1, matricula); // Eliminar vehículo por matrícula
	            pstmt.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    //
	    //Metodo para obtener las ordenes de reparacion de cada mecanico
	    //
		public ArrayList<String[]> obtenerOrdenes() {
			ArrayList<String[]> ordenes = new ArrayList<>();
		    String query = "SELECT * FROM orden_reparacion LIMIT 3";

		    try (Connection conn =this.conectar();
		         Statement stmt = conn.createStatement();
		         ResultSet rs = stmt.executeQuery(query)) {

		        // Obtener los metadatos de las columnas
		        ResultSetMetaData metaData = rs.getMetaData();
		        int columnCount = metaData.getColumnCount();

		        // Leer los datos de la tabla
		        while (rs.next()) {
		            String[] fila = new String[columnCount];
		            for (int i = 1; i <= columnCount; i++) {
		                fila[i - 1] = rs.getString(i);
		            }
		            ordenes.add(fila);
		        }

		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
			return ordenes;

		}
}


