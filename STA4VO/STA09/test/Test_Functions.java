package test;

import math.All_Functions;

public class Test_Functions {

    public static void main(String[] args) {
        All_Functions fun = new All_Functions();

        int[] arr = new int[10];

        arr = fun.first_one();

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }

}
