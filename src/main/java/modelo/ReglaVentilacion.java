package modelo;

import java.util.List;

/**
 * Clase que implementa la regla de ventilación (R1).
 * Controla el encendido y apagado del ventilador en base a la temperatura y la presencia .
 */
public class ReglaVentilacion implements Regla {

    /**
     * Aplica la lógica de control sobre el ventilador según los datos de los sensores.
     * @param sensores   Lista de sensores para consultar su estado actual.
     * @param actuadores Lista de actuadores para ejecutar acciones.
     */
    @Override
    public void aplicar(List<Sensor> sensores, List<Actuador> actuadores) {
        double temperatura = 0.0;
        boolean hayPresencia = false;
        Actuador ventilador = null;

        // Extraemos la información actual de los sensores
        for (Sensor sensor : sensores) {
            if (sensor.getId().equals("temp")) {
                temperatura = sensor.getValor();
            } else if (sensor.getId().equals("pir")) {
                hayPresencia = (sensor.getValor() == 1.0);
            }
        }

        //  Buscamos el actuador que queremos controlar
        for (Actuador actuador : actuadores) {
            if (actuador.getId().equals("fan")) {
                ventilador = actuador;
                break;
            }
        }

        // Aplicamos la lógica si encontramos el ventilador
        if (ventilador != null) {
            if (temperatura > 28.0 && hayPresencia) {
                ventilador.ejecutarAccion("HIGH");
            } else {
                // Si no hace calor o no hay nadie apagamos el ventilador
                ventilador.ejecutarAccion("OFF");
            }
        }
    }
}