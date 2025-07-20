//La interfaz de usuario debe permitir agregar vehículos, listar vehículos, mostrar boletas y buscar
// vehículos cuyo arriendo supere los 7 días.

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        GestionFlota flota = new GestionFlota();
        Scanner scanner = new Scanner(System.in);

        int opcion = -1;
        do {
            System.out.println("Bienvenido al sistema de gestión Drive Quest Rentals\n");
            System.out.println("1. Agregar vehículo");
            System.out.println("2. Listar vehículos");
            System.out.println("3. Mostrar boletas");
            System.out.println("4. Mostrar vehiculos con arriendo mayor a 7 días");
            System.out.println("0. Salir\n");
            System.out.print("Seleccione una opción: ");

            //Por si el usuario ingresa un valor no numerico
            try {
                opcion = scanner.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
                scanner.nextLine(); // Limpiar el buffer
                continue; // Volver al inicio del bucle para pedir la opción nuevamente
            }

                switch (opcion) {
                    case 1:
                        System.out.println("\nIngrese tipo de vehículo:\n1. Carga\n2. Pasajero ");
                        int tipoVehiculo = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer
                        System.out.println("Ingrese patente: ");
                        String patente = scanner.nextLine();
                        System.out.println("Ingrese marca: ");
                        String marca = scanner.nextLine();
                        System.out.println("Ingrese modelo: ");
                        String modelo = scanner.nextLine();
                        System.out.println("Ingrese año: ");
                        String anio = scanner.nextLine();
                        System.out.println("Ingrese precio de arriendo: ");
                        int precioArriendo = scanner.nextInt();
                        System.out.println("Ingrese días de arriendo: ");
                        int diasArriendo = scanner.nextInt();

                        if (tipoVehiculo == 1) {
                            System.out.println("Ingrese capacidad de carga: ");
                            int capacidadCarga = scanner.nextInt();
                            VehiculoCarga vehiculoCarga = new VehiculoCarga(patente, marca, modelo, anio, precioArriendo, diasArriendo, capacidadCarga);
                            flota.agregarVehiculo(vehiculoCarga);
                        } else if (tipoVehiculo == 2) {
                            System.out.println("Ingrese capacidad de pasajeros: ");
                            int capacidadPasajeros = scanner.nextInt();
                            VehiculoPasajero vehiculoPasajero = new VehiculoPasajero(patente, marca, modelo, anio, precioArriendo, diasArriendo, capacidadPasajeros);
                            flota.agregarVehiculo(vehiculoPasajero);
                        } else {
                            System.out.println("Tipo de vehículo no válido.");
                        }
                        break;
                    case 2:
                        flota.listarVehiculos();
                        break;
                    case 3:
                        System.out.println("\nBoletas de vehículos:");
                        if (flota.getFlotaVehiculos().isEmpty()) {
                            System.out.println("No hay vehículos en la flota para mostrar boletas.");
                        } else {
                            System.out.println("-----------------------------");
                            for (Vehiculo vehiculo : flota.getFlotaVehiculos().values()) {
                                if (vehiculo instanceof CalculoBoleta) { // Verifica si el vehículo implementa la interfaz CalculoBoleta
                                    System.out.println("Boleta para vehículo con patente: " + vehiculo.getPatente().toUpperCase());
                                    ((CalculoBoleta) vehiculo).calcularBoleta();
                                }
                                System.out.println("-----------------------------");

                            }
                        }
                        break;
                    case 4:
                        flota.filtrarVehiculosPorArriendo();
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opcion no valida, intente nuevamente.");
                }


        }while (opcion != 0);

    }
}
