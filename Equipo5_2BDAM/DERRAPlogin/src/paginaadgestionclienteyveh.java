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
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class paginaadgestionclienteyveh extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private static JTable tableClientes;
	private JTable tableMecanicos;
	private JTable tableVehiculos;

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
        lbldashboard.setFont(new Font("Tahoma", Font.BOLD, 14));
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
        
        JButton btnVolver = new JButton("VOLVER");
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
	        JLabel lblMecanicos = new JLabel("GESTIÓN DE MECÁNICOS");
	        lblMecanicos.setFont(new Font("Tahoma", Font.BOLD, 18));
	        lblMecanicos.setBounds(10, 10, 300, 30);
	        panelMecanicos.add(lblMecanicos);
	
	        JButton btnCrearMecanico = new JButton("Crear");
	        btnCrearMecanico.setBounds(10, 50, 80, 30);
	        panelMecanicos.add(btnCrearMecanico);
	
	        JButton btnActualizarMecanico = new JButton("Actualizar");
	        btnActualizarMecanico.setBounds(100, 50, 100, 30);
	        panelMecanicos.add(btnActualizarMecanico);
	
	        JButton btnEliminarMecanico = new JButton("Eliminar");
	        btnEliminarMecanico.setBounds(210, 50, 100, 30);
	        panelMecanicos.add(btnEliminarMecanico);
	
	        JScrollPane scrollMecanicos = new JScrollPane();
	        scrollMecanicos.setBounds(10, 90, 500, 300);
	        panelMecanicos.add(scrollMecanicos);
	
	        tableMecanicos = new JTable();
	        tableMecanicos.setBackground(new Color(240, 240, 250));
	        scrollMecanicos.setViewportView(tableMecanicos);
	        cardPanel.add(panelMecanicos, "MECÁNICOS");
	        
			    //
		        // Subsecciones de MECÁNICOS
			    //    
	        
			        JPanel panelCrearMecanico = crearSubPanel("CREAR MECÁNICO");
			        cardPanel.add(panelCrearMecanico, "CREAR_MECÁNICO");
			
			        JPanel panelActualizarMecanico = crearSubPanel("ACTUALIZAR MECÁNICO");
			        cardPanel.add(panelActualizarMecanico, "ACTUALIZAR_MECÁNICO");
			
			        JPanel panelEliminarMecanico = crearSubPanel("ELIMINAR MECÁNICO");
			        cardPanel.add(panelEliminarMecanico, "ELIMINAR_MECÁNICO");
			
			        btnCrearMecanico.addActionListener(e -> cardLayout.show(cardPanel, "CREAR_MECÁNICO"));
			        btnActualizarMecanico.addActionListener(e -> cardLayout.show(cardPanel, "ACTUALIZAR_MECÁNICO"));
			        btnEliminarMecanico.addActionListener(e -> cardLayout.show(cardPanel, "ELIMINAR_MECÁNICO"));
			        
		//
        // Sección de VEHÍCULOS
		//
			        
	        JPanel panelVehiculos = new JPanel();
	        panelVehiculos.setBackground(new Color(240, 240, 250));
	        panelVehiculos.setLayout(null);
	        JLabel lblVehiculos = new JLabel("GESTIÓN DE VEHÍCULOS");
	        lblVehiculos.setFont(new Font("Tahoma", Font.BOLD, 18));
	        lblVehiculos.setBounds(10, 10, 300, 30);
	        panelVehiculos.add(lblVehiculos);
	
	        JButton btnCrearVehiculo = new JButton("Crear");
	        btnCrearVehiculo.setBounds(10, 50, 80, 30);
	        panelVehiculos.add(btnCrearVehiculo);
	
	        JButton btnActualizarVehiculo = new JButton("Actualizar");
	        btnActualizarVehiculo.setBounds(100, 50, 100, 30);
	        panelVehiculos.add(btnActualizarVehiculo);
	
	        JButton btnEliminarVehiculo = new JButton("Eliminar");
	        btnEliminarVehiculo.setBounds(210, 50, 100, 30);
	        panelVehiculos.add(btnEliminarVehiculo);
	
	        JScrollPane scrollVehiculos = new JScrollPane();
	        scrollVehiculos.setBounds(10, 90, 500, 300);
	        panelVehiculos.add(scrollVehiculos);
	
	        tableVehiculos = new JTable();
	        tableVehiculos.setBackground(new Color(240, 240, 250));
	        scrollVehiculos.setViewportView(tableVehiculos);
	        cardPanel.add(panelVehiculos, "VEHÍCULOS");
	        
	        	//
	        	// Subsecciones de VEHÍCULOS
	        	//
	        
			        JPanel panelCrearVehiculo = crearSubPanel("CREAR VEHÍCULO");
			        cardPanel.add(panelCrearVehiculo, "CREAR_VEHÍCULO");
			
			        JPanel panelActualizarVehiculo = crearSubPanel("ACTUALIZAR VEHÍCULO");
			        cardPanel.add(panelActualizarVehiculo, "ACTUALIZAR_VEHÍCULO");
			
			        JPanel panelEliminarVehiculo = crearSubPanel("ELIMINAR VEHÍCULO");
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
           btnMecanicos.addActionListener(e -> {
               cardLayout.show(cardPanel, "MECÁNICOS");
               cargarTablaMecanicos();
           });

           btnClientes.addActionListener(e -> {
               cardLayout.show(cardPanel, "CLIENTES");
               cargarTablaClientes();
           });

           btnVehiculos.addActionListener(e -> {
               cardLayout.show(cardPanel, "VEHÍCULOS");
               cargarTablaVehiculos();
           });
        
        
        
    }
   // Helper method to create sub-panels
    private JPanel crearSubPanel(String titulo) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 250));
        panel.setLayout(null);
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(10, 10, 300, 30);
        panel.add(lblTitulo);
        return panel;
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+METODOS PARA CARD LAYOUT CREARCLIENTES++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private JPanel crearPanelCrearCliente() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 250));
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Crear Cliente");
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
        panelActualizarCliente.setLayout(null);

        JLabel lblActualizarCliente = new JLabel("ACTUALIZAR CLIENTE");
        lblActualizarCliente.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblActualizarCliente.setBounds(10, 10, 300, 30);
        panelActualizarCliente.add(lblActualizarCliente);
        
        JLabel lblDNI = new JLabel("DNI:");
        lblDNI.setBounds(10, 60, 100, 25);  // Mantén la posición a la izquierda
        panelActualizarCliente.add(lblDNI);

        JTextField txtDNI = new JTextField("Escribe el DNI");
        txtDNI.setBounds(120, 60, 200, 25);  // Mantén la posición alineada
        panelActualizarCliente.add(txtDNI);

        // Ajusta la posición de los elementos de "Nombre" para que no se solapen
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 100, 100, 25);  // Cambié la posición en el eje Y para que no se solapen
        panelActualizarCliente.add(lblNombre);

        JTextField txtNombre = new JTextField("Escribe el nombre");
        txtNombre.setBounds(120, 100, 200, 25);  // Cambié la posición en el eje Y para que no se solapen
        panelActualizarCliente.add(txtNombre);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(10, 140, 100, 25);
        panelActualizarCliente.add(lblApellidos);

        JTextField txtApellidos = new JTextField("Escribe los apellidos");
        txtApellidos.setBounds(120, 140, 200, 25);
        panelActualizarCliente.add(txtApellidos);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(10, 180, 100, 25);
        panelActualizarCliente.add(lblDireccion);

        JTextField txtDireccion = new JTextField("Escribe la dirección");
        txtDireccion.setBounds(120, 180, 200, 25);
        panelActualizarCliente.add(txtDireccion);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(10, 220, 100, 25);
        panelActualizarCliente.add(lblTelefono);

        JTextField txtTelefono = new JTextField("Escribe el teléfono");
        txtTelefono.setBounds(120, 220, 200, 25);
        panelActualizarCliente.add(txtTelefono);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, 260, 100, 25);
        panelActualizarCliente.add(lblEmail);

        JTextField txtEmail = new JTextField("Escribe el email");
        txtEmail.setBounds(120, 260, 200, 25);
        panelActualizarCliente.add(txtEmail);

        // Botón de actualizar
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(10, 310, 120, 30);
        panelActualizarCliente.add(btnActualizar);

        // Botón de volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(150, 310, 120, 30);
        panelActualizarCliente.add(btnVolver);

        // Listeners para los campos de texto
        agregarPlaceholder(txtNombre, "Escribe el nombre");
        agregarPlaceholder(txtApellidos, "Escribe los apellidos");
        agregarPlaceholder(txtDireccion, "Escribe la dirección");
        agregarPlaceholder(txtTelefono, "Escribe el teléfono");
        agregarPlaceholder(txtEmail, "Escribe el email");
        agregarPlaceholder(txtDNI, "Escribe el DNI");

        // Acción del botón Volver
        btnVolver.addActionListener(e -> cardLayout.show(cardPanel, "CLIENTES"));

        // Acción del botón Actualizar
        btnActualizar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();
            String email = txtEmail.getText();
            String dni = txtDNI.getText();

            if (nombre.isEmpty() || apellidos.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || email.isEmpty()|| dni.isEmpty()) {
                System.out.println("Por favor, rellena todos los campos.");
            } else {
                ConexionMySql conexion = new ConexionMySql();
                if (conexion.actualizarCliente(nombre, apellidos, direccion, telefono, email, dni)) {
                    System.out.println("Cliente actualizado con éxito.");
                    cardLayout.show(cardPanel, "CLIENTES");
                    cargarTablaClientes();
                } else {
                    System.out.println("Error al actualizar el cliente.");
                }
            }
        });

        return panelActualizarCliente;
    }

    // Método para agregar placeholders a los campos de texto
    private void agregarPlaceholder(JTextField textField, String placeholder) {
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(java.awt.Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(java.awt.Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
        textField.setForeground(java.awt.Color.GRAY);
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
        String[] columnas = { "Matrícula", "Modelo", "Marca", "Año", "Km Totales", "DNI Cliente" };
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        for (String[] vehiculo : vehiculos) {
            modelo.addRow(vehiculo);
        }
        tableVehiculos.setModel(modelo);
    }
}
