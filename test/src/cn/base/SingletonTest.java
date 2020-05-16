package cn.base;

public class SingletonTest {

    private static volatile SingletonTest instance;

    private SingletonTest() {
    }

    public static SingletonTest getInstance() {
        if (instance == null) {
            synchronized (SingletonTest.class) {
                if (instance == null) {
                    instance = new SingletonTest();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println("第" + finalI + "个： " + SingletonTest.getInstance());
            }).start();
        }
    }


}
