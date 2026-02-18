package Trabajo_Final_Trimestre_2t;

public class CentroEmergencias {
    static final int MAX_PERSONAL = 30;
    static final int MAX_UNIDADES = 20;
    static final int MAX_INCIDENTES = 50;
    private static final int MAX_LOGS = 100;

    /**
     * Guarda los logs
     */
    private LogAccion[] logs;
    private int numLogs;

    /**
     * Guarda los Incidentes dadas de alta en el array incidentes
     */
    private int numIncidentes;
    private Incidente[] incidentes;

    /**
     * Guarda las unidades dadas de alta en el array unidades
     */
    private int numUnidades;
    private Unidad[] unidades;


    /**
     * Guarda cuántas personas hay dadas de alta en el array personal.
     */
    private int numPersonal;
    private PersonalEmergencias[] personal;

    /**
     * Pide memoria para el array personal e inicializa numPersonal
     */
    public CentroEmergencias() {

        personal = new PersonalEmergencias[MAX_PERSONAL];
        numPersonal = 0;
        unidades = new Unidad[MAX_UNIDADES];
        numUnidades = 0;
        incidentes = new Incidente[MAX_INCIDENTES];
        numIncidentes = 0;
        logs = new LogAccion[MAX_LOGS];
        numLogs = 0;

    }

    /**
     * Comprueba si cabe personal en el centro
     *
     * @return True si hay espacio para guardar una persona más. False, en caso contrario.
     */
    public boolean cabePersonalEmergencias() {
        return numPersonal < MAX_PERSONAL;
    }

    /**
     * Busca una posición libre en el array de personal
     *
     * @return El número de la posición libre. -1 si no hay.
     */
    private int buscaHuecoPersonal() {
        for (int i = 0; i < personal.length; i++) {
            if (personal[i] == null) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Da de alta una persona nueva si hay espacio para ello.
     * Apóyate en los métodos cabePersonalEmergencias y buscaHuecoPersonal
     *
     * @param pe Persona de emergencias que hay que dar de alta.
     * @return True si se ha podido dar de alta. False, en caso contrario.
     */
    public boolean altaPersonalEmergencias(PersonalEmergencias pe) {
        if (!cabePersonalEmergencias()) {
            return false;
        }

        int pos = buscaHuecoPersonal();
        if (pos == -1) {
            return false;
        }

        personal[pos] = pe;
        numPersonal++;
        return true;


    }

    /**
     * Construye un String con la representación de la información de todo el personal
     * de emergencias.
     * Debes apoyarte en el método de PersonalEmergencias que transforma a String.
     *
     * @return String con la información de todo el personal de emergencias dado de alta.
     */
    public String personalToString() {
        if (numPersonal == 0) {
            return "No hay personal de alta";
        }

        String resultado = "";
        for (int i = 0; i < personal.length; i++) {
            if (personal[i] != null) {
                resultado += personal[i].personalEmergenciasToString();
            }
        }

        return resultado;
    }

    /**
     * Busca en qué posición está la persona que tiene el id indicado
     *
     * @param id Identificador que se está buscando
     * @return La posición donde está la persona. -1, si no se encuentra.
     */
    public int buscarPosicionPersonalPorId(int id) {
        for (int i = 0; i < personal.length; i++) {
            if (personal[i] != null && personal[i].getId() == id) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Retorna la persona que tiene el id indicado.
     * Apóyate en buscarPosicionPersonalPorId.
     *
     * @param id Identificador que se está buscando
     * @return El objeto persona que coincide con el id. Null, si no se encuentra.
     */
    private PersonalEmergencias buscarPersonalPorId(int id) {

        int pos = buscarPosicionPersonalPorId(id);
        if (pos == -1) {
            return null;
        }
        return personal[pos];
    }

    /**
     * Cambia la disponibilidad de la persona con el id indicado.
     *
     * @param id             Identificador de la persona a la que hay que cambiar la disponibilidad.
     * @param disponibilidad Nueva disponibilidad
     * @return True si se ha podido cambiar. False, en caso contrario.
     */
    public boolean cambiarDisponibilidadPersonal(int id, boolean disponibilidad) {
        PersonalEmergencias personalEmergencias = buscarPersonalPorId(id);
        if (personalEmergencias == null) {
            return false;
        }
        personalEmergencias.setDisponible(disponibilidad);
        return true;
    }

    /**
     * Elimina a la persona que tiene el id indicado.
     *
     * @param id Identificador de la persona que hay que eliminar.
     * @return True si se ha podido eliminar. False, en caso contrario.
     */
    public boolean eliminarPersonal(int id) {
        int pos = buscarPosicionPersonalPorId(id);
        if (pos == -1) {
            return false;
        }
        personal[pos] = null;
        numPersonal--;
        return true;
    }

    public boolean cabenUnidades() {

        return numUnidades < MAX_UNIDADES;
    }

    /**
     * Busca una posición libre en el array de unidades
     *
     * @return El número de la posición libre. -1 si no hay.
     */
    private int buscaHuecoUnidad() {

        for (int i = 0; i < unidades.length; i++) {
            if (unidades[i] == null) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Da de alta una undad nueva si hay espacio.
     * Apóyate en los métodos cabenUnidades y buscaHuecoUnidad
     *
     * @param unidad Unidad que hay que dar de alta.
     * @return True si se ha podido dar de alta. False, en caso contrario.
     */
    public boolean altaUnidad(Unidad unidad) {


        if (!cabenUnidades()) {
            return false;
        }

        int pos = buscaHuecoUnidad();
        if (pos == -1) {
            return false;
        }

        unidades[pos] = unidad;
        numUnidades++;
        return true;
    }

    /**
     * Construye un String con toda la información de las unidades, incluyendo:
     *
     * @return String con la información de todas las unidades
     */
    public String unidadesToString() {
        if (numUnidades == 0) {
            return "No hay unidades dadas de alta";
        }

        String resultado = "";
        for (int i = 0; i < unidades.length; i++) {
            if (unidades[i] != null) {
                resultado += unidades[i].unidadToString();
            }
        }

        return resultado;
    }

    /**
     * Busca en qué posición está la unidad que tiene el id indicado
     *
     * @param id Identificador que se está buscando
     * @return La posición donde está la unidad. -1, si no se encuentra.
     */
    public int buscarPosicionUnidadPorId(int id) {
        for (int i = 0; i < unidades.length; i++) {
            if (unidades[i] != null && unidades[i].getId() == id) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Retorna la unidad que tiene el id indicado.
     * Apóyate en buscarPosicionUnidadPorId.
     *
     * @param id Identificador que se está buscando
     * @return El objeto Unidad que coincide con el id. Null, si no se encuentra.
     */
    public Unidad buscarUnidadPorId(int id) {
        int pos = buscarPosicionUnidadPorId(id);
        if (pos == -1) {
            return null;
        }

        return unidades[pos];
    }

    /**
     * Asigna la persona con el id indicado a la unidad con idUnidad.
     * Apóyate en el método o métodos de Unidad que necesites.
     *
     * @param idUnidad   Id de la unidad a la que se debe añadir el personal
     * @param idPersonal Id de la persona que hay que incorporar
     * @return True si se ha podido añadir. False, en caso contrario.
     */
    public boolean asignarPersonalAUnidad(int idUnidad, int idPersonal) {
        Unidad unidad = buscarUnidadPorId(idUnidad);
        PersonalEmergencias personal = buscarPersonalPorId(idPersonal);

        if (unidad == null || personal == null) {
            return false;
        }

        return unidad.incorporarPersonalEmergencias(personal);
    }

    /**
     * Elimina a la persona con el id indicado de la unidad con idUnidad.
     * Apóyate en el método o métodos de Unidad que necesites para hacerlo.
     *
     * @param idUnidad   Id de la unidad a la que se debe quitar el personal
     * @param idPersonal Id de la persona que hay que desasignar.
     * @return True si se ha podido desasignar. False, en caso contrario.
     */
    public boolean desasignarPersonalAUnidad(int idUnidad, int idPersonal) {

        Unidad unidad = buscarUnidadPorId(idUnidad);
        if (unidad == null) {
            return false;
        }

        return unidad.quitarPersonalEmergencias(idPersonal);
    }

    /**
     * Cambia la disponibilidad de la unidad con el id indicado.
     *
     * @param idUnidad       Identificador de la unidad a la que hay que cambiar la disponibilidad.
     * @param disponibilidad Nueva disponibilidad
     * @return True si se ha podido cambiar. False, en caso contrario.
     */
    public boolean cambiarDisponibilidadUnidad(int idUnidad, boolean disponibilidad) {
        Unidad unidad = buscarUnidadPorId(idUnidad);
        if (unidad == null) {
            return false;
        }

        unidad.setDisponible(disponibilidad);
        return true;
    }

    /**
     * Se cambia la ubicación de la unidad indicada.
     *
     * @param idUnidad  Id de la unidad a la que se debe cambiar la ubicación
     * @param ubicacion La nueva ubicación
     * @return True si se ha podido cambiar. False, en caso contrario.
     */
    public boolean cambiarUbicacionUnidad(int idUnidad, Ubicacion ubicacion) {
        Unidad unidad = buscarUnidadPorId(idUnidad);
        if (unidad == null) {
            return false;
        }

        unidad.setUbicacion(ubicacion);
        return true;
    }

    /**
     * Elimina a la unidad que tiene el id indicado.
     *
     * @param id Identificador de la unidad que hay que eliminar.
     * @return True si se ha podido eliminar. False, en caso contrario.
     */
    public boolean eliminarUnidad(int id) {
        int pos = buscarPosicionUnidadPorId(id);
        if (pos == -1) {
            return false;
        }

        unidades[pos] = null;
        numUnidades--;
        return true;
    }

    /**
     * Comprueba si caben nuevos incidentes
     *
     * @return True si hay espacio para guardar un incidente más. False, en caso contrario.
     */
    public boolean cabenIncidentes() {
        return numIncidentes < MAX_INCIDENTES;
    }

    /**
     * Da de alta un incidente nuevo si hay espacio.
     *
     * @param incidente Incidente que hay que dar de alta.
     * @return True si se ha podido dar de alta. False, en caso contrario.
     */
    public boolean altaIncidente(Incidente incidente) {
        if (!cabenIncidentes()) {
            return false;
        }

        for (int i = 0; i < incidentes.length; i++) {
            if (incidentes[i] == null) {
                incidentes[i] = incidente;
                numIncidentes++;
                return true;
            }
        }
        return false;
    }

    /**
     * Construye un String con toda la información de los incidentes
     *
     * @return String con la información de todas los incidentes
     */
    public String incidentesToString() {
        if (numIncidentes == 0) {
            return "No hay incidentes dados de alta";
        }

        String resultado = "";
        for (int i = 0; i < incidentes.length; i++) {
            if (incidentes[i] != null) {
                resultado += incidentes[i].incidenteToString() + "\n";
            }
        }

        return resultado;
    }

    /**
     * Busca en qué posición está el incidente que tiene el id indicado
     *
     * @param id Identificador que se está buscando
     * @return La posición donde está el incidente. -1, si no se encuentra.
     */
    private int buscarPosicionIncidentePorId(int id) {

        for (int i = 0; i < incidentes.length; i++) {
            if (incidentes[i] != null && incidentes[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Retorna el incidente que tiene el id indicado.
     * Apóyate en buscarPosicionIncidentePorId.
     *
     * @param id Identificador que se está buscando
     * @return El objeto Incidente que coincide con el id. Null, si no se encuentra.
     */
    public Incidente buscarIncidentePorId(int id) {
        int pos = buscarPosicionIncidentePorId(id);
        if (pos == -1) {
            return null;
        }

        return incidentes[pos];
    }

    /**
     * Asigna la unidad que tiene el idUnidad al incidente con idIncidente
     *
     * @param idIncidente Id del incidente al que se tiene que añadir la unidad
     * @param idUnidad    Id de la unidad que se tiene que añadir
     * @return True si se ha podido añadir. False, en caso contrario
     */
    public boolean asignarUnidadAIncidente(int idIncidente, int idUnidad) {
        Incidente incidente = buscarIncidentePorId(idIncidente);
        Unidad unidad = buscarUnidadPorId(idUnidad);

        if (incidente == null || unidad == null) {
            return false;
        }

        return incidente.asignarUnidad(unidad);
    }

    /**
     * Inicia la resolución del incidente.
     *
     * @param id Id del incidente que debe iniciarse
     * @return True si se ha podido inciar. False, en caso contrario.
     */
    public boolean iniciarIncidente(int id) {
        Incidente incidente = buscarIncidentePorId(id);
        if (incidente == null) {
            return false;
        }

        return incidente.iniciarResolucion();
    }

    /**
     * Marca el incidente como resuelto.
     *
     * @param id                Id del incidente que debe marcarse como resuelto.
     * @param minutosResolucion Minutos que se ha tardado en resolver.
     * @return True si se ha podido marcar como resuelto. False, en caso contrario.
     */
    public boolean marcarResolucionIncidente(int id, int minutosResolucion) {
        Incidente incidente = buscarIncidentePorId(id);
        if (incidente == null) {
            return false;
        }
        return incidente.finalizarIncidente(minutosResolucion);
    }

    /**
     * Genera un String los datos de los incidentes que tienen el estado que se pasa como parámetro
     *
     * @param estadoIncidente Indica el estado de los incidentes que hemos de buscar
     * @return String que contiene la información de los incidentes que tienen el estado indicado.
     */
    public String getIncidentesXEstado(Incidente.EstadoIncidente estadoIncidente) {
        String resultado = "";

        for (int i = 0; i < numIncidentes; i++) {
            if (incidentes[i].getEstado() == estadoIncidente) {
                resultado += incidentes[i].toString() + "\n";
            }
        }

        if (resultado.equals("")) {
            return "No hay incidentes con el estado indicado.";
        }

        return resultado;
    }


    /**
     * Busca el incidente con el tiempo de resolución más largo. Se buscará entre los incidentes que ya se hayan resuelto.
     *
     * @return Retorna el incidente con el tiempo de resolución más alto o bien null si no hay incidente o ninguno está resuelto.
     */
    public Incidente getIncidenteMasLargo() {
        Incidente incidenteMasLargo = null;

        for (int i = 0; i < numIncidentes; i++) {

            if (incidentes[i].getEstado() == Incidente.EstadoIncidente.CERRADO) {

                if (incidenteMasLargo == null ||
                        incidentes[i].getMinutosResolucion() > incidenteMasLargo.getMinutosResolucion()) {

                    incidenteMasLargo = incidentes[i];
                }
            }
        }

        return incidenteMasLargo;
    }


    /**
     * Genera un String que contiene por cada tipo de incidente la media de tiempo de resolución.
     * Para calcular estas medias sólo se tienen en cuenta los incidentes con estado resuelto.
     * Pista: Con esto Incidente.TipoIncidente.values() obtenemos un array con los diferentes tipos de incidentes.
     *
     * @return String con la información indicada.
     */
    public String getMediasPorTipoIncidente() {
        String resultado = "";

        for (Incidente.TipoIncidente tipo : Incidente.TipoIncidente.values()) {

            int suma = 0;
            int contador = 0;

            for (int i = 0; i < numIncidentes; i++) {

                if (incidentes[i].getTipo() == tipo &&
                        incidentes[i].getEstado() == Incidente.EstadoIncidente.CERRADO) {

                    suma += incidentes[i].getMinutosResolucion();
                    contador++;
                }
            }

            if (contador > 0) {
                double media = (double) suma / contador;
                resultado += tipo + " → Media: " + media + " minutos\n";
            } else {
                resultado += tipo + " → No hay incidentes resueltos\n";
            }
        }

        return resultado;
    }

    /**
     * Vuelve a pedir memoria para el array de logs y pone el numLogs a cero.
     */
    public void resetearLogs() {
        logs = new LogAccion[MAX_LOGS];
        numLogs = 0;
    }


    /**
     * Añade un log al listado de log de acciones con el mensaje indicado
     *
     * @param mensaje Mensaje que se debe incorporar al log
     */
    public void añadirLog(String mensaje) {

        if (numLogs < MAX_LOGS) {
            logs[numLogs] = new LogAccion(mensaje);
            numLogs++;
        }
    }


    /**
     * Se construye un String con la información de los últimos logs generados.
     *
     * @param numLogsVisualizar Cantidad de logs que se quieren visualizar.
     * @return String con la información de la cantidad de logs solicitados
     */
    public String getInfoLogs(int numLogsVisualizar) {

        if (numLogs == 0) {
            return null;
        }

        String resultado = "";

        int cantidad = Math.min(numLogsVisualizar, numLogs);

        for (int i = numLogs - 1; i >= numLogs - cantidad; i--) {
            resultado += logs[i].logActionToString() + "\n";
        }

        return resultado;
    }

}






