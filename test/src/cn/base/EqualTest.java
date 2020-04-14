package cn.base;

public class EqualTest {

    public static void main(String[] args) {

        System.out.println("a" == "a"); // true
        System.out.println("a" == new String("a"));
        System.out.println("a" == new String("a").toString());
        System.out.println(new String("a") == new String("a"));
        System.out.println("a" == new StringBuilder("a").toString());
        System.out.println("1"+"3"+new String("1")+"4" == "1234");
        System.out.println("1"+"3"+new String("1")+"4" == "1"+"3"+new String("1")+"4");

    }
}
