package controlador;

// Importamos todas las clases de nuestro modelo
import modelo.*;
import java.util.ArrayList;
import java.util.List;

// Importaciones para manejar la escritura de archivos y la hora actual
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.time.LocalDateTime;

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

        // Intentamos recuperar los datos previos si existiesen
        cargarEstadoJSON();
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
    public String ejecutarCicloSimulacion() {
        // Usamos StringBuilder para ir construyendo el texto que devolveremos a la ventana
        StringBuilder reporte = new StringBuilder();
        reporte.append("--- INICIANDO SISTEMA ---");

        // Los sensores se inician y cargan los valores.
        for (Sensor sensor : sensores) {
            sensor.actualizarValor();
            reporte.append(sensor.getNombre()).append(" (").append(sensor.getId()).append(") ha medido: ").append(sensor.getEstadoActual()).append("\n");
        }

        // Se ejecutan las reglas
        for (Regla regla : reglas) {
            regla.aplicar(sensores, actuadores);
        }

        // Comprobamos cómo han quedado los actuadores
        reporte.append("\n--- ESTADO DE LOS ACTUADORES ---");
        for (Actuador actuador : actuadores) {
            reporte.append(actuador.getNombre()).append(" (").append(actuador.getId()).append("): ").append(actuador.getEstadoActual()).append("\n");
        }
        reporte.append("-------------------------------------\n");

        // Guardamos el estado de los actuadores en el LOG y los valores de los sensores en el JSON
        guardarLogActuadores();
        guardarEstadoJSON();

        // Devolvemos el texto acumulado.
        return reporte.toString();
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
        // Abrimos el archivo sin el true para que se sobrescriba siempre
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
    /**
     * Lee el archivo estado_sistema.json si existe al arrancar
     * y recupera los valores guardados de los sensores.
     */
    private void cargarEstadoJSON() {
        File archivo = new File("estado_sistema.json");

        if (!archivo.exists()) {
            System.out.println("No hay archivo JSON previo. Los sensores iniciarán con valores base.");
            return;
        }

        // Forzamos la lectura en UTF-8 para que no haya problemas con símbolos de unidades
        try (BufferedReader br = new BufferedReader(
                new java.io.InputStreamReader(new java.io.FileInputStream(archivo), java.nio.charset.StandardCharsets.UTF_8))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                // Ignorar las llaves de apertura y cierre
                if (linea.equals("{") || linea.equals("}")) {
                    continue;
                }

                // Separamos la clave del valor usando los primeros dos puntos que encuentre
                int posicionDosPuntos = linea.indexOf(":");
                if (posicionDosPuntos != -1) {
                    // Extraemos y limpiamos las comillas del ID
                    String idSensor = linea.substring(0, posicionDosPuntos).replace("\"", "").trim();
                    // Extraemos y limpiamos las comillas y la coma final del valor
                    String valor = linea.substring(posicionDosPuntos + 1).replace("\"", "").replace(",", "").trim();

                    // Buscamos el sensor con ese ID para asignarle el estado recuperado
                    for (Sensor s : sensores) {
                        if (s.getId().equals(idSensor)) {
                            s.setEstadoActual(valor);
                            break;
                        }
                    }
                }
            }
            System.out.println("--> Persistencia cargada con éxito. Datos del JSON cargados.");

        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }
    /**
     * Devolvemos los valores del json para cargar al inicio de la ejecución del programa
     */
    public String obtenerEstadoInicial() {
        StringBuilder estado = new StringBuilder();
        estado.append("--- ESTADO INICIAL RECUPERADO ---\n");
        for (Sensor sensor : sensores) {
            estado.append(sensor.getNombre()).append(": ").append(sensor.getEstadoActual()).append("\n");
        }
        estado.append("----------------------------------------\n\n");
        return estado.toString();
    }
}