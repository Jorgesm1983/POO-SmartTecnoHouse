package modelo;

/**
 * Clase concreta que representa un sensor de luminosidad.
 * Hereda de la clase abstracta Sensor.
 */
public class SensorLuz extends Sensor {

    public SensorLuz(String id) {
        // ID, nombre, y la unidad de medida de la luz (lux)
        super(id, "Sensor de Luz", "lux");
    }

    @Override
    public void actualizarValor() {
        // Simulamos luz ambiental entre 0lm para oscuridad y 1000lm para luz.
        this.valorActual = Math.round(Math.random() * 1000.0);
    }
}