package model;

import myTools.MyArrayTools;

import java.util.*;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }


    /**
     * 先序遍历
     * @return 结果数组
     */
    public List<Integer> preOrder() {
        List<Integer> list = new ArrayList<>();
        TreeNode node = this;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                list.add(node.val);
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop().right;
            }
        }
        return  list;
    }

    /**
     * 中序遍历
     * @return 结果数组
     */
    public List<Integer> inOrder() {
        List<Integer> list = new ArrayList<>();
        TreeNode node = this;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                list.add(node.val);
                node = node.right;
            }
        }
        return list;
    }

    /**
     * 后序遍历
     * @return 结果数组
     */
    public List<Integer> postOrder() {
        List<Integer> list = new ArrayList<>();
        TreeNode node = this;
        TreeNode r = null;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.peek();
                if (node.right == null || node.right == r) {
                    list.add(node.val);
                    r = stack.pop();
                    node = null;
                } else {
                    node = node.right;
                }
            }
        }
        return list;
    }

    /**
     * 层序遍历
     * @return 结果数组
     */
    public List<Integer> levelOrder() {
        List<Integer> out = new ArrayList<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(this);
        TreeNode node;
        while (!list.isEmpty()) {
            node = list.poll();
            out.add(node.val);
            if (node.left != null) {
                list.offer(node.left);
            }
            if (node.right != null) {
                list.offer(node.right);
            }
        }
        return out;
    }

    /**
     * 根据先序和中序数组生成二叉树
     * @param pre 先序数组
     * @param in 中序数组
     * @return 根节点
     * @throws Exception 数组错误，无法生成
     */
    public static TreeNode buildByPreAndInOrder(int[] pre, int[] in) throws Exception {
        if (pre.length != in.length || pre.length == 0) {
            return null;
        }
        return buildByPreAndInOrder(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }
    private static TreeNode buildByPreAndInOrder(int[] pre, int preStart, int preEnd,
                                                          int[] in, int inStart, int inEnd) throws Exception {
        if (preStart > preEnd) {
            return null;
        }
        int v = pre[preStart];
        TreeNode node = new TreeNode(v);
        int inIndex = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (v == in[i]) {
                inIndex = i;
                node.left = buildByPreAndInOrder(pre, preStart + 1, preStart + inIndex - inStart,
                        in, inStart, inIndex - 1);
                node.right = buildByPreAndInOrder(pre, preStart + inIndex - inStart + 1, preEnd,
                        in, inIndex + 1, inEnd);
                break;
            }
        }
        if (inIndex == -1) {
            throw new Exception("preArr or inArr is error");
        }
        return node;
    }

    /**
     * 根据后序和中序数组生成二叉树
     * @param post 后序数组
     * @param in 中序数组
     * @return 根节点
     * @throws Exception 数组错误，无法生成
     */
    public static TreeNode buildByPostAndInOrder(int[] post, int[] in) throws Exception {
        if (post.length != in.length || post.length == 0) {
            return null;
        }
        return buildByPostAndInOrder(post, 0, post.length - 1,
                in, 0, in.length - 1);
    }
    private static TreeNode buildByPostAndInOrder(int[] post, int postStart,
                                                           int postEnd, int[] in, int inStart, int inEnd) throws Exception {
        if (postStart > postEnd) {
            return null;
        }
        int v = post[postEnd];
        TreeNode node = new TreeNode(v);
        int inIndex = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (v == in[i]) {
                inIndex = i;
                node.left = buildByPostAndInOrder(post, postStart, postStart + inIndex - inStart - 1,
                        in, inStart, inIndex - 1);
                node.right = buildByPostAndInOrder(post, postStart + inIndex - inStart, postEnd - 1,
                        in, inIndex + 1, inEnd);
                break;
            }
        }
        if (inIndex == -1) {
            throw new Exception("postArr or inArr is error");
        }
        return node;
    }


    /**
     * 根据层序生成二叉树(允许出现null)
     * @param level 任意对象数组
     * @return 根节点
     */
    public static TreeNode buildByLevelOrder(Integer[] level) {

        if (level.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(level[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode f;
        int i = 1;
        Integer temp;
        while (i < level.length) {
            f = queue.poll();
            if (f == null) {
                throw new NullPointerException("arr is error of null!");
            }
            temp = level[i++];
            if (temp != null) {
                TreeNode l = new TreeNode(temp);
                queue.offer(l);
                f.left = l;
            }
            if (i == level.length) {
                break;
            }
            temp = level[i++];
            if (temp != null) {
                TreeNode r = new TreeNode(temp);
                queue.offer(r);
                f.right = r;
            }
        }
        return root;
    }

    /**
     * 根据层序生成二叉树（int类型，没有null）
     * @param level int数组
     * @return 根节点
     */
    public static TreeNode buildByLevelOrder(int[] level) {
        if (level == null || level.length == 0) {
            return null;
        }
        return buildByLevelOrder(MyArrayTools.intToInteger(level));
    }

}
