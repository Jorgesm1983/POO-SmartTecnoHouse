package modelo;

import java.util.List;

/**
 * Clase que implementa la regla de la iluminación.
 * Gestiona el encendido de la bombilla  según los valores de lus y presencia.
 */
public class ReglaIluminacion implements Regla{

    /**
     * Aplica la lógica de control sobre la bombilla.
     * @param sensores   Lista de sensores para consultar su estado actual.
     * @param actuadores Lista de actuadores para ejecutar acciones.
     */
    @Override
    public void aplicar(List<Sensor> sensores, List<Actuador> actuadores ){
        // Iniciamos luminidad a un valor 1000 lumenes.
        double luminosidad = 1000.0;
        boolean hayPresencia = false;
        Actuador bombilla = null;

        // Extraemops la información actual de los sensores
        for (Sensor sensor : sensores){
            if (sensor.getId().equals("light")) {
                luminosidad = sensor.getValor();
            } else if (sensor.getId().equals("pir")){
                hayPresencia = (sensor.getValor() == 1.0);
            }
        }

        // Buscamos el actuador de la bombilla
        for (Actuador actuador: actuadores) {
            if (actuador.getId().equals("bulb")){
                bombilla = actuador;
                break;
            }
        }

        // Aplicamos la lógica
        if (bombilla != null){
            if (luminosidad < 300.0 && hayPresencia) {
                bombilla.ejecutarAccion("ON");
            } else {
                // si hay suficiente luz o no hay nadie apagamos la bombilla
                bombilla.ejecutarAccion("OFF");
            }
        }
    }
}