import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author xiakai
 * @create 2020-08-13 12:10
 */
public class ClassifyPicture {

    public static void main(String[] args) throws IOException {


        System.out.println("请输入图片目录：");
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        File pictureDir = new File(path);

        File heightDir = new File(pictureDir, "2160");
        File widthDir = new File(pictureDir, "3840");
        File originalDir = new File(pictureDir, "0000");

        if (!heightDir.exists()) {
            heightDir.mkdir();
            System.out.println("创建2160文件夹");
        } else {
            System.out.println("2160文件夹已存在");
        }
        if (!widthDir.exists()) {
            widthDir.mkdir();
            System.out.println("创建3840文件夹");
        } else {
            System.out.println("3840文件夹已存在");
        }
        if (!originalDir.exists()) {
            originalDir.mkdir();
            System.out.println("创建0000文件夹");
        } else {
            System.out.println("0000文件夹已存在");
        }

        int success = 0;
        int fail = 0;
        System.out.println("开始处理。。。");
        for (File file : pictureDir.listFiles()) {
            if (file.isFile()) {
                String fullName = file.getName();
                if (fullName.equals("desktop.ini")) {
                    System.out.println("存在desktop.ini文件");
                    if (file.delete()) {
                        System.out.println("desktop.ini已被删除");
                    } else {
                        System.out.println("desktop.ini删除失败");
                    }
                    continue;
                }
                int a = fullName.lastIndexOf('.');
                String name = fullName.substring(0, a);
                String suffix = fullName.substring(a + 1);
                int h;
                int w;
                try (FileImageInputStream fis = new FileImageInputStream(file)) {
                    ImageReader reader = ImageIO.getImageReadersBySuffix(suffix).next();
                    reader.setInput(fis);
                    h = reader.getHeight(reader.getMinIndex());
                    w = reader.getWidth(reader.getMinIndex());
                }

                if ((h == 2160 && w >= 3840) || (w == 3840 && h >= 2160)) {
                    if (file.renameTo(new File(originalDir, fullName))) {
                        System.out.println(name + " 被移入 0000");
                    } else {
                        System.out.println(name + " 移动失败");
                        fail++;
                    }
                } else if ((double) h / w > 0.5626) {
                    if (file.renameTo(new File(widthDir, fullName))) {
                        System.out.println(name + " 被移入 3840");
                        success++;
                    } else {
                        System.out.println(name + " 移动失败");
                        fail++;
                    }

                } else {
                    if (file.renameTo(new File(heightDir, fullName))) {
                        System.out.println(name + " 被移入 2160");
                    } else {
                        System.out.println(name + " 移动失败");
                        fail++;
                    }
                }
            }
        }
        System.out.println();
        System.out.println("---------------------------");
        System.out.println("成功：" + success + ", 失败：" + fail);
        System.out.println("END！");

    }

}
