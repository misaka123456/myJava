public class OfferTest {


    public static void main(String[] args) {

//        int[] numbers = {3, 1, 2, 0, 2, 5, 3};
//        System.out.println(Offer.findOneDuplication(numbers));


//        int[] data = {4, 5, 3, 6, 9, 1, 6, 7, 2, 3, 0};
//        Offer.quickSort(data);
//        System.out.println(Arrays.toString(data));

//
//        System.out.println(Offer.maxProductAfterCutting(0));
//        System.out.println(Offer.maxProductAfterCutting(2));
//        System.out.println(Offer.maxProductAfterCutting(8));
//        System.out.println(Offer.maxProductAfterCutting(18));
//

//        Offer.print1ToMaxOfNDigits(0);
//        Offer.print1ToMaxOfNDigits(1);
//        Offer.print1ToMaxOfNDigits(5);
//
//        int[] data = {4, 5, 3, 6, 9, 1, 6, 7, 2, 3, 0};
//        int[] data = {2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 8, 9, 9, 9, 9, 9 ,9, 9, 9, 9, 9, 9, 9};
//        Offer.reorderOddEven(data);
//        System.out.println(Arrays.toString(data));
//        int[] data = {4, 5, 3, 6, 9, 1, 6, 7, 2, 3, 0};
//        Offer.reorderOddEvenStrict1(data);
//        System.out.println(Arrays.toString(data));
//
//        int[][] matrix = {
//                {1, 2, 3, 4},
//                {5, 6, 7, 8},
//                {9, 10, 11, 12},
//                {13, 14, 15, 16},
//                {13, 14, 15, 16},
//        };
//        System.out.println(Offer.printMatrixClockWisely(matrix));
//
//
//        System.out.println(Offer.permutation(""));
//        System.out.println(Offer.permutation("abc"));
//        System.out.println(Offer.permutationAll("aabc"));
//
//        int[] data = {1, -2, 3, 10, -4, 7, 2, -5};
//        System.out.println(Offer.findGreatestSumOfSubArray(data));
//
//        System.out.println(Offer.getTranslationCount(12258));
//        System.out.println(Offer.getTranslationCount(-1));
//        System.out.println(Offer.getTranslationCount(25));
//        System.out.println(Offer.getTranslationCount(26));
//
//
//        int[][] matrix = {
//                {1, 10, 3, 8},
//                {12, 2, 9, 6},
//                {5, 7, 4, 11},
//                {3, 7, 16, 5},
//        };
//        System.out.println(Offer.getMaxValueSolution(matrix));
//
//        System.out.println(Offer.longestSubstringWithoutDuplication("arabcacfr"));
//        System.out.println(Offer.longestSubstringWithoutDuplication("abcdefg"));
//        System.out.println(Offer.longestSubstringWithoutDuplication("aaaa"));
//        System.out.println(Offer.longestSubstringWithoutDuplication("ababab"));

//        System.out.println(Offer.getUglyNumber(2));
//        System.out.println(Offer.getUglyNumber(3));
//        System.out.println(Offer.getUglyNumber(10));
//        System.out.println(Offer.getUglyNumber(1500));

//        System.out.println(Offer.findNotRepeatingChar(""));
//        System.out.println(Offer.findNotRepeatingChar("a"));
//        System.out.println(Offer.findNotRepeatingChar("abaccdeff"));
//        System.out.println(Offer.findNotRepeatingChar("asdgadfgdafhgfgsadgfadgasdgasdf"));

//        LinkedNode nodeA6 = new LinkedNode(6, null);
//        LinkedNode nodeA5 = new LinkedNode(5, nodeA6);
//        LinkedNode nodeA4 = new LinkedNode(4, nodeA5);
//        LinkedNode nodeA3 = new LinkedNode(3, nodeA4);
//        LinkedNode nodeA2 = new LinkedNode(2, nodeA3);
//        LinkedNode nodeA1 = new LinkedNode(1, nodeA2);
//        LinkedNode nodeB2 = new LinkedNode(2, nodeA5);
//        LinkedNode nodeB1 = new LinkedNode(1, nodeB2);
//        System.out.println(Offer.findFirstCommonNode(nodeA1, nodeB1).getValue());

//        LinkedNode node5 = new LinkedNode(5, null);
//        LinkedNode node4 = new LinkedNode(4, node5);
//        LinkedNode node3 = new LinkedNode(3, node4);
//        LinkedNode node2 = new LinkedNode(2, node3);
//        LinkedNode head = new LinkedNode(1, node2);
//        System.out.println(Offer.findKToTail(head, 0));
//        System.out.println(Offer.findKToTail(head, 1).getValue());
//        System.out.println(Offer.findKToTail(head, 5).getValue());
//        System.out.println(Offer.findKToTail(head, 6));

//        LinkedNode node6 = new LinkedNode(6, null);
//        LinkedNode node5 = new LinkedNode(5, node6);
//        LinkedNode node4 = new LinkedNode(4, node5);
//        LinkedNode node3 = new LinkedNode(3, node4);
//        LinkedNode node2 = new LinkedNode(2, node3);
//        LinkedNode node1 = new LinkedNode(1, node2);
//        LinkedNode head = new LinkedNode(0, node1);
//        node6.setNext(head);
//        System.out.println(Offer.entryNodeOfLoop(head).getValue());
//        node6.setNext(node3);
//        System.out.println(Offer.entryNodeOfLoop(head).getValue());
//        node6.setNext(null);
//        System.out.println(Offer.entryNodeOfLoop(head));
//        node6.setNext(node6);
//        System.out.println(Offer.entryNodeOfLoop(head).getValue());

//        int[] data = {1, 1, 3, 4, 6, 6, 6, 6, 9, 9, 12, 12, 15, 17, 17, 19, 23, 35};
//        System.out.println(Offer.getNumberOfK(data, 0));
//        System.out.println(Offer.getNumberOfK(data, 2));
//        System.out.println(Offer.getNumberOfK(data, 6));
//        System.out.println(Offer.getNumberOfK(data, 17));
//        System.out.println(Offer.getNumberOfK(data, 23));

//        System.out.println(Offer.getMissingNumber(new int[]{0, 1, 2, 4, 5}));
//        System.out.println(Offer.getMissingNumber(new int[]{1, 2, 3, 4, 5}));
//        System.out.println(Offer.getMissingNumber(new int[]{0, 1, 2}));
//        System.out.println(Offer.getMissingNumber(new int[]{0, 1, 2, 5}));

//        System.out.println(Offer.getNumberSameAsIndex(new int[]{-3, -1, 1, 3, 5}));
//        System.out.println(Offer.getNumberSameAsIndex(new int[]{1, 2, 3, 4}));
//        System.out.println(Offer.getNumberSameAsIndex(new int[]{0}));

//        TreeNode node2 = new TreeNode(2, null, null);
//        TreeNode node4 = new TreeNode(4, null, null);
//        TreeNode node3 = new TreeNode(3, node2, node4);
//        TreeNode node6= new TreeNode(6, null, null);
//        TreeNode node8 = new TreeNode(8, null, null);
//        TreeNode node7 = new TreeNode(7, node6, node8);
//        TreeNode node5 = new TreeNode(5, node3, node7);
//        System.out.println(Offer.getKthNode(node5, 3).getValue());


//        System.out.println(Arrays.toString(Offer.findNumbersWithSum(new int[]{1, 2, 4, 7, 11, 15}, 15)));
//        System.out.println(Arrays.toString(Offer.findNumbersWithSum(new int[]{1, 2, 4, 7, 11, 15}, 14)));
//        System.out.println(Arrays.toString(Offer.findNumbersWithSum(new int[]{1, 2, 4, 7, 11, 15}, 3)));


//        System.out.println(Offer.findContinuousSequence(1));
//        System.out.println(Offer.findContinuousSequence(15));
//        System.out.println(Offer.findContinuousSequence(100));

//        System.out.println(Offer.reverseSentence("a"));
//        System.out.println(Offer.reverseSentence("  abc   dfg  we"));
//        System.out.println(Offer.reverseSentence("I an a student."));


//        System.out.println(Offer.maxInWindows(new int[]{2, 3, 4, 2, 6, 2, 5, 1}, 3));
//        System.out.println(Offer.maxInWindows(new int[]{2, 3, 4, 2, 6, 2, 5, 1}, 1));
//        System.out.println(Offer.maxInWindows(new int[]{2, 0, 3, 5, 4, 7, 8, 5, 6, 3, 2, 8, 4, 1, 2, 6, 10}, 4));



//        Offer.getProbability(4);


        System.out.println(Offer.maxDiff(new int[]{9, 11, 8, 5, 7, 12, 16, 14}));
        System.out.println(Offer.maxDiff(new int[]{9}));
        System.out.println(Offer.maxDiff(new int[]{1, 2, 3, 4, 5}));
        System.out.println(Offer.maxDiff(new int[]{-1, -4, -7, -9}));

    }
}
