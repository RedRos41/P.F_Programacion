package Proyecto;

public class Administrador {

    public static int TARIFA_MOTO_CLASICA = 2000;
    public static int TARIFA_MOTO_HIBRIDA = 5000;
    public static int TARIFA_CARRO = 10000;

    private static int ingresosMotoClasica = 0;
    private static int ingresosMotoHibrida = 0;
    private static int ingresosCarro = 0;

    public static void setTarifas(int tarifaMotoClasica, int tarifaMotoHibrida, int tarifaCarro) {
        TARIFA_MOTO_CLASICA = tarifaMotoClasica;
        TARIFA_MOTO_HIBRIDA = tarifaMotoHibrida;
        TARIFA_CARRO = tarifaCarro;
    }

    public static void IngresoMotoClasica(int costo) {
        ingresosMotoClasica += costo;
    }

    public static void IngresoMotoHibrida(int costo) {
        ingresosMotoHibrida += costo;
    }

    public static void IngresoCarro(int costo) {
        ingresosCarro += costo;
    }

    public static int getIngresoMotoClasica() {
        return ingresosMotoClasica;
    }

    public static int getIngresoMotoHibrida() {
        return ingresosMotoHibrida;
    }

    public static int getIngresoCarro() {
        return ingresosCarro;
    }
}