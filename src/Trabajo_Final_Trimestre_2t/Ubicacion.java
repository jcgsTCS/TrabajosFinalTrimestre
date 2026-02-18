package Trabajo_Final_Trimestre_2t;

public class Ubicacion {
    private String poblacion;
    private String direccion;

    /**
     * Constructor por defecto
     */
    public Ubicacion() {
    }

    /**
     * Pregunta al usuario la dirección y la población y lo guarda en los atributos de la clase
     */
    public void pedirDatos() {
        direccion = newUTIL.preguntaTexto("Introduce la dirección:");
        poblacion = newUTIL.preguntaTexto("Introduce la población:");
    }

    /**
     * Transforma la información de la ubicación en un string
     *
     * @return String con la información de la ubicación
     */
    public String ubicacionToString() {

        return direccion + " (" + poblacion + ")";
    }

}
