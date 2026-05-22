package principal;

import controlador.Controlador;
import vista.VentanaPrincipal;
import javax.swing.SwingUtilities;

/**
 * Clase principal que actúa como punto de entrada de la aplicación.
 * Se encarga de instanciar el Controlador y lanzar la interfaz gráfica de forma segura.
 */
public class Main {

    /**
     * Metodo principal que arranca la ejecución del programa.
     */
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