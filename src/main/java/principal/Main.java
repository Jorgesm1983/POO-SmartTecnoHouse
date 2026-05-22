package principal;

import controlador.Controlador;
import vista.VentanaPrincipal;

import javax.swing.SwingUtilities;

/**
 * Clase principal que arranca la aplicación.
 */
public class Main {
    public static void main(String[] args) {

        // SwingUtilities asegura que la interfaz gráfica arranque de forma segura en su propio hilo
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 1. Creamos el director de orquesta (Controlador)
                Controlador controlador = new Controlador();

                // 2. Creamos la ventana y le pasamos el controlador
                VentanaPrincipal ventana = new VentanaPrincipal(controlador);

                // 3. Mostramos la ventana en pantalla
                ventana.mostrar();
            }
        });
    }
}