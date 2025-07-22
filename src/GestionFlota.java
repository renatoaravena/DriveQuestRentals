import java.util.concurrent.ConcurrentHashMap;

public class GestionFlota {

    private final ConcurrentHashMap<String, Vehiculo> flotaVehiculos = new ConcurrentHashMap<>();

    //Metodo para agregar vehiculos que valide la no duplicidad de patentes al querer agregar un vehiculo, utilizar hashmap
    public synchronized void agregarVehiculo(Vehiculo vehiculo) {
        String patente = vehiculo.getPatente();

        if (flotaVehiculos.containsKey(patente)) {
            System.out.println("Error: Ya existe un vehículo con la patente " + patente);

        }else{
            flotaVehiculos.put(patente, vehiculo);
            //Llamamos al metodo para agregar el vehiculo al archivo CSV
            FileHandler.agregarVehiculosCSV(vehiculo, "vehiculos.csv");

            System.out.println("Vehículo agregado exitosamente: " + patente + "\n");
        }

    }

    //Metodo para listar vehiculos
    public void listarVehiculos() {
        if (flotaVehiculos.isEmpty()) {
            System.out.println("No hay vehículos en la flota.");
        } else {
            System.out.println("-----------------------------");
            for (Vehiculo vehiculo : flotaVehiculos.values()) {
                vehiculo.datosVehiculo();
                System.out.println("-----------------------------");
            }
        }
    }

    //Metodo para buscar vehiculos en donde su arriendo supere los 7 días
    public void filtrarVehiculosPorArriendo(){
        if (flotaVehiculos.isEmpty()) {
            System.out.println("No hay vehículos en la flota.");
        } else {
            boolean encontrado = false;
            System.out.println("-----------------------------");
            for (Vehiculo vehiculo : flotaVehiculos.values()) {
               if (vehiculo.getDiasArriendo() >= 7) {
                   encontrado = true;
                   vehiculo.datosVehiculo();
                   System.out.println("-----------------------------");
               }
           }
              if (!encontrado) {
                System.out.println("No hay vehículos con arriendo mayor a 7 días.");
              }


        }
    }

    public ConcurrentHashMap<String, Vehiculo> getFlotaVehiculos() {
        return flotaVehiculos;
    }
}
