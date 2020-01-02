import model.TreeNode;

import java.text.SimpleDateFormat;
import java.util.*;


public class Test {


    public static void main(String[] args) throws Exception {

        TreeNode<Integer> root = TreeNode.buildByLevelOrder(new Integer[]{1,2,3,4,5,6,7,8,9,0});
        System.out.println(root.isFull());
        System.out.println(root.isComplete());



    }

}







