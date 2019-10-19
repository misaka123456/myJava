import java.io.File;
import java.util.Scanner;

public class FileAddArtistName {

    public static void main(String[] args) {

        Scanner cs = new Scanner(System.in);
        String path = cs.nextLine();
        File[] artistList = new File(path).listFiles();
        for (File artist : artistList) {
            String artistName = artist.getName();
            File[] picList = artist.listFiles();
            if (picList == null || picList.length == 0) {
                continue;
            }
            for (File pic : picList) {
                pic.renameTo(new File(path + "/" + artistName + "/" + artistName + "-" + pic.getName()));
                System.out.println(pic.getName()+"添加前缀："+artistName);
            }

        }

    }
}
