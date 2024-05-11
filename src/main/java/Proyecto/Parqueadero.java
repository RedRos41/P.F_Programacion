package Proyecto;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class Parqueadero extends Application {

    @Override
    public void start(Stage primaryStage) {
        Vehiculo[][] parqueadero = new Vehiculo[2][2];

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        ChoiceBox<String> tipoVehiculoChoiceBox = new ChoiceBox<>();
        tipoVehiculoChoiceBox.getItems().addAll("Carro", "Moto Clásica", "Moto Híbrida");
        tipoVehiculoChoiceBox.setValue("Carro"); // Valor por defecto

        TextField placaField = new TextField();
        placaField.setPromptText("Placa");

        TextField modeloField = new TextField();
        modeloField.setPromptText("Modelo");

        TextField propietarioField = new TextField();
        propietarioField.setPromptText("Propietario");

        Button addButton = new Button("Agregar Vehículo");
        addButton.setOnAction(event -> {
            String tipoVehiculo = tipoVehiculoChoiceBox.getValue();

            String placa = placaField.getText();
            String modelo = modeloField.getText();
            String propietario = propietarioField.getText();

            Vehiculo vehiculo = switch (tipoVehiculo) {
                case "Carro" -> new Carro(placa, modelo, propietario, tipoVehiculo);
                case "Moto Clásica" -> new MotoClasica(placa, modelo, propietario, tipoVehiculo);
                case "Moto Híbrida" -> new MotoHibrida(placa, modelo, propietario, tipoVehiculo);
                default -> null;
            };

            for (int i = 0; i < parqueadero.length; i++) {
                for (int j = 0; j < parqueadero[i].length; j++) {
                    if (parqueadero[i][j] == null) {
                        parqueadero[i][j] = vehiculo;
                        // Actualizamos la interfaz gráfica
                        actualizarInterfaz(parqueadero, gridPane);
                        return;
                    }
                }
            }
            System.out.println("El parqueadero está lleno.");
        });

        gridPane.add(new Label("Tipo de Vehículo:"), 0, 0);
        gridPane.add(tipoVehiculoChoiceBox, 1, 0);
        gridPane.add(new Label("Placa:"), 0, 1);
        gridPane.add(placaField, 1, 1);
        gridPane.add(new Label("Modelo:"), 0, 2);
        gridPane.add(modeloField, 1, 2);
        gridPane.add(new Label("Propietario:"), 0, 3);
        gridPane.add(propietarioField, 1, 3);
        gridPane.add(addButton, 0, 4, 2, 1);

        Scene scene = new Scene(gridPane, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Parqueadero");
        primaryStage.show();
    }

    private void actualizarInterfaz(Vehiculo[][] parqueadero, GridPane gridPane) {
        gridPane.getChildren().clear();

        for (int i = 0; i < parqueadero.length; i++) {
            for (int j = 0; j < parqueadero[i].length; j++) {
                Label label = getLabel(j, parqueadero[i]);
                gridPane.add(label, j, i);
            }
        }
    }

    private static @NotNull Label getLabel(int j, Vehiculo[] parqueadero) {
        Vehiculo vehiculo = parqueadero[j];
        Label label = new Label();
        if (vehiculo != null) {
            label.setText("Tipo: " + vehiculo.obtenerTipoVehiculo() + "\n" +
                    "Placa: " + vehiculo.obtenerPlaca() + "\n" +
                    "Modelo: " + vehiculo.obtenerModelo() + "\n" +
                    "Propietario: " + vehiculo.obtenerPropietario());
        } else {
            label.setText("Parqueadero Vacío");
        }
        return label;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
