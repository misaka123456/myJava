import java.util.*;
public class Main{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine();
        char[][] map = new char[N][];
        boolean[][] vit = new boolean[N][];
        for (int i = 0; i < N; i++) {
            map[i] = sc.nextLine().toCharArray();
            vit[i] = new boolean[map[i].length];
        }


        int M = sc.nextInt();
        sc.nextLine();
        String[] ins = new String[M];
        for (int i = 0; i < M; i++) {
            ins[i] = sc.nextLine();
        }

        ArrayList<String> res = new ArrayList<>();
        flag:
        for (int i = 0; i < M; i++) {
            char[] arr = ins[i].toCharArray();
            for (int x = 0; x < map.length; x++) {
                for (int y = 0; y < map[x].length; y++) {
                    if (arr[0] == map[x][y]) {
                        if (isInMap(map, vit, arr, 0, x, y)) {
                            res.add(ins[i]);
                            continue flag;
                        }
                    }
                }
            }
        }
        res.sort(Comparator.comparingInt(o -> o.charAt(0)));
        for (String s : res) {
            System.out.println(s);
        }

    }
    private static boolean isInMap(char[][] map, boolean[][] vit, char[] arr, int len, int x, int y) {
        len++;
        if (len == arr.length) {
            return true;
        }
        vit[x][y] = true;
        if ((x > 0 && y < map[x - 1].length && !vit[x - 1][y] && arr[len] == map[x - 1][y] && isInMap(map, vit, arr, len, x - 1, y))
                || (x < map.length - 1 && y < map[x + 1].length && !vit[x + 1][y] && arr[len] == map[x + 1][y]  && isInMap(map, vit, arr, len, x + 1, y))
                || (y > 0 && !vit[x][y - 1] && arr[len] == map[x][y - 1] && isInMap(map, vit, arr, len, x, y - 1))
                || (y < map[x].length - 1 && !vit[x][y + 1] && arr[len] == map[x][y + 1]  && isInMap(map, vit, arr, len, x, y + 1))
                ) {
            vit[x][y] = false;
            return true;
        }
        vit[x][y] = false;
        return false;


    }

}