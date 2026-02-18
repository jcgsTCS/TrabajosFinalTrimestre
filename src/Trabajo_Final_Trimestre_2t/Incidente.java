package Trabajo_Final_Trimestre_2t;

/**
 * Prepara:
 * - getter del id.
 */
public class Incidente {
    static final int MAX_UNIDADES = 5;
    private static int contador = 0;
    private int id;
    private EstadoIncidente estado;
    private TipoIncidente tipo;
    private int prioridad; //1..5
    private Ubicacion ubicacion;
    private String descripcion;
    private int minutosEstimados; //estimación inicial
    private int minutosResolucion; //-1 si aun no está cerrado
    private Unidad unidadAsignada;

    /**
     * Constructor por defecto.
     * Asigna el id según el valor del contador.
     * Pone el estado inicial a ABIERTO y los minutos de resolución a -1
     */
    public Incidente() {
        contador++;
        id = contador;
        estado = EstadoIncidente.ABIERTO;
        minutosResolucion = -1;
    }

    /**
     * Se solicita: el tipo de incidente, la prioridad del 1 al 5 (tienes un preguntaNumero que controla límites),
     * la ubicación, la descripción y los minutos estimados de resolución.
     */
    public void pedirDatos() {

        tipo = newUTIL.preguntaEnum("Tipo de incidente:", TipoIncidente.class);

        prioridad = newUTIL.preguntaNumero(
                "Prioridad del incidente (1-5):", 1, 5
        );

        ubicacion = new Ubicacion();
        ubicacion.pedirDatos();

        descripcion = newUTIL.preguntaTexto("Descripción del incidente:");

        minutosEstimados = newUTIL.preguntaNumero(
                "Minutos estimados de resolución:"
        );
    }


    /**
     * Asigna la unidad siempre que esté disponible y no haya una unidad ya asignada.
     * Al asignar la unidad se debe poner a no disponible y su ubicación pasará a ser la del incidente.
     * @param unidad Unidad que se quiere asignar.
     * @return True si se ha podido asignar la unidad. False, en caso contrario.
     */
    public boolean asignarUnidad(Unidad unidad) {
        // Ya hay una unidad asignada
        if (unidadAsignada != null) {
            return false;
        }

        // La unidad debe estar disponible
        if (!unidad.isDisponible()) {
            return false;
        }

        // Asignar la unidad
        unidadAsignada = unidad;

        // La unidad pasa a no estar disponible
        unidad.setDisponible(false);

        // La ubicación del incidente pasa a ser la de la unidad
        this.ubicacion = unidad.getUbicacion();

        return true;
    }

    /**
     * Transforma los datos del incidente a un String.
     * Incluyendo: los datos de la ubicación y de la unidad (con el personal asignado)
     * @return String con la información del incidente
     */
    public String incidenteToString() {
        String resultado = "Incidente " + id + "\n";
        resultado += "Tipo: " + tipo + "\n";
        resultado += "Estado: " + estado + "\n";
        resultado += "Prioridad: " + prioridad + "\n";
        resultado += "Descripción: " + descripcion + "\n";
        resultado += "Minutos estimados: " + minutosEstimados + "\n";

        if (minutosResolucion != -1) {
            resultado += "Minutos de resolución: " + minutosResolucion + "\n";
        }

        if (ubicacion != null) {
            resultado += "Ubicación: " + ubicacion.ubicacionToString() + "\n";
        }

        if (unidadAsignada != null) {
            resultado += "Unidad asignada:\n";
            resultado += unidadAsignada.unidadToString();
        } else {
            resultado += "Unidad asignada: ninguna\n";
        }

        return resultado;
    }

    /**
     * El incidente pasa a estado EN_CURSO siempre que:
     * - Tenga una unidad asignada
     * - El estado actual sea ABIERTO
     * @return True si se ha podido iniciar la resolución. False, en caso contrario.
     */
    public boolean iniciarResolucion() {
        if (unidadAsignada == null) {
            return false;
        }

        if (estado != EstadoIncidente.ABIERTO) {
            return false;
        }

        estado = EstadoIncidente.EN_CURSO;
        return true;
    }

    /**
     * Para poder cerrar la incidencia el estado previo debe ser EN_CURSO.
     * Se debe liberar a la unidad asignada (método nuevo en la clase Unidad)
     * @param minutosResolucion Minutos que se ha tardado en resolver la incidencia.
     * @return True si se ha podido finalizar el incidente. False, en caso contrario.
     */
    public boolean finalizarIncidente(int minutosResolucion) {
        if (estado != EstadoIncidente.EN_CURSO) {
            return false;
        }

        this.minutosResolucion = minutosResolucion;
        estado = EstadoIncidente.CERRADO;

        if (unidadAsignada != null) {
            unidadAsignada.liberaUnidad();
            unidadAsignada = null;
        }

        return true;
    }
    // forzados
    public int getId() {
        return id;
    }
    /**
     * Devuelve el tipo del incidente.
     * @return TipoIncidente asociado al incidente.
     */
    public TipoIncidente getTipo() {
        return tipo;
    }
    /**
     * Devuelve el estado del incidente.
     * @return EstadoIncidente asociado al incidente.
     */
    public EstadoIncidente getEstado() {
        return estado;
    }
    /**
     * Devuelve los minutos empleados en la resolución del incidente.
     * @return Número de minutos de resolución.
     */
    public int getMinutosResolucion() {
        return minutosResolucion;
    }




    public Ubicacion setUbicacion(Ubicacion ub) {
        return ubicacion;
    }
    //

    enum EstadoIncidente {ABIERTO, EN_CURSO, CERRADO}

    enum TipoIncidente {MEDICO, INCENDIO, SEGURIDAD, RESCATE}
}
