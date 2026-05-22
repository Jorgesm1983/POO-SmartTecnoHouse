package modelo;

/**
 * Clase que representa un actuador de ventilador.
 * Hereda de la clase abstracta Actuador.
 */
public class ActuadorVentilador extends Actuador {

    /** Constante con las acciones válidas que puede ejecutar este ventilador. */
    private static final String[] ACCIONES = {"OFF", "LOW", "MED", "HIGH"};

    /**
     * Constructor del actuador ventilador.
     * @param id El identificador único del actuador.
     */
    public ActuadorVentilador(String id) {
        super(id, "Ventilador Inteligente");
    }

    /**
     * Devuelve la lista de acciones permitidas para el ventilador
     * @return Array de Strings con los estados posibles.
     */
    @Override
    public String[] getAccionesPosibles() {
        return ACCIONES;
    }

    /**
     * Ejecuta la acción solicitada tras comprobar que es válida
     * @param accion La acción a ejecutar (ej. "HIGH").
     */
    @Override
    public void ejecutarAccion(String accion) {
        // Comprobamos si la acción solicitada está dentro del array de permitidas
        for (String accionValida : ACCIONES) {
            if (accionValida.equals(accion)) {
                this.estado = accion; // Actualizamos el estado interno
                return; // Acción completada con éxito, salimos del método
            }
        }

        // Si el estadao no es uno de los autorizados...
        System.out.println("Error: La acción '" + accion + "' no es válida para el ventilador.");
    }
}