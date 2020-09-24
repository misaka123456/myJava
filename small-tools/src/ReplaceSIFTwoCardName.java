import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author xiakai
 * @create 2020-08-11 18:18
 */
public class ReplaceSIFTwoCardName {

    private static String[] japanNames = {
            "東條希",
            "西木野真姫",
            "桜内梨子",
            "黒澤ルビィ",
            "星空凛",
            "津島善子",
            "絢瀬絵里",
            "渡辺曜",
            "園田海未",
            "小泉花陽",
            "高海千歌",
            "南ことり",
            "国木田花丸",
            "黒澤ダイヤ",
            "矢澤にこ",
            "高坂穂乃果",
            "松浦果南",
            "小原鞠莉",
    };
    private static String[] chineseNames = {
            "东条希",
            "西木野真姬",
            "樱内梨子",
            "黑泽露比",
            "星空凛",
            "津岛善子",
            "绚濑绘里",
            "渡边曜",
            "园田海未",
            "小泉花阳",
            "高海千歌",
            "南小鸟",
            "国木田花丸",
            "黑泽黛雅",
            "矢泽妮可",
            "高坂穗乃果",
            "松浦果南",
            "小原鞠莉",
    };
    private static HashMap<String, String> nameMap;

    public static void main(String[] args) throws IOException {

        nameMap = new HashMap<>();
        for (int i = 0; i < chineseNames.length; i++) {
            nameMap.put(japanNames[i], chineseNames[i]);
        }

        System.out.println("请输入目录");
        Scanner cs = new Scanner(System.in);
        String cardPathName = cs.nextLine();


        File hekaDir = new File(cardPathName, "合卡");

        if (!hekaDir.exists()) {
            hekaDir.mkdir();
        }



        changeName(new File(cardPathName, "2"), new File(cardPathName, "1.txt"), hekaDir);

    }

    private static void changeName(File cardDir, File txt, File outDir) throws IOException {
        BufferedReader nameBR = new BufferedReader(new InputStreamReader(new FileInputStream(txt)));
        String s = null;
        HashMap<String, String> map = new HashMap<>();

        while ((s = nameBR.readLine()) != null) {
            int a = s.indexOf('-');
            map.put(s.substring(0, a), s.substring(a + 1));
        }

        for (File card : cardDir.listFiles()) {
            int a = card.getName().lastIndexOf('.');
            String name  = card.getName().substring(0, a);
            String ext = card.getName().substring(a + 1);
            String newName = map.get(name);

            int b = newName.lastIndexOf('-') + 1;
            String[] person = newName.substring(b).split("_");
            String nnewName = newName.substring(0, b) + nameMap.get(person[0]) + "_" + nameMap.get(person[1]);

            if (newName != null) {
                card.renameTo(new File(outDir, nnewName + "." + ext));
                System.out.println(name + " 改名为：" + nnewName);
            }
        }
    }

}
