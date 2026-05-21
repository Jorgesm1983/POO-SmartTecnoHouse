package modelo;

/**
 * Clase concreta que representa una bombilla.
 * Hereda de la clase abstracta Actuador.
 */
public class ActuadorBombilla extends Actuador {

    // Estados posibles de la bombilla
    private static final String[] ACCIONES = {"OFF", "ON"};

    public ActuadorBombilla(String id) {
        super(id, "Bombilla Inteligente");
    }

    @Override
    public String[] getAccionesPosibles() {
        return ACCIONES;
    }

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