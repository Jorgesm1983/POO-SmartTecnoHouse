package principal;

import controlador.Controlador;

/**
 * Clase principal que arranca la aplicación.
 */
public class Main {
    public static void main(String[] args) {

        // Instanciamos el controlador
        // Al crearlo, se inicializan automáticamente todos los sensores, actuadores y reglas.
        Controlador controlador = new Controlador();

        // Ejecutamos un primer ciclo de prueba para probar el funcionamiento del modelo antes de avanzar con la interfáz gráfica
        // Los sensores generarán valores aleatorios y las reglas actuarán según los parametros definidos.
        controlador.ejecutarCicloSimulacion();

        // Esperamos un momento y inicamos otra prueba
        // para comprobar cómo cambian los valores y los estados.
        try {
            Thread.sleep(1500); // Pausa de 1.5 segundos
        } catch (InterruptedException e) {
            System.out.println("Error en la pausa de simulación.");
        }

        System.out.println("=== ACTUALIZANDO SISTEMA ===");
        controlador.ejecutarCicloSimulacion();
    }
}