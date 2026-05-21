package modelo;

/**
 * Clase abstracta que representa un Actuador genérico que sirve de plantilla para instanciar actuadores específicos.
 * Implementa la interfaz IDispositivo.
 */
public abstract class Actuador implements IDispositivo {

    // Usamos "protected" para garantizar que las clases hijas puedan acceder a estas variables sin romper el encapsulamiento.

    /** Identificador único del actuador. */
    protected String id;

    /** Nombre del actuador. */
    protected String nombre;

    /** Estado del actuador, String para soportar múltiples estados . */
    protected String estado;

    /**
     * Constructor para cualquier actuador
     * @param id, identificador único que se le asignará al actuador
     * @param nombre, nombre del actuador.
     */
    public Actuador(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.estado = "OFF"; // Configuramos de manera inicial el estado como apagado para cada actuador.
    }

// Definición de los metodos obligatorios de IDispositivo.

    /**
     * Obtiene el identificador único del actuador
     * @return id, ID del actuador.
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Obtiene el nombre del actuador
     * @return nombre, nombre del actuador.
     */
    @Override
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Consulta si el actuador está encendido
     * @return  String con el estado actual.
     */
    @Override
    public String getEstadoActual() {
        return this.estado;
    }

// Métodos propios de los Actuadores.

    /**
     * Método abstracto para validar y ejecutar una acción en el actuador.
     * @param accion, la acción a ejecutar.
     */
    public abstract void ejecutarAccion(String accion);

    /**
     * Método abstracto que obliga a los hijos a definir qué acciones pueden realizar.
     * @return Array de Strings con las acciones válidas.
     */
    public abstract String[] getAccionesPosibles();
}