package cn.base;

/**
 * @author xiakai
 * @create 2020-06-28 11:34
 */
public class StaticTest {

    public static void main(String[] args) {
        Child child = new Child();
        System.out.println("--------------");
        Child child1 = new Child();


    }


    static class Father {

        static {
            System.out.println("father static code 1");
        }


        static int a = getA();

        int b = getB();

        static {
            System.out.println("father static code 2");
            System.out.println("father a = " + a);
        }


        private static int getA() {
            System.out.println("father static a = 1");
            return 1;
        }

        private int getB() {
            System.out.println("father b = 2");
            return 2;
        }
    }

    static class Child extends Father {

        static {
            System.out.println("Child static code 1");
        }

        static int a = getA();

        int b = getB();

        static {
            System.out.println("Child static code 2");
        }


        private static int getA() {
            System.out.println("Child static a = 11");
            return 11;
        }

        private int getB() {
            System.out.println("Child static b = 22");
            return 22;
        }
    }
}
