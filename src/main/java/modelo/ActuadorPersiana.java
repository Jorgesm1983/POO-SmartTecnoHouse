package modelo;

/**
 * Clase que representa un motor de persiana.
 * Hereda de la clase abstracta Actuador.
 */
public class ActuadorPersiana extends Actuador {

    /** Constante con las acciones que admite el motor de la persiana. */
    private static final String[] ACCIONES = {"SUBIR", "BAJAR", "PAUSAR"};

    /**
     * Constructor de la persiana inteligente.
     * @param id, El identificador único del actuador.
     */
    public ActuadorPersiana(String id) {
        super(id, "Persiana Inteligente");
        // Sobrescribimos el estado inicial genérico ("OFF") por uno más lógico para una persiana
        this.estado = "PAUSAR";
    }

    /**
     * Devuelve la lista de acciones permitidas para la persiana.
     * @return Array de Strings con los estados posibles.
     */
    @Override
    public String[] getAccionesPosibles() {
        return ACCIONES;
    }

    /**
     * Ejecuta la acción solicitada tras comprobar que es válida.
     * @param accion, La acción a ejecutar en el motor de la persiana.
     */
    @Override
    public void ejecutarAccion(String accion) {
        for (String accionValida : ACCIONES) {
            if (accionValida.equals(accion)) {
                this.estado = accion;
                return;
            }
        }
        System.out.println("Error: La acción '" + accion + "' no es válida para la persiana.");
    }
}