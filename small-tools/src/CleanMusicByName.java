import java.io.File;
import java.util.Scanner;

/**
 * @author xiakai
 * @create 2020-05-23 19:34
 */
public class CleanMusicByName {

    public static void main(String[] args) {

        System.out.println("请输入目录");
        Scanner cs = new Scanner(System.in);
        String musicPath = cs.nextLine();

        if (musicPath == null || musicPath.length() == 0) {
            System.out.println("非法");
            return;
        }
        File[] musicList = new File(musicPath).listFiles();

        for (File music : musicList) {
            if (music.isDirectory()) {
                continue;
            }
            String name = music.getName();
//            System.out.println(name);
            String artist = name.substring(0, name.indexOf(" - "));
            File artistDir = new File(musicPath, artist);
            if (!artistDir.exists()) {
                artistDir.mkdirs();
            }
            music.renameTo(new File(artistDir, name));
            System.out.println(name + " 移入：" + artistDir.getName());
        }
        System.out.println("end!");
    }

}
