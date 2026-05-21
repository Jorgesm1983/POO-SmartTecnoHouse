package modelo;

/**
 * Clase concreta que representa un sensor de humedad.
 * Hereda de la clase abstracta Sensor.
 */
public class SensorHumedad extends Sensor {

    /**
     * Constructor del sensor de humedad.
     * @param id, El identificador único del sensor.
     */
    public SensorHumedad(String id) {
        // ID, nombre y porcentaje como unidad de medida
        super(id, "Sensor de Humedad", "%");
    }

    /**
     * Simula la lectura de la humedad relativa del aire.
     */
    @Override
    public void actualizarValor() {
        // Simulamos una humedad realista para una casa
        // Redondeamos a dos decimales
        this.valorActual = Math.round((30.0 + Math.random() * 50.0) * 100.0) / 100.0;
    }
}