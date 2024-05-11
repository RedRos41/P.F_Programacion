package Proyecto;

// Interfaz para definir las propiedades básicas de un vehículo
interface Vehiculo {
    String obtenerPlaca();
    String obtenerModelo();
    String obtenerPropietario();
}

// Clase base para vehículos
abstract class VehiculoBase implements Vehiculo {
    private String placa;
    private String modelo;
    private String propietario;

    public VehiculoBase(String placa, String modelo, String propietario) {
        this.placa = placa;
        this.modelo = modelo;
        this.propietario = propietario;
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
}

// Clase para representar una moto clásica
class MotoClasica extends VehiculoBase {
    public MotoClasica(String placa, String modelo, String propietario) {
        super(placa, modelo, propietario);
    }
}

// Clase para representar una moto híbrida
class MotoHibrida extends VehiculoBase {
    public MotoHibrida(String placa, String modelo, String propietario) {
        super(placa, modelo, propietario);
    }
}

// Clase para representar un carro
class Carro extends VehiculoBase {
    public Carro(String placa, String modelo, String propietario) {
        super(placa, modelo, propietario);
    }
}
