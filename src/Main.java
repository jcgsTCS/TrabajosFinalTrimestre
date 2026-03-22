import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== MENÚ PRINCIPAL ===");
            System.out.println("1. Ver opción 1");
            System.out.println("2. Ver opción 2");
            System.out.println("3. Ver opción 3");
            System.out.println("0. Salir");
            System.out.print("Escoge una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Has seleccionado la opción 1.");
                    break;
                case 2:
                    System.out.println("Has seleccionado la opción 2.");
                    break;
                case 3:
                    System.out.println("Has seleccionado la opción 3.");
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

            System.out.println();
        } while (opcion != 0);

        sc.close();
    }
}