package modelo;

/**
 * Claseque representa un actuador de una bombilla.
 * Hereda de la clase abstracta Actuador.
 */
public class ActuadorBombilla extends Actuador {

    // Estados posibles de la bombilla
    private static final String[] ACCIONES = {"OFF", "ON"};

    /**
     * Constructor del actuador bombilla.
     * @param id El identificador único del actuador.
     */
    public ActuadorBombilla(String id) {
        super(id, "Bombilla Inteligente");
    }

    @Override
    public String[] getAccionesPosibles() {
        return ACCIONES;
    }

    /**
     * Modifica el estado de la bombilla para encenderla o apagarla.
     * @param accion El comando de encendido o apagado.
     */
    @Override
    public void ejecutarAccion(String accion) {
        for (String accionValida : ACCIONES) {
            if (accionValida.equals(accion)) {
                this.estado = accion;
                return;
            }
        }
        System.out.println("Error: La acción '" + accion + "' no es válida para la bombilla.");
    }
}