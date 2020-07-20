import java.util.*;

/**
 * @author xiakai
 * @create 2020-06-25 11:09
 */
@SuppressWarnings("all")
public class LeetCodeGraph {


    /**
     * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 出发。
     * <p>
     * 说明:
     * 如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
     * 所有的机场都用三个大写字母表示（机场代码）。
     * 假定所有机票至少存在一种合理的行程。
     * <p>
     * 示例 1:
     * 输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
     * 输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
     * <p>
     * 示例 2:
     * 输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
     * 输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
     * 解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
     */
    public static List<String> _0332_重新安排行程(List<List<String>> tickets) {

        List<String> res = new ArrayList<>();
        if (tickets.size() == 0) {
            return res;
        }
        Map<String, Queue<String>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
            Queue<String> queue = map.get(ticket.get(0));
            if (queue == null) {
                queue = new PriorityQueue<>();
                map.put(ticket.get(0), queue);
            }
            queue.offer(ticket.get(1));
        }
        buildRoute("JFK", map, res);
        return res;

    }

    private static void buildRoute(String begin, Map<String, Queue<String>> map, List<String> res) {
        Queue<String> queue = map.get(begin);
        while (queue != null && !queue.isEmpty()) {
            buildRoute(queue.poll(), map, res);
        }
        res.add(0, begin);
    }


    public static boolean _1361_验证二叉树(int n, int[] leftChild, int[] rightChild) {
        int[] arr = new int[n];
        Arrays.fill(arr, -1);
        for (int i = 0; i < n; i++) {
            if (leftChild[i] != -1) {
                // 这个判断能依次解决循环 后面节点指向前面节点 多个节点指向同一节点的问题
                if ((leftChild[i] > i || arr[i] == -1) && arr[leftChild[i]] == -1 || arr[leftChild[i]] == i) {
                    arr[leftChild[i]] = i;
                } else {
                    return false;
                }
            }
            if (rightChild[i] != -1) {
                if ((rightChild[i] > i || arr[i] == -1) && arr[rightChild[i]] == -1 || arr[rightChild[i]] == i) {
                    arr[rightChild[i]] = i;
                } else {
                    return false;
                }
            }
        }
        boolean flag = false;
        for (int a : arr) {
            if (a == -1) {
                if (!flag) {
                    flag = true;
                } else {
                    return false;
                }
            }
        }
        return flag;
    }


    public static List<Integer> _0802_找到最终的安全状态(int[][] graph) {

        int[] dp = new int[graph.length];

        List<Set<Integer>> inGraphList = new ArrayList<>();

        for (int i = 0; i < graph.length; i++) {
            inGraphList.add(new HashSet<Integer>());
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            dp[i] = graph[i].length;
            if (graph[i].length == 0) {
                queue.offer(i);
            }
            for (int j = 0; j < graph[i].length; j++) {
                inGraphList.get(graph[i][j]).add(i);
            }
        }

        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int i = queue.poll();
            res.add(i);
            for (int j : inGraphList.get(i)) {
                dp[j]--;
                if (dp[j] == 0) {
                    queue.offer(j);
                }
            }
        }
        res.sort(Comparator.comparingInt(o -> o));
        return res;
    }


    public static int[] _1042_不邻接植花(int N, int[][] paths) {
        List<Set<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new HashSet<Integer>());
        }
        for (int[] path : paths) {
            graph.get(path[0] - 1).add(path[1] - 1);
            graph.get(path[1] - 1).add(path[0] - 1);
        }
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            boolean[] colors = new boolean[4];
            for (int j : graph.get(i)) {
                if (res[j] != 0) {
                    colors[res[j] - 1] = true;
                }
            }
            for (int j = 0; j < 4; j++) {
                if (!colors[j]) {
                    res[i] = j + 1;
                    break;
                }
            }
        }
        return res;
    }


    public int[] _0210_课程表_II(int numCourses, int[][] prerequisites) {
        ArrayList<Set<Integer>> outGraphList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            outGraphList.add(new HashSet<Integer>());
        }

        int[] inCount = new int[numCourses];
        for (int[] pre : prerequisites) {
            inCount[pre[0]]++;
            outGraphList.get(pre[1]).add(pre[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inCount[i] == 0) {
                queue.offer(i);
            }
        }

        int[] res = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            int o = queue.poll();
            res[index++] = o;
            for (int i : outGraphList.get(o)) {
                inCount[i]--;
                if (inCount[i] == 0) {
                    queue.offer(i);
                }
            }
        }
        if (index == numCourses) {
            return res;
        } else {
            return new int[0];
        }
    }


    public static int _0743_网络延迟时间(int[][] times, int N, int K) {
        List<Map<Integer, Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new HashMap<Integer, Integer>());
        }
        for (int[] time : times) {
            graph.get(time[0] - 1).put(time[1] - 1, time[2]);
        }
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = -1;
        }
        arr[K - 1] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(K - 1);
        while (!queue.isEmpty()) {
            int s = queue.poll();
            for (Map.Entry<Integer, Integer> e : graph.get(s).entrySet()) {
                if (arr[e.getKey()] == -1 || arr[e.getKey()] > arr[s] + e.getValue()) {
                    arr[e.getKey()] = arr[s] + e.getValue();
                    queue.offer(e.getKey());
                }
            }
        }
        int max = 0;
        for (int a : arr) {
            if (a == -1) {
                return -1;
            }
            max = Math.max(max, a);
        }
        return max;
    }


    public static List<Boolean> _1462_课程安排_IV(int n, int[][] prerequisites, int[][] queries) {
        boolean[][] dp = new boolean[n][n];
        for (int[] pre : prerequisites) {
            dp[pre[0]][pre[1]] = true;
            for (int i = 0; i < n; i++) {
                if (dp[i][pre[0]]) {
                    dp[i][pre[1]] = true;
                }
                if (dp[pre[1]][i]) {
                    dp[pre[0]][i] = true;
                }
            }
        }
        List<Boolean> res = new ArrayList<>();
        for (int[] que : queries) {
            res.add(dp[que[0]][que[1]]);
        }
        return res;
    }


    public static boolean _0841_钥匙和房间(List<List<Integer>> rooms) {
        boolean[] arrive = new boolean[rooms.size()];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        arrive[0] = true;
        int num = 0;
        while (!queue.isEmpty()) {
            int s = queue.poll();
            num++;
            for (int e : rooms.get(s)) {
                if (!arrive[e]) {
                    queue.offer(e);
                    // 设置已访问一点要在这里设置，不然会导致重复添加
                    arrive[e] = true;
                }
            }
        }
        return num == arrive.length;
    }


    public boolean _0207_课程表(int numCourses, int[][] prerequisites) {

        List<Set<Integer>> outGraph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            outGraph.add(new HashSet<Integer>());
        }
        int[] dp = new int[numCourses];
        for (int[] pre : prerequisites) {
            dp[pre[0]]++;
            outGraph.get(pre[1]).add(pre[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (dp[i] == 0) {
                queue.offer(i);
            }
        }

        int num = 0;
        while (!queue.isEmpty()) {
            int s = queue.poll();
            num++;
            for (int e : outGraph.get(s)) {
                dp[e]--;
                if (dp[e] == 0) {
                    queue.offer(e);
                }
            }
        }
        return num == numCourses;
    }


    public static int _01162_地图分析(int[][] grid) {
        int N = grid.length << 1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                } else {
                    grid[i][j] = N;
                    if (i > 0 && grid[i - 1][j] != N) {
                        grid[i][j] = grid[i - 1][j] + 1;
                    }
                    if (j > 0 && grid[i][j - 1] != N) {
                        grid[i][j] = Math.min(grid[i][j], grid[i][j - 1] + 1);
                    }
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = grid.length - 1; j >= 0; j--) {
                if (grid[i][j] != 0) {
                    if (i > 0 && grid[i - 1][j] != N) {
                        grid[i][j] = Math.min(grid[i][j], grid[i - 1][j] + 1);
                    }
                    if (j < grid.length - 1 && grid[i][j + 1] != N) {
                        grid[i][j] = Math.min(grid[i][j], grid[i][j + 1] + 1);
                    }

                }
            }
        }
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid.length - 1; j >= 0; j--) {
                if (grid[i][j] != 0) {
                    if (i < grid.length - 1 && grid[i + 1][j] != N) {
                        grid[i][j] = Math.min(grid[i][j], grid[i + 1][j] + 1);
                    }
                    if (j < grid.length - 1 && grid[i][j + 1] != N) {
                        grid[i][j] = Math.min(grid[i][j], grid[i][j + 1] + 1);
                    }

                }
            }
        }
        int max = 0;
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] != 0) {
                    if (i < grid.length - 1 && grid[i + 1][j] != N) {
                        grid[i][j] = Math.min(grid[i][j], grid[i + 1][j] + 1);
                    }
                    if (j > 0 && grid[i][j - 1] != N) {
                        grid[i][j] = Math.min(grid[i][j], grid[i][j - 1] + 1);
                    }
                    if (grid[i][j] != N) {
                        max = Math.max(max, grid[i][j]);
                    }
                }
            }
        }
        return max == 0 ? -1 : max;
    }


    /**
     * 给一个有 n 个结点的有向无环图，找到所有从 0 到 n-1 的路径并输出（不要求按顺序）
     * 二维数组的第 i 个数组中的单元都表示有向图中 i 号结点所能到达的下一些结点（译者注：有向图是有方向的，即规定了a→b你就不能从b→a）空就是没有下一个结点了。
     * <p>
     * 示例:
     * 输入: [[1,2], [3], [3], []]
     * 输出: [[0,1,3],[0,2,3]]
     * 解释: 图是这样的:
     * 0--->1
     * |    |
     * v    v
     * 2--->3
     * 这有两条路: 0 -> 1 -> 3 和 0 -> 2 -> 3.
     */
    public static List<List<Integer>> _0797_所有可能的路径(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        if (graph.length == 0) {
            return res;
        }
        LinkedList<Integer> route = new LinkedList<>();
        route.add(0);
        allPathsSourceTarget(graph, 0, route, res);
        return res;
    }

    private static void allPathsSourceTarget(int[][] graph, int start, LinkedList<Integer> route, List<List<Integer>> res) {
        if (start == graph.length - 1) {
            res.add(new ArrayList(route));
            return;
        }
        for (int e : graph[start]) {
            route.add(e);
            allPathsSourceTarget(graph, e, route, res);
            route.removeLast();
        }
    }


    public static boolean _0785_判断二分图(int[][] graph) {

        int[] colors = new int[graph.length];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < graph.length; i++) {
            if (colors[i] == 0) {
                colors[i] = 1;
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int s = queue.poll();
                    for (int e : graph[s]) {
                        if (colors[e] == 0) {
                            colors[e] = 3 - colors[s];
                            queue.offer(e);
                        } else if (colors[e] + colors[s] != 3) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {


    }

}
