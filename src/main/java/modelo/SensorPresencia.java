package modelo;

/**
 * Clase concreta que representa un sensor de presencia.
 * Hereda de la clase abstracta Sensor.
 */
public class SensorPresencia extends Sensor {

    /**
     * Constructor del sensor de presencia.
     * @param id El identificador único del sensor.
     */
    public SensorPresencia(String id) {
        super(id, "Sensor de Presencia", "");
    }

    /**
     * Simula la detección de presencia asignando 1.0, detectado o 0.0, no detectado.
     */
    @Override
    public void actualizarValor() {
        // 1.0 para detección y 0.0 para no detección
        this.valorActual = Math.random() > 0.5 ? 1.0 : 0.0;
    }

    /**
     * Consulta el estado actual formateado en texto.
     * @return String indicando si hay movimiento detectado o no.
     */
    @Override
    public String getEstadoActual() {
        return this.valorActual == 1.0 ? "Movimiento Detectado" : "Sin Movimiento";
    }
}