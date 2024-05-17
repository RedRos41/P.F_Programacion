package Proyecto;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

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

    public void agregarVehiculo(Vehiculo vehiculo, GridPane gridPane) {
        for (int i = 0; i < parqueadero.length; i++) {
            for (int j = 0; j < parqueadero[i].length; j++) {
                if (parqueadero[i][j] == null) {
                    parqueadero[i][j] = vehiculo;
                    // Actualizamos la interfaz gráfica
                    actualizarInterfaz(gridPane);
                    return;
                }
            }
        }
    }

    public void actualizarInterfaz(GridPane gridPane) {
        gridPane.getChildren().clear();

        for (int i = 0; i < parqueadero.length; i++) {
            for (int j = 0; j < parqueadero[i].length; j++) {
                Label label = getLabel(j, parqueadero[i]);
                gridPane.add(label, j, i);
            }
        }
    }

    private static Label getLabel(int j, Vehiculo[] parqueadero) {
        Vehiculo vehiculo = parqueadero[j];
        Label label = new Label();
        if (vehiculo != null) {
            StringBuilder labelText = new StringBuilder();
            labelText.append("Tipo: ").append(vehiculo.obtenerTipoVehiculo()).append("\n")
                    .append("Placa: ").append(vehiculo.obtenerPlaca()).append("\n")
                    .append("Modelo: ").append(vehiculo.obtenerModelo()).append("\n")
                    .append("Propietario: ").append(vehiculo.obtenerPropietario()).append("\n");
            // Si es una moto, agregar la velocidad máxima
            if (vehiculo instanceof MotoClasica) {
                labelText.append("Velocidad Máxima: ").append(((MotoClasica) vehiculo).obtenerVelocidadMaxima()).append("\n");
            } else if (vehiculo instanceof MotoHibrida) {
                labelText.append("Velocidad Máxima: ").append(((MotoHibrida) vehiculo).obtenerVelocidadMaxima()).append("\n");
            }
            label.setText(labelText.toString());
        } else {
            label.setText("Cupo");
        }
        return label;
    }

    public boolean verificarPuestoDisponible() {
        for (Vehiculo[] vehiculos : parqueadero) {
            for (Vehiculo vehiculo : vehiculos) {
                if (vehiculo == null) {
                    return true;
                }
            }
        }
        return false;
    }
}
