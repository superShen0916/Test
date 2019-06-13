package serialize;

import java.io.*;

/**
 * @Description: 测试不定义序列化参数会不会有问题
 * @Author: shenpeng
 * @Date: 2019-05-17
 */
public class SerializeTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("person.log");
        //序列化
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        Person person = new Person(1, "a");
        objectOutputStream.writeObject(person);
        objectOutputStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
        Object newPerson = inputStream.readObject();
        inputStream.close();
        System.out.println(newPerson);
    }
}
