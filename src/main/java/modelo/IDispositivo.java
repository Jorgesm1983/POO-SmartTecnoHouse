package modelo;

/**
 * Interfaz que define el contrato de comportamiento para cualquier dispositivo
 */

public interface IDispositivo {
    /**
     * Devuelve el identificador único del dispositivo
     * @return  String con el ID del dispositivo.
     */
    String getId();

    /**
     * Devuelve un nombre legible del dispositivo
     * @return String con el nombre.
     */
    String getNombre();

    /**
     * Devuelve un resumen del estado actual
     * @return String con el estado.
     */
    String getEstadoActual();
}