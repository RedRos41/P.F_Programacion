package Proyecto;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ReportesTarifa {
    public static String mostrarReporteDiario(LocalDate fecha) {
        Stage reportStage = new Stage();
        reportStage.setTitle("Reporte Diario de Recaudación");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        int[] ingresos = Administrador.getIngresosDiarios(fecha);

        Label motoClasicaLabel = new Label("Moto Clásica: $" + ingresos[0]);
        GridPane.setConstraints(motoClasicaLabel, 0, 0);

        Label motoHibridaLabel = new Label("Moto Híbrida: $" + ingresos[1]);
        GridPane.setConstraints(motoHibridaLabel, 0, 1);

        Label carroLabel = new Label("Carro: $" + ingresos[2]);
        GridPane.setConstraints(carroLabel, 0, 2);

        grid.getChildren().addAll(motoClasicaLabel, motoHibridaLabel, carroLabel);

        Scene scene = new Scene(grid, 300, 200);
        reportStage.setScene(scene);
        reportStage.show();
        return null;
    }

    public static String mostrarReporteMensual(String mes) {
        Stage reportStage = new Stage();
        reportStage.setTitle("Reporte Mensual de Recaudación");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        int[] ingresos = Administrador.getIngresosMensuales(mes);

        Label totalLabel = new Label("Total: $" + (ingresos[0] + ingresos[1] + ingresos[2]));
        GridPane.setConstraints(totalLabel, 0, 3);
        grid.getChildren().addAll(totalLabel);

        Scene scene = new Scene(grid, 300, 200);
        reportStage.setScene(scene);
        reportStage.show();
        return mes;
    }
}