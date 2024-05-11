package Proyecto;

interface Vehiculo {
    String obtenerPlaca();
    String obtenerModelo();
    String obtenerPropietario();
    String obtenerTipoVehiculo();
    int obtenerVelocidadMaxima(); // Nuevo método
}

abstract class VehiculoBase implements Vehiculo {
    private final String placa;
    private final String modelo;
    private final String propietario;
    private final String tipoVehiculo;

    public VehiculoBase(String placa, String modelo, String propietario, String tipoVehiculo) {
        this.placa = placa;
        this.modelo = modelo;
        this.propietario = propietario;
        this.tipoVehiculo = tipoVehiculo;
    }

    @Override
    public String obtenerPlaca() {
        return placa;
    }

    @Override
    public String obtenerModelo() {
        return modelo;
    }

    @Override
    public String obtenerPropietario() {
        return propietario;
    }

    @Override
    public String obtenerTipoVehiculo() {
        return tipoVehiculo;
    }
}

class MotoClasica extends VehiculoBase {
    private final int velocidadMaxima; // Nueva propiedad

    public MotoClasica(String placa, String modelo, String propietario, String tipoVehiculo, int velocidadMaxima) {
        super(placa, modelo, propietario, tipoVehiculo);
        this.velocidadMaxima = velocidadMaxima;
    }

    @Override
    public int obtenerVelocidadMaxima() {
        return velocidadMaxima;
    }
}

class MotoHibrida extends VehiculoBase {
    private final int velocidadMaxima; // Nueva propiedad

    public MotoHibrida(String placa, String modelo, String propietario, String tipoVehiculo, int velocidadMaxima) {
        super(placa, modelo, propietario, tipoVehiculo);
        this.velocidadMaxima = velocidadMaxima;
    }

    @Override
    public int obtenerVelocidadMaxima() {
        return velocidadMaxima;
    }
}

class Carro extends VehiculoBase {
    public Carro(String placa, String modelo, String propietario, String tipoVehiculo) {
        super(placa, modelo, propietario, tipoVehiculo);
    }

    @Override
    public int obtenerVelocidadMaxima() {
        return -1; // En el caso de un carro, la velocidad máxima no está definida (-1 indica no definida)
    }
}
