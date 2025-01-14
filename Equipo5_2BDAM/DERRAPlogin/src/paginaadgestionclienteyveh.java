import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class paginaadgestionclienteyveh extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JTable tableClientes, tableMecanicos, tableVehiculos;

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

    public paginaadgestionclienteyveh() {
        setTitle("Derrap Administrador | Gestion clientes y vehículos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Sidebar panel
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

        // Panels for each section
        JPanel panelClientes = new JPanel();
        panelClientes.setLayout(null);
        JLabel lblClientes = new JLabel("GESTIÓN DE CLIENTES");
        lblClientes.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblClientes.setBounds(10, 10, 300, 30);
        panelClientes.add(lblClientes);

        // Buttons for CRUD actions
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
        scrollClientes.setBounds(10, 90, 500, 400);
        panelClientes.add(scrollClientes);

        tableClientes = new JTable();
        scrollClientes.setViewportView(tableClientes);
        cardPanel.add(panelClientes, "CLIENTES");

        JPanel panelMecanicos = new JPanel();
        panelMecanicos.setLayout(null);
        JLabel lblMecanicos = new JLabel("GESTIÓN DE MECÁNICOS");
        lblMecanicos.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblMecanicos.setBounds(10, 10, 300, 30);
        panelMecanicos.add(lblMecanicos);

        // Buttons for CRUD actions
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
        scrollMecanicos.setBounds(10, 90, 500, 400);
        panelMecanicos.add(scrollMecanicos);

        tableMecanicos = new JTable();
        scrollMecanicos.setViewportView(tableMecanicos);
        cardPanel.add(panelMecanicos, "MECÁNICOS");

        JPanel panelVehiculos = new JPanel();
        panelVehiculos.setLayout(null);
        JLabel lblVehiculos = new JLabel("GESTIÓN DE VEHÍCULOS");
        lblVehiculos.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblVehiculos.setBounds(10, 10, 300, 30);
        panelVehiculos.add(lblVehiculos);

        // Buttons for CRUD actions
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
        scrollVehiculos.setBounds(10, 90, 500, 400);
        panelVehiculos.add(scrollVehiculos);

        tableVehiculos = new JTable();
        scrollVehiculos.setViewportView(tableVehiculos);
        cardPanel.add(panelVehiculos, "VEHÍCULOS");

        // Add action listeners for buttons
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

    // Methods to load data for each table
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