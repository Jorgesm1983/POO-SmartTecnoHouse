package modelo;

import java.util.List;

/**
 * Interfaz que define el contrato para cualquier regla del sistema.
 * Implementa el Patrón de Diseño Strategy.
 */
public interface Regla {

    /**
     * Evalúa las condiciones de los sensores y ejecuta acciones en los actuadores si se cumplen.
     * @param sensores, lista de todos los sensores disponibles.
     * @param actuadores, lista de todos los actuadores disponibles.
     */
    void aplicar(List<Sensor> sensores, List<Actuador> actuadores);
}