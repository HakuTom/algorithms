package com.company;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dokgo on 18.11.16.
 */
public class Find {


    public static Result maxCrossing(int[] A, int low, int mid, int high) {
        int left_sum = Integer.MIN_VALUE;
        int right_sum = Integer.MIN_VALUE;
        int temp_sum = 0;
        int left = mid;
        int right = mid + 1;

        for (int i = mid; i >= low; i--) {
            temp_sum += A[i];
            if (temp_sum > left_sum) {
                left = i;
                left_sum = temp_sum;
            }
        }
        temp_sum = 0;
        for (int i = mid + 1; i <= high; i++) {
            temp_sum += A[i];
            if (temp_sum > right_sum) {
                right = i;
                right_sum = temp_sum;
            }
        }
        return new Result(left, right, left_sum + right_sum);
    }

    public static Result maxSubarrayRec(int[] A, int low, int high) {
        if (low == high)
            return new Result(low, high, A[low]);

        int mid = (low + high) / 2;
        Result left = maxSubarrayRec(A, low, mid);
        Result right = maxSubarrayRec(A, mid + 1, high);
        Result cross = maxCrossing(A, low, mid, high);

        if (left.SUM >= right.SUM && left.SUM >= cross.SUM)
            return left;
        if (right.SUM >= left.SUM && right.SUM >= cross.SUM)
            return right;

        return cross;
    }

    public static Result maxSubLinear(int[] A, int low, int high){
        int sum = A[low], temp_sum = 0;
        int left = 0, right = 0;
        int temp_left = 0;
        for (int i = low; i <= high; i++) {
            temp_sum = Math.max(A[i], temp_sum + A[i]);
            if (temp_sum > sum){
                sum = temp_sum;
                right = i;
                left = temp_left;
            }
            if (temp_sum == A[i]) {
                temp_left = i;
            }
        }
        return new Result(left, right, sum);
    }
}

class Result {
    int LEFT;
    int RIGHT;
    int SUM;

    public Result(int l, int r, int s) {
        LEFT = l;
        RIGHT = r;
        SUM = s;
    }

    @Override
    public String toString(){
        return String.format("left: %d, right: %d, sum: %d", LEFT, RIGHT, SUM);
    }
}