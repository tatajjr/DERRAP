import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Estilos.estilos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField pwClave;

    ConexionMySql conexion = new ConexionMySql();
    Connection cn = null;
    Statement stm = null;

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

    public login() {
        setTitle("Inicio Derrap");
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

        // Add the image to the left side
        Image img = new ImageIcon(getClass().getResource("/Imagenes/imagenlogin.jpg")).getImage(); // Resize the image
        JLabel lblImage = new JLabel(new ImageIcon(img));
        lblImage.setBounds(0, 0, 351, 511); // Position and size of the image
        contentPane.add(lblImage);

        // Title Section
        JLabel lblLogo1 = new JLabel("Talleres");
        lblLogo1.setFont(estilos.obtenerFuenteTitulo());
        lblLogo1.setForeground(estilos.COLOR_TEXTO_TITULO);
        lblLogo1.setBounds(465, 41, 150, 40);
        contentPane.add(lblLogo1);

        JLabel lblLogo2 = new JLabel("Derrap");
        lblLogo2.setFont(estilos.obtenerFuenteTitulo());
        lblLogo2.setForeground(estilos.COLOR_TEXTO_SECUNDARIO);
        lblLogo2.setBounds(565, 41, 150, 40);
        contentPane.add(lblLogo2);

        // Welcome message
        JLabel lblBienvenido = new JLabel("Bienvenido de vuelta");
        lblBienvenido.setFont(estilos.obtenerFuenteSubtitulo());
        lblBienvenido.setForeground(estilos.COLOR_TEXTO_ERROR);
        lblBienvenido.setBounds(465, 117, 300, 30);
        contentPane.add(lblBienvenido);

        // User Input Section
        JLabel lblUsuario = new JLabel("Usuario (DNI)");
        lblUsuario.setFont(estilos.obtenerFuenteCampos());
        lblUsuario.setBounds(465, 182, 189, 20);
        contentPane.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setFont(estilos.obtenerFuenteCampos());
        txtUsuario.setBounds(465, 207, 250, 30);
        txtUsuario.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        contentPane.add(txtUsuario);
        txtUsuario.setColumns(10);

        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setFont(estilos.obtenerFuenteCampos());
        lblContrasena.setBounds(465, 241, 119, 20);
        contentPane.add(lblContrasena);

        pwClave = new JPasswordField();
        pwClave.setFont(estilos.obtenerFuenteCampos());
        pwClave.setBounds(465, 266, 250, 30);
        pwClave.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        contentPane.add(pwClave);
        pwClave.setColumns(10);

        // Login Button
        JButton btnInicioSesion = new JButton("Iniciar sesión");
        btnInicioSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id_usuario = txtUsuario.getText();
                String clave_usuario = pwClave.getText();
                if (conexion.buscarusuario(id_usuario, clave_usuario)) {
                    String tipoUsuario = conexion.obtenerTipoUsuario(id_usuario);
                    if ("Administrador".equalsIgnoreCase(tipoUsuario)) {
                        paginaadgestionclienteyveh paginaadgestionclienteyveh = new paginaadgestionclienteyveh();
                        paginaadgestionclienteyveh.setVisible(true);
                        paginaadgestionclienteyveh.cargarTablaClientes();
                        dispose();
                    } else if ("Mecanico".equalsIgnoreCase(tipoUsuario)) {
                        paginaprincipalmec paginaprincipalmec = new paginaprincipalmec();
                        paginaprincipalmec.setVisible(true);
                        paginaprincipalmec.mostrarOrdenes();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(btnInicioSesion, "Tipo de usuario desconocido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(btnInicioSesion, "Usuario o contraseña incorrectos", "error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnInicioSesion.setFont(estilos.obtenerFuenteBoton());
        btnInicioSesion.setBackground(estilos.COLOR_BOTON_NORMAL);
        btnInicioSesion.setForeground(Color.WHITE);
        btnInicioSesion.setFocusPainted(false);
        btnInicioSesion.setBounds(465, 312, estilos.obtenerAnchoBoton(), estilos.obtenerAltoBoton());
        btnInicioSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        contentPane.add(btnInicioSesion);

        // Hover effect for button
        btnInicioSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInicioSesion.setBackground(estilos.COLOR_BOTON_HOVER);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInicioSesion.setBackground(estilos.COLOR_BOTON_NORMAL);
            }
        });

        // Sign-up section
        JLabel lblCrearCuenta = new JLabel("¿Todavía no tienes una cuenta?");
        lblCrearCuenta.setFont(estilos.obtenerFuenteCampos());
        lblCrearCuenta.setBounds(465, 366, 250, 25);
        contentPane.add(lblCrearCuenta);

        JButton btnNewButton = new JButton("Crear una");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearcuenta crearcuenta = new crearcuenta();
                crearcuenta.setVisible(true);
                dispose();
            }
        });
        btnNewButton.setFont(estilos.obtenerFuenteBoton());
        btnNewButton.setForeground(new Color(51, 51, 255)); // Blue text
        btnNewButton.setContentAreaFilled(false);
        btnNewButton.setBorderPainted(false);
        btnNewButton.setFocusPainted(false);
        btnNewButton.setBounds(665, 366, 100, 25);
        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        contentPane.add(btnNewButton);
        
        
        //Eventos de teclado para el login 
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnInicioSesion.doClick(); 
                }
            }
        });

        pwClave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnInicioSesion.doClick(); 
                }
            }
        });
    }
}
