package cn.base;

public class StringTest {

    public static void main(String[] args) {


        String a = "12345";
        System.out.println(a.substring(0, 1));  // 1
        System.out.println(a.substring(0, 5));  // 12345

        System.out.println("a" + "b" == "ab");  // true

        StringBuilder stringBuilder = new StringBuilder("a");
        System.out.println(stringBuilder.toString() == "a"); // false

        Integer integer1 = new Integer(1);
        System.out.println(integer1 == 1);          //true

        Integer integer2 = new Integer(1);
        System.out.println(integer2 == new Integer(1)); //false

        Integer i1 = 2;
        Integer i2 = 2;
        System.out.println(i1 == i2); // true  -127 ~ 128
        i1 = 129;
        i2 = 129;
        System.out.println(i1 == i2); // false

        String s = new String("aaa");
        System.out.println(s == "aaa");         // false
        System.out.println(s.intern() == "aaa");       //true


        StringBuilder sb = new StringBuilder();
        System.out.println(sb.append(1111).append("--").append(true).append(false).toString());
        // 1111--truefalse
    }
}
