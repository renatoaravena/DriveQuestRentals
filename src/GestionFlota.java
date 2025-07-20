import java.util.HashMap;

public class GestionFlota {

    private HashMap<String, Vehiculo> flotaVehiculos = new HashMap<>();

    //Metodo para agregar vehiculos que valide la no duplicidad de patentes al querer agregar un vehiculo, utilizar hashmap
    public void agregarVehiculo(Vehiculo vehiculo) {
        String patente = vehiculo.getPatente();

        if (flotaVehiculos.containsKey(patente)) {
            System.out.println("Error: Ya existe un vehículo con la patente " + patente);

        }else{
            flotaVehiculos.put(patente, vehiculo);
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

    public HashMap<String, Vehiculo> getFlotaVehiculos() {
        return flotaVehiculos;
    }
}
