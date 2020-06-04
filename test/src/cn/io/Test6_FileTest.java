package cn.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

/**
 * @author xiakai
 * @create 2020-06-03 15:15
 */
public class Test6_FileTest {

    public static void main(String[] args) {


        File[] roots = File.listRoots();  // 列举所有根目录
        System.out.println(Arrays.toString(roots)); // [C:\, D:\, E:\, F:\, G:\, I:\]

        File dRoot = new File("D:\\");
        String[] list = dRoot.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                // dir == dRoot
                System.out.println(name);
                return true;
            }
        });
        System.out.println(Arrays.toString(list));

    }

}
