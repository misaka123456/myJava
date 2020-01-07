package cn.stream;

import sun.awt.Symbol;

import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintWriterTest {
    public static void main(String[] args) throws FileNotFoundException {

        PrintWriter writer = new PrintWriter("E:\\workspace\\myJava\\test\\src\\cn\\stream\\out.txt");
        writer.print("ashdfhjasdhfgdsafsadf");
        writer.close();



    }

}
