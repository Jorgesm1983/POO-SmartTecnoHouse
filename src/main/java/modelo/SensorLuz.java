package modelo;

/**
 * Clase que representa un sensor de luminosidad.
 * Hereda de la clase abstracta Sensor.
 */
public class SensorLuz extends Sensor {

    /**
     * Constructor del sensor de luz.
     * @param id El identificador único del sensor.
     */
    public SensorLuz(String id) {
        // ID, nombre, y la unidad de medida de la luz (lux)
        super(id, "Sensor de Luz", "lux");
    }

    /**
     * Simula la lectura de la luz ambiental y actualiza el valor interno.
     * Registra valores entre 0 oscuridad y 1000 máxima luminosidad.
     */
    @Override
    public void actualizarValor() {
        this.valorActual = Math.round(Math.random() * 1000.0);
    }
}