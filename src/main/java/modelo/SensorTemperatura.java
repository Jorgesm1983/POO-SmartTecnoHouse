package modelo;

/**
 * Clase concreta que representa un sensor de temperatura.
 * Hereda de la clase abstracta Sensor.
 */
public class SensorTemperatura extends Sensor {

    /**
     * Constructor del sensor de temperatura.
     * @param id El identificador único del sensor.
     */
    public SensorTemperatura(String id) {
        // Invocamos al constructor de la clase padre
        // Pasamos el id recibido, el nombre  y la unidad de medida.
        super(id, "Sensor de Temperatura", "°C");
    }

    /**
     * Simula la lectura de una nueva temperatura y actualiza el valor interno.
     */
    @Override
    public void actualizarValor() {
        // Generamos una temperatura aleatoria
        // Redondeamos a dos decimales
        this.valorActual = Math.round((15.0 + Math.random() * 15.0) * 100.0) / 100.0;
    }
}