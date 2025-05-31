package utils;

import java.util.Random;

public class ArraUtils {

    private ArraUtils() {
    }

    public static int[] generateRandomArray(final int size, final int min, final int max) {
        if (size <= 0) {
            throw new IllegalArgumentException("Ingrese un valor mayor que cero para el tamaño del arreglo.");
        }
        if (min > max) {
            throw new IllegalArgumentException("El valor mínimo no puede ser mayor que el máximo.");
        }

        int[] result = new int[size]; // --------- DEFINICION DE VALOR ----------
        for (int index = 0; index < result.length; index++) { // ---------- BUSCO UN NUMERO RANDOM ---------------
            result[index] = getRandomInRange(min, max);
        }
        return result;
    }

    public static void displayArray(final int[] data) { // ---------- MUESTRO EL ARRAY -------------
        System.out.print("Array: ");
        for (int i = 0; i < data.length; i++) {
            if (i < data.length - 1) {
                System.out.print("%d, ".formatted(data[i]));
            } else {
                System.out.print("%d".formatted(data[i]));
            }
        }
        System.out.println();
    }

    private static int getRandomInRange(final int min, final int max) { // -------- BUSCO UN NUMERO RANDOM CON LOS SIGUIENTES PARAMETROS ---------
        return new Random().nextInt(max - min + 1) + min;
    }
}
