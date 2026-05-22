package principal;

import controlador.Controlador;
import vista.VentanaPrincipal;
import javax.swing.SwingUtilities;

/**
 * Clase principal que arranca la aplicación.
 */
public class Main {
    public static void main(String[] args) {

        // SwingUtilities asegura que la interfaz gráfica arranque de forma segura
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Creamos el controlador
                Controlador controlador = new Controlador();

                // Creamos la ventana y le pasamos el controlador
                VentanaPrincipal ventana = new VentanaPrincipal(controlador);

                // Mostramos la ventana en pantalla
                ventana.mostrar();
            }
        });
    }
}