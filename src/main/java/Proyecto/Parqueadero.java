package Proyecto;

public class Parqueadero {
    private final Vehiculo[][] parqueadero = new Vehiculo[3][3];

    public Vehiculo crearVehiculo(String tipoVehiculo, String placa, int modelo, String propietario, int velocidadMaxima) {
        return switch (tipoVehiculo) {
            case "Carro" -> new Carro(placa, modelo, propietario, tipoVehiculo);
            case "Moto Clásica" -> new MotoClasica(placa, modelo, propietario, tipoVehiculo, velocidadMaxima);
            case "Moto Híbrida" -> new MotoHibrida(placa, modelo, propietario, tipoVehiculo, velocidadMaxima);
            default -> null;
        };
    }

    public int[] agregarVehiculo(Vehiculo vehiculo) {
    if (vehiculo == null) {
        return null;
    }
    for (int i = 0; i < parqueadero.length; i++) {
        for (int j = 0; j < parqueadero[i].length; j++) {
            if (parqueadero[i][j] == null) {
                parqueadero[i][j] = vehiculo;
                return new int[]{i, j};
            }
        }
    }
    return null;
}


    public boolean verificarPuestoDisponible() {
        for (Vehiculo[] fila : parqueadero) {
            for (Vehiculo vehiculo : fila) {
                if (vehiculo == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verificarPuestoOcupado(int fila, int columna) {
        if (fila < 0 || fila >= parqueadero.length || columna < 0 || columna >= parqueadero[fila].length) {
            throw new ArrayIndexOutOfBoundsException("Índices fuera de los límites del parqueadero");
        }
        return parqueadero[fila][columna] != null;
    }

    public boolean eliminarVehiculo(int fila, int columna) {
        if (fila < parqueadero.length && columna < parqueadero[fila].length) {
            if (parqueadero[fila][columna] != null) {
                parqueadero[fila][columna] = null;
                return true;
            }
        }
        return false;
    }

    public Vehiculo[][] obtenerParqueadero() {
        return parqueadero;
    }
}
