import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Estilos.estilos;

import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.sql.*;
import java.util.ArrayList;
import java.awt.CardLayout;

public class paginaprincipalmec extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static JTable tablaOrdenes; // Tabla para mostrar las órdenes

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    paginaprincipalmec frame = new paginaprincipalmec();
                    mostrarOrdenes();
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
    public paginaprincipalmec() {
    	setTitle("Derrap Mecanico | Pagina principal");
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
        


        // Tabla para mostrar las órdenes
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(224, 122, 560, 70);
        contentPane.add(scrollPane);

        tablaOrdenes = new JTable();
        tablaOrdenes.setBackground(new Color(240, 240, 250));
        scrollPane.setViewportView(tablaOrdenes);

        // Etiqueta "MECANICO"
        JLabel lblMecanico = new JLabel("Mecánico");
        lblMecanico.setBackground(new Color(240, 240, 250));
        lblMecanico.setFont(estilos.obtenerFuenteTitulo());
        lblMecanico.setForeground(estilos.COLOR_TEXTO_TITULO);
        lblMecanico.setBounds(224, 11, 167, 47);
        contentPane.add(lblMecanico);
        
        JPanel sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBackground(new Color(240, 240, 250));
        sidebar.setBounds(0, 0, 200, 461);
        contentPane.add(sidebar);
        
        JLabel lblLogo1 = new JLabel("Talleres");
        lblLogo1.setForeground(new Color(51, 51, 153));
        lblLogo1.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblLogo1.setBounds(10, 10, 112, 47);
        sidebar.add(lblLogo1);
        
        JLabel lblLogo2 = new JLabel("Derrap");
        lblLogo2.setForeground(new Color(255, 102, 102));
        lblLogo2.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblLogo2.setBounds(110, 10, 112, 47);
        sidebar.add(lblLogo2);
        
        JLabel lbldashboard = new JLabel("MENU");
        lbldashboard.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbldashboard.setBounds(10, 68, 112, 23);
        sidebar.add(lbldashboard);
        
        JButton btnMisOrdenes = new JButton("MIS ORDENES");
        btnMisOrdenes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarOrdenes();
            }
        });
        btnMisOrdenes.setBounds(10, 123, 180, 30);
        btnMisOrdenes.setFont(estilos.obtenerFuenteBoton());
        btnMisOrdenes.setBackground(estilos.COLOR_BOTON_NORMAL);
        btnMisOrdenes.setForeground(Color.WHITE);
        btnMisOrdenes.setFocusPainted(false);
        //btnVehiculos.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
        btnMisOrdenes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sidebar.add(btnMisOrdenes);
        
        JButton btnOrdenesDispo = new JButton("ORDENES LIBRES");
        btnOrdenesDispo.setFont(estilos.obtenerFuenteBoton());
        btnOrdenesDispo.setBackground(estilos.COLOR_BOTON_NORMAL);
        btnOrdenesDispo.setForeground(Color.WHITE);
        btnOrdenesDispo.setFocusPainted(false);
        //btnVehiculos.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
        btnOrdenesDispo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnOrdenesDispo.setBounds(10, 173, 180, 30);
        sidebar.add(btnOrdenesDispo);
        
        JButton btnStock = new JButton("STOCK");
        btnStock.setFont(estilos.obtenerFuenteBoton());
        btnStock.setBackground(estilos.COLOR_BOTON_NORMAL);
        btnStock.setForeground(Color.WHITE);
        btnStock.setFocusPainted(false);
        //btnVehiculos.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
        btnStock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnStock.setBounds(10, 223, 180, 30);
        sidebar.add(btnStock);
        
        // Crear el botón Cerrar Sesión
        JButton btnprovisional = new JButton("CERRAR SESION");
        btnprovisional.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirmacion = JOptionPane.showConfirmDialog(
                    null, 
                    "¿Estás seguro de que quieres cerrar sesión?", 
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

        // Añadir el evento de teclado para Ctrl + Enter
        InputMap inputMap = contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = contentPane.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK), "closeSession");
        actionMap.put("closeSession", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	btnprovisional.doClick(); // Simula el clic en el botón "CERRAR SESION"
            }
        });

        btnprovisional.setForeground(Color.WHITE);
        btnprovisional.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnprovisional.setBackground(Color.RED);
        btnprovisional.setBounds(10, 372, 180, 30);
        sidebar.add(btnprovisional);
        
        JButton btnAnadirorden = new JButton("Añadir");
        btnAnadirorden.setBounds(40, 81, 89, 23);
        btnAnadirorden.setBounds(224, 81, 100, 30);
        btnAnadirorden.setFont(estilos.obtenerFuenteBoton());
        btnAnadirorden.setBackground(estilos.COLOR_BOTON_NORMAL);
        btnAnadirorden.setForeground(Color.WHITE);
        btnAnadirorden.setFocusPainted(false);
        //btnVehiculos.setBounds(10, 100, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
        btnAnadirorden.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        contentPane.add(btnAnadirorden);

    }
 // Método para mostrar las órdenes en la tabla
    static void mostrarOrdenes() {
        // Crear una instancia de la conexión personalizada
        ConexionMySql conexion = new ConexionMySql();

        // Llamar al método que devuelve las órdenes en un ArrayList
        ArrayList<String[]> ordenes = conexion.obtenerOrdenes();

        // Definir las columnas que tendrá la tabla
        String[] columnas = { "ID_Orden", "Fecha Entrada", "Fecha Salida", "Estado", "Situacion", "Matricula" }; // Reemplaza con los nombres reales de tus columnas

        // Crear el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        // Agregar las filas obtenidas al modelo
        for (String[] orden : ordenes) {
            modelo.addRow(orden);
        }

        // Establecer el modelo en la tabla
        tablaOrdenes.setModel(modelo);

        // Calcular el tamaño dinámico del JTable
        int rowCount = modelo.getRowCount();
        int rowHeight = tablaOrdenes.getRowHeight();
        int headerHeight = tablaOrdenes.getTableHeader().getHeight();
        int totalHeight = rowCount * rowHeight + headerHeight;

        // Ajustar el tamaño del JScrollPane
        JScrollPane scrollPane = (JScrollPane) tablaOrdenes.getParent().getParent();
        scrollPane.setPreferredSize(new java.awt.Dimension(scrollPane.getWidth(), Math.min(totalHeight, 400))); // Máximo de 400 píxeles

        // Actualizar el layout para reflejar el cambio
        scrollPane.revalidate();
        scrollPane.repaint();
    }
}
