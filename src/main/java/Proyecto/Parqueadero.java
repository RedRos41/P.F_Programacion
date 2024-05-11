package Proyecto;

public class Parqueadero {
    public static void main(String[] args) {
        int[][] parqueadero = {{1,2}, {3,4}};
        for (int[] ints : parqueadero) {
            for (int anInt : ints) {
                System.out.println("Parqueadero: " + anInt);
            }
        }
    }
}
