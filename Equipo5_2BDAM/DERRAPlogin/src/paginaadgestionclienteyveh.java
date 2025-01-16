import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.util.ArrayList;

public class paginaadgestionclienteyveh extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JTable tableClientes, tableMecanicos, tableVehiculos;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                paginaadgestionclienteyveh frame = new paginaadgestionclienteyveh();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public paginaadgestionclienteyveh() {
        setTitle("Derrap Administrador | Gestión clientes y vehículos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        //
        // PANEL LATERAL CON SUS OPCIONES CORRESPONDIENTES
        //
        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 200, 461);
        sidebar.setLayout(null);
        contentPane.add(sidebar);

        JLabel lblDerrap = new JLabel("DERRAP");
        lblDerrap.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblDerrap.setBounds(10, 20, 112, 27);
        sidebar.add(lblDerrap);

        JLabel lbldashboard = new JLabel("MENU");
        lbldashboard.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbldashboard.setBounds(10, 60, 112, 23);
        sidebar.add(lbldashboard);

        JButton btnClientes = new JButton("CLIENTES");
        btnClientes.setBounds(10, 100, 180, 30);
        sidebar.add(btnClientes);

        JButton btnMecanicos = new JButton("MECÁNICOS");
        btnMecanicos.setBounds(10, 150, 180, 30);
        sidebar.add(btnMecanicos);

        JButton btnVehiculos = new JButton("VEHÍCULOS");
        btnVehiculos.setBounds(10, 200, 180, 30);
        sidebar.add(btnVehiculos);

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
	        panelClientes.setLayout(null);
	        JLabel lblClientes = new JLabel("GESTIÓN DE CLIENTES");
	        lblClientes.setFont(new Font("Tahoma", Font.BOLD, 18));
	        lblClientes.setBounds(10, 10, 300, 30);
	        panelClientes.add(lblClientes);
	
	        JButton btnCrearCliente = new JButton("Crear");
	        btnCrearCliente.setBounds(10, 50, 80, 30);
	        panelClientes.add(btnCrearCliente);
	
	        JButton btnActualizarCliente = new JButton("Actualizar");
	        btnActualizarCliente.setBounds(100, 50, 100, 30);
	        panelClientes.add(btnActualizarCliente);
	
	        JButton btnEliminarCliente = new JButton("Eliminar");
	        btnEliminarCliente.setBounds(210, 50, 100, 30);
	        panelClientes.add(btnEliminarCliente);
	
	        JScrollPane scrollClientes = new JScrollPane();
	        scrollClientes.setBounds(10, 90, 500, 300);
	        panelClientes.add(scrollClientes);
	
	        tableClientes = new JTable();
	        scrollClientes.setViewportView(tableClientes);
	        cardPanel.add(panelClientes, "CLIENTES");
     
		        //
		        // Subsecciones de CLIENTES
		        //
        
			        JPanel panelCrearCliente = crearPanelCrearCliente();
			        cardPanel.add(panelCrearCliente, "CREAR_CLIENTE");
			
			
			        JPanel panelActualizarCliente = crearActualizarClientePanel();
			        cardPanel.add(panelActualizarCliente, "ACTUALIZAR_CLIENTE");
			
			        JPanel panelEliminarCliente = crearSubPanel("ELIMINAR CLIENTE");
			        cardPanel.add(panelEliminarCliente, "ELIMINAR_CLIENTE");
			
			        btnCrearCliente.addActionListener(e -> cardLayout.show(cardPanel, "CREAR_CLIENTE"));
			        btnActualizarCliente.addActionListener(e -> cardLayout.show(cardPanel, "ACTUALIZAR_CLIENTE"));
			        btnEliminarCliente.addActionListener(e -> cardLayout.show(cardPanel, "ELIMINAR_CLIENTE"));
			        
        //
        // Sección de MECÁNICOS
	    //
			        
	        JPanel panelMecanicos = new JPanel();
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
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("CREAR CLIENTE");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(10, 10, 300, 30);
        panel.add(lblTitulo);

        JLabel lblDni = new JLabel("DNI:");
        lblDni.setBounds(10, 60, 80, 25);
        panel.add(lblDni);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 100, 80, 25);
        panel.add(lblNombre);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(10, 140, 80, 25);
        panel.add(lblApellidos);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(10, 180, 80, 25);
        panel.add(lblDireccion);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(10, 220, 80, 25);
        panel.add(lblTelefono);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, 260, 80, 25);
        panel.add(lblEmail);

        // Campos de texto
        JTextField txtDni = new JTextField();
        txtDni.setBounds(100, 60, 200, 25);
        panel.add(txtDni);

        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(100, 100, 200, 25);
        panel.add(txtNombre);

        JTextField txtApellidos = new JTextField();
        txtApellidos.setBounds(100, 140, 200, 25);
        panel.add(txtApellidos);

        JTextField txtDireccion = new JTextField();
        txtDireccion.setBounds(100, 180, 200, 25);
        panel.add(txtDireccion);

        JTextField txtTelefono = new JTextField();
        txtTelefono.setBounds(100, 220, 200, 25);
        panel.add(txtTelefono);

        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(100, 260, 200, 25);
        panel.add(txtEmail);

        // Botón Crear
        JButton btnCrear = new JButton("Crear");
        btnCrear.setBounds(10, 310, 100, 30);
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
        btnVolver.setBounds(120, 310, 100, 30);
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
        
     // Etiqueta y campo de DNI
        JLabel lblDni = new JLabel("DNI:");
        lblDni.setBounds(10, 60, 100, 25);
        panelActualizarCliente.add(lblDni);

        JTextField txtDni = new JTextField("");
        txtDni.setBounds(120, 60, 200, 25);
        panelActualizarCliente.add(txtDni);

        // Etiqueta y campo de Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 100, 100, 25);
        panelActualizarCliente.add(lblNombre);

        JTextField txtNombre = new JTextField("");
        txtNombre.setBounds(120, 100, 200, 25);
        panelActualizarCliente.add(txtNombre);

        // Etiqueta y campo de Apellidos
        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(10, 140, 100, 25);
        panelActualizarCliente.add(lblApellidos);

        JTextField txtApellidos = new JTextField("");
        txtApellidos.setBounds(120, 140, 200, 25);
        panelActualizarCliente.add(txtApellidos);

        // Etiqueta y campo de Dirección
        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(10, 180, 100, 25);
        panelActualizarCliente.add(lblDireccion);

        JTextField txtDireccion = new JTextField("");
        txtDireccion.setBounds(120, 180, 200, 25);
        panelActualizarCliente.add(txtDireccion);

        // Etiqueta y campo de Teléfono
        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(10, 220, 100, 25);
        panelActualizarCliente.add(lblTelefono);

        JTextField txtTelefono = new JTextField("");
        txtTelefono.setBounds(120, 220, 200, 25);
        panelActualizarCliente.add(txtTelefono);

        // Etiqueta y campo de Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, 260, 100, 25);
        panelActualizarCliente.add(lblEmail);

        JTextField txtEmail = new JTextField("");
        txtEmail.setBounds(120, 260, 200, 25);
        panelActualizarCliente.add(txtEmail);

        // Botón de Actualizar
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(10, 310, 120, 30);
        panelActualizarCliente.add(btnActualizar);

        // Botón de Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(150, 310, 120, 30);
        panelActualizarCliente.add(btnVolver);

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

        // Acción del botón Actualizar
        btnActualizar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();
            String email = txtEmail.getText();
            String dni = txtDni.getText();

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
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+METODOS PARA CARGAR LOS DATOS DE CADA TABLAS++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void cargarTablaClientes() {
        ConexionMySql conexion = new ConexionMySql();
        ArrayList<String[]> clientes = conexion.obtenerClientes();
        String[] columnas = { "DNI", "Nombre", "Apellidos", "Dirección", "Teléfono", "Email" };
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        for (String[] cliente : clientes) {
            modelo.addRow(cliente);
        }
        tableClientes.setModel(modelo);
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
