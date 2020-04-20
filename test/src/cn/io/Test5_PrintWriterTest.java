package cn.io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Test5_PrintWriterTest {
    public static void main(String[] args) throws FileNotFoundException {

        PrintWriter writer = new PrintWriter("test\\src\\cn\\stream\\out.txt");
        writer.print("ashdfhjasdhfgdsafsadf");
        writer.close();



    }

}
