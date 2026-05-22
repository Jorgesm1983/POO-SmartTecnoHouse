package modelo;

import java.util.List;

/**
 * Clase que implementa la regla para el control automático de la persiana.
 * Contrala el funcionamiento de la persiana según los valores del sensor de humedad.
 */
public class ReglaPersiana implements Regla {

    /**
     * Aplica la lógica de control sobre la persiana en función de la humedad.
     * @param sensores   Lista de sensores para consultar su estado actual.
     * @param actuadores Lista de actuadores para ejecutar acciones.
     */
    @Override
    public void aplicar(List<Sensor> sensores, List<Actuador> actuadores) {
        double humedad = 50.0; // Valor neutro por defecto
        Actuador persiana = null;

        // Extraer la información del sensor
        for (Sensor sensor : sensores) {
            if (sensor.getId().equals("hum")) {
                humedad = sensor.getValor();
                break;
            }
        }

        // Buscar el actuador de la persiana
        for (Actuador actuador : actuadores) {
            if (actuador.getId().equals("blind")) {
                persiana = actuador;
                break;
            }
        }

        // Aplicar la lógica si encontramos la persiana
        if (persiana != null) {
            if (humedad > 60.0) {
                // Si hay mucha humedad exterior bajamos la persiana
                persiana.ejecutarAccion("BAJAR");
            } else if (humedad < 40.0) {
                // Si el ambiente es muy seco, subimos para ventilar
                persiana.ejecutarAccion("SUBIR");
            } else {
                // En condiciones óptimas, detenemos el motor
                persiana.ejecutarAccion("PAUSAR");
            }
        }
    }
}