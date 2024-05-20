package Proyecto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParqueaderoTest {

    @Test
    public void testAgregarVehiculoCompleto() {
        Parqueadero parqueadero = new Parqueadero();
        Vehiculo carro = parqueadero.crearVehiculo("Carro", "ABC123", 2022, "Propietario", 180);
        int[] posicion = parqueadero.agregarVehiculo(carro);

        assertNotNull(posicion);
        assertEquals(carro, parqueadero.obtenerParqueadero()[posicion[0]][posicion[1]]);
    }

    @Test
    public void testAgregarVehiculoNull() {
        Parqueadero parqueadero = new Parqueadero();
        int[] posicion = parqueadero.agregarVehiculo(null);

        assertNull(posicion);
    }

    @Test
    public void testVerificarPuestoDisponible() {
        Parqueadero parqueadero = new Parqueadero();
        assertTrue(parqueadero.verificarPuestoDisponible());

        Vehiculo carro = parqueadero.crearVehiculo("Carro", "ABC123", 2022, "Propietario", 180);
        parqueadero.agregarVehiculo(carro);

        assertTrue(parqueadero.verificarPuestoDisponible());
    }

    @Test
    public void testVerificarPuestoOcupado() {
        Parqueadero parqueadero = new Parqueadero();
        assertFalse(parqueadero.verificarPuestoOcupado(0, 0));

        Vehiculo carro = parqueadero.crearVehiculo("Carro", "ABC123", 2022, "Propietario", 180);
        parqueadero.agregarVehiculo(carro);

        assertTrue(parqueadero.verificarPuestoOcupado(0, 0));
    }

    @Test
    public void testVerificarPuestoOcupadoFueraDeLimites() {
        Parqueadero parqueadero = new Parqueadero();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> parqueadero.verificarPuestoOcupado(10, 10));
    }

    @Test
    public void testEliminarVehiculo() {
        Parqueadero parqueadero = new Parqueadero();
        Vehiculo carro = parqueadero.crearVehiculo("Carro", "ABC123", 2022, "Propietario", 180);
        parqueadero.agregarVehiculo(carro);

        assertTrue(parqueadero.eliminarVehiculo(0, 0));
        assertNull(parqueadero.obtenerParqueadero()[0][0]);
    }

    @Test
    public void testEliminarVehiculoFueraDeLimites() {
        Parqueadero parqueadero = new Parqueadero();
        assertFalse(parqueadero.eliminarVehiculo(10, 10));
    }
}
