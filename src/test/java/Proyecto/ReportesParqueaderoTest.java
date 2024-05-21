package Proyecto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReportesParqueaderoTest {

    @Test
    public void testAgregarRegistro() {
        Vehiculo vehiculoSimulado = new Vehiculo() {
            @Override
            public String obtenerPlaca() {
                return "ABC123";
            }

            @Override
            public int obtenerModelo() {
                return 2022;
            }

            @Override
            public String obtenerPropietario() {
                return "Propietario";
            }

            @Override
            public String obtenerTipoVehiculo() {
                return "Carro";
            }

            @Override
            public int obtenerVelocidadMaxima() {
                return 180;
            }
        };

        ReportesParqueadero reportesParqueadero = new ReportesParqueadero();

        reportesParqueadero.agregarRegistro(0, 0, vehiculoSimulado);

        assertEquals(1, reportesParqueadero.obtenerRegistros().size());
        ReportesParqueadero.Registro registro = reportesParqueadero.obtenerRegistros().getFirst();
        assertEquals(0, registro.getFila());
        assertEquals(0, registro.getColumna());
        assertEquals("ABC123", registro.getVehiculo().obtenerPlaca());
        assertEquals(2022, registro.getVehiculo().obtenerModelo());
        assertEquals("Propietario", registro.getVehiculo().obtenerPropietario());
        assertEquals("Carro", registro.getVehiculo().obtenerTipoVehiculo());
        assertEquals(180, registro.getVehiculo().obtenerVelocidadMaxima());
    }
}
