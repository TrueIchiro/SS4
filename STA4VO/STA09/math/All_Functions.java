package math;

public class All_Functions {

    public All_Functions() {}

    static float varianz = 100;
    static int sum = 200;

    public int[] first_one() {
        int[] arr = new int[10];
        int count = 0;
        int countOfItems = 0;

        for (int i = 0; i <= 200 && countOfItems < 10; i++) {

            for (int j = 200; j >= 0 && countOfItems < 10; j--) {

                float mittelwert = ((i + j) / 2);
                float var = (((i - mittelwert) * (i - mittelwert)) + ((j - mittelwert) * (j - mittelwert)));
                int summe = (i + j);

                if (var == varianz && summe == sum && !(isInArray(arr, i))) {
                    arr[count] = i;
                    arr[9 - count] = j;

                    countOfItems++;
                    count++;

                }

            }

        }

        return arr;
    }

    public boolean isInArray(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return true;
            }
        }

        return false;
    }

}
