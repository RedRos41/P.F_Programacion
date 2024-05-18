package Proyecto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Administrador {
    public static int TARIFA_MOTO_CLASICA;
    public static int TARIFA_MOTO_HIBRIDA;
    public static int TARIFA_CARRO;

    private static int ingresoMotoClasica = 0;
    private static int ingresoMotoHibrida = 0;
    private static int ingresoCarro = 0;

    private static Map<LocalDate, int[]> ingresosDiarios = new HashMap<>();
    private static Map<String, int[]> ingresosMensuales = new HashMap<>();

    public static void setTarifas(int motoClasica, int motoHibrida, int carro) {
        TARIFA_MOTO_CLASICA = motoClasica;
        TARIFA_MOTO_HIBRIDA = motoHibrida;
        TARIFA_CARRO = carro;
    }

    public static void IngresoMotoClasica(int ingreso) {
        ingresoMotoClasica += ingreso;
        registrarIngresoDiario(0, ingreso);
    }

    public static void IngresoMotoHibrida(int ingreso) {
        ingresoMotoHibrida += ingreso;
        registrarIngresoDiario(1, ingreso);
    }

    public static void IngresoCarro(int ingreso) {
        ingresoCarro += ingreso;
        registrarIngresoDiario(2, ingreso);
    }

    public static int getIngresoMotoClasica() {
        return ingresoMotoClasica;
    }

    public static int getIngresoMotoHibrida() {
        return ingresoMotoHibrida;
    }

    public static int getIngresoCarro() {
        return ingresoCarro;
    }

    private static void registrarIngresoDiario(int tipoVehiculo, int ingreso) {
        LocalDate hoy = LocalDate.now();
        int[] ingresos = ingresosDiarios.getOrDefault(hoy, new int[3]);
        ingresos[tipoVehiculo] += ingreso;
        ingresosDiarios.put(hoy, ingresos);

        String mes = hoy.getYear() + "-" + hoy.getMonthValue();
        int[] ingresosMes = ingresosMensuales.getOrDefault(mes, new int[3]);
        ingresosMes[tipoVehiculo] += ingreso;
        ingresosMensuales.put(mes, ingresosMes);
    }

    public static int[] getIngresosDiarios(LocalDate fecha) {
        return ingresosDiarios.getOrDefault(fecha, new int[3]);
    }

    public static int[] getIngresosMensuales(String mes) {
        return ingresosMensuales.getOrDefault(mes, new int[3]);
    }
}