package modelo;

/**
 * Clase abstracta que representa un Sensor genérico que sirve de plantilla para instanciar sensores específicos.
 * Implementa la interfaz IDispositivo.
 */
public abstract class Sensor implements IDispositivo {

    // Usamos "protected" para garantizar que las clases hijas puedan acceder a estas variables sin romper el encapsulamiento.

    /** Identificador único del sensor. */
    protected String id;

    /** Nombre del sensor. */
    protected String nombre;

    /** Último valor numérico medido por el sensor. */
    protected double valorActual;

    /** Unidad de medida del sensor. */
    protected String unidad;

    /**
     * Constructor para cualquier sensor
     * @param id, identificador único que se le asignará al sensor.
     * @param nombre, nombre del sensor.
     * @param unidad, unidad de medida.
     */
    public Sensor(String id, String nombre, String unidad) {
        this.id = id;
        this.nombre = nombre;
        this.unidad = unidad;
        this.valorActual = 0.0; // Valor inicial por defecto en cada sensor.
    }

// Definición de los metodos obligatorios de IDispositivo

    /**
     * Obtiene el identificador único del sensor
     * @return id, ID del sensor.
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Obtiene el nombre del sensor
     * @return nombre, nombre del sensor.
     */
    @Override
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Consulta el estado actual del sensor
     * @return String con el valor y su unidad.
     */
    @Override
    public String getEstadoActual() {
        return this.valorActual + " " + this.unidad;
    }

// Métodos propios de los Sensores

    /**
     * Devuelve la última lectura registrada por el sensor
     * @return el valor actual de la medición.
     */
    public double getValor() {
        return this.valorActual;
    }

    /**
     * Metodo abstracto que obliga a todas las clases hijas a definir
     * cómo realizan sus mediciones.
     */
    public abstract void actualizarValor();

    /**
     * Recuperamos e inyectamos el valor previo del json.
     */
    public void setEstadoActual(String estado) {
        try {
            // Limpiamos los valores del json
            String numeroLimpio = estado.replaceAll("[^0-9\\.-]", "");

            // Si después de limpiar ha quedado un número válido, lo convertimos
            if (!numeroLimpio.isEmpty()) {
                this.valorActual = Double.parseDouble(numeroLimpio);
            }

        } catch (Exception e) {
            System.out.println("No se pudo procesar la persistencia del sensor con valor: " + estado);
        }
    }
}