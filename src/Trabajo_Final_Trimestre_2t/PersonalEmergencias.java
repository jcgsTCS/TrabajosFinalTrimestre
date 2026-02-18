package Trabajo_Final_Trimestre_2t;

/**
 * A esta clase se le deben añadir:
 * - Getters: el del id, el de disponible
 * - Setters: el de disponible.
 */
public class PersonalEmergencias {
    /**
     * Utilizamos este atributo static para asignar ids de forma automática
     */
    private static int contador = 0;
    private int id;
    private String nombre;
    private Especialidad especialidad;
    private boolean disponible;

    public int getId() {
        return id;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Constructor por defecto.
     * Asigna el id que corresponde.
     * Inicialmente el personal está disponible.
     */
    public PersonalEmergencias() {
        contador++;
        id = contador;
        disponible = true;
    }

    /**
     * Pide por consola el nombre y la especialidad y lo guarda en los
     * atributos correspondientes.
     */
    public void pedirDatos() {
        this.nombre = newUTIL.preguntaTexto("Introduce el nombre:");
        int opc = newUTIL.preguntaNumero("Especialidad (1-SANITARIO, 2-BOMBERO, 3-POLICIA):");

        switch (opc) {
            case 1:
                especialidad = Especialidad.SANITARIO;
                break;
            case 2:
                especialidad = Especialidad.BOMBERO;
                break;
            case 3:
                especialidad = Especialidad.POLICIA;
                break;
            default:
                System.out.println("Opción no valida, se asigna por defecto a sanitario");
                especialidad = Especialidad.SANITARIO;
        }

    }

    /**
     * Construye un string que contiene la información de esta persona
     *
     * @return String construido
     */
    public String personalEmergenciasToString() {
        String estado;
        if (disponible) {
            estado = "Disponible";
        }else{
        estado = "No disponible";}
        return this.id + " | " + this.nombre + " | " + this.especialidad + " | " + estado;
    }


    enum Especialidad {SANITARIO, BOMBERO, POLICIA}
}


