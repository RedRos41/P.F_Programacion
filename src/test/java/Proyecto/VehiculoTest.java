package Proyecto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehiculoTest {

    @Test
    public void testMotoClasica() {
        MotoClasica motoClasica = new MotoClasica("DEF456", 2021, "Propietario2", "Moto Clásica", 200);
        assertEquals("DEF456", motoClasica.obtenerPlaca());
        assertEquals(2021, motoClasica.obtenerModelo());
        assertEquals("Propietario2", motoClasica.obtenerPropietario());
        assertEquals("Moto Clásica", motoClasica.obtenerTipoVehiculo());
        assertEquals(200, motoClasica.obtenerVelocidadMaxima());
    }

    @Test
    public void testMotoHibrida() {
        MotoHibrida motoHibrida = new MotoHibrida("GHI789", 2020, "Propietario3", "Moto Híbrida", 180);
        assertEquals("GHI789", motoHibrida.obtenerPlaca());
        assertEquals(2020, motoHibrida.obtenerModelo());
        assertEquals("Propietario3", motoHibrida.obtenerPropietario());
        assertEquals("Moto Híbrida", motoHibrida.obtenerTipoVehiculo());
        assertEquals(180, motoHibrida.obtenerVelocidadMaxima());
    }

    @Test
    public void testCarro() {
        Carro carro = new Carro("JKL012", 2019, "Propietario4", "Carro");
        assertEquals("JKL012", carro.obtenerPlaca());
        assertEquals(2019, carro.obtenerModelo());
        assertEquals("Propietario4", carro.obtenerPropietario());
        assertEquals("Carro", carro.obtenerTipoVehiculo());
        assertEquals(-1, carro.obtenerVelocidadMaxima());
    }
}
