import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author xiakai
 * @create 2020-08-11 16:20
 */
public class RePlaceSIFCardName {

    public static void main(String[] args) throws IOException {

        System.out.println("请输入目录");
        Scanner cs = new Scanner(System.in);
        String cardPathName = cs.nextLine();

        System.out.println("请输入前缀名");
        String prefixName = cs.nextLine();

        File kapaiDir = new File(cardPathName, prefixName + "卡牌");
        File lihuiDir = new File(cardPathName, prefixName + "立绘");
        if (!kapaiDir.exists()) {
            kapaiDir.mkdir();
        }
        if (!lihuiDir.exists()) {
            lihuiDir.mkdir();
        }

        changeName(new File(cardPathName, "2"), new File(cardPathName, "1.txt"), prefixName + "卡牌", kapaiDir);
        changeName(new File(cardPathName, "4"), new File(cardPathName, "3.txt"), prefixName + "立绘", lihuiDir);
        changeName(new File(cardPathName, "6"), new File(cardPathName, "5.txt"), prefixName + "立绘", lihuiDir);


    }

    private static void changeName(File cardDir, File txt, String prefixName, File outDir) throws IOException {
        BufferedReader nameBR = new BufferedReader(new InputStreamReader(new FileInputStream(txt)));
        String s = null;
        HashMap<String, String> map = new HashMap<>();

        while ((s = nameBR.readLine()) != null) {
            int a = s.indexOf('-');
            map.put(s.substring(0, a), s.substring(a + 1));
        }

        for (File card : cardDir.listFiles()) {
            int a = card.getName().lastIndexOf('.');
            int b = card.getName().indexOf('_');
            String name = null;

            if (b == -1) {
                name = card.getName().substring(0, a);
            } else {
                name = card.getName().substring(0, b);
            }

            name.replace("(BOX)", "");
            name.replace("(特典)", "");
            name.replace("(活动)", "");
            name.replace("(BOX)", "");
            String ext = card.getName().substring(a + 1);
            String newName = map.get(name);
            if (newName != null) {
                card.renameTo(new File(outDir, prefixName + "-" + newName + "." + ext));
                System.out.println(name + " 改名为：" + prefixName + "-" + newName);
            }
        }
    }

}
