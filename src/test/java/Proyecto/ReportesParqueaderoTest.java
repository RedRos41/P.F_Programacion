package Proyecto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReportesParqueaderoTest {

    @Test
    public void testAgregarRegistro() {
        // Crear un vehículo simulado
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

        // Crear una instancia de ReportesParqueadero
        ReportesParqueadero reportesParqueadero = new ReportesParqueadero();

        // Agregar un registro
        reportesParqueadero.agregarRegistro(0, 0, vehiculoSimulado);

        // Obtener los registros y verificar que se agregó correctamente
        assertEquals(1, reportesParqueadero.obtenerRegistros().size());
        ReportesParqueadero.Registro registro = reportesParqueadero.obtenerRegistros().get(0);
        assertEquals(0, registro.getFila());
        assertEquals(0, registro.getColumna());
        assertEquals("ABC123", registro.getVehiculo().obtenerPlaca());
        assertEquals(2022, registro.getVehiculo().obtenerModelo());
        assertEquals("Propietario", registro.getVehiculo().obtenerPropietario());
        assertEquals("Carro", registro.getVehiculo().obtenerTipoVehiculo());
        assertEquals(180, registro.getVehiculo().obtenerVelocidadMaxima());
    }
}
