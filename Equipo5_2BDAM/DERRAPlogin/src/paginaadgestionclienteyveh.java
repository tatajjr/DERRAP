import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Estilos.estilos;

import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class paginaadgestionclienteyveh extends JFrame {
	
    ConexionMySql conexion = new ConexionMySql();
    Connection cn = null;
    Statement stm = null;
    
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private static JTable tableClientes;
	private JTable tableMecanicos;
	private JTable tableVehiculos;
	private JTable tableClientesact;
	private JTextField txtNuevoCampoMecanico;
	private JTextField txtNuevoCampoCliente;
	private JTextField txtNuevoCampoVehiculo;
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {

                paginaadgestionclienteyveh frame = new paginaadgestionclienteyveh();
            	cargarTablaClientes();
                frame.setVisible(true);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public paginaadgestionclienteyveh() {
        setTitle("Derrap Administrador | Gestión clientes y vehículos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 550);
        setResizable(false);
        setLocationRelativeTo(null);
     // Set up the main panel with background color and layout
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(estilos.obtenerColorFondo());
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //
        // PANEL LATERAL CON SUS OPCIONES CORRESPONDIENTES
        //
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(240, 240, 250));
        sidebar.setBounds(0, 0, 200, 461);
        sidebar.setLayout(null);
        contentPane.add(sidebar);

        JLabel lblDerrap = new JLabel("DERRAP");
        lblDerrap.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblDerrap.setBounds(10, 20, 112, 27);
        //sidebar.add(lblDerrap);
     // Title Section
        JLabel lblLogo1 = new JLabel("Talleres");
        lblLogo1.setFont(estilos.obtenerFuenteTitulo());
        lblLogo1.setForeground(estilos.COLOR_TEXTO_TITULO);
        lblLogo1.setBounds(10, 10, 112, 47);
        sidebar.add(lblLogo1);

        JLabel lblLogo2 = new JLabel("Derrap");
        lblLogo2.setFont(estilos.obtenerFuenteTitulo());
        lblLogo2.setForeground(estilos.COLOR_TEXTO_SECUNDARIO);
        lblLogo2.setBounds(110, 10, 112, 47);
        sidebar.add(lblLogo2);

        JLabel lbldashboard = new JLabel("MENU");
        lbldashboard.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbldashboard.setBounds(10, 68, 112, 23);
        sidebar.add(lbldashboard);

        JButton btnClientes = new JButton("CLIENTES");
        btnClientes.setBounds(10, 123, 180, 30);
        
        btnClientes.setFont(estilos.obtenerFuenteBoton());
        btnClientes.setBackground(estilos.COLOR_BOTON_NORMAL);
        btnClientes.setForeground(Color.WHITE);
        btnClientes.setFocusPainted(false);
        //btnClientes.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
        btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sidebar.add(btnClientes);

        JButton btnMecanicos = new JButton("MECÁNICOS");
        btnMecanicos.setBounds(10, 173, 180, 30);
        btnMecanicos.setFont(estilos.obtenerFuenteBoton());
        btnMecanicos.setBackground(estilos.COLOR_BOTON_NORMAL);
        btnMecanicos.setForeground(Color.WHITE);
        btnMecanicos.setFocusPainted(false);
        //btnMecanicos.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
        btnMecanicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sidebar.add(btnMecanicos);

        JButton btnVehiculos = new JButton("VEHÍCULOS");
        btnVehiculos.setBounds(10, 223, 180, 30);
        btnVehiculos.setFont(estilos.obtenerFuenteBoton());
        btnVehiculos.setBackground(estilos.COLOR_BOTON_NORMAL);
        btnVehiculos.setForeground(Color.WHITE);
        btnVehiculos.setFocusPainted(false);
        //btnVehiculos.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
        btnVehiculos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sidebar.add(btnVehiculos);
        
        JButton btnVolver = new JButton("CERRAR SESION");
        btnVolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		login login= new login();
        		login.setVisible(true);
                dispose();
        	}
        });
        btnVolver.setBounds(10, 372, 180, 30);
        btnVolver.setFont(estilos.obtenerFuenteBoton());
        btnVolver.setBackground(Color.RED);
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        //btnVehiculos.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
        btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sidebar.add(btnVolver);

        // Main content area with CardLayout
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.setBounds(200, 0, 534, 461);
        contentPane.add(cardPanel);
        //
        // Sección de CLIENTES
        //
	        JPanel panelClientes = new JPanel();
	        panelClientes.setBackground(new Color(240, 240, 250));
	        panelClientes.setLayout(null);
	        JLabel lblClientes = new JLabel("Gestión de clientes");
	        lblClientes.setFont(estilos.obtenerFuenteTitulo());
	        lblClientes.setForeground(estilos.COLOR_TEXTO_TITULO);
	        lblClientes.setBounds(24, 10, 300, 47);
	        panelClientes.add(lblClientes);
	
	        JButton btnCrearCliente = new JButton("Crear");
	        btnCrearCliente.setBounds(24, 81, 100, 30);
	        btnCrearCliente.setFont(estilos.obtenerFuenteBoton());
	        btnCrearCliente.setBackground(estilos.COLOR_BOTON_NORMAL);
	        btnCrearCliente.setForeground(Color.WHITE);
	        btnCrearCliente.setFocusPainted(false);
	        //btnVehiculos.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
	        btnCrearCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        panelClientes.add(btnCrearCliente);
	
	        JButton btnActualizarCliente = new JButton("Actualizar");	      
	        btnActualizarCliente.setBounds(134, 81, 120, 30);
	        btnActualizarCliente.setFont(estilos.obtenerFuenteBoton());
	        btnActualizarCliente.setBackground(estilos.COLOR_BOTON_NORMAL);
	        btnActualizarCliente.setForeground(Color.WHITE);
	        btnActualizarCliente.setFocusPainted(false);
	        //btnVehiculos.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
	        btnActualizarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        panelClientes.add(btnActualizarCliente);
	
	        JScrollPane scrollClientes = new JScrollPane();
	        scrollClientes.setBounds(24, 122, 500, 283);
	        panelClientes.add(scrollClientes);
	
	        tableClientes = new JTable();
	        tableClientes.setBackground(new Color(240, 240, 250));
	        scrollClientes.setViewportView(tableClientes);
	        cardPanel.add(panelClientes, "CLIENTES");
     
		        //
		        // Subsecciones de CLIENTES
		        //
        
			        JPanel panelCrearCliente = crearPanelCrearCliente();
			        cardPanel.add(panelCrearCliente, "CREAR_CLIENTE");
			
			
			        JPanel panelActualizarCliente = crearActualizarClientePanel();
			        cardPanel.add(panelActualizarCliente, "ACTUALIZAR_CLIENTE");

			
			        btnCrearCliente.addActionListener(e -> cardLayout.show(cardPanel, "CREAR_CLIENTE"));
			        btnActualizarCliente.addActionListener(e -> cardLayout.show(cardPanel, "ACTUALIZAR_CLIENTE"));
			        
        //
        // Sección de MECÁNICOS
	    //
			        
	        
	        
	        JPanel panelMecanicos = new JPanel();
	        panelMecanicos.setBackground(new Color(240, 240, 250));
	        panelMecanicos.setLayout(null);
	        JLabel lblMecanicos = new JLabel("Gestión de mecánicos");
	        lblMecanicos.setFont(estilos.obtenerFuenteTitulo());
	        lblMecanicos.setForeground(estilos.COLOR_TEXTO_TITULO);
	        lblMecanicos.setBounds(24, 10, 300, 47);
	        panelMecanicos.add(lblMecanicos);
	        
	        
	        
	        JButton btnCrearMecanico = new JButton("Crear");
	        btnCrearMecanico.setBounds(24, 81, 100, 30);
	        btnCrearMecanico.setFont(estilos.obtenerFuenteBoton());
	        btnCrearMecanico.setBackground(estilos.COLOR_BOTON_NORMAL);
	        btnCrearMecanico.setForeground(Color.WHITE);
	        btnCrearMecanico.setFocusPainted(false);
	        //btnVehiculos.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
	        btnCrearMecanico.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        panelMecanicos.add(btnCrearMecanico);
	
	        JButton btnActualizarMecanico = new JButton("Actualizar");	      
	        btnActualizarMecanico.setBounds(134, 81, 120, 30);
	        btnActualizarMecanico.setFont(estilos.obtenerFuenteBoton());
	        btnActualizarMecanico.setBackground(estilos.COLOR_BOTON_NORMAL);
	        btnActualizarMecanico.setForeground(Color.WHITE);
	        btnActualizarMecanico.setFocusPainted(false);
	        //btnVehiculos.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
	        btnActualizarMecanico.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        panelMecanicos.add(btnActualizarMecanico);
	
	        JScrollPane scrollMecanicos = new JScrollPane();
	        scrollMecanicos.setBounds(24, 122, 500, 283);
	        panelMecanicos.add(scrollMecanicos);
	
	        tableMecanicos = new JTable();
	        tableMecanicos.setBackground(new Color(240, 240, 250));
	        scrollMecanicos.setViewportView(tableMecanicos);
	        cardPanel.add(panelMecanicos, "MECÁNICOS");
	        	
	        
			    //
		        // Subsecciones de MECÁNICOS
			    //    
	        
			        JPanel panelCrearMecanico = crearPanelCrearMecanico();
			        cardPanel.add(panelCrearMecanico, "CREAR_MECÁNICO");
			
			        JPanel panelActualizarMecanico = crearActualizarMecanicoPanel();
			        cardPanel.add(panelActualizarMecanico, "ACTUALIZAR_MECÁNICO");
			
			
			        btnCrearMecanico.addActionListener(e -> cardLayout.show(cardPanel, "CREAR_MECÁNICO"));
			        btnActualizarMecanico.addActionListener(e -> cardLayout.show(cardPanel, "ACTUALIZAR_MECÁNICO"));

			        
		//
        // Sección de VEHÍCULOS
		//
			        
	        JPanel panelVehiculos = new JPanel();
	        panelVehiculos.setBackground(new Color(240, 240, 250));
	        panelVehiculos.setLayout(null);
	        JLabel lblVehiculos = new JLabel("Gestión de vehículos");
	        lblVehiculos.setFont(estilos.obtenerFuenteTitulo());
	        lblVehiculos.setForeground(estilos.COLOR_TEXTO_TITULO);
	        lblVehiculos.setBounds(24, 10, 500, 47);
	        panelVehiculos.add(lblVehiculos);
	
	        JButton btnCrearVehiculo = new JButton("Crear");
	        btnCrearVehiculo.setFont(estilos.obtenerFuenteBoton());
	        btnCrearVehiculo.setBackground(estilos.COLOR_BOTON_NORMAL);
	        btnCrearVehiculo.setForeground(Color.WHITE);
	        btnCrearVehiculo.setFocusPainted(false);
	        //btnVehiculos.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
	        btnCrearVehiculo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        btnCrearVehiculo.setBounds(24, 81, 100, 30);
	        panelVehiculos.add(btnCrearVehiculo);
	
	        JButton btnActualizarVehiculo = new JButton("Actualizar");
	        btnActualizarVehiculo.setFont(estilos.obtenerFuenteBoton());
	        btnActualizarVehiculo.setBackground(estilos.COLOR_BOTON_NORMAL);
	        btnActualizarVehiculo.setForeground(Color.WHITE);
	        btnActualizarVehiculo.setFocusPainted(false);
	        //btnVehiculos.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
	        btnActualizarVehiculo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        btnActualizarVehiculo.setBounds(134, 81, 120, 30);
	        panelVehiculos.add(btnActualizarVehiculo);
	
	        JButton btnEliminarVehiculo = new JButton("Eliminar");
	        btnEliminarVehiculo.setFont(estilos.obtenerFuenteBoton());
	        btnEliminarVehiculo.setBackground(estilos.COLOR_BOTON_NORMAL);
	        btnEliminarVehiculo.setForeground(Color.WHITE);
	        btnEliminarVehiculo.setFocusPainted(false);
	        //btnVehiculos.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
	        btnEliminarVehiculo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        btnEliminarVehiculo.setBounds(264, 81, 100, 30);
	        panelVehiculos.add(btnEliminarVehiculo);
	
	        JScrollPane scrollVehiculos = new JScrollPane();
	        scrollVehiculos.setBounds(24, 122, 500, 283);
	        panelVehiculos.add(scrollVehiculos);
	
	        tableVehiculos = new JTable();
	        tableVehiculos.setBackground(new Color(240, 240, 250));
	        scrollVehiculos.setViewportView(tableVehiculos);
	        cardPanel.add(panelVehiculos, "VEHÍCULOS");
	        
	        	//
	        	// Subsecciones de VEHÍCULOS
	        	//
	        
			        JPanel panelCrearVehiculo = crearPanelCrearVehiculo();
			        cardPanel.add(panelCrearVehiculo, "CREAR_VEHÍCULO");
			
			        JPanel panelActualizarVehiculo = crearActualizarVehiculoPanel();
			        cardPanel.add(panelActualizarVehiculo, "ACTUALIZAR_VEHÍCULO");
			
			        JPanel panelEliminarVehiculo = crearEliminarVehiculoPanel();
			        cardPanel.add(panelEliminarVehiculo, "ELIMINAR_VEHÍCULO");

			        btnCrearVehiculo.addActionListener(e -> cardLayout.show(cardPanel, "CREAR_VEHÍCULO"));
			        btnActualizarVehiculo.addActionListener(e -> cardLayout.show(cardPanel, "ACTUALIZAR_VEHÍCULO"));
			        btnEliminarVehiculo.addActionListener(e -> cardLayout.show(cardPanel, "ELIMINAR_VEHÍCULO"));

	    //
        // Sidebar navigation
		//
			        
        btnClientes.addActionListener(e -> cardLayout.show(cardPanel, "CLIENTES"));
        btnMecanicos.addActionListener(e -> cardLayout.show(cardPanel, "MECÁNICOS"));
        btnVehiculos.addActionListener(e -> cardLayout.show(cardPanel, "VEHÍCULOS"));
        
        //
        // Add action listeners for buttons
        //
	       btnClientes.addActionListener(e -> {
	            cardLayout.show(cardPanel, "CLIENTES");
	            cargarTablaClientes();
	        });
	        
           btnMecanicos.addActionListener(e -> {
               cardLayout.show(cardPanel, "MECÁNICOS");
               cargarTablaMecanicos();
           });


           btnVehiculos.addActionListener(e -> {
               cardLayout.show(cardPanel, "VEHÍCULOS");
               cargarTablaVehiculos();
           });
        
        
        
    }
   // Helper method to create sub-panels

    private JPanel crearPanelCrearCliente() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 250));
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Crear cliente");
        lblTitulo.setFont(estilos.obtenerFuenteTitulo());
        lblTitulo.setForeground(estilos.COLOR_TEXTO_TITULO);
        lblTitulo.setBounds(24, 10, 300, 47);
        panel.add(lblTitulo);

        JLabel lblDni = new JLabel("Usuario (DNI)");
        lblDni.setBounds(36, 89, 184, 25);
        lblDni.setFont(estilos.obtenerFuenteCampos());
        panel.add(lblDni);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(306, 89, 80, 25);
        lblNombre.setFont(estilos.obtenerFuenteCampos());
        panel.add(lblNombre);

        JLabel lblApellidos = new JLabel("Apellidos");
        lblApellidos.setBounds(36, 148, 80, 25);
        lblApellidos.setFont(estilos.obtenerFuenteCampos());
        panel.add(lblApellidos);

        JLabel lblDireccion = new JLabel("Dirección");
        lblDireccion.setBounds(306, 148, 80, 25);
        lblDireccion.setFont(estilos.obtenerFuenteCampos());
        panel.add(lblDireccion);

        JLabel lblTelefono = new JLabel("Teléfono");
        lblTelefono.setBounds(36, 209, 80, 25);
        lblTelefono.setFont(estilos.obtenerFuenteCampos());
        panel.add(lblTelefono);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(306, 209, 80, 25);
        lblEmail.setFont(estilos.obtenerFuenteCampos());
        panel.add(lblEmail);

        // Campos de texto
        JTextField txtDni = new JTextField();
        txtDni.setFont(estilos.obtenerFuenteCampos());
        txtDni.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtDni.setBounds(36, 114, 200, 25);
        panel.add(txtDni);

        JTextField txtNombre = new JTextField();
        txtNombre.setFont(estilos.obtenerFuenteCampos());
        txtNombre.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtNombre.setBounds(306, 114, 200, 25);
        panel.add(txtNombre);

        JTextField txtApellidos = new JTextField();
        txtApellidos.setFont(estilos.obtenerFuenteCampos());
        txtApellidos.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtApellidos.setBounds(36, 173, 200, 25);
        panel.add(txtApellidos);

        JTextField txtDireccion = new JTextField();
        txtDireccion.setFont(estilos.obtenerFuenteCampos());
        txtDireccion.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtDireccion.setBounds(306, 173, 200, 25);
        panel.add(txtDireccion);

        JTextField txtTelefono = new JTextField();
        txtTelefono.setFont(estilos.obtenerFuenteCampos());
        txtTelefono.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtTelefono.setBounds(36, 233, 200, 25);
        panel.add(txtTelefono);

        JTextField txtEmail = new JTextField();
        txtEmail.setFont(estilos.obtenerFuenteCampos());
        txtEmail.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtEmail.setBounds(306, 233, 200, 25);
        panel.add(txtEmail);

        // Botón Crear
        JButton btnCrear = new JButton("CREAR");
        btnCrear.setFont(estilos.obtenerFuenteBoton());
        btnCrear.setBackground(estilos.COLOR_BOTON_NORMAL);
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setFocusPainted(false);
        //btnVehiculos.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
        btnCrear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCrear.setBounds(36, 372, 180, 30);
        panel.add(btnCrear);

        btnCrear.addActionListener(e -> {
            String dni = txtDni.getText();
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();
            String email = txtEmail.getText();

            // Llamar al método para insertar en la base de datos
            ConexionMySql conexion = new ConexionMySql();
            boolean exito = conexion.insertarCliente(dni, nombre, apellidos, direccion, telefono, email);

            if (exito) {
                javax.swing.JOptionPane.showMessageDialog(this, "Cliente creado exitosamente.");
                // Limpiar los campos
                txtDni.setText("");
                txtNombre.setText("");
                txtApellidos.setText("");
                txtDireccion.setText("");
                txtTelefono.setText("");
                txtEmail.setText("");
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Error al crear cliente. Revisa los datos.");
            }
        });

        // Botón Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(estilos.obtenerFuenteBoton());
        btnVolver.setForeground(new Color(51, 51, 255)); // Blue text
        btnVolver.setContentAreaFilled(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnVolver.setBounds(406, 370, 100, 30);
        panel.add(btnVolver);

     // Acción del botón Volver
        btnVolver.addActionListener(e -> {
            // Limpiar los campos del formulario
            txtDni.setText("");
            txtNombre.setText("");
            txtApellidos.setText("");
            txtDireccion.setText("");
            txtTelefono.setText("");
            txtEmail.setText("");

            // Cambiar al panel "CLIENTES"
            cardLayout.show(cardPanel, "CLIENTES");
        });

        return panel;
    }
  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+METODOS PARA CARD LAYOUT ACTUALZIARCLIENTES+++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private JPanel crearActualizarClientePanel() {
        JPanel panelActualizarCliente = new JPanel();
        panelActualizarCliente.setBackground(new Color(240, 240, 250));
        panelActualizarCliente.setLayout(null);

        JLabel lblActualizarCliente = new JLabel("Actualizar cliente");
        panelActualizarCliente.add(lblActualizarCliente);
        lblActualizarCliente.setFont(estilos.obtenerFuenteTitulo());
        lblActualizarCliente.setForeground(estilos.COLOR_TEXTO_TITULO);
        lblActualizarCliente.setBounds(24, 10, 300, 47);

        
        JLabel lblDNI = new JLabel("Introduzca Usuario (DNI)");
        lblDNI.setFont(estilos.obtenerFuenteCampos());
        lblDNI.setBounds(24, 68, 200, 25);  // Mantén la posición a la izquierda
        panelActualizarCliente.add(lblDNI);

        JTextField txtDni = new JTextField("");
        txtDni.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtDni.setBounds(24, 94, 200, 25);  // Mantén la posición alineada
        panelActualizarCliente.add(txtDni);

        // Botón de actualizar
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setFont(estilos.obtenerFuenteBoton());
        btnActualizar.setBackground(estilos.COLOR_BOTON_NORMAL);
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFocusPainted(false);
        //btnVehiculos.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
        btnActualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnActualizar.setBounds(284, 322, 120, 30);
        panelActualizarCliente.add(btnActualizar);

        // Botón de volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setForeground(new Color(51, 51, 255)); // Blue text
        btnVolver.setContentAreaFilled(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolver.setBounds(414, 322, 120, 30);
        panelActualizarCliente.add(btnVolver);
        
        JScrollPane scrollClientes = new JScrollPane();
        scrollClientes.setBounds(24, 172, 500, 54);
        panelActualizarCliente.add(scrollClientes);
        
        tableClientesact = new JTable();
        tableClientesact.setBackground(new Color(240, 240, 250));
        tableClientesact.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Nombre", "Apellidos", "Direccion", "Telefono", "Email"
        	}
        ));
        tableClientesact.getColumnModel().getColumn(2).setPreferredWidth(63);
        scrollClientes.setViewportView(tableClientesact);
        
        JButton btnBuscarUsuario = new JButton("Buscar");
        btnBuscarUsuario.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBuscarUsuario.setForeground(new Color(51, 51, 255)); // Blue text
        btnBuscarUsuario.setContentAreaFilled(false);
        btnBuscarUsuario.setBorderPainted(false);
        btnBuscarUsuario.setFocusPainted(false);
        btnBuscarUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBuscarUsuario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ArrayList<String[]> clientes = conexion.buscarClientePorDNI(txtDni.getText());
        		// Obtener el modelo de la tabla
        		DefaultTableModel model = (DefaultTableModel) tableClientesact.getModel();

        		// Limpiar cualquier dato existente en la tabla
        		model.setRowCount(0);

        		// Recorrer el ArrayList y agregar las filas al modelo
        		for (String[] cliente : clientes) {
        		    model.addRow(new Object[] {
        		        cliente[0], // Nombre
        		        cliente[1], // Apellidos
        		        cliente[2], // Dirección
        		        cliente[3], // Teléfono
        		        cliente[4]  // Email
        		    });
        		}
        		
        	}
        });
        btnBuscarUsuario.setBounds(248, 95, 89, 23);
        panelActualizarCliente.add(btnBuscarUsuario);
        
        JComboBox cbxCampos = new JComboBox();
        cbxCampos.setModel(new DefaultComboBoxModel(new String[] {"Nombre", "Apellidos", "Direccion", "Telefono", "Email"}));
        cbxCampos.setFont(estilos.obtenerFuenteCampos());
        cbxCampos.setBounds(24, 326, 120, 22);
        panelActualizarCliente.add(cbxCampos);
        
        txtNuevoCampoCliente = new JTextField();
        txtNuevoCampoCliente.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtNuevoCampoCliente.setBounds(154, 322, 120, 30);
        panelActualizarCliente.add(txtNuevoCampoCliente);
        txtNuevoCampoCliente.setColumns(10);
        
        JLabel lblInfo = new JLabel("Selccione campo e introduzca valor");
        lblInfo.setFont(estilos.obtenerFuenteCampos());
        lblInfo.setBounds(24, 292, 333, 25);
        panelActualizarCliente.add(lblInfo);

        // Acción del botón Volver
        btnVolver.addActionListener(e -> {
            // Limpiar los campos del formulario
            txtDni.setText("");
            txtNuevoCampoCliente.setText("");

            // Cambiar al panel "CLIENTES"
            cardLayout.show(cardPanel, "CLIENTES");
        });

        // Acción del botón Actualizar
        btnActualizar.addActionListener(e -> {
            String dni = txtDni.getText();
            String nuevoValor2 = txtNuevoCampoCliente.getText(); // Obtiene el nuevo valor del campo
            System.out.println("txtNuevoCampo: " + nuevoValor2);
            String campoSeleccionado = cbxCampos.getSelectedItem().toString(); // Obtiene el campo seleccionado

            if (dni.isEmpty() || nuevoValor2.isEmpty()) {
            	System.out.println("DNI: " + txtDni.getText());
            	System.out.println("txtNuevoCampo: " + txtNuevoCampoCliente.getText());
                System.out.println("Por favor, rellena todos los campos.");
            } else {
                ConexionMySql conexion = new ConexionMySql();
                if (conexion.actualizarCliente(dni, campoSeleccionado, nuevoValor2)) { // Pasa los valores al método
                    System.out.println("Cliente actualizado con éxito.");
                    cardLayout.show(cardPanel, "CLIENTES");
                    cargarTablaClientes(); // Actualiza la tabla
                 // Limpiar los campos
                    txtDni.setText("");
                    txtNuevoCampoCliente.setText("");
                    cbxCampos.setSelectedIndex(0);
                    DefaultTableModel model = (DefaultTableModel) tableClientesact.getModel();
                    model.setRowCount(0);// Restablecer el JComboBox al primer elemento
                    
                } else {
                    System.out.println("Error al actualizar el cliente.");
                }
            }
        });

        return panelActualizarCliente;
    }

   
  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+METODOS PARA CARD LAYOUT CREARMECANICOS++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private JPanel crearPanelCrearMecanico() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 250));
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Crear mecánico");
        lblTitulo.setFont(estilos.obtenerFuenteTitulo());
        lblTitulo.setForeground(estilos.COLOR_TEXTO_TITULO);
        lblTitulo.setBounds(24, 10, 300, 47);
        panel.add(lblTitulo);

        JLabel lblDni = new JLabel("Usuario (DNI)");
        lblDni.setBounds(36, 89, 184, 25);
        lblDni.setFont(estilos.obtenerFuenteCampos());
        panel.add(lblDni);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(306, 89, 80, 25);
        lblNombre.setFont(estilos.obtenerFuenteCampos());
        panel.add(lblNombre);

        JLabel lblClave = new JLabel("Clave");
        lblClave.setBounds(36, 148, 80, 25);
        lblClave.setFont(estilos.obtenerFuenteCampos());
        panel.add(lblClave);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(306, 148, 80, 25);
        lblEmail.setFont(estilos.obtenerFuenteCampos());
        panel.add(lblEmail);

        

        // Campos de texto
        JTextField txtDni = new JTextField();
        txtDni.setFont(estilos.obtenerFuenteCampos());
        txtDni.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtDni.setBounds(36, 114, 200, 25);
        panel.add(txtDni);

        JTextField txtNombre = new JTextField();
        txtNombre.setFont(estilos.obtenerFuenteCampos());
        txtNombre.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtNombre.setBounds(306, 114, 200, 25);
        panel.add(txtNombre);

        JTextField txtClave = new JTextField();
        txtClave.setFont(estilos.obtenerFuenteCampos());
        txtClave.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtClave.setBounds(36, 173, 200, 25);
        panel.add(txtClave);

        JTextField txtEmail = new JTextField();
        txtEmail.setFont(estilos.obtenerFuenteCampos());
        txtEmail.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtEmail.setBounds(306, 173, 200, 25);
        panel.add(txtEmail);

        // Botón Crear
        JButton btnCrear = new JButton("CREAR");
        btnCrear.setFont(estilos.obtenerFuenteBoton());
        btnCrear.setBackground(estilos.COLOR_BOTON_NORMAL);
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setFocusPainted(false);
        //btnVehiculos.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
        btnCrear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCrear.setBounds(36, 372, 180, 30);
        panel.add(btnCrear);

        btnCrear.addActionListener(e -> {
            String dni = txtDni.getText();
            String nombre = txtNombre.getText();
            String clave = txtClave.getText();
            String email = txtEmail.getText();
            
            
            // Llamar al método para insertar en la base de datos
            ConexionMySql conexion = new ConexionMySql();
            boolean exito = conexion.insertarMecanico(dni, nombre, clave, email);

            if (exito) {
                javax.swing.JOptionPane.showMessageDialog(this, "Cliente creado exitosamente.");
                // Limpiar los campos
                txtDni.setText("");
                txtNombre.setText("");
                txtClave.setText("");
                txtEmail.setText("");

            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Error al crear cliente. Revisa los datos.");
            }
        });

        // Botón Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(estilos.obtenerFuenteBoton());
        btnVolver.setForeground(new Color(51, 51, 255)); // Blue text
        btnVolver.setContentAreaFilled(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnVolver.setBounds(406, 370, 100, 30);
        panel.add(btnVolver);

     // Acción del botón Volver
        btnVolver.addActionListener(e -> {
            // Limpiar los campos del formulario
            txtDni.setText("");
            txtNombre.setText("");
            txtClave.setText("");
            txtEmail.setText("");;

            // Cambiar al panel "CLIENTES"
            cardLayout.show(cardPanel, "CLIENTES");
        });

        return panel;
    }
  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+METODOS PARA CARD LAYOUT ACTUALZIARMECANICOS+++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private JPanel crearActualizarMecanicoPanel() {
        JPanel panelActualizarMecanico = new JPanel();
        panelActualizarMecanico.setBackground(new Color(240, 240, 250));
        panelActualizarMecanico.setLayout(null);

        JLabel lblActualizarMecanico = new JLabel("Actualizar mecánico");
        panelActualizarMecanico.add(lblActualizarMecanico);
        lblActualizarMecanico.setFont(estilos.obtenerFuenteTitulo());
        lblActualizarMecanico.setForeground(estilos.COLOR_TEXTO_TITULO);
        lblActualizarMecanico.setBounds(24, 10, 300, 47);

        
        JLabel lblDNI = new JLabel("Introduzca Usuario (DNI)");
        lblDNI.setFont(estilos.obtenerFuenteCampos());
        lblDNI.setBounds(24, 68, 200, 25);  // Mantén la posición a la izquierda
        panelActualizarMecanico.add(lblDNI);

        JTextField txtDNI = new JTextField("");
        txtDNI.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtDNI.setBounds(24, 94, 200, 25);  // Mantén la posición alineada
        panelActualizarMecanico.add(txtDNI);

        // Botón de actualizar
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setFont(estilos.obtenerFuenteBoton());
        btnActualizar.setBackground(estilos.COLOR_BOTON_NORMAL);
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFocusPainted(false);
        //btnVehiculos.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
        btnActualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnActualizar.setBounds(284, 322, 120, 30);
        panelActualizarMecanico.add(btnActualizar);

        // Botón de volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setForeground(new Color(51, 51, 255)); // Blue text
        btnVolver.setContentAreaFilled(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolver.setBounds(414, 322, 120, 30);
        panelActualizarMecanico.add(btnVolver);
        
        JScrollPane scrollMecanicos = new JScrollPane();
        scrollMecanicos.setBounds(24, 172, 500, 54);
        panelActualizarMecanico.add(scrollMecanicos);
        
        JTable tableMecanicosact = new JTable();
        tableMecanicosact.setBackground(new Color(240, 240, 250));
        tableMecanicosact.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Clave", "Nombre", "Email"
        	}
        ));
        tableMecanicosact.getColumnModel().getColumn(2).setPreferredWidth(63);
        scrollMecanicos.setViewportView(tableMecanicosact);
        
        
        JButton btnBuscarUsuario = new JButton("Buscar");
        btnBuscarUsuario.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBuscarUsuario.setForeground(new Color(51, 51, 255)); // Blue text
        btnBuscarUsuario.setContentAreaFilled(false);
        btnBuscarUsuario.setBorderPainted(false);
        btnBuscarUsuario.setFocusPainted(false);
        btnBuscarUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBuscarUsuario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ArrayList<String[]> mecanicos = conexion.buscarMecanicoPorDNI(txtDNI.getText());
        		// Obtener el modelo de la tabla
        		DefaultTableModel model = (DefaultTableModel) tableMecanicosact.getModel();

        		// Limpiar cualquier dato existente en la tabla
        		model.setRowCount(0);

        		// Recorrer el ArrayList y agregar las filas al modelo
        		for (String[] mecanico : mecanicos) {
        		    model.addRow(new Object[] {
        		    		mecanico[0], // Clave
        		    		mecanico[1], // Nombre
        		    		mecanico[2], // Email
  
        		    });
        		}
        		
        	}
        });
        btnBuscarUsuario.setBounds(248, 95, 89, 23);
        panelActualizarMecanico.add(btnBuscarUsuario);
        
        JComboBox cbxCampos = new JComboBox();
        cbxCampos.setModel(new DefaultComboBoxModel(new String[] {"Clave", "Nombre", "Email"}));
        cbxCampos.setFont(estilos.obtenerFuenteCampos());
        cbxCampos.setBounds(24, 326, 120, 22);
        panelActualizarMecanico.add(cbxCampos);
        
        txtNuevoCampoMecanico = new JTextField();
        txtNuevoCampoMecanico.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtNuevoCampoMecanico.setBounds(154, 322, 120, 30);
        panelActualizarMecanico.add(txtNuevoCampoMecanico);
        txtNuevoCampoMecanico.setColumns(10);
        
        JLabel lblInfo = new JLabel("Selccione campo e introduzca valor");
        lblInfo.setFont(estilos.obtenerFuenteCampos());
        lblInfo.setBounds(24, 292, 333, 25);
        panelActualizarMecanico.add(lblInfo);

        // Acción del botón Volver
        btnVolver.addActionListener(e -> {
            // Limpiar los campos del formulario
            txtDNI.setText("");
            txtNuevoCampoMecanico.setText("");

            // Cambiar al panel "MECÁNICOS"
            cardLayout.show(cardPanel, "MECÁNICOS");
        });

        // Acción del botón Actualizar
        btnActualizar.addActionListener(e -> {
            String dni = txtDNI.getText();
            String nuevoValor = txtNuevoCampoMecanico.getText(); // Obtiene el nuevo valor del campo
            String campoSeleccionado = cbxCampos.getSelectedItem().toString(); // Obtiene el campo seleccionado

            if (dni.isEmpty() || nuevoValor.isEmpty()) {
                System.out.println("Por favor, rellena todos los campos.");
            } else {
            	ConexionMySql conexion = new ConexionMySql();
                if (conexion.actualizarMecanico(dni, campoSeleccionado, nuevoValor)) { // Pasa los valores al método
                    System.out.println("Mecanico actualizado con éxito.");
                    cardLayout.show(cardPanel, "MECÁNICOS");
                    cargarTablaMecanicos(); // Actualiza la tabla
                 // Limpiar los campos
                    txtDNI.setText("");
                    txtNuevoCampoMecanico.setText("");
                    cbxCampos.setSelectedIndex(0);
                    DefaultTableModel model = (DefaultTableModel) tableClientesact.getModel();
                    model.setRowCount(0);// Restablecer el JComboBox al primer elemento
                    
                } else {
                    System.out.println("Error al actualizar el cliente.");
                }
            }
        });

        return panelActualizarMecanico;
    }
    
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+METODOS PARA CARGAR LOS DATOS DE CADA TABLAS++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    static void cargarTablaClientes() {
        ConexionMySql conexion = new ConexionMySql();
        ArrayList<String[]> clientes = conexion.obtenerClientes();
        String[] columnas = { "DNI", "Nombre", "Apellidos", "Dirección", "Teléfono", "Email" };
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        for (String[] cliente : clientes) {
            modelo.addRow(cliente);
        }
        try {
			tableClientes.setModel(modelo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void cargarTablaMecanicos() {
        ConexionMySql conexion = new ConexionMySql();
        ArrayList<String[]> mecanicos = conexion.obtenerMecanicos();
        String[] columnas = { "ID Usuario", "Nombre", "Email" };
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        for (String[] mecanico : mecanicos) {
            modelo.addRow(mecanico);
        }
        tableMecanicos.setModel(modelo);
    }

    private void cargarTablaVehiculos() {
        ConexionMySql conexion = new ConexionMySql();
        ArrayList<String[]> vehiculos = conexion.obtenerVehiculos();
        String[] columnas = { "Matrícula", "Modelo", "Marca", "Anio", "Km Totales", "DNI Cliente" };
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        for (String[] vehiculo : vehiculos) {
            modelo.addRow(vehiculo);
        }
        tableVehiculos.setModel(modelo);
    }
  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+METODOS PARA CARD LAYOUT CREARVEHICULOS++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private JPanel crearPanelCrearVehiculo() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 250));
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Crear vehículo");
        lblTitulo.setFont(estilos.obtenerFuenteTitulo());
        lblTitulo.setForeground(estilos.COLOR_TEXTO_TITULO);
        lblTitulo.setBounds(24, 10, 300, 47);
        panel.add(lblTitulo);

        JLabel lblMatricula = new JLabel("Matricula");
        lblMatricula.setBounds(36, 89, 184, 25);
        lblMatricula.setFont(estilos.obtenerFuenteCampos());
        panel.add(lblMatricula);

        JLabel lblModelo = new JLabel("Modelo");
        lblModelo.setBounds(306, 89, 80, 25);
        lblModelo.setFont(estilos.obtenerFuenteCampos());
        panel.add(lblModelo);

        JLabel lblMarca = new JLabel("Marca");
        lblMarca.setBounds(36, 148, 80, 25);
        lblMarca.setFont(estilos.obtenerFuenteCampos());
        panel.add(lblMarca);

        JLabel lblAnio = new JLabel("Anio");
        lblAnio.setBounds(306, 148, 80, 25);
        lblAnio.setFont(estilos.obtenerFuenteCampos());
        panel.add(lblAnio);
        
        JLabel lblKmTotales = new JLabel("KM Totales");
        lblKmTotales.setBounds(36, 209, 80, 25);
        lblKmTotales.setFont(estilos.obtenerFuenteCampos());
        panel.add(lblKmTotales);
        
        JLabel lblDniCliente = new JLabel("DNI Cliente");
        lblDniCliente.setBounds(306, 209, 80, 25);
        lblDniCliente.setFont(estilos.obtenerFuenteCampos());
        panel.add(lblDniCliente);
        

        // Campos de texto
        JTextField txtMatricula = new JTextField();
        txtMatricula.setFont(estilos.obtenerFuenteCampos());
        txtMatricula.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtMatricula.setBounds(36, 114, 200, 25);
        panel.add(txtMatricula);

        JTextField txtModelo = new JTextField();
        txtModelo.setFont(estilos.obtenerFuenteCampos());
        txtModelo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtModelo.setBounds(306, 114, 200, 25);
        panel.add(txtModelo);

        JTextField txtMarca = new JTextField();
        txtMarca.setFont(estilos.obtenerFuenteCampos());
        txtMarca.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtMarca.setBounds(36, 173, 200, 25);
        panel.add(txtMarca);

        JTextField txtAnio = new JTextField();
        txtAnio.setFont(estilos.obtenerFuenteCampos());
        txtAnio.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtAnio.setBounds(306, 173, 200, 25);
        panel.add(txtAnio);
        

        JTextField txtKmTotales = new JTextField();
        txtKmTotales.setFont(estilos.obtenerFuenteCampos());
        txtKmTotales.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtKmTotales.setBounds(36, 233, 200, 25);
        panel.add(txtKmTotales);
        

        JTextField txtDniCliente = new JTextField();
        txtDniCliente.setFont(estilos.obtenerFuenteCampos());
        txtDniCliente.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtDniCliente.setBounds(306, 233, 200, 25);
        panel.add(txtDniCliente);

        // Botón Crear
        JButton btnCrear = new JButton("CREAR");
        btnCrear.setFont(estilos.obtenerFuenteBoton());
        btnCrear.setBackground(estilos.COLOR_BOTON_NORMAL);
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setFocusPainted(false);
        //btnVehiculos.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
        btnCrear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCrear.setBounds(36, 372, 180, 30);
        panel.add(btnCrear);

        btnCrear.addActionListener(e -> {
            String matricula = txtMatricula.getText();
            String modelo = txtModelo.getText();
            String marca = txtMarca.getText();
            String anio = txtAnio.getText();
            String kmtotales = txtKmTotales.getText();
            String dnicliente = txtDniCliente.getText();
            
            
            // Llamar al método para insertar en la base de datos
            ConexionMySql conexion = new ConexionMySql();
            boolean exito = conexion.insertarVehiculo(matricula, modelo, marca, anio, kmtotales, dnicliente);

            if (exito) {
                javax.swing.JOptionPane.showMessageDialog(this, "Cliente creado exitosamente.");
                // Limpiar los campos
                txtMatricula.setText("");
                txtModelo.setText("");
                txtMarca.setText("");
                txtAnio.setText("");
                txtKmTotales.setText("");
                txtDniCliente.setText("");

            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Error al crear cliente. Revisa los datos.");
            }
        });

        // Botón Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(estilos.obtenerFuenteBoton());
        btnVolver.setForeground(new Color(51, 51, 255)); // Blue text
        btnVolver.setContentAreaFilled(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnVolver.setBounds(406, 370, 100, 30);
        panel.add(btnVolver);

     // Acción del botón Volver
        btnVolver.addActionListener(e -> {
            // Limpiar los campos del formulario
            txtMatricula.setText("");
            txtModelo.setText("");
            txtMarca.setText("");
            txtAnio.setText("");
            txtKmTotales.setText("");
            txtDniCliente.setText("");

            // Cambiar al panel "VEHICULOS"
            cardLayout.show(cardPanel, "VEHÍCULOS");
        });

        return panel;
    }
  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+METODOS PARA CARD LAYOUT ACTUALZIARVEHICULOS+++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private JPanel crearActualizarVehiculoPanel() {
        JPanel panelActualizarVehiculo = new JPanel();
        panelActualizarVehiculo.setBackground(new Color(240, 240, 250));
        panelActualizarVehiculo.setLayout(null);

        JLabel lblActualizarVehiculo = new JLabel("Actualizar vehículo");
        panelActualizarVehiculo.add(lblActualizarVehiculo);
        lblActualizarVehiculo.setFont(estilos.obtenerFuenteTitulo());
        lblActualizarVehiculo.setForeground(estilos.COLOR_TEXTO_TITULO);
        lblActualizarVehiculo.setBounds(24, 10, 300, 47);

        
        JLabel lblMatriucla = new JLabel("Introduzca Matricula");
        lblMatriucla.setFont(estilos.obtenerFuenteCampos());
        lblMatriucla.setBounds(24, 68, 200, 25);  // Mantén la posición a la izquierda
        panelActualizarVehiculo.add(lblMatriucla);

        JTextField txtMatricula = new JTextField("");
        txtMatricula.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtMatricula.setBounds(24, 94, 200, 25);  // Mantén la posición alineada
        panelActualizarVehiculo.add(txtMatricula);

        // Botón de actualizar
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setFont(estilos.obtenerFuenteBoton());
        btnActualizar.setBackground(estilos.COLOR_BOTON_NORMAL);
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFocusPainted(false);
        //btnVehiculos.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
        btnActualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnActualizar.setBounds(284, 322, 120, 30);
        panelActualizarVehiculo.add(btnActualizar);

        // Botón de volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setForeground(new Color(51, 51, 255)); // Blue text
        btnVolver.setContentAreaFilled(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolver.setBounds(414, 322, 120, 30);
        panelActualizarVehiculo.add(btnVolver);
        
        JScrollPane scrollVehiculo = new JScrollPane();
        scrollVehiculo.setBounds(24, 172, 500, 54);
        panelActualizarVehiculo.add(scrollVehiculo);
        
        JTable tableVehiculosact = new JTable();
        tableVehiculosact.setBackground(new Color(240, 240, 250));
        tableVehiculosact.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Modelo", "Marca", "Anio", "Km Totales", "Dni Cliente"
        	}
        ));
        tableVehiculosact.getColumnModel().getColumn(2).setPreferredWidth(63);
        scrollVehiculo.setViewportView(tableVehiculosact);
        
        
        JButton btnBuscarVehiculo = new JButton("Buscar");
        btnBuscarVehiculo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBuscarVehiculo.setForeground(new Color(51, 51, 255)); // Blue text
        btnBuscarVehiculo.setContentAreaFilled(false);
        btnBuscarVehiculo.setBorderPainted(false);
        btnBuscarVehiculo.setFocusPainted(false);
        btnBuscarVehiculo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBuscarVehiculo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ArrayList<String[]> vehiculos = conexion.buscarVehiculoPorMatricula(txtMatricula.getText());
        		// Obtener el modelo de la tabla
        		DefaultTableModel model = (DefaultTableModel) tableVehiculosact.getModel();

        		// Limpiar cualquier dato existente en la tabla
        		model.setRowCount(0);

        		// Recorrer el ArrayList y agregar las filas al modelo
        		for (String[] vehiculo : vehiculos) {
        		    model.addRow(new Object[] {
        		    		vehiculo[0], // Clave
        		    		vehiculo[1], // Nombre
        		    		vehiculo[2],
        		    		vehiculo[3],
        		    		vehiculo[4],// Email
  
        		    });
        		}
        		
        	}
        });
        btnBuscarVehiculo.setBounds(248, 95, 89, 23);
        panelActualizarVehiculo.add(btnBuscarVehiculo);
        
        JComboBox cbxCampos = new JComboBox();
        cbxCampos.setModel(new DefaultComboBoxModel(new String[] {"Modelo", "Marca", "Anio", "Km Totales", "DNI Cliente"}));
        cbxCampos.setFont(estilos.obtenerFuenteCampos());
        cbxCampos.setBounds(24, 326, 120, 22);
        panelActualizarVehiculo.add(cbxCampos);
        
        txtNuevoCampoVehiculo = new JTextField();
        txtNuevoCampoVehiculo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtNuevoCampoVehiculo.setBounds(154, 322, 120, 30);
        panelActualizarVehiculo.add(txtNuevoCampoVehiculo);
        txtNuevoCampoVehiculo.setColumns(10);
        
        JLabel lblInfo = new JLabel("Selccione campo e introduzca valor");
        lblInfo.setFont(estilos.obtenerFuenteCampos());
        lblInfo.setBounds(24, 292, 333, 25);
        panelActualizarVehiculo.add(lblInfo);

        // Acción del botón Volver
        btnVolver.addActionListener(e -> {
            // Limpiar los campos del formulario
        	txtMatricula.setText("");
        	txtNuevoCampoVehiculo.setText("");

            // Cambiar al panel "VEHÍCULOS"
            cardLayout.show(cardPanel, "VEHÍCULOS");
        });

        // Acción del botón Actualizar
        btnActualizar.addActionListener(e -> {
            String matricula = txtMatricula.getText();
            String nuevoValor = txtNuevoCampoVehiculo.getText(); // Obtiene el nuevo valor del campo
            String campoSeleccionado = cbxCampos.getSelectedItem().toString(); // Obtiene el campo seleccionado

            if (matricula.isEmpty() || nuevoValor.isEmpty()) {
                System.out.println("Por favor, rellena todos los campos.");
            } else {
            	ConexionMySql conexion = new ConexionMySql();
                if (conexion.actualizarVehiculo(matricula, campoSeleccionado, nuevoValor)) { // Pasa los valores al método
                    System.out.println("Vehiculo actualizado con éxito.");
                    cardLayout.show(cardPanel, "VEHÍCULOS");
                    cargarTablaVehiculos(); // Actualiza la tabla
                 // Limpiar los campos
                    txtMatricula.setText("");
                    txtNuevoCampoVehiculo.setText("");
                    cbxCampos.setSelectedIndex(0);
                    DefaultTableModel model = (DefaultTableModel) tableVehiculosact.getModel();
                    model.setRowCount(0);// Restablecer el JComboBox al primer elemento
                    
                    
                } else {
                    System.out.println("Error al actualizar el vehiculo.");
                }
            }
        });

        return panelActualizarVehiculo;
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+METODOS PARA CARD LAYOUT crearPanelEliminarVehiculo+++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private JPanel crearEliminarVehiculoPanel() {
        JPanel panelEliminarVehiculo = new JPanel();
        panelEliminarVehiculo.setBackground(new Color(240, 240, 250));
        panelEliminarVehiculo.setLayout(null);
        // Tabla para mostrar los detalles del vehículo
        JScrollPane scrollVehiculos = new JScrollPane();
        scrollVehiculos.setBounds(24, 172, 500, 54);
        panelEliminarVehiculo.add(scrollVehiculos);
        
        JTable tableVehiculos2 = new JTable();
        tableVehiculos2.setBackground(new Color(240, 240, 250));
        tableVehiculos2.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Marca", "Matrícula", "Modelo", "Año", "Km Totales"
            }
        ));
        tableVehiculos2.getColumnModel().getColumn(2).setPreferredWidth(63);
        scrollVehiculos.setViewportView(tableVehiculos2);

        JLabel lblEliminarVehiculo = new JLabel("Eliminar Vehículo");
        lblEliminarVehiculo.setFont(estilos.obtenerFuenteTitulo());
        lblEliminarVehiculo.setForeground(estilos.COLOR_TEXTO_TITULO);
        lblEliminarVehiculo.setBounds(24, 10, 300, 47);
        panelEliminarVehiculo.add(lblEliminarVehiculo);

        JLabel lblMatricula = new JLabel("Introduzca Matrícula");
        lblMatricula.setFont(estilos.obtenerFuenteCampos());
        lblMatricula.setBounds(24, 68, 200, 25);  // Mantén la posición a la izquierda
        panelEliminarVehiculo.add(lblMatricula);

        JTextField txtMatricula = new JTextField("");
        txtMatricula.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        txtMatricula.setBounds(24, 94, 200, 25);  // Mantén la posición alineada
        panelEliminarVehiculo.add(txtMatricula);

        // Botón de buscar vehículo
        JButton btnBuscarVehiculo = new JButton("Buscar");
        btnBuscarVehiculo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBuscarVehiculo.setForeground(new Color(51, 51, 255)); // Azul
        btnBuscarVehiculo.setContentAreaFilled(false);
        btnBuscarVehiculo.setBorderPainted(false);
        btnBuscarVehiculo.setFocusPainted(false);
        btnBuscarVehiculo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBuscarVehiculo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String matricula = txtMatricula.getText();
                ArrayList<String[]> vehiculos = conexion.buscarVehiculoPorMatricula2(matricula);
                // Obtener el modelo de la tabla
                DefaultTableModel model = (DefaultTableModel) tableVehiculos2.getModel();

                // Limpiar cualquier dato existente en la tabla
                model.setRowCount(0);

                // Recorrer el ArrayList y agregar las filas al modelo
                for (String[] vehiculo : vehiculos) {
                    model.addRow(new Object[] {
                        vehiculo[0], // Marca
                        vehiculo[1], // Matrícula
                        vehiculo[2], // Modelo
                        vehiculo[3], // Año
                        vehiculo[4]// Kilómetros
                    });
                }
            }
        });
        btnBuscarVehiculo.setBounds(248, 95, 89, 23);
        panelEliminarVehiculo.add(btnBuscarVehiculo);

    

        

        // Botón de eliminar
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(estilos.obtenerFuenteBoton());
        btnEliminar.setBackground(estilos.COLOR_BOTON_NORMAL);
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEliminar.setBounds(284, 322, 120, 30);
        panelEliminarVehiculo.add(btnEliminar);

        // Acción del botón Eliminar
        btnEliminar.addActionListener(e -> {
            String matricula = txtMatricula.getText();

            if (matricula.isEmpty()) {
                System.out.println("Por favor, introduce la matrícula.");
            } else {
                ConexionMySql conexion = new ConexionMySql();
                if (conexion.eliminarVehiculo(matricula)) { // Elimina el vehículo usando la matrícula
                	javax.swing.JOptionPane.showMessageDialog(this, "Vehículo eliminado exitosamente.");
                    cargarTablaVehiculos(); // Actualiza la tabla
                    // Limpiar los campos
                    txtMatricula.setText("");
                    DefaultTableModel model = (DefaultTableModel) tableVehiculos2.getModel();
                    model.setRowCount(0); // Restablecer la tabla
                } else {
                    System.out.println("Error al eliminar el vehículo.");
                }
            }
        });

        // Botón Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setForeground(new Color(51, 51, 255)); // Azul
        btnVolver.setContentAreaFilled(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolver.setBounds(414, 322, 120, 30);
        panelEliminarVehiculo.add(btnVolver);

        // Acción del botón Volver
        // Acción del botón Volver
        btnVolver.addActionListener(e -> {
            // Limpiar los campos del formulario
        	txtMatricula.setText("");


            // Cambiar al panel "VEHÍCULOS"
            cardLayout.show(cardPanel, "VEHÍCULOS");
        });

        return panelEliminarVehiculo;
    }



}
