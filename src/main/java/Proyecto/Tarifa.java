package Proyecto;

import java.util.Scanner;

public class Tarifa {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Administrador.setTarifas(2000, 5000, 10000);

        int tipoVehiculo = -1;
        while (tipoVehiculo < 1 || tipoVehiculo > 3) {
            System.out.println("Ingrese el tipo de vehículo:" + "\n" + "1: Moto Clásica" + "\n" + "2: Moto Híbrida" + "\n" + "3: Carro");
            if (scanner.hasNextInt()) {
                tipoVehiculo = scanner.nextInt();
                if (tipoVehiculo < 1 || tipoVehiculo > 3) {
                    System.out.println("Tipo de vehículo no válido. Por favor, seleccione uno correcto.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
                scanner.next(); // Limpiar la entrada inválida
            }
        }

        int horas = -1;
        while (horas <= 0) {
            System.out.println("\nIngrese el número de horas de estacionamiento:");
            if (scanner.hasNextInt()) {
                horas = scanner.nextInt();
                if (horas <= 0) {
                    System.out.println("El número de horas debe ser mayor a cero. Por favor, ingrese un valor válido.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
                scanner.next(); // Limpiar la entrada inválida
            }
        }

        int costo = 0;
        switch (tipoVehiculo) {
            case 1:
                costo = horas * Administrador.TARIFA_MOTO_CLASICA;
                Administrador.IngresoMotoClasica(costo);
                break;
            case 2:
                costo = horas * Administrador.TARIFA_MOTO_HIBRIDA;
                Administrador.IngresoMotoHibrida(costo);
                break;
            case 3:
                costo = horas * Administrador.TARIFA_CARRO;
                Administrador.IngresoCarro(costo);
                break;
        }

        if (costo > 0) {
            System.out.println("\nEl costo total del estacionamiento es: " + costo);
        } else {
            System.out.println("El costo debe ser mayor a cero (0)");
        }

        ReportesTarifa.ReporteRecaudado();

        scanner.close();
    }
}
