package cn.EnumTest;

public enum Idol {

    HONOKA("穗乃果", 18), KOTORI("南小鸟", 19), UMI("海未", 20);

    private String name;
    private int age;

    Idol(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public static void main(String[] args) {

        System.out.println(Idol.HONOKA.getAge());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Idol{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
