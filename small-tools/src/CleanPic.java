import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanPic {


    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入原图目录：");
        String originalPath = sc.nextLine();
        System.out.println("请输入原图修正目录：");
        String revisePath = sc.nextLine();
        System.out.println("请输入壁纸目录");
        String picturePath = sc.nextLine();
        System.out.println("请输入输出目录");
        String outPath = sc.nextLine();

        File originalOut = new File(outPath, "原图");
        if (!originalOut.exists()) {
            originalOut.mkdir();
        }
        File reviseOut = new File(outPath, "原图修正");
        if (!reviseOut.exists()) {
            reviseOut.mkdir();
        }
        File pictureOut = new File(outPath, "壁纸");
        if (!pictureOut.exists()) {
            pictureOut.mkdir();
        }


        Set<String> originalSet = new HashSet<>();
        Map<String, File> pictureMap = new HashMap<>();
        int originalDeleteCount = 0;    // 原图删除数
        int combineCount = 0;           //
        int pictureDeleteCount = 0;     //
        int reviseDeleteCount = 0;

        System.out.println("Start。。。");
        Pattern compile = Pattern.compile("combine(\\d+)");
        File pictureDir = new File(picturePath);

//        List<File> pictureList = new ArrayList<>();
//        List<File> originalList = new ArrayList<>();
//        List<File> reviseList = new ArrayList<>();

        int picReviseCount = 0;

        // 处理壁纸
        for (File pic : pictureDir.listFiles()) {
            String fileName = pic.getName();
            if (fileName.equals("desktop.ini")) {
                pic.delete();
                System.out.println(fileName + " is deleted");
                continue;
            }
            int a = fileName.lastIndexOf('.');
            String[] names = fileName.substring(0, a).split("-");
            Matcher matcher = compile.matcher(names[1]);
            if (names[1].indexOf("rotate") > 0
                    || names[1].indexOf("tailor") > 0
                    || names[1].indexOf("combine") > 0 ) {
                picReviseCount++;
            }
            names[1] = names[1].substring(0, names[1].indexOf('('));
            pictureMap.put(names[0] + "-" + names[1] + "-" + names[2], pic);
            if (matcher.find()) {
                names[1] = names[1].substring(0, names[1].lastIndexOf('p') + 1) + matcher.group(1);
                pictureMap.put(names[0] + "-" + names[1]+ "-" + names[2], null);
                combineCount++;
            }
        }

        // 清除原图
        File originalDir = new File(originalPath);
        for (File pic : originalDir.listFiles()) {
            String fileName = pic.getName();
            if (fileName.equals("desktop.ini")) {
                pic.delete();
                System.out.println(fileName + " is deleted");
                continue;
            }
            int a = fileName.lastIndexOf('.');
            String name = fileName.substring(0, a);
            if (!pictureMap.containsKey(name)) {
                pic.renameTo(new File(originalOut, fileName));
                System.out.println(name + " 被删除到 原图");
                originalDeleteCount++;
            } else {
                originalSet.add(name);
            }
        }

        // 清除壁纸
        for (Map.Entry<String, File> fileEntry : pictureMap.entrySet()) {
            if (fileEntry.getValue() != null && !originalSet.contains(fileEntry.getKey())) {
                fileEntry.getValue().renameTo(new File(pictureOut, fileEntry.getValue().getName()));
                System.out.println(fileEntry.getKey() + " 被删除到壁纸");
                pictureDeleteCount++;
            }
        }


        // 清除原图修正
        File reviseDir = new File(revisePath);
        for (File pic : reviseDir.listFiles()) {
            String fileName = pic.getName();
            if (fileName.equals("desktop.ini")) {
                pic.delete();
                System.out.println(fileName + " is deleted");
                continue;
            }
            int a = fileName.lastIndexOf('.');
            String[] names = fileName.substring(0, a).split("-");
            String name = names[0] + "-" + names[1].substring(0, names[1].indexOf('(')) + "-" + names[2];
            if (!originalSet.contains(name)) {
                pic.renameTo(new File(reviseOut, fileName));
                System.out.println(name + " 被删除到 原图修正");
                reviseDeleteCount++;
            }
        }


        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("原图删除数：" + originalDeleteCount);
        System.out.println("壁纸删除数：" + pictureDeleteCount);
        System.out.println("原图修正删除数：" + reviseDeleteCount);
        System.out.println("原图剩余数：" + (originalSet.size() - combineCount) + " 包含combine数量：" + combineCount);
        System.out.println("壁纸剩余数：" + (pictureMap.size() - combineCount - pictureDeleteCount) + " 包含修正数：" + picReviseCount);
        System.out.println("END!");
    }


}
