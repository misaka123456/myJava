

import java.util.*;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Test {


    public static void main(String[] args) throws Exception {
        System.out.println(distributeCandies(new int[]{100,1,1,1}));

    }

    public  static int distributeCandies(int[] candies) {
        if (candies == null || candies.length == 0) {
            return 0;
        }
        if (candies.length % 2 == 1) {
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<>();

        for (int c : candies) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int countAll = 0;
        int countOdd = 0;
        int countOne = 0;
        for (int key : map.keySet()) {
            int v = map.get(key);
            if (v % 2 == 0) {
                countAll++;
            } else {
                if (v > 1) {
                    countOdd++;
                } else {
                    countOne++;
                }
            }
        }
        return countAll + countOdd + (countOdd >= countOne ? countOne : (countOne - countOdd) / 2);
    }
}







