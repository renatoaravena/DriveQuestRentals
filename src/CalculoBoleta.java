public interface CalculoBoleta {

    //Constantes de iva, descuento_carga y descuento_pasajero
    double IVA = 0.19; //19%
    double DESCUENTO_CARGA = 0.07; //7%
    double DESCUENTO_PASAJERO = 0.12; //12%

    //Metodo para calcular la boleta

    void calcularBoleta();

}
