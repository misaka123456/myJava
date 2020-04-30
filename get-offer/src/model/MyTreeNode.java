package model;

import myTools.MyArrayTools;

import java.util.*;

public class MyTreeNode<E> {

    private Object val;
    private MyTreeNode left;
    private MyTreeNode right;

    public MyTreeNode() {
    }

    public MyTreeNode(E val) {
        this.val = val;
    }

    public MyTreeNode(E val, MyTreeNode<E> left, MyTreeNode<E> right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @SuppressWarnings("unchecked")
    private MyTreeNode<E> treeNode(MyTreeNode n) {
        return (MyTreeNode<E>) n;
    }

    @SuppressWarnings("unchecked")
    public E getValue() {
        return (E) val;
    }

    public void setValue(E val) {
        this.val = val;
    }

    public MyTreeNode<E> getLeft() {
        return treeNode(left);
    }

    public void setLeft(MyTreeNode<E> left) {
        this.left = left;
    }

    public MyTreeNode<E> getRight() {
        return treeNode(right);
    }

    public void setRight(MyTreeNode<E> right) {
        this.right = right;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    /**
     * 判断是否是完全二叉树
     */
    public boolean isComplete() {
        MyTreeNode root;
        Queue<MyTreeNode> queue = new LinkedList<>();
        queue.offer(this);
        boolean flag = false;
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root == null) {
                flag = true;
                continue;
            }
            if (flag) {
                return false;
            }
            queue.offer(root.left);
            queue.offer(root.right);
        }
        return true;
    }

    /**
     * 通过层序遍历判断是否是满二叉树
     */
    public boolean isFull() {
        Queue<MyTreeNode> queue = new LinkedList<>();
        int n = 0;
        queue.offer(this);
        MyTreeNode node;
        boolean flag = false;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node == null) {
                if (!flag && (n & (n + 1)) != 0) {
                    return false;
                }
                flag = true;
                continue;
            }
            if (flag) {
                return false;
            }
            n++;
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return true;
    }

    /**
     * 通过层序遍历获取深度
     */
    public int deep() {
        int deep = 0;
        LinkedList<MyTreeNode> list = new LinkedList<>();
        int curCount = 1;
        int nextCount = 0;
        list.offer(this);
        MyTreeNode node;
        while (!list.isEmpty()) {
            deep++;
            while (curCount-- > 0) {
                node = list.poll();
                assert node != null;
                if (node.left != null) {
                    list.offer(node.left);
                    nextCount++;
                }
                if (node.right != null) {
                    list.offer(node.right);
                    nextCount++;
                }
            }
            curCount = nextCount;
            nextCount = 0;
        }
        return deep;
    }

    /**
     * 获取树的节点总数
     */
    public int nodeCount() {
        MyTreeNode node = this;
        Stack<MyTreeNode> stack = new Stack<>();
        int count = 0;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                count++;
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop().right;
            }
        }
        return count;
    }

    @SuppressWarnings("unchecked")
    private List<E> nodeList(List arr) {
        return (List<E>) arr;
    }

    /**
     * 先序遍历
     * @return 结果数组
     */
    public List<E> preOrder() {
        List<Object> list = new ArrayList<>();
        MyTreeNode node = this;
        Stack<MyTreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                list.add(node.val);
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop().right;
            }
        }
        return  nodeList(list);
    }

    /**
     * 中序遍历
     * @return 结果数组
     */
    public List<E> inOrder() {
        List<Object> list = new ArrayList<>();
        MyTreeNode node = this;
        Stack<MyTreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                list.add(node.getValue());
                node = node.right;
            }
        }
        return  nodeList(list);
    }

    /**
     * 后序遍历
     * @return 结果数组
     */
    public List<E> postOrder() {
        List<Object> list = new ArrayList<>();
        MyTreeNode node = this;
        MyTreeNode r = null;
        Stack<MyTreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.peek();
                if (node.right == null || node.right == r) {
                    list.add(node.getValue());
                    r = stack.pop();
                    node = null;
                } else {
                    node = node.right;
                }
            }
        }
        return nodeList(list);
    }

    /**
     * 层序遍历
     * @return 结果数组
     */
    public List<E> levelOrder() {
        List<Object> out = new ArrayList<>();
        LinkedList<MyTreeNode> list = new LinkedList<>();
        list.offer(this);
        MyTreeNode node;
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
        return nodeList(out);
    }

    /**
     * 根据先序和中序数组生成二叉树
     * @param pre 先序数组
     * @param in 中序数组
     * @return 根节点
     * @throws Exception 数组错误，无法生成
     */
    public static MyTreeNode<Integer> buildByPreAndInOrder(int[] pre, int[] in) throws Exception {
        if (pre.length != in.length || pre.length == 0) {
            return null;
        }
        return buildByPreAndInOrder(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }
    private static MyTreeNode<Integer> buildByPreAndInOrder(int[] pre, int preStart, int preEnd,
                                                          int[] in, int inStart, int inEnd) throws Exception {
        if (preStart > preEnd) {
            return null;
        }
        int v = pre[preStart];
        MyTreeNode<Integer> node = new MyTreeNode<>(v);
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
    public static MyTreeNode<Integer> buildByPostAndInOrder(int[] post, int[] in) throws Exception {
        if (post.length != in.length || post.length == 0) {
            return null;
        }
        return buildByPostAndInOrder(post, 0, post.length - 1,
                in, 0, in.length - 1);
    }
    private static MyTreeNode<Integer> buildByPostAndInOrder(int[] post, int postStart,
                                                           int postEnd, int[] in, int inStart, int inEnd) throws Exception {
        if (postStart > postEnd) {
            return null;
        }
        int v = post[postEnd];
        MyTreeNode<Integer> node = new MyTreeNode<>(v);
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

    @SuppressWarnings("unchecked")
    private static <E> MyTreeNode<E> elementNode(MyTreeNode node) {
        return (MyTreeNode<E>) node;
    }

    /**
     * 根据层序生成二叉树(允许出现null)
     * @param level 任意对象数组
     * @return 根节点
     */
    public static <E> MyTreeNode<E> buildByLevelOrder(E[] level) {

        if (level.length == 0) {
            return null;
        }
        MyTreeNode<E> root = new MyTreeNode<>(level[0]);
        Queue<MyTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        MyTreeNode f;
        int i = 1;
        E temp;
        while (i < level.length) {
            f = queue.poll();
            if (f == null) {
                throw new NullPointerException("arr is error of null!");
            }
            temp = level[i++];
            if (temp != null) {
                MyTreeNode l = new MyTreeNode<>(temp);
                queue.offer(l);
                f.left = l;
            }
            if (i == level.length) {
                break;
            }
            temp = level[i++];
            if (temp != null) {
                MyTreeNode r = new MyTreeNode<>(temp);
                queue.offer(r);
                f.right = r;
            }
        }
        return root;
    }

    /**
     * 根据层序生成二叉树(允许出现null)
     * @param c Collection类对象
     * @return 根节点
     */
    public static <E> MyTreeNode<E> buildByLevelOrder(Collection<? extends E> c) {
        return elementNode(buildByLevelOrder(c.toArray(new Object[0])));
    }

    /**
     * 根据层序生成二叉树（int类型，没有null）
     * @param level int数组
     * @return 根节点
     */
    public static MyTreeNode<Integer> buildByLevelOrder(int[] level) {
        if (level == null || level.length == 0) {
            return null;
        }
        return buildByLevelOrder(MyArrayTools.intToInteger(level));
    }
    
}
