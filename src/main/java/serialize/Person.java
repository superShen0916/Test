package serialize;

import java.io.Serializable;

/**
 * @Description: 需要存盘的类
 * @Author: shenpeng
 * @Date: 2019-05-17
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 4531534556213008895L;

    int age;

    String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + "  " + age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
