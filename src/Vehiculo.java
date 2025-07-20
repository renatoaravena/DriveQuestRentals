//Clase padre

public abstract class Vehiculo {

    private String patente, marca, modelo, anio;
    private int precioArriendo, diasArriendo;

    Vehiculo() {
    }

    Vehiculo(String patente, String marca, String modelo, String anio, int precioArriendo, int diasArriendo) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.precioArriendo = precioArriendo;
        this.diasArriendo = diasArriendo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public int getPrecioArriendo() {
        return precioArriendo;
    }

    public void setPrecioArriendo(int precioArriendo) {
        this.precioArriendo = precioArriendo;
    }

    public int getDiasArriendo() {
        return diasArriendo;
    }

    public void setDiasArriendo(int diasArriendo) {
        this.diasArriendo = diasArriendo;
    }


    //Metodo abstracto para que las clases hijas implementen su propia version
    public abstract void datosVehiculo();
}
