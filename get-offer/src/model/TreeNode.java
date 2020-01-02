package model;

import java.util.*;

public class TreeNode<E> {

    private Object value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(E value) {
        this.value = value;
    }

    public TreeNode(E value, TreeNode<E> left, TreeNode<E> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @SuppressWarnings("unchecked")
    private TreeNode<E> treeNode(TreeNode n) {
        return (TreeNode<E>) n;
    }

    @SuppressWarnings("unchecked")
    public E getValue() {
        return (E) value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public TreeNode<E> getLeft() {
        return treeNode(left);
    }

    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    public TreeNode<E> getRight() {
        return treeNode(right);
    }

    public void setRight(TreeNode<E> right) {
        this.right = right;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    /**
     * 判断是否是完全二叉树
     */
    public boolean isComplete() {
        TreeNode root;
        Queue<TreeNode> queue = new LinkedList<>();
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
        Queue<TreeNode> queue = new LinkedList<>();
        int n = 0;
        queue.offer(this);
        TreeNode node;
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
        LinkedList<TreeNode> list = new LinkedList<>();
        int curCount = 1;
        int nextCount = 0;
        list.offer(this);
        TreeNode node;
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
        TreeNode node = this;
        Stack<TreeNode> stack = new Stack<>();
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
        TreeNode node = this;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                list.add(node.value);
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
        TreeNode node = this;
        Stack<TreeNode> stack = new Stack<>();
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
        LinkedList<TreeNode> list = new LinkedList<>();
        int curCount = 1;
        int nextCount = 0;
        list.offer(this);
        TreeNode node;
        while (!list.isEmpty()) {
            while (curCount > 0) {
                node = list.poll();
                curCount--;
                assert node != null;
                out.add(node.value);
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
        return nodeList(out);
    }

    /**
     * 根据先序和中序数组生成二叉树
     * @param pre 先序数组
     * @param in 中序数组
     * @return 根节点
     * @throws Exception 数组错误，无法生成
     */
    public static TreeNode<Integer> buildByPreAndInOrder(int[] pre, int[] in) throws Exception {
        if (pre == null || in == null || pre.length != in.length || pre.length == 0) {
            return null;
        }
        return buildByPreAndInOrder(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }
    private static TreeNode<Integer> buildByPreAndInOrder(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) throws Exception {
        if (preStart > preEnd) {
            return null;
        }
        int v = pre[preStart];
        TreeNode<Integer> node = new TreeNode<>(v);
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
    public static TreeNode<Integer> buildByPostAndInOrder(int[] post, int[] in) throws Exception {
        if (post == null || in == null || post.length != in.length || post.length == 0) {
            return null;
        }
        return buildByPostAndInOrder(post, 0, post.length - 1, in, 0, in.length - 1);
    }
    private static TreeNode<Integer> buildByPostAndInOrder(int[] post, int postStart, int postEnd, int[] in, int inStart, int inEnd) throws Exception {
        if (postStart > postEnd) {
            return null;
        }
        int v = post[postEnd];
        TreeNode<Integer> node = new TreeNode<>(v);
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
    private static TreeNode<Integer> intNode(TreeNode node) {
        return (TreeNode<Integer>) node;
    }

    /**
     * 根据层序生成二叉树
     * @param level 层序数组
     * @return 根节点
     */
    public static TreeNode<Integer> buildByLevelOrder(int[] level) {
        if (level == null || level.length == 0) {
            return null;
        }
        TreeNode[] arr = new TreeNode[level.length];
        arr[0] = intNode(new TreeNode<>(level[0]));
        for (int i = 1; i < level.length; i++) {
            TreeNode node = intNode(new TreeNode<>(level[i]));
            if (i % 2 == 1) {
                arr[(i - 1) >> 1].left = node;
            } else {
                arr[(i - 1) >> 1].right = node;
            }
            arr[i] = node;
        }
        return intNode(arr[0]);
    }

    /**
     * 根据层序生成二叉树(允许出现null)
     * @param level 层序数组
     * @return 根节点
     */
    public static TreeNode<Integer> buildByLevelOrder(Integer[] level) {
        if (level == null || level.length == 0) {
            return null;
        }
        TreeNode root = intNode(new TreeNode<>(level[0]));
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
                TreeNode l = intNode(new TreeNode<>(temp));
                queue.offer(l);
                f.left = l;
            }
            if (i == level.length) {
                break;
            }
            temp = level[i++];
            if (temp != null) {
                TreeNode r = intNode(new TreeNode<>(temp));
                queue.offer(r);
                f.right = r;
            }
        }
        return intNode(root);
    }





}
