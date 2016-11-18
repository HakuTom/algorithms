package com.company;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws Exception {
       /* BufferedReader reader =
                new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("/home/dokgo/Documents/input.txt")
                        , StandardCharsets.UTF_8));


        Map<String, Long> map =
            reader.lines()
                .map(s -> s.replaceAll("[^а-яА-Яa-zA-Z0-9]", " ")
                        .toLowerCase()
                        .split("\\s+"))
                    .flatMap(Stream::of)
                    .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        map
            .entrySet()
            .stream()
            .sorted((b,a) -> a.getValue().compareTo(b.getValue()) == 0
                    ? b.getKey().compareTo(a.getKey())
                    : a.getValue().compareTo(b.getValue())
            )
            .limit(10)
            .forEach(s -> System.out.println(s.getKey()));*/
        int[] arr = {20, -21, 43, -23, -92, 45, -56, -5, 34, -17,
                34, 65, 89, -109, 125, 2, -10, 89, 46, 65, -49,
                3, -45, 34, 76, 32, -76, -2, 3, -45, 44, 34, 67,
                -67, 99, -104, 11, -37, 44, 76, -90, 89, -32, 34,
                88, 56, -6, -89, -90, -34, -56, 23, 29, 2, 6, 9,
                2, -34, -45, 34, 22, -177, 44, 90, -45, -36, 55,
                23, 56, -56, -167, -54, 23, 45, 14, 62, -46, -56,
                -34, 45, 32, 20, -87, 39, 82, 95, -67, -45, 88,
                -36, 21, 18, 16, 81, -102, 99, -45, -67, -45, -76};

        //System.out.println("#\trecursive\tbrute force\tdiff");

//        for (int k = 0; k != 100; k++)
        /*for (int i = 0; i < 100; i++) {
            long start_rec = System.nanoTime();
            FindMaximumSubarray(arr, 0, i);
            long end_rec = System.nanoTime();

            long start_brute = System.nanoTime();
            FindMaxSub(arr, 0, i);
            long end_brute = System.nanoTime();

            long rec = end_rec - start_rec;
            long brute = end_brute - start_brute;

            System.out.println(i + 1);
            System.out.println("#: " + (i + 1)
                    + "\t"
                    + rec
                    + "ns\t"
                    + brute
                    + "ns\t"
                    + (rec - brute)
            );


        }*/

        int[] arr2 = FindMaximumSubarray(arr, 0, arr.length - 1);
        Result res = Find.maxSubLinear(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr2));

        System.out.println(res);


    }

    static void bubble_sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; ++i) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    static int[] FindMaxSubLin(int[] A, int low, int high) {
        int left = 0;
        int right = 0;
        int sum = A[low];
        int temp_left = 0;
        int temp_sum = 0;

        for (int i = 0; i <= high; i++) {
            temp_sum = Math.max(A[i], temp_sum + A[i]);
            if (temp_sum > sum) {
                right = i;
                sum = temp_sum;
                left = temp_left;
            }
            if (temp_sum == A[i])
                temp_left = i;
        }
        return new int[]{left, right, sum};
    }

    static int[] FindMaxSub(int[] A, int low, int high) {
        int left = low;
        int right = high;
        int sum = Integer.MIN_VALUE;
        int temp_sum;

        for (int i = low; i < high; i++) {
            temp_sum = 0;
            for (int j = i; j < high; j++) {
                temp_sum += A[j];
                if (temp_sum > sum) {
                    sum = temp_sum;
                    left = i;
                    right = j;
                }
            }
        }
        return new int[]{left, right, sum};
    }

    static int[] FindMaximumSubarray(int[] A, int low, int high) {
        if (high - low < 33) {
            return FindMaxSub(A, low, high);
        }
        int mid = (low + high) / 2;

        int[] left = FindMaximumSubarray(A, low, mid);
        int[] right = FindMaximumSubarray(A, mid + 1, high);
        int[] cross = FindMaxCrossingSubarray(A, low, mid, high);

        if (left[2] >= right[2] && left[2] >= cross[2])
            return left;
        if (right[2] >= left[2] && right[2] >= cross[2])
            return right;

        return cross;
    }

    static int[] FindMaxCrossingSubarray(int[] A, int low, int mid, int high) {
        int left_sum, right_sum;
        left_sum = right_sum = Integer.MIN_VALUE;
        int sum = 0;
        int max_left, max_right;
        max_left = max_right = 0;

        for (int i = mid; i >= low; i--) {
            sum += A[i];
            if (sum > left_sum) {
                left_sum = sum;
                max_left = i;
            }
        }
        sum = 0;
        for (int i = mid + 1; i <= high; i++) {
            sum += A[i];
            if (sum > right_sum) {
                right_sum = sum;
                max_right = i;
            }
        }

        return new int[]{max_left, max_right, left_sum + right_sum};
    }


}


