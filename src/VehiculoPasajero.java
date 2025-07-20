public class VehiculoPasajero extends Vehiculo implements CalculoBoleta {

    private int capacidadPasajeros;

    VehiculoPasajero() {
    }

    VehiculoPasajero(String patente, String marca, String modelo, String anio, int precioArriendo, int diasArriendo, int capacidadPasajeros) {
        super(patente, marca, modelo, anio, precioArriendo, diasArriendo);
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    @Override
    public void datosVehiculo() {
        System.out.println("Patente: " + getPatente());
        System.out.println("Marca: " + getMarca());
        System.out.println("Modelo: " + getModelo());
        System.out.println("Año: " + getAnio());
        System.out.println("Precio Arriendo: " + getPrecioArriendo());
        System.out.println("Días de Arriendo: " + getDiasArriendo());
        System.out.println("Capacidad de Pasajeros: " + capacidadPasajeros);
    }

    //Implementacion del metodo calcularBoleta de la interfaz CalculoBoleta
    @Override
    public void calcularBoleta() {
        double total = getPrecioArriendo() * getDiasArriendo();
        System.out.printf("Total arriendo: $%.0f%n", total);
        double descuento = total * DESCUENTO_PASAJERO;
        System.out.printf("Descuento por arriendo de carga(7%%): -$%.0f%n", descuento);
        double iva = (total - descuento) * IVA;
        System.out.printf("IVA (19%%): $%.0f%n", iva);
        double totalFinal = total - descuento + iva;
        System.out.printf("Total a pagar: $%.0f%n", totalFinal);
    }
}
