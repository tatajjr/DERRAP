package Estilos;

import java.awt.Color;
import java.awt.Font;

public class estilos {

    // Colores
    public static final Color COLOR_FONDO = new Color(240, 240, 250); // Light lavender background
    public static final Color COLOR_BOTON_NORMAL = new Color(51, 102, 255); // Blue
    public static final Color COLOR_BOTON_HOVER = new Color(31, 82, 220); // Darker blue
    public static final Color COLOR_TEXTO_TITULO = new Color(51, 51, 153); // Navy blue
    public static final Color COLOR_TEXTO_SECUNDARIO = new Color(255, 102, 102); // Coral red
    public static final Color COLOR_TEXTO_ERROR = new Color(51, 51, 51); // Dark gray

    // Fuentes
    public static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 28);
    public static final Font FUENTE_SUBTITULO = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font FUENTE_CAMPOS = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FUENTE_BOTON = new Font("Segoe UI", Font.BOLD, 14);

    // Botón
    public static final int ANCHO_BOTON = 250;
    public static final int ALTO_BOTON = 35;

    // Métodos para obtener los estilos, por si necesitas personalizar más en otras clases
    public static Color obtenerColorFondo() {
        return COLOR_FONDO;
    }

    public static Font obtenerFuenteTitulo() {
        return FUENTE_TITULO;
    }

    public static Font obtenerFuenteSubtitulo() {
        return FUENTE_SUBTITULO;
    }

    public static Font obtenerFuenteCampos() {
        return FUENTE_CAMPOS;
    }

    public static Font obtenerFuenteBoton() {
        return FUENTE_BOTON;
    }

    public static Color obtenerColorBotonNormal() {
        return COLOR_BOTON_NORMAL;
    }

    public static Color obtenerColorBotonHover() {
        return COLOR_BOTON_HOVER;
    }

    public static int obtenerAnchoBoton() {
        return ANCHO_BOTON;
    }

    public static int obtenerAltoBoton() {
        return ALTO_BOTON;
    }
}
