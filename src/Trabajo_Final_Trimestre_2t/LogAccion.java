package Trabajo_Final_Trimestre_2t;

import java.time.LocalDateTime;

public class LogAccion {
    private LocalDateTime timestamp;
    private String mensaje;

    /**
     * Constructor por parámetros.
     * La marca temporal se inicializa a la actual
     *
     * @param mensaje Se guarda en el atributo correspondiente.
     */
    public LogAccion(String mensaje) {
        this.timestamp = LocalDateTime.now();
        this.mensaje = mensaje;
    }


    /**
     * Construye un String con la informació del log y lo retorna.
     * Debe contener la marca temporal (usa el método correspondiente de Utils) y el mensaje
     *
     * @return String con la información del log.
     */
    public String logActionToString() {
        return newUTIL.dateTimeToString(timestamp) + " - " + mensaje;
    }
}
