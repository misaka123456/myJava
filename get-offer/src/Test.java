import model.MyListByArray;
import model.MyStackByArray;
import model.TreeNode;
import myTools.MyArrayTreeTools;

import java.util.*;

public class Test {

    public static void main(String[] args) throws Exception {

//        TreeNode t = TreeNode.buildByPreAndIn(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 5, 2, 1, 6, 7, 3});
//        System.out.println(t.preOrder());
//        System.out.println(t.inOrder());

//        TreeNode<Integer> t7 = new TreeNode<>(7);
//        TreeNode<Integer> t6 = new TreeNode<>(6);
//        TreeNode<Integer> t5 = new TreeNode<>(5);
//        TreeNode<Integer> t4 = new TreeNode<>(4);
//        TreeNode<Integer> t3 = new TreeNode<>(3, t6, t7);
//        TreeNode<Integer> t2 = new TreeNode<>(2, t4, t5);
//        TreeNode<Integer> t1 = new TreeNode<>(1, t2, t3);
//        System.out.println(t1.preOrder());
//        System.out.println(t1.inOrder());
//        System.out.println(t1.levelOrder());
//
        TreeNode t = TreeNode.buildByPreAndIn(new int[]{1,2,4,8,5,3,6,9,7}, new int[]{4,8,2,5,1,9,6,3,7});
        System.out.println(t.preOrder());
        System.out.println(t.inOrder());

        TreeNode t1 = TreeNode.buildByPostAndIn(new int[]{8,4,5,2,9,6,7,3,1}, new int[]{4,8,2,5,1,9,6,3,7});
        System.out.println(t1.preOrder());
        System.out.println(t1.postOrder());



    }
}


