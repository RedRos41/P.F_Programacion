package Proyecto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReportesTarifaTest {

    @Test
    public void testMostrarReporteDiario() {
        // Prueba con una fecha existente
        LocalDate fechaExistente = LocalDate.now();
        assertDoesNotThrow(() -> ReportesTarifa.mostrarReporteDiario(fechaExistente));

        // Prueba con una fecha no existente (fuera de tus datos)
        LocalDate fechaNoExistente = LocalDate.of(2022, 1, 1);
        assertDoesNotThrow(() -> ReportesTarifa.mostrarReporteDiario(fechaNoExistente));
    }

    @Test
    public void testMostrarReporteMensual() {
        // Prueba con un mes existente
        String mesExistente = "Enero";
        assertDoesNotThrow(() -> ReportesTarifa.mostrarReporteMensual(mesExistente));

        // Prueba con un mes no existente (fuera de tus datos)
        String mesNoExistente = "Febrero";
        assertDoesNotThrow(() -> ReportesTarifa.mostrarReporteMensual(mesNoExistente));
    }
}
