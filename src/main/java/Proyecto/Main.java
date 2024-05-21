package Proyecto;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.List;

public class Main extends Application {
    private final Parqueadero parqueadero = new Parqueadero();
    private final ReportesParqueadero reportes = new ReportesParqueadero();
    private final int filas = 3;
    private final int columnas = 3;
    private final TextField velocidadMaximaField = new TextField();
    private Stage ventanaParqueadero;
    private GridPane gridPaneParqueadero;
    private Label resultadoLabel;

    public static void main(String[] args) {
        Administrador.setTarifas(2000, 5000, 10000);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Parqueadero y Tarifas");

        GridPane gridParqueadero = new GridPane();
        gridParqueadero.setHgap(10);
        gridParqueadero.setVgap(10);
        gridParqueadero.setPadding(new Insets(10));

        ChoiceBox<String> tipoVehiculoChoiceBox = new ChoiceBox<>();
        tipoVehiculoChoiceBox.getItems().addAll("Carro", "Moto Clásica", "Moto Híbrida");
        tipoVehiculoChoiceBox.setValue("Carro");

        TextField placaField = new TextField();
        placaField.setPromptText("Placa");

        TextField modeloField = new TextField();
        modeloField.setPromptText("Modelo");

        TextField propietarioField = new TextField();
        propietarioField.setPromptText("Propietario");

        propietarioField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("[a-zA-Z\\s]*")) {
                return change;
            }
            return null;
        }));

        modeloField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            }
            return null;
        }));

        velocidadMaximaField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            }
            return null;
        }));

        velocidadMaximaField.setPromptText("Velocidad Máxima");
        velocidadMaximaField.setDisable(true);

        tipoVehiculoChoiceBox.setOnAction(event -> {
            String tipoVehiculo = tipoVehiculoChoiceBox.getValue();
            velocidadMaximaField.setDisable(!tipoVehiculo.equals("Moto Clásica") && !tipoVehiculo.equals("Moto Híbrida"));
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

            int velocidadMaxima = -1;
            if (!velocidadMaximaField.isDisabled()) {
                velocidadMaxima = Integer.parseInt(velocidadMaximaField.getText().trim());
            }

            Vehiculo vehiculo = parqueadero.crearVehiculo(tipoVehiculo, placa, modelo, propietario, velocidadMaxima);
            int[] posicion = parqueadero.agregarVehiculo(vehiculo);
            if (posicion != null) {
                reportes.agregarRegistro(posicion[0], posicion[1], vehiculo);
                mostrarVentanaParqueadero();
            } else {
                mostrarAlerta("Parqueadero lleno", "El parqueadero está lleno.");
            }
        });

        TextField filaField = new TextField();
        filaField.setPromptText("Fila");
        filaField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            }
            return null;
        }));

        TextField columnaField = new TextField();
        columnaField.setPromptText("Columna");
        columnaField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            }
            return null;
        }));

        Button checkButton = new Button("Verificar Puesto");
        checkButton.setOnAction(event -> {
            String filaText = filaField.getText().trim();
            String columnaText = columnaField.getText().trim();

            if (filaText.isEmpty() || columnaText.isEmpty()) {
                mostrarAlerta("Entrada incompleta", "Por favor, ingrese la fila y la columna.");
                return;
            }

            int fila, columna;
            try {
                fila = Integer.parseInt(filaText);
                columna = Integer.parseInt(columnaText);
            } catch (NumberFormatException e) {
                mostrarAlerta("Entrada inválida", "Por favor, ingrese números válidos para la fila y la columna.");
                return;
            }

            if (fila >= filas || columna >= columnas) {
                mostrarAlerta("Fuera de rango", "La fila y columna deben estar dentro del rango de 0 a " + (filas - 1) + " y 0 a " + (columnas - 1) + " respectivamente.");
                return;
            }

            if (parqueadero.verificarPuestoOcupado(fila, columna)) {
                mostrarAlerta("Puesto ocupado", "El puesto en la fila " + fila + " y columna " + columna + " está ocupado.");
            } else {
                mostrarAlerta("Puesto disponible", "El puesto en la fila " + fila + " y columna " + columna + " está disponible.");
            }
        });

        TextField filaEliminarField = new TextField();
        filaEliminarField.setPromptText("Fila a Eliminar");
        filaEliminarField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            }
            return null;
        }));

        TextField columnaEliminarField = new TextField();
        columnaEliminarField.setPromptText("Columna a Eliminar");
        columnaEliminarField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            }
            return null;
        }));

        Button removeButton = new Button("Eliminar Vehículo");
        removeButton.setOnAction(event -> {
            String filaEliminarText = filaEliminarField.getText().trim();
            String columnaEliminarText = columnaEliminarField.getText().trim();

            if (filaEliminarText.isEmpty() || columnaEliminarText.isEmpty()) {
                mostrarAlerta("Entrada incompleta", "Por favor, ingrese la fila y la columna a eliminar.");
                return;
            }

            int filaEliminar, columnaEliminar;
            try {
                filaEliminar = Integer.parseInt(filaEliminarText);
                columnaEliminar = Integer.parseInt(columnaEliminarText);
            } catch (NumberFormatException e) {
                mostrarAlerta("Entrada inválida", "Por favor, ingrese números válidos para la fila y la columna a eliminar.");
                return;
            }

            if (filaEliminar >= filas || columnaEliminar >= columnas) {
                mostrarAlerta("Fuera de rango", "La fila y columna deben estar dentro del rango de 0 a " + (filas - 1) + " y 0 a " + (columnas - 1) + " respectivamente.");
                return;
            }

            if (parqueadero.eliminarVehiculo(filaEliminar, columnaEliminar)) {
                mostrarAlerta("Vehículo eliminado", "El vehículo en la fila " + filaEliminar + " y columna " + columnaEliminar + " ha sido eliminado.");
                mostrarVentanaParqueadero();
            } else {
                mostrarAlerta("Puesto vacío", "El puesto en la fila " + filaEliminar + " y columna " + columnaEliminar + " ya está vacío.");
            }
        });

        Button showRecordsButton = new Button("Mostrar Registros");
        showRecordsButton.setOnAction(event -> mostrarRegistros());

        gridParqueadero.add(new Label("Tipo de Vehículo:"), 0, 0);
        gridParqueadero.add(tipoVehiculoChoiceBox, 1, 0);
        gridParqueadero.add(new Label("Placa:"), 0, 1);
        gridParqueadero.add(placaField, 1, 1);
        gridParqueadero.add(new Label("Modelo:"), 0, 2);
        gridParqueadero.add(modeloField, 1, 2);
        gridParqueadero.add(new Label("Propietario:"), 0, 3);
        gridParqueadero.add(propietarioField, 1, 3);
        gridParqueadero.add(new Label("Velocidad Máxima:"), 0, 4);
        gridParqueadero.add(velocidadMaximaField, 1, 4);
        gridParqueadero.add(addButton, 0, 5, 2, 1);
        gridParqueadero.add(new Label("Fila:"), 0, 6);
        gridParqueadero.add(filaField, 1, 6);
        gridParqueadero.add(new Label("Columna:"), 0, 7);
        gridParqueadero.add(columnaField, 1, 7);
        gridParqueadero.add(checkButton, 0, 8, 2, 1);
        gridParqueadero.add(new Label("Fila a Eliminar:"), 0, 9);
        gridParqueadero.add(filaEliminarField, 1, 9);
        gridParqueadero.add(new Label("Columna a Eliminar:"), 0, 10);
        gridParqueadero.add(columnaEliminarField, 1, 10);
        gridParqueadero.add(removeButton, 0, 11, 2, 1);
        gridParqueadero.add(showRecordsButton, 0, 12, 2, 1);

        GridPane gridTarifas = new GridPane();
        gridTarifas.setPadding(new Insets(10, 10, 10, 10));
        gridTarifas.setVgap(8);
        gridTarifas.setHgap(10);

        Label tipoVehiculoLabel = new Label("Seleccione el tipo de vehículo:");
        GridPane.setConstraints(tipoVehiculoLabel, 0, 0);

        ComboBox<String> tipoVehiculoComboBoxTarifa = new ComboBox<>();
        tipoVehiculoComboBoxTarifa.getItems().addAll("Moto Clásica", "Moto Híbrida", "Carro");
        tipoVehiculoComboBoxTarifa.setValue("Carro");
        GridPane.setConstraints(tipoVehiculoComboBoxTarifa, 1, 0);

        Label horasLabel = new Label("Ingrese el número de horas:");
        GridPane.setConstraints(horasLabel, 0, 1);

        TextField horasInput = new TextField();
        horasInput.setPromptText("Horas");
        GridPane.setConstraints(horasInput, 1, 1);
        horasInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                horasInput.setText(newValue.replaceAll("\\D", ""));
            }
        });

        Button calcularButton = new Button("Calcular Tarifa");
        GridPane.setConstraints(calcularButton, 1, 2);

        Button reporteDiarioButton = new Button("Mostrar Reporte Diario");
        GridPane.setConstraints(reporteDiarioButton, 1, 3);

        Button reporteMensualButton = new Button("Mostrar Reporte Mensual");
        GridPane.setConstraints(reporteMensualButton, 1, 4);

        resultadoLabel = new Label();
        GridPane.setConstraints(resultadoLabel, 0, 5, 2, 1);

        calcularButton.setOnAction(e -> {
            String tipoVehiculo = tipoVehiculoComboBoxTarifa.getValue();
            String horasTexto = horasInput.getText();
            int horas;
            try {
                horas = Integer.parseInt(horasTexto);
                if (horas <= 0) {
                    showError("El número de horas debe ser mayor a cero.");
                    return;
                }
            } catch (NumberFormatException ex) {
                showError("Por favor, ingrese un número entero para las horas.");
                return;
            }

            int costo = 0;
            if (tipoVehiculo != null) {
                switch (tipoVehiculo) {
                    case "Moto Clásica":
                        costo = horas * Administrador.TARIFA_MOTO_CLASICA;
                        Administrador.IngresoMotoClasica(costo);
                        break;
                    case "Moto Híbrida":
                        costo = horas * Administrador.TARIFA_MOTO_HIBRIDA;
                        Administrador.IngresoMotoHibrida(costo);
                        break;
                    case "Carro":
                        costo = horas * Administrador.TARIFA_CARRO;
                        Administrador.IngresoCarro(costo);
                        break;
                }
                resultadoLabel.setText("El costo total del estacionamiento es: " + costo);
            } else {
                showError("Por favor, seleccione un tipo de vehículo.");
            }
        });

        reporteDiarioButton.setOnAction(e -> {
            LocalDate hoy = LocalDate.now();
            String reporte = ReportesTarifa.mostrarReporteDiario(hoy);
        });

        reporteMensualButton.setOnAction(e -> {
            LocalDate hoy = LocalDate.now();
            String mesActual = hoy.getYear() + "-" + hoy.getMonthValue();
            String reporte = ReportesTarifa.mostrarReporteMensual(mesActual);
        });

        gridTarifas.getChildren().addAll(tipoVehiculoLabel, tipoVehiculoComboBoxTarifa, horasLabel, horasInput, calcularButton, reporteDiarioButton, reporteMensualButton, resultadoLabel);

        VBox mainVBox = new VBox();
        mainVBox.setPadding(new Insets(10));
        mainVBox.setSpacing(20);
        mainVBox.getChildren().addAll(new Label("Gestión de Parqueadero"), gridParqueadero, new Label("Gestión de Tarifas"), gridTarifas);

        Scene scene = new Scene(mainVBox, 500, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sistema de Parqueadero y Tarifas");
        primaryStage.show();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarVentanaParqueadero() {
        if (ventanaParqueadero == null) {
            ventanaParqueadero = new Stage();
            ventanaParqueadero.setTitle("Estado del Parqueadero");

            gridPaneParqueadero = new GridPane();
            gridPaneParqueadero.setHgap(10);
            gridPaneParqueadero.setVgap(10);
            gridPaneParqueadero.setPadding(new Insets(10));

            Scene sceneParqueadero = new Scene(gridPaneParqueadero, 400, 400);
            ventanaParqueadero.setScene(sceneParqueadero);
        }
        actualizarEstadoParqueadero();
        ventanaParqueadero.show();
    }

    private void actualizarEstadoParqueadero() {
        gridPaneParqueadero.getChildren().clear();

        Vehiculo[][] matrizParqueadero = parqueadero.obtenerParqueadero();
        for (int i = 0; i < matrizParqueadero.length; i++) {
            for (int j = 0; j < matrizParqueadero[i].length; j++) {
                Label label = getLabel(j, matrizParqueadero[i]);
                gridPaneParqueadero.add(label, j, i);
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
            if (vehiculo instanceof MotoClasica) {
                labelText.append("Velocidad Máxima: ").append(((MotoClasica) vehiculo).obtenerVelocidadMaxima()).append("\n");
            } else if (vehiculo instanceof MotoHibrida) {
                labelText.append("Velocidad Máxima: ").append(((MotoHibrida) vehiculo).obtenerVelocidadMaxima()).append("\n");
            }
            label.setText(labelText.toString());
        } else {
            label.setText("Disponible");
        }
        return label;
    }

    private void mostrarRegistros() {
        Stage ventanaRegistros = new Stage();
        ventanaRegistros.setTitle("Registros de Ingresos");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        List<ReportesParqueadero.Registro> registros = reportes.obtenerRegistros();
        for (ReportesParqueadero.Registro registro : registros) {
            StringBuilder registroText = new StringBuilder("Fila: " + registro.getFila() + ", Columna: " + registro.getColumna() + ", Vehículo: " +
                    registro.getVehiculo().obtenerTipoVehiculo() + " - " + registro.getVehiculo().obtenerPlaca() + " - " +
                    registro.getVehiculo().obtenerModelo() + " - " + registro.getVehiculo().obtenerPropietario());
            if (registro.getVehiculo() instanceof MotoClasica) {
                registroText.append(" - Velocidad Máxima: ").append(((MotoClasica) registro.getVehiculo()).obtenerVelocidadMaxima());
            } else if (registro.getVehiculo() instanceof MotoHibrida) {
                registroText.append(" - Velocidad Máxima: ").append(((MotoHibrida) registro.getVehiculo()).obtenerVelocidadMaxima());
            }
            Label label = new Label(registroText.toString());
            vbox.getChildren().add(label);
        }
        Scene sceneRegistros = new Scene(vbox, 400, 400);
        ventanaRegistros.setScene(sceneRegistros);
        ventanaRegistros.show();
    }

    private void showError(String message) {
        resultadoLabel.setText(message);
    }
}
