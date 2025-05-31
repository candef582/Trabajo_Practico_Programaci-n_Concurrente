package model;

import java.util.concurrent.RecursiveAction;

public class ConcurrentQuickSorter extends RecursiveAction {
    private static final int MAX_PARALLELISM = Runtime.getRuntime().availableProcessors();

    private final int[] data;
    private final int start;
    private final int end;

    public ConcurrentQuickSorter(final int[] data, final int start, final int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (start >= end) { // ----------- SI ES IGUAL O MENOR A 1 = NN --------------
            return;
        }

        int pivotIndex = partition(data, start, end); // -------------- BUSCO PIVOTE ----------------

        ConcurrentQuickSorter leftTask = new ConcurrentQuickSorter(data, start, pivotIndex);
        ConcurrentQuickSorter rightTask = new ConcurrentQuickSorter(data, pivotIndex + 1, end);

        if (getSurplusQueuedTaskCount() < MAX_PARALLELISM) { // ---------- CORRER -----------
            leftTask.fork();
            rightTask.fork();
        } else {
            leftTask.invoke();
            rightTask.invoke();
        }

        leftTask.join();
        rightTask.join();
    }

    private int partition(final int[] array, final int low, final int high) { // ------------ SELECCIONO PIVOTE ------------
        int pivot = array[low + (high - low) / 2];
        int i = low;
        int j = high;

        while (true) {
            while (i <= j && array[i] < pivot) {
                i++;
            }
            while (i <= j && array[j] > pivot) {
                j--;
            }
            if (i >= j) {
                return j;
            }
            swap(array, i, j); // ------- ORDENO ------------
            i++;
            j--;
        }
    }

    private void swap(final int[] array, final int i, final int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
