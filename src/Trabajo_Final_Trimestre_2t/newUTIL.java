package Trabajo_Final_Trimestre_2t;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;



public class newUTIL {

        /**
         * Pregunta un número entero haciendo la pregunta que se indica
         * @param mensaje Pregunta que se muestra al usuario
         * @return Número entero introducido por el usuario
         */
        public static int preguntaNumero(String mensaje)
        {
            do {
                try {
                    Scanner s = new Scanner(System.in);
                    System.out.println(mensaje);
                    return Integer.parseInt(s.nextLine());
                } catch (NumberFormatException e) {
                    System.err.println("Formato de número incorrecto.");
                }
            }while(true);
        }

        /**
         * Pregunta un número double haciendo la pregunta que se indica
         * @param mensaje Pregunta que se muestra al usuario
         * @return Número double introducido por el usuario
         */
        public static double preguntaDecimal(String mensaje)
        {
            do {
                try {
                    Scanner s = new Scanner(System.in);
                    System.out.println(mensaje);
                    return Double.parseDouble(s.nextLine());
                } catch (NumberFormatException e) {
                    System.err.println("Formato de número incorrecto.");
                }
            }while(true);
        }

        /**
         * Pregunta un texto haciendo la pregunta que se indica
         * @param mensaje Pregunta que se muestra al usuario
         * @return Texto introducido por el usuario
         */
        public static String preguntaTexto(String mensaje)
        {
            Scanner s = new Scanner(System.in);
            System.out.println(mensaje);
            return s.nextLine();
        }

        /**
         * Hace la pregunta de sí o no al usuario utilizando el texto indicado
         * @param mensaje Pregunta que se muestra al usuario
         * @return True si el usuario escribe S o s. False si escribe N o n
         */
        public static boolean preguntaFlag(String mensaje)
        {
            Scanner s = new Scanner(System.in);
            String opcion;
            do
            {
                System.out.println(mensaje);
                System.out.println("S / N");
                opcion = s.nextLine().toLowerCase();
            }while(!opcion.startsWith("s") && !opcion.startsWith("n"));
            if (opcion.startsWith("s")) return true;
            else return false;
        }

        /**
         * Pregunta una fecha por consola
         * @param mensaje Mensaje que se da al usuario
         * @return LocalDate inicializado con los valores indicados por el usuario
         */
        public static LocalDate preguntaFecha(String mensaje)
        {
            Scanner s = new Scanner(System.in);
            System.out.println(mensaje);
            int dia = newUTIL.preguntaNumero("Día:");
            int mes = newUTIL.preguntaNumero("Mes:");
            int anyo = newUTIL.preguntaNumero("Año:");
            return LocalDate.of(anyo, mes, dia);
        }

        /**
         * Pregunta una fecha por consola
         * @param mensaje Mensaje que se da al usuario
         * @return LocalDateTime inicializado con los valores indicados por el usuario
         */
        public static LocalDateTime preguntaFechaHora(String mensaje)
        {
            Scanner s = new Scanner(System.in);
            System.out.println(mensaje);
            int dia = newUTIL.preguntaNumero("Día:");
            int mes = newUTIL.preguntaNumero("Mes:");
            int anyo = newUTIL.preguntaNumero("Año:");
            int hora = newUTIL.preguntaNumero("Hora:");
            int minuto = newUTIL.preguntaNumero("Minuto:");
            return LocalDateTime.of(anyo, mes, dia, hora, minuto);
        }

        /**
         * Pregunta al usuario entre las diferentes alternativas dadas por un enum
         * @param <T> Enum
         * @param mensaje Pregunta que se hace al usuario
         * @param enumClass Enum sobre el que se pregunta
         * @return Opción del enum escogida por el usuario
         */
        public static <T extends Enum<T>> T preguntaEnum(String mensaje, Class<T> enumClass) {
            T[] enumValues = enumClass.getEnumConstants();
            int opc;
            do {
                for (int i = 0; i < enumValues.length; i++) {
                    System.out.println((i + 1) + ". " + enumValues[i]);
                }
                opc = newUTIL.preguntaNumero(mensaje);
            } while (opc < 1 || opc > enumValues.length);
            return enumValues[opc - 1];
        }

        /**
         * Muestra al usuario las opciones de un menú y le pide escoger una opción
         * @param titulo Título del menú
         * @param opciones Opciones del menú
         * @return La opción escogida. En caso de escoger la opción de Salir o Volver se retorna un 0.
         */
        public static int menu(String titulo, String[] opciones)
        {
            System.out.println(titulo);
            return menu(opciones);
        }

        /**
         * Muestra al usuario las opciones de un menú y le pide escoger una opción
         * @param opciones Opciones del menú
         * @return La opción escogida. En caso de escoger la opción de Salir o Volver se retorna un 0.
         */
        public static int menu(String[] opciones)
        {
            int opc = -1;
            do
            {
                for (int i = 0; i < opciones.length; i++) {
                    System.out.println((i+1)+". "+opciones[i]);
                }
                opc = preguntaNumero("Escoge: ");
            }while(opc<1 || opc>opciones.length);
            if (opc==opciones.length)
                opc = 0;
            return opc;
        }

        /**
         * Genera un número aleatorio entre un número mínimo y máximo incluidos.
         * @param min Límite inferior
         * @param max Límite superior
         * @return El número generado
         */
        public static int numRandom(int min, int max) {
            return (int) (Math.random() * (max - min + 1)) + min;
        }

        /**
         * Retorna un String con la representación de un LocalDateTime
         * @param dateTime Fecha/hora que se quiere mostrar
         * @return String con la información
         */
        public static String dateTimeToString(LocalDateTime dateTime) {
            String str = dateTime.getDayOfMonth()+"/"+dateTime.getMonthValue()+"/"+dateTime.getYear()+" - "+
                    dateTime.getHour()+":"+dateTime.getMinute();
            return str;
        }

        /**
         * Retorna un String con la representación de un LocalDate
         * @param date Fecha que se quiere mostrar
         * @return String con la información
         */
        public static String dateToString(LocalDate date) {
            String str = date.getDayOfMonth()+"/"+date.getMonthValue()+"/"+date.getYear();
            return str;
        }

    /**
     * Pregunta un número entero haciendo la pregunta que se indica.
     * Fuerza a que el número esté entre el mínimo y el máximo indicado (incluidos)
     * @param mensaje Pregunta que se muestra al usuario
     * @param min Valor mínimo que puede tomar el número (incluido).
     * @param max Valor mínimo que puede tomar el número (incluido)
     * @return Número entero introducido por el usuario
     */
    public static int preguntaNumero(String mensaje, int min, int max)
    {
        do {
            int num = preguntaNumero(mensaje);
            if (num<min || num>max) {
                System.err.println("El número sobrepasa los límites");
                continue;
            }
            return num;
        }while(true);
    }

    }


