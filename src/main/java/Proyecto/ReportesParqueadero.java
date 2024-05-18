package Proyecto;

import java.util.ArrayList;
import java.util.List;

public class ReportesParqueadero {
    private final List<Registro> registros = new ArrayList<>();

    public void agregarRegistro(int fila, int columna, Vehiculo vehiculo) {
        registros.add(new Registro(fila, columna, vehiculo));
    }

    public List<Registro> obtenerRegistros() {
        return registros;
    }

    public static class Registro {
        private final int fila;
        private final int columna;
        private final Vehiculo vehiculo;

        public Registro(int fila, int columna, Vehiculo vehiculo) {
            this.fila = fila;
            this.columna = columna;
            this.vehiculo = vehiculo;
        }

        public int getFila() {
            return fila;
        }

        public int getColumna() {
            return columna;
        }

        public Vehiculo getVehiculo() {
            return vehiculo;
        }
    }
}
