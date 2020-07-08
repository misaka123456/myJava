package model;


/**
 * @author xiakai
 * @create 2020-06-18 20:30
 */
class Solution {

    int[] arr;

    public Solution(int[] nums) {
        arr = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return arr;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if (arr.length <= 1) {
            return arr;
        }
        boolean[] used = new boolean[arr.length];
        int[] newArr = new int[arr.length];
        int n = arr.length;
        for (int i = 0; i < arr.length; i++) {

            int index = (int) (Math.random() * n);
            n--;
            for (int k = 0; k < arr.length; k++) {
                if (!used[k]) {
                    if (index == 0) {
                        newArr[i] = arr[k];
                        used[k] = true;
                        break;
                    } else {
                        index--;
                    }
                }
            }

        }
        return newArr;
    }
}



