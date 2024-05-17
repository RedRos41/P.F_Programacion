package Proyecto;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main1 extends Application {

    private final Parqueadero parqueadero = new Parqueadero();
    private final TextField velocidadMaximaField = new TextField(); // Campo de texto para la velocidad máxima

    @Override
    public void start(Stage primaryStage) {

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

        // Añadir un TextFormatter para permitir solo letras en el campo propietario
        propietarioField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("[a-zA-Z\\s]*")) {
                return change;
            }
            return null;
        }));

        // Añadir un TextFormatter para permitir solo números en el campo modelo
        modeloField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            }
            return null;
        }));

        // Añadir un TextFormatter para permitir solo números en el campo velocidad máxima
        velocidadMaximaField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            }
            return null;
        }));

        velocidadMaximaField.setPromptText("Velocidad Máxima");
        velocidadMaximaField.setDisable(true); // Inicialmente deshabilitado

        tipoVehiculoChoiceBox.setOnAction(event -> {
            String tipoVehiculo = tipoVehiculoChoiceBox.getValue();
            // Deshabilitar campo de texto para velocidad máxima
            velocidadMaximaField.setDisable(!tipoVehiculo.equals("Moto Clásica") && !tipoVehiculo.equals("Moto Híbrida")); // Habilitar campo de texto para velocidad máxima
        });

        Button addButton = new Button("Agregar Vehículo");
        addButton.setOnAction(event -> {
            String tipoVehiculo = tipoVehiculoChoiceBox.getValue();

            String placa = placaField.getText().trim();
            String modeloText = modeloField.getText().trim();
            String propietario = propietarioField.getText().trim();

            if (placa.isEmpty() || modeloText.isEmpty() || propietario.isEmpty() || (!velocidadMaximaField.isDisabled() && velocidadMaximaField.getText().trim().isEmpty())) {
                mostrarAlerta("Entrada incompleta", "Por favor, complete todas las opciones.");
                return;
            }

            int modelo;
            try {
                modelo = Integer.parseInt(modeloText);
            } catch (NumberFormatException e) {
                mostrarAlerta("Entrada inválida", "Por favor, ingrese un modelo válido.");
                return;
            }

            int velocidadMaxima = -1; // Valor predeterminado para velocidad máxima

            if (!velocidadMaximaField.isDisabled()) {
                velocidadMaxima = Integer.parseInt(velocidadMaximaField.getText().trim());
            }

            Vehiculo vehiculo = parqueadero.crearVehiculo(tipoVehiculo, placa, modelo, propietario, velocidadMaxima);

            if (parqueadero.verificarPuestoDisponible()) {
                parqueadero.agregarVehiculo(vehiculo, gridPane);
            } else {
                mostrarAlerta("Parqueadero lleno", "El parqueadero está lleno.");
            }
        });

        gridPane.add(new Label("Tipo de Vehículo:"), 0, 0);
        gridPane.add(tipoVehiculoChoiceBox, 1, 0);
        gridPane.add(new Label("Placa:"), 0, 1);
        gridPane.add(placaField, 1, 1);
        gridPane.add(new Label("Modelo:"), 0, 2);
        gridPane.add(modeloField, 1, 2);
        gridPane.add(new Label("Propietario:"), 0, 3);
        gridPane.add(propietarioField, 1, 3);
        gridPane.add(new Label("Velocidad Máxima:"), 0, 4); // Etiqueta para la velocidad máxima
        gridPane.add(velocidadMaximaField, 1, 4); // Campo de texto para la velocidad máxima
        gridPane.add(addButton, 0, 5, 2, 1);

        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Parqueadero");
        primaryStage.show();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
