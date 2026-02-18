package Trabajo_Final_Trimestre_2t;

public class Principal {
    /**
     * Gestiona el menú de Incidentes
     *
     * @param centro Centro de emergencias desde el que se gestionan los incidentes
     */
    public static void menuIncidentes(CentroEmergencias centro) {
        int opcion;

        do {
            System.out.println("MENÚ DE INCIDENTES");
            System.out.println("1. Crear incidente");
            System.out.println("2. Listar incidentes");
            System.out.println("3. Asignar unidad a incidente");
            System.out.println("4. Iniciar incidente");
            System.out.println("5. Resolver incidente");
            System.out.println("6. Cancelar incidente");
            System.out.println("7. Cambiar ubicación del incidente");
            System.out.println("8. Volver");

            opcion = newUTIL.preguntaNumero("Elige una opción (1-8)");

            switch (opcion) {

                case 1: {
                    Incidente i = new Incidente();
                    i.pedirDatos();

                    if (centro.altaIncidente(i)) {
                        System.out.println("Incidente creado correctamente.");
                    } else {
                        System.out.println("No se ha podido crear el incidente.");
                    }
                    break;
                }

                case 2:
                    System.out.println(centro.incidentesToString());
                    break;

                case 3: {
                    int idIncidente = newUTIL.preguntaNumero("ID del incidente:");
                    int idUnidad = newUTIL.preguntaNumero("ID de la unidad:");

                    if (centro.asignarUnidadAIncidente(idIncidente, idUnidad)) {
                        System.out.println("Unidad asignada al incidente.");
                    } else {
                        System.out.println("No se ha podido asignar la unidad.");
                    }
                    break;
                }

                case 4: {
                    int id = newUTIL.preguntaNumero("ID del incidente:");

                    if (centro.iniciarIncidente(id)) {
                        System.out.println("Incidente iniciado.");
                    } else {
                        System.out.println("No se ha podido iniciar el incidente.");
                    }
                    break;
                }

                case 5: {
                    int id = newUTIL.preguntaNumero("ID del incidente:");
                    int minutos = newUTIL.preguntaNumero("Minutos de resolución:");

                    if (centro.marcarResolucionIncidente(id, minutos)) {
                        System.out.println("Incidente resuelto.");
                    } else {
                        System.out.println("No se ha podido resolver el incidente.");
                    }
                    break;
                }

                case 6: {
                    int id = newUTIL.preguntaNumero("ID del incidente:");

                    // Cancelar = resolver sin minutos reales
                    if (centro.marcarResolucionIncidente(id, 0)) {
                        System.out.println("Incidente cancelado.");
                    } else {
                        System.out.println("No se ha podido cancelar el incidente.");
                    }
                    break;
                }

                case 7: {
                    int id = newUTIL.preguntaNumero("ID del incidente:");
                    Ubicacion ub = new Ubicacion();
                    ub.pedirDatos();

                    Incidente inc = centro.buscarIncidentePorId(id);
                    if (inc != null) {
                        inc.setUbicacion(ub);
                        System.out.println("Ubicación del incidente modificada.");
                    } else {
                        System.out.println("No existe el incidente.");
                    }
                    break;
                }

                case 8:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 8);

    }

    /**
     * Gestiona el menú de unidades
     *
     * @param centro Centro de emergencias desde el que se gestionan las unidades
     */
    public static void menuUnidades(CentroEmergencias centro)
    {
        int opcion;

        do {
            System.out.println("MENÚ DE UNIDADES");
            System.out.println("1. Crear unidad");
            System.out.println("2. Listar unidades");
            System.out.println("3. Añadir personal a unidad");
            System.out.println("4. Quitar personal de unidad");
            System.out.println("5. Cambiar disponibilidad de una unidad");
            System.out.println("6. Cambiar ubicación de una unidad");
            System.out.println("7. Eliminar unidad");
            System.out.println("8. Volver");

            opcion = newUTIL.preguntaNumero("Elige una opción (1-8):");

            switch (opcion) {

                case 1: {
                    Unidad u = new Unidad();
                    u.pedirDatos();
                    if (centro.altaUnidad(u)) {
                        System.out.println("Unidad creada correctamente.");
                    } else {
                        System.out.println("No se ha podido crear la unidad.");
                    }
                    break;
                }

                case 2:
                    System.out.println(centro.unidadesToString());
                    break;

                case 3: {
                    int idUnidad = newUTIL.preguntaNumero("ID de la unidad:");
                    int idPersonal =newUTIL.preguntaNumero("ID del personal:");

                    if (centro.asignarPersonalAUnidad(idUnidad, idPersonal)) {
                        System.out.println("Personal añadido a la unidad.");
                    } else {
                        System.out.println("No se ha podido añadir el personal.");
                    }
                    break;
                }

                case 4: {
                    int idUnidad = newUTIL.preguntaNumero("ID de la unidad:");
                    int idPersonal = newUTIL.preguntaNumero("ID del personal:");

                    if (centro.desasignarPersonalAUnidad(idUnidad, idPersonal)) {
                        System.out.println("Personal quitado de la unidad.");
                    } else {
                        System.out.println("No se ha podido quitar el personal.");
                    }
                    break;
                }

                case 5: {
                    int idUnidad = newUTIL.preguntaNumero("ID de la unidad:");
                    boolean disponible = newUTIL.preguntaFlag("¿Disponible?");
                    if (centro.cambiarDisponibilidadUnidad(idUnidad, disponible)) {
                        System.out.println("Disponibilidad modificada.");
                    } else {
                        System.out.println("No se ha podido modificar la disponibilidad.");
                    }
                    break;
                }

                case 6: {
                    int idUnidad = newUTIL.preguntaNumero("ID de la unidad:");
                    Ubicacion ub = new Ubicacion();
                    ub.pedirDatos();

                    if (centro.cambiarUbicacionUnidad(idUnidad, ub)) {
                        System.out.println("Ubicación modificada.");
                    } else {
                        System.out.println("No se ha podido cambiar la ubicación.");
                    }
                    break;
                }

                case 7: {
                    int idUnidad = newUTIL.preguntaNumero("ID de la unidad:");
                    if (centro.eliminarUnidad(idUnidad)) {
                        System.out.println("Unidad eliminada.");
                    } else {
                        System.out.println("No se ha podido eliminar la unidad.");
                    }
                    break;
                }

                case 8:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 8);
    }

    /**
     * Gestiona el menú de personal de emergencias
     *
     * @param centro Centro de emergencias desde el que se gestiona el personal
     */
    public static void menuPersonalEmergencias(CentroEmergencias centro) {
        int opc;
        do {
            System.out.println("MENÚ PERSONAL DE EMERGENCIAS");
            System.out.println("1. Dar de alta personal");
            System.out.println("2. Listar personal");
            System.out.println("3. Cambiar disponibilidad");
            System.out.println("4. Eliminar personal");
            System.out.println("5. Volver");

            opc = newUTIL.preguntaNumero("Elige una opción (1-5)");
            switch (opc) {
                case 1:
                    PersonalEmergencias personalEmergencias = new PersonalEmergencias();
                    personalEmergencias.pedirDatos();

                    if (centro.altaPersonalEmergencias(personalEmergencias)) {
                        System.out.println("Personal dado de alta correctamente.");
                    } else {
                        System.out.println("No se ha podido dar de alta el personal.");
                    }
                    break;
                case 2:
                    System.out.println(centro.personalToString());
                    break;
                case 3:
                    int idCambiar = newUTIL.preguntaNumero("Introduce el ID del personal:");
                    String resp = newUTIL.preguntaTexto("¿Está disponible? (s/n): ");
                    boolean nuevaDisponibilidad = resp.equalsIgnoreCase("s");

                    if (centro.cambiarDisponibilidadPersonal(idCambiar, nuevaDisponibilidad)) {
                        System.out.println("Disponibilidad modificada correctamente.");
                    } else {
                        System.out.println("No existe personal con ese ID.");
                    }
                    break;
                case 4:
                    int idEliminar = newUTIL.preguntaNumero("Introduce el ID del personal a eliminar:");

                    if (centro.eliminarPersonal(idEliminar)) {
                        System.out.println("Personal eliminado correctamente.");
                    } else {
                        System.out.println("No existe personal con ese ID.");
                    }
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal");
                    break;
                default:
                    System.out.println("Opción no valida");
            }
        } while (opc != 5);
    }

    /**
     * Gestiona el menú de consultas e informes
     *
     * @param centro Centro de emergencias del que se solicita información
     */
    private static void menuInformes(CentroEmergencias centro) {

        String[] opciones = {
                "Ver incidentes por estado",
                "Incidente con mayor tiempo de resolución",
                "Media por tipo de incidente",
                "Ver logs",
                "Resetear logs",
                "Volver"
        };

        int opc;

        do {
            opc = newUTIL.menu("=== INFORMES ===", opciones);

            switch (opc) {

                case 1:
                    Incidente.EstadoIncidente estado =
                            newUTIL.preguntaEnum("Selecciona estado:",
                                    Incidente.EstadoIncidente.class);

                    System.out.println(centro.getIncidentesXEstado(estado));
                    break;

                case 2:
                    Incidente masLargo = centro.getIncidenteMasLargo();

                    if (masLargo != null)
                        System.out.println(masLargo);
                    else
                        System.out.println("No hay incidentes cerrados.");
                    break;

                case 3:
                    System.out.println(centro.getMediasPorTipoIncidente());
                    break;

                case 4:
                    int num = newUTIL.preguntaNumero("¿Cuántos logs quieres visualizar?");
                    String logs = centro.getInfoLogs(num);

                    if (logs == null)
                        System.out.println("No hay logs.");
                    else
                        System.out.println(logs);
                    break;

                case 5:
                    centro.resetearLogs();
                    System.out.println("Logs reseteados.");
                    break;

            }

        } while (opc != 0);
    }


    /**
     * Prepara un menú con las opciones del menú principal
     * Inicializa un objeto CentroEmergencias desde el que llamaremos a la funcionalidad
     * principal del programa.
     *
     * @param args
     */
    public static void main(String[] args) {

        CentroEmergencias centroEmergencias = new CentroEmergencias();
        int opc;
        do {
            System.out.println("MENÚ PRINCIPAL");
            System.out.println("1. Gestión de personal de emergencias");
            System.out.println("2. Gestión de unidades");
            System.out.println("3. Gestión de incidentes");
            System.out.println("4. Consultas e informes");
            System.out.println("5. Salir");

            opc = newUTIL.preguntaNumero("Elige una opción (1-5)");

            switch (opc) {
                case 1:
                    menuPersonalEmergencias(centroEmergencias);
                    break;
                case 2:
                    menuUnidades(centroEmergencias);
                    break;
                case 3:
                    menuIncidentes(centroEmergencias);
                    break;
                case 4:
                    menuInformes(centroEmergencias);
                    break;
                case 5:
                    System.out.println("Saliendo del menú");
                    break;
                default:
                    System.out.println("Opción no valida");
            }
        } while (opc != 5);

    }
}

