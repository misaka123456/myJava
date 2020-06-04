package cn.io;

import java.io.*;

/**
 * @author xiakai
 * @create 2020-06-03 15:57
 */
public class Test7_Serializable {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("test/src/cn/io/userObject.txt");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        User user = new User("迪丽热巴", 12, new Parent("玛尔扎哈"));

        oos.writeObject(user);
        oos.flush();
        oos.close();


        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        User newUser = (User) ois.readObject();
        System.out.println(newUser);
    }

    static class User implements Serializable {
        String name;
        Integer age;
        Parent father;

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", father=" + father +
                    '}';
        }

        public User(String name, Integer age, Parent father) {
            this.name = name;
            this.age = age;
            this.father = father;

        }
    }

    static class Parent implements Serializable {
        String name;
        Parent(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Parent{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
