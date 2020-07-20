package cn.exception;

public class MyMathException extends RuntimeException {

    public MyMathException() {
        super();
    }

    public MyMathException(String msg) {
        super(msg);
        System.out.println("my math exception : " + msg);
    }
}
