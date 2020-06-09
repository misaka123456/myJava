import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanPic {


    public static void main(String[] args) throws IOException {

        Scanner cs = new Scanner(System.in);
        String yuanPath = cs.nextLine();
        String biPath = cs.nextLine();
        String shanPath = cs.nextLine();

        File shanDir = new File(shanPath);
        if(!shanDir.exists()){
            shanDir.mkdirs();
        }
        File yuanShanDir = new File(shanPath, "yuantu");
        if(!yuanShanDir.exists()){
            yuanShanDir.mkdirs();
        }
        File biShanDir = new File(shanPath, "bizhi");
        if(!biShanDir.exists()){
            biShanDir.mkdirs();
        }

        File[] yuanPics = new File(yuanPath).listFiles();
        File[] biPics = new File(biPath).listFiles();


//        for (int i = 0; i < biPics.length; i++) {
//            if (biPics[i].getName().contains("desktop")) {
//                System.out.println(biPics[i].getName());
//                System.out.println(i);
//                biPics[i].delete();
//            }
//        }



        Arrays.sort(yuanPics, Comparator.comparing(f -> f.getName().substring(0, f.getName().lastIndexOf('.'))));
        Arrays.sort(biPics, Comparator.comparing(f -> {
            Matcher m = Pattern.compile("(.+-.+?)\\(.+\\)(-.+)\\..+").matcher(f.getName());
            if (m.find()) {
                return m.group(1) + m.group(2);
            } else {
                return f.getName().substring(0, f.getName().lastIndexOf('.'));
            }
        }));

        String[] yuanNames = new String[yuanPics.length];
        for (int i = 0; i < yuanPics.length; i++) {
            yuanNames[i] = yuanPics[i].getName().substring(0, yuanPics[i].getName().lastIndexOf('.'));
        }
        String[] biNames = new String[biPics.length];
        for (int i = 0; i < biPics.length; i++) {
            Matcher m = Pattern.compile("(.+-.+?)\\(.+\\)(-.+)\\..+").matcher(biPics[i].getName());
            if (m.find()) {
                biNames[i] =  m.group(1) + m.group(2);
            } else {
                biNames[i] = biPics[i].getName().substring(0, biPics[i].getName().lastIndexOf('.'));
            }
        }
        int yuanCount = 0;
        int biCount = 0;
        int j = 0;
        for (int i = 0; i < yuanNames.length; i++) {
            if (j >= biNames.length) {
                for (int k = i; k < yuanNames.length; k++) {
                    System.out.println(++yuanCount + " yuan: " + yuanNames[k]);
                    yuanPics[k].renameTo(new File(yuanShanDir, yuanPics[k].getName()));
                }
                break;
            }
            int cha = yuanNames[i].compareTo(biNames[j]);
            if (cha == 0) {
                j++;
            } else if (cha > 0) {
                System.out.println(++biCount + " bizhi: " + biNames[j]);
                biPics[j].renameTo(new File(biShanDir, biPics[j].getName()));
                j++;
                i--;
            } else {
                System.out.println(++yuanCount + " yuan: " + yuanNames[i]);
                yuanPics[i].renameTo(new File(yuanShanDir, yuanPics[i].getName()));
            }
        }
        System.out.println("yuan: " + yuanCount + "; bi: " + biCount);

    }

}
