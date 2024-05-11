package Proyecto;

interface Vehiculo {
    String obtenerPlaca();
    String obtenerModelo();
    String obtenerPropietario();
    String obtenerTipoVehiculo();
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
    public MotoClasica(String placa, String modelo, String propietario, String tipoVehiculo) {
        super(placa, modelo, propietario, tipoVehiculo);
    }
}

class MotoHibrida extends VehiculoBase {
    public MotoHibrida(String placa, String modelo, String propietario, String tipoVehiculo) {
        super(placa, modelo, propietario, tipoVehiculo);
    }
}

class Carro extends VehiculoBase {
    public Carro(String placa, String modelo, String propietario, String tipoVehiculo) {
        super(placa, modelo, propietario, tipoVehiculo);
    }
}
