package com.example;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DesafioAlgoritmoTime {
    public static void main(String[] args) {

        String[] names = creatArrayFromFile();
        String[] bubbleArray = names.clone();
        String[] insertionArray = names.clone();
        String[] quickArray = names.clone();



        Instant bubbleStart = Instant.now();
        bubbleSort(bubbleArray);
        Instant bubbleEnd = Instant.now();
        long timeBubble = Duration.between(bubbleStart, bubbleEnd).toMillis();

        Instant insertionStart = Instant.now();
        insertionSort(insertionArray);
        Instant insertionEnd = Instant.now();
        long timeInsertion = Duration.between(insertionStart, insertionEnd).toMillis();

        Instant quickStart = Instant.now();
        quickSort(quickArray);
        Instant quickEnd = Instant.now();
        long timeQuick = Duration.between(quickStart, quickEnd).toMillis();

        System.out.printf("Insertion Sort: %dms\n", timeInsertion);
        System.out.printf("Bubble Sort: %dms\n", timeBubble);
        System.out.printf("Quick Sort: %dms\n", timeQuick);

        
    }

    private static String[] creatArrayFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> inputs = new ArrayList<>();

        try {
            File jsonFile = new File("input.json");
            inputs = objectMapper.readValue(jsonFile, new TypeReference<List<String>>() {});

            
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] names = new String[inputs.size()];
        names = inputs.toArray(names);
        return names;
    }

    public static <T extends Comparable<T>> T[] bubbleSort(T[] nums) {
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            boolean swapped = false;
            for (int j = 0; j < N - 1 - i; j++) {
                if (nums[j].compareTo(nums[j + 1]) > 0) {
                    T temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return nums;
    }

    public static <T extends Comparable<T>> T[] insertionSort(T[] array) {
        int N = array.length;
        for (int i = 1; i < N; i++) {
            T aux = array[i];
            int j = i - 1;
            while (j >= 0 && array[j].compareTo(aux) > 0) {
                array[j + 1] = array[j];
                j -= 1;
            }
            array[j + 1] = aux;
        }
        return array;
    }

    public static <T extends Comparable<T>> T[] quickSort(T[] nums) {
        quickSortTailRecursive(nums, 0, nums.length - 1);
        return nums;
    }

    private static <T extends Comparable<T>> void quickSortTailRecursive(T[] nums, int left, int right) {
        if (left < right) {
            int pivot = partition(nums, left, right);
            quickSortTailRecursive(nums, left, pivot - 1);
            quickSortTailRecursive(nums, pivot + 1, right);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] nums, int left, int right) {
        T pivot = nums[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (nums[j].compareTo(pivot) <= 0) {
                swap(nums, j, i);
                i++;
            }
        }
        swap(nums, i, right);
        return i;
    }

    private static <T extends Comparable<T>> void swap(T[] nums, int a, int b) {
        T aux = nums[a];
        nums[a] = nums[b];
        nums[b] = aux;
    }

}
