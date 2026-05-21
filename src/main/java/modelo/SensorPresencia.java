package modelo;

/**
 * Clase concreta que representa un sensor de presencia.
 * Hereda de la clase abstracta Sensor.
 */
public class SensorPresencia extends Sensor {

    public SensorPresencia(String id) {
        super(id, "Sensor de Presencia", "");
    }

    @Override
    public void actualizarValor() {
        // 1.0 para detección y 0.0 para no detección
        this.valorActual = Math.random() > 0.5 ? 1.0 : 0.0;
    }

    @Override
    public String getEstadoActual() {
        // Sobrescribimos este método mejorar el formato
        return this.valorActual == 1.0 ? "Movimiento Detectado" : "Sin Movimiento";
    }
}