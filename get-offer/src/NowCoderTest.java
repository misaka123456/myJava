import model.ListNode;
import model.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;

public class NowCoderTest {

    public static void main(String[] args) throws Exception {

//        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8};
//        int[] arr2 = {2 ,3, 5, 6, 8 ,9 ,10, 13, 23, 45};
//        System.out.println(NowCoder.getKthMinFromTwoArr(arr1, arr2, 3));
//        System.out.println(NowCoder.getKthMinFromTwoArr(arr1, arr2, 5));
//        System.out.println(NowCoder.getKthMinFromTwoArr(arr1, arr2, 8));
//        System.out.println(NowCoder.getKthMinFromTwoArr(arr1, arr2, 10));
//        System.out.println(NowCoder.getKthMinFromTwoArr(arr1, arr2, 15));


//        int[] arr = new int[] {1, 2, 1, 1, 1};
//        System.out.println(NowCoder.getLengthOfPlusArrInSum(arr, 3));

//        int[] arr = new int[] {1, 3, 4, 5, -3, -5, 3, -5, 4, 3, -6, 4, 3, -7, 3};
//        System.out.println(NowCoder.sumEqualKMaxLength(arr, 9));


//        int[] arr = new int[] {4, 3, 4, 5, -3, -5, 3, -5, 4, 3, 6, 4, 3, -7, 3};
//        System.out.println(NowCoder.sumLessKMaxLength(arr, 3));


//        System.out.println(NowCoder.intParseToAZStr(35961));


//        int[][] buildings = {
//                {2, 5, 6},
//                {1, 7, 4},
//                {4, 6, 7},
//                {3, 6, 5},
//                {10, 13, 2},
//                {9, 11, 3},
//                {10, 12, 5},
//                {12, 14, 4}
//        };
//        ArrayList<int[]> arr = NowCoder.getBuilding(buildings);
//        for (int[] a : arr) {
//            System.out.println(Arrays.toString(a));
//        }


//        TreeNode node2 = new TreeNode(2, null, null);
//        TreeNode node4 = new TreeNode(4, null, null);
//        TreeNode node3 = new TreeNode(3, node2, node4);
//        TreeNode node6= new TreeNode(6, null, null);
//        TreeNode node8 = new TreeNode(8, null, null);
//        TreeNode node7 = new TreeNode(7, node6, node8);
//        TreeNode node5 = new TreeNode(5, node3, node7);
//        System.out.println(NowCoder.getRouteMaxSumInTree(node5));


//        TreeNode node2 = new TreeNode(2, null, null);
//        TreeNode node4 = new TreeNode(4, null, null);
//        TreeNode node3 = new TreeNode(3, node2, node4);
//        TreeNode node6= new TreeNode(6, null, null);
//        TreeNode node8 = new TreeNode(8, null, null);
//        TreeNode node7 = new TreeNode(7, node6, node8);
//        TreeNode node5 = new TreeNode(5, node3, node7);
//        System.out.println(NowCoder.getRouteMaxLengthIntree(node5));


        int[][] mapMatrix = {
                {1, -4, 10},
                {3, -2, -1},
                {2, -1, 0},
                {0, 5, -2}
        };
        System.out.println(NowCoder.getMaxRouteLengthOfMap(mapMatrix));
        System.out.println(NowCoder.getMaxRouteLengthLimitOfMap(mapMatrix));

        int[][] mapMatrix1 = {
                {-1, -1, -1, 100},
                {-1, -1, -1, -1},
                {-1, -1, -1, -1},
                {-1, -1, -1, -1},
                {-1, -1, -1, -1},
                {-1, -1, -1, -1},
                {1, 1, 1, 1},
                {0, 5, -2, 2}
        };
        System.out.println(NowCoder.getMaxRouteLengthOfMap(mapMatrix1));
        System.out.println(NowCoder.getMaxRouteLengthLimitOfMap(mapMatrix1));
    }


}
