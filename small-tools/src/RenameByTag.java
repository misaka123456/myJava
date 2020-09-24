import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author xiakai
 * @create 2020-09-09 14:03
 */
public class RenameByTag {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输出配置文件夹");
        String confPath = sc.nextLine();


        String artistConf = confPath + "\\artist.txt";
        String titleConf = confPath + "\\title.txt";

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(artistConf)));

        Map<String, String> artistMap = new HashMap<>();
        // 读取标签文件的作者信息并且把\ 替换为,
        // 因为文件名不支持\
        String line;
        br.read();
        while ((line = br.readLine()) != null && line.length() != 0) {
            String musicOldName = line.substring(line.lastIndexOf('\\') + 1);
            line = br.readLine();
            String artistName = line.substring(line.indexOf('"') + 1, line.lastIndexOf('"'));
            artistName =  artistName
                    .replaceAll("/", "／")
                    .replaceAll("\\?", "？")
                    .replaceAll("<", "＜")
                    .replaceAll(">", "＞")
                    .replaceAll("\\*", "＊")
                    .replaceAll("\"", "“")
                    .replaceAll(":", "：");

            artistMap.put(musicOldName, artistName);

            br.readLine();
            br.readLine();
        }

//        for (String s : artistMap.keySet()) {
//            System.out.println(s + "  " + artistMap.get(s));
//        }
        br = new BufferedReader(new InputStreamReader(new FileInputStream(titleConf)));

        int falseCount = 0;
        br.read();
        while ((line = br.readLine()) != null && line.length() != 0) {
            File musicFile = new File(line);
            line = br.readLine();

            br.readLine();
            br.readLine();

            if (!musicFile.exists()) {
//                System.out.println(musicFile.getName() + " 不存在");
                continue;
            }
            String titleName = line.substring(line.indexOf('"') + 1, line.lastIndexOf('"'))
                    .replaceAll("/", "／")
                    .replaceAll("\\?", "？")
                    .replaceAll("<", "＜")
                    .replaceAll(">", "＞")
                    .replaceAll("\\*", "＊")
                    .replaceAll("\"", "“")
                    .replaceAll(":", "：");
            String musicNewName = artistMap.get(musicFile.getName()) + " - " + titleName + ".mp3";
            if (musicNewName.equals(musicFile.getName())) {
//                System.out.println(musicFile.getName() + " 未变");
                continue;
            }
            if (musicFile.renameTo(new File(musicFile.getParentFile(), musicNewName))) {
//                System.out.println(musicFile.getName() + " 被成功改名为：" + musicNewName);
            } else {
                falseCount++;
                System.out.println(musicFile.getName() + " 改名失败");
                System.out.println("新文件名为：" + musicNewName);
            }

        }
        System.out.println();
        System.out.println("---------------------------");
        System.out.println("失败： " + falseCount);
        System.out.println("END");

    }

}
