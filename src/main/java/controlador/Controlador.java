package controlador;

import modelo.*; // Importamos todas las clases de nuestro modelo
import java.util.ArrayList;
import java.util.List;

// Importaciones nuevas para manejar la escritura de archivos y la hora actual
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase principal de la lógica de control del modelo MVC.
 * Gestiona la interacción entre los sensores, reglas y actuadores.
 */
public class Controlador {

    private List<Sensor> sensores;
    private List<Actuador> actuadores;
    private List<Regla> reglas;

    /**
     * Constructor del Controlador. Inicializa las listas y carga los dispositivos.
     */
    public Controlador() {
        this.sensores = new ArrayList<>();
        this.actuadores = new ArrayList<>();
        this.reglas = new ArrayList<>();
        inicializarSistema();
    }

    /**
     * Instancia todos los dispositivos y reglas del sistema.
     */
    private void inicializarSistema() {
        // Instanciar Sensores
        this.sensores.add(new SensorTemperatura("temp"));
        this.sensores.add(new SensorLuz("light"));
        this.sensores.add(new SensorPresencia("pir"));
        this.sensores.add(new SensorHumedad("hum")); // Ampliación

        // Instanciar Actuadores
        this.actuadores.add(new ActuadorVentilador("fan"));
        this.actuadores.add(new ActuadorBombilla("bulb"));
        this.actuadores.add(new ActuadorPersiana("blind")); // Ampliación

        // Instanciar las reglas
        this.reglas.add(new ReglaVentilacion());
        this.reglas.add(new ReglaIluminacion());
        this.reglas.add(new ReglaPersiana());
    }

    /**
     * Ejecuta un ciclo completo: actualiza sensores, evalúa reglas y muestra resultados.
     */
    public void ejecutarCicloSimulacion() {
        System.out.println("--- INICIANDO SISTEMA ---");

        // Los sensores se inician y cargan los valores.
        for (Sensor sensor : sensores) {
            sensor.actualizarValor();
            System.out.println(sensor.getNombre() + ", " + sensor.getId() + ", ha medido: " + sensor.getEstadoActual());
        }

        // Las reglas piensan y actúan
        for (Regla regla : reglas) {
            regla.aplicar(sensores, actuadores);
        }

        // Comprobamos cómo han quedado los actuadores
        System.out.println("\n--- ESTADO DE LOS ACTUADORES ---");
        for (Actuador actuador : actuadores) {
            System.out.println(actuador.getNombre() + ", " + actuador.getId() + ",: " + actuador.getEstadoActual());
        }
        System.out.println("-------------------------------------\n");

        // Guardamos el estado de los actuadores
        guardarLogActuadores();
        guardarEstadoJSON();
    }

    /**
     * Escribe el estado actual de los actuadores en el archivo actuators.log
     */
    private void guardarLogActuadores() {
        String timestamp = LocalDateTime.now().toString();

        try (FileWriter fileWriter = new FileWriter("actuators.log", true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

             printWriter.println("Timestamp, Actuador, Estado, Fuente");

            // Escribimos cada actuador separado por comas
            for (Actuador actuador : actuadores) {
                printWriter.println(timestamp + ", " + actuador.getId() + ", " + actuador.getEstadoActual() + ", AUTO");
            }

        } catch (IOException e) {
            System.out.println("Error crítico al escribir el log: " + e.getMessage());
        }
    }

    /**
     * Generamos el archivo json para la persistencia del estadoo del sistema.
     */
    private void guardarEstadoJSON() {
        // Abrimos el archivo SIN el true para que se sobrescriba siempre
        try (FileWriter fileWriter = new FileWriter("estado_sistema.json");
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            printWriter.println("{");

            // Recorremos los sensores para guardar sus claves y valores
            for (int i = 0; i < sensores.size(); i++) {
                Sensor s = sensores.get(i);

                // Construimos el formado del JSON: "id": "valor"
                printWriter.print("  \"" + s.getId() + "\": \"" + s.getEstadoActual() + "\"");

                if (i < sensores.size() - 1) {
                    printWriter.println(",");
                } else {
                    printWriter.println();
                }
            }

            printWriter.println("}");

        } catch (IOException e) {
            System.out.println("Error al generar el archivo JSON: " + e.getMessage());
        }
    }
}