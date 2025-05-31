package model;

public class SingleThreadQuickSorter {

    private SingleThreadQuickSorter() {
    }

    /**
     * Ordena el arreglo usando Quicksort de forma secuencial.
     * @param values arreglo a ordenar
     * @param begin índice inicial
     * @param end índice final
     * @throws IllegalArgumentException si el arreglo es null
     */
    public static void sort(final int[] values, final int begin, final int end) {
        if (values == null) {
            throw new IllegalArgumentException("El arreglo no puede ser null");
        }
        if (begin < end) { // ----------- CONFIRMAR SI EL ARRAY TIENE 2 O MAS ELEMENTOS Y SIGO -------------
            int pivotIndex = divide(values, begin, end); // ----------- FRACCIONAR ARRAY PARA ENCONTRAR EL PIVOTE -----------
            sort(values, begin, pivotIndex - 1); // --------- ORDENAR MENORES ------------------
            sort(values, pivotIndex + 1, end); // ------------- ORDENAR MAYORES------------------
        }
    }

    private static int divide(final int[] values, final int begin, final int end) {
        int pivotValue = values[end]; // ---------- DEFINO EL PIVOTE ----------------
        int smallerIndex = begin - 1; // -------------- MARCO EL FINAL DEL MENOR AL PIVOTE ------------------

        for (int current = begin; current < end; current++) { // -------------- RECORRO EL ARRAY ------------
            if (values[current] < pivotValue) { // ----------- IF ES MENOR AVANZA A LA SIGUIENTE POSICION -------------
                smallerIndex++;
                swap(values, smallerIndex, current); // ---------- UBICO EL MENOR ------------
            }
        }

        swap(values, smallerIndex + 1, end); // -------------- UBICO EL MAYOR ------------
        return smallerIndex + 1;
    }

    private static void swap(final int[] array, final int idx1, final int idx2) {
        int temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
    }

    /**
     * Método para validar que el arreglo está ordenado de menor a mayor.
     * @param values arreglo a verificar
     * @return true si está ordenado, false en caso contrario
     */
    public static boolean isSorted(final int[] values) {
        for (int i = 1; i < values.length; i++) {
            if (values[i - 1] > values[i]) {
                return false;
            }
        }
        return true;
    }
}
