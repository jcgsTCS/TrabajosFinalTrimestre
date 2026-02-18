package Trabajo_Final_Trimestre_2t;

/**
 * Se debe incorporar:
 * - el getter del id
 * - el getter y setter de disponible
 * - el setter de la ubicación
 * - Puedes crearte métodos privados de apoyo.
 */
public class Unidad {
    /**
     * Utilizamos este atributo static para asignar ids de forma automática
     */
    private static int contador = 0;
    private int id;
    private TipoUnidad tipo;
    private boolean disponible;
    private int capacidadPersonal;
    private Ubicacion ubicacion;
    private int numPersonasAsignadas = 0;
    private PersonalEmergencias[] personalAsignado;

    /**
     * Constructor por defecto.
     * Asigna el id según el valor del contador.
     * Pone disponible a false y a cero el numero de personas asignadas
     */
    public Unidad() {
        contador++;
        id = contador;
        disponible = false;
        numPersonasAsignadas = 0;
    }

    /**
     * Pide el usuario el tipo de unidad,
     * la capacidad máxima (y pide memoria para el array de personal según esta capacidad),
     * se pregunta si tiene ubicación asignada y, si la tiene, se pregunta.
     * Se guarda la información en los atributos correspondientes.
     */
    public void pedirDatos() {
        tipo = newUTIL.preguntaEnum("Tipo de unidad:", TipoUnidad.class);
        capacidadPersonal = newUTIL.preguntaNumero(
                "Capacidad máxima de personal:", 1, 20);
        personalAsignado = new PersonalEmergencias[capacidadPersonal];
        boolean tieneUbicacion = newUTIL.preguntaFlag("¿Tiene ubicación asignada?");
        if (tieneUbicacion) {
            ubicacion = new Ubicacion();
            ubicacion.pedirDatos();
        }
    }

    /**
     * El personal se añade.
     * Para poder añadirlo, este personal debe estar disponible.
     * Y no nos podemos pasar de la capacidad máxima de esta unidad.
     * Si la unidad estaba no disponible porque no tenía personas asignadas,
     * ahora pasará a estar disponible.
     *
     * @param pe Persona a añadir
     * @return true si se ha podido añadir. False en caso contrario
     */
    public boolean incorporarPersonalEmergencias(PersonalEmergencias pe) {
        // El personal debe estar disponible
        if (!pe.isDisponible()) {
            return false;
        }

        // No se puede superar la capacidad máxima
        if (numPersonasAsignadas >= capacidadPersonal) {
            return false;
        }

        // Buscar hueco en el array
        int pos = buscaHuecoPersonal();
        if (pos == -1) {
            return false;
        }

        // Añadir personal
        personalAsignado[pos] = pe;
        numPersonasAsignadas++;

        // El personal pasa a no estar disponible
        pe.setDisponible(false);

        // Si antes no había personal, la unidad pasa a estar disponible
        if (numPersonasAsignadas == 1) {
            disponible = true;
        }

        return true;
    }

    /**
     * Busca un espacio en el array de personal y retorna la posición
     *
     * @return Posición libre. -1 si no hay
     */
    private int buscaHuecoPersonal() {
        for (int i = 0; i < personalAsignado.length; i++) {
            if (personalAsignado[i] == null) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Busca la posición donde está una persona a partir de su id
     *
     * @param idPersonal Id que estamos buscando
     * @return La posición donde está esa persona. -1 si no está
     */
    private int buscaPosicionPersonal(int idPersonal) {
        for (int i = 0; i < personalAsignado.length; i++) {
            if (personalAsignado[i] != null && personalAsignado[i].getId() == idPersonal) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Elimina a una persona de la unidad.
     * Sólo se puede quitar a personas si la unidad está disponible.
     * El id de la persona debe existir en la unidad para poderlo eliminar.
     *
     * @param idPersonal Id de la persona que queremos eliminar.
     * @return True si se ha podido eliminar. False, en caso contrario
     */
    public boolean quitarPersonalEmergencias(int idPersonal) {
        // La unidad tiene q estar disponible
        if (!disponible) {
            return false;
        }

        // Buscar la posición del personal por id
        int pos = buscaPosicionPersonal(idPersonal);
        if (pos == -1) {
            return false;
        }

        // El personal vuelve a estar disponible
        personalAsignado[pos].setDisponible(true);

        // Eliminar de la unidad
        personalAsignado[pos] = null;
        numPersonasAsignadas--;

        // Si ya no queda personal, la unidad pasa a no disponible
        if (numPersonasAsignadas == 0) {
            disponible = false;
        }

        return true;
    }

    /**
     * Transforma los datos de la unidad a un String.
     * Incluyendo los del personal asignado
     *
     * @return String con la información de la unidad.
     */
    public String unidadToString() {
        String resultado = "Unidad " + id + " - " + tipo + " - "
                + (disponible ? "Disponible" : "No disponible") + "\n";

        if (ubicacion != null) {
            resultado += "Ubicación: " + ubicacion.ubicacionToString() + "\n";
        }

        resultado += "Personal asignado:\n";

        for (int i = 0; i < personalAsignado.length; i++) {
            if (personalAsignado[i] != null) {
                resultado += "  - " + personalAsignado[i].personalEmergenciasToString() + "\n";
            }
        }
        return resultado;
    }

    /**
     * Pone la unidad a disponible y todo el personal asignado también a disponible.
     */
    public void liberaUnidad() {

        // La unidad pasa a estar disponible
        disponible = true;

        // Todo el personal asignado pasa a estar disponible y se desasigna
        for (int i = 0; i < personalAsignado.length; i++) {
            if (personalAsignado[i] != null) {
                personalAsignado[i].setDisponible(true);
                personalAsignado[i] = null;
            }
        }

        // Se reinicia el contador de personal asignado
        numPersonasAsignadas = 0;
    }
//Forzados
    public int getId() {
        return id;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public boolean isDisponible() {
        return disponible;
    }
//
    enum TipoUnidad {AMBULANCIA, CAMION_BOMBEROS, PATRULLA_POLICIA}
}
