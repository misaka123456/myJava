package model;

class NumArray {
    int[] tree;
    int n;
    public NumArray(int[] nums) {
        if (nums.length > 0) {
            n = nums.length;
            tree = new int[n * 2];
            buildTree(nums);
        }
    }

    private void buildTree(int[] nums) {
        for (int i = n, j = 0;  i < 2 * n; i++,  j++)
            tree[i] = nums[j];
        for (int i = n - 1; i > 0; --i)
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }

    void update(int pos, int val) {
        pos += n;
        int diff = val - tree[pos];
        //tree[pos] = val;
        while(pos > 0){
            tree[pos] += diff;
            pos /= 2;
        }
    }


    public int sumRange(int left, int right) {
        // 找到数组中对应left的index
        left += n;
        // 找到数组中对于right的index
        right += n;
        int sum = 0;
        while (left <= right) {
            // left和right对应的一定是叶子节点
            // left的如果是其父亲的右儿子，right如果是父亲的做儿子
            // 就立刻把自己加上，因为查询区间没有覆盖父亲，只覆盖了自己。
            if ((left % 2) == 1) {
                sum += tree[left];
                left++;
            }
            if ((right % 2) == 0) {
                sum += tree[right];
                right--;
            }
            // 找到left和right的父亲节点
            left /= 2;
            right /= 2;
        }
        return sum;
    }

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{2, 4, 5, 7, 8, 9});
        numArray.sumRange(1, 2);
    }
}


