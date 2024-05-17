package Proyecto;

public class ReportesTarifa {

    public static void ReporteRecaudado() {
        System.out.println("\n" + "Total recaudado por tipo de vehículo:");
        System.out.println("Moto Clásica: $" + Administrador.getIngresoMotoClasica());
        System.out.println("Moto Híbrida: $" + Administrador.getIngresoMotoHibrida());
        System.out.println("Carro: $" + Administrador.getIngresoCarro());
    }
}