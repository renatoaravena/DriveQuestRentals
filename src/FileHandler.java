import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FileHandler {


    private static final ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void cargarVehiculosCSV(GestionFlota gestionFlota, String archivo) {
        executor.submit(() -> {


            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                boolean primeraLinea = true;

                while ((linea = reader.readLine()) != null) {
                    if (primeraLinea) {
                        primeraLinea = false; // Saltar la primera línea (encabezados)
                        continue;
                    }

                    //Recolectamos los datos de cada línea del archivo CSV
                    String[] datos = linea.split(",");

                    String tipoVehiculo = datos[0].trim();
                    String patente = datos[1].trim();
                    String marca = datos[2].trim();
                    String modelo = datos[3].trim();
                    String anio = datos[4].trim();
                    int precioArriendo = Integer.parseInt(datos[5]);
                    int diasArriendo = Integer.parseInt(datos[6]);
                    int capacidad = Integer.parseInt(datos[7]);

                    //Creamos un objeto Vehiculo dependiendo del tipo de vehiculo
                    if (tipoVehiculo.equalsIgnoreCase("Carga")) {
                        gestionFlota.agregarVehiculo(new VehiculoCarga(patente, marca, modelo, anio,
                                precioArriendo, diasArriendo, capacidad));

                    } else if (tipoVehiculo.equalsIgnoreCase("Pasajero")) {
                        gestionFlota.agregarVehiculo(new VehiculoPasajero(patente, marca, modelo, anio,
                                precioArriendo, diasArriendo, capacidad));
                    }

                }

            } catch (IOException | NumberFormatException e) {
                System.err.println("Error al cargar vehiculos: " + e.getMessage());
            }
        });
    }

    public static void agregarVehiculosCSV(Vehiculo vehiculo, String archivo) {



        executor.submit(() -> {

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {

                String tipoVehiculo = vehiculo instanceof VehiculoCarga ? "carga" : "pasajero";

                int capacidad = vehiculo instanceof VehiculoCarga ? ((VehiculoCarga) vehiculo).getCapacidadCarga()
                        : ((VehiculoPasajero) vehiculo).getCapacidadPasajeros();

                String linea = String.format("%s,%s,%s,%s,%s,%d,%d,%d",
                        tipoVehiculo,
                        vehiculo.getPatente(),
                        vehiculo.getMarca(),
                        vehiculo.getModelo(),
                        vehiculo.getAnio(),
                        vehiculo.getPrecioArriendo(),
                        vehiculo.getDiasArriendo(),
                        capacidad);

                writer.newLine();
                writer.write(linea);


            } catch (IOException e) {
                System.err.println("Error al guardar el vehículo: " + e.getMessage());
            }
        });
    }



    public static void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }


}