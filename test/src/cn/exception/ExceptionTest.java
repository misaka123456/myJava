package cn.exception;

public class ExceptionTest {


    public static void main(String[] args) {


        try {
            int i = 0;
            MyException ex = new MyException("hello exception");
            throw(ex);
        } catch (MyException e) {
            e.printStackTrace();
        } finally {
            System.out.println("ok");
        }
        System.out.println("end");

    }
}
