package modelo;

import java.util.List;

/**
 * Clase concreta que implementa la regla para el control automático de la persiana.
 * Da uso al Sensor de Humedad y al Actuador Persiana creados como ampliación.
 */
public class ReglaPersiana implements Regla {

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