package test;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

import model.ConcurrentQuickSorter;
import model.SingleThreadQuickSorter;
import utils.ArrayHelper;

public class SortingComparisonApp {

    public static void main(String[] args) {
        double startTime = 0, endTime = 0; // ----------- MEDICION DE TIEMPO -------------
        int[] original = ArrayHelper.generateRandomArray(1000000, 1, 10000); // ------------ RANDOMIZAR NUMEROS POR PARAMETROS ----------
        int[] clone = Arrays.copyOf(original, original.length);

        runSequential(original, startTime, endTime); // ---------------- ORDENO SECUENCIALMENTE ----------------
        runParallel(clone, startTime, endTime);
    }

    public static void runSequential(final int[] input, double start, double end) { // ---------- DEFINICION DEL QUICKSORT ----------
        System.out.println("Ejecutando Quicksort secuencial...");
        start = System.nanoTime(); // ---------- MEDICION DE TIEMPO -----------------
        SingleThreadQuickSorter.sort(input, 0, input.length - 1); // ---------- DEFINICION DEL QUICKSORT ----------
        end = System.nanoTime();
        System.out.println("Demora en microsegundos: %.3f us".formatted((end - start) / 1000));  // ---------- MEDICION DE TIEMPO -----------------

        // Validar si el arreglo quedó ordenado
        if (SingleThreadQuickSorter.isSorted(input)) {
            System.out.println("El arreglo está correctamente ordenado.");
        } else {
            System.out.println("Error: el arreglo NO está ordenado correctamente.");
        }
    }

    public static void runParallel(final int[] input, double start, double end) { // ---------- ORDENAR -----------
        ForkJoinPool executor = new ForkJoinPool();
        ConcurrentQuickSorter task = new ConcurrentQuickSorter(input, 0, input.length - 1); // ---------- DEFINICION DEL QUICKSORT ----------
        System.out.println("Ejecutando Quicksort concurrente...");
        start = System.nanoTime(); // ---------- MEDICION DE TIEMPO -----------------
        executor.invoke(task); // ------ CORRER EN EL POOL --------------
        executor.close();
        end = System.nanoTime(); // ---------- MEDICION DE TIEMPO -----------------
        System.out.println("Demora en microsegundos: %.3f us".formatted((end - start) / 1000));

        // Validar si el arreglo quedó ordenado
        if (isSorted(input)) {
            System.out.println("El arreglo está correctamente ordenado.");
        } else {
            System.out.println("Error: el arreglo NO está ordenado correctamente.");
        }
    }

    /**
     * Método para validar si un arreglo está ordenado.
     * @param arr arreglo a verificar
     * @return true si está ordenado, false en caso contrario
     */
    private static boolean isSorted(final int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }
}
