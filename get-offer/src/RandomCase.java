import java.util.Random;

public class RandomCase {

    public static int[] getRandomIntArr(int size, int min, int max) {

        int[] arr = new int[(int) ((size + 1) * Math.random())];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = min + (int) ((max - min + 1) * Math.random());
        }
        return arr;
    }

}
