package serialize;

import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.AtomicDouble;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: 测试不定义序列化参数会不会有问题
 * @Author: shenpeng
 * @Date: 2019-05-17
 */
public class SerializeTest {

    //    public static void main(String[] args) throws IOException, ClassNotFoundException {
    //        File file = new File("person.log");
    //        //序列化
    //        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
    //        Person person = new Person(1, "a");
    //        objectOutputStream.writeObject(person);
    //        objectOutputStream.close();
    //
    //        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
    //        Object newPerson = inputStream.readObject();
    //        inputStream.close();
    //        System.out.println(newPerson);
    //    }

    public static void main(String[] args) {
        Data data = new Data();
        data.getAtomicLong().set(100);
        data.getAtomicDouble().set(1.1);
        String s = JSONObject.toJSONString(data);
        Data data1 = JSONObject.parseObject(s, Data.class);
        System.out.println(data1.getAtomicLong().get());//100
        System.out.println(data1.getAtomicDouble().get());//1.1
    }

    static class Data {

        int a;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        AtomicDouble atomicDouble = new AtomicDouble();

        public AtomicDouble getAtomicDouble() {
            return atomicDouble;
        }

        public void setAtomicDouble(AtomicDouble atomicDouble) {
            this.atomicDouble = atomicDouble;
        }

        AtomicLong atomicLong = new AtomicLong();

        public Data() {
        }

        public Data(AtomicLong atomicLong) {
            this.atomicLong = atomicLong;
        }

        public AtomicLong getAtomicLong() {
            return atomicLong;
        }

        public void setAtomicLong(AtomicLong atomicLong) {
            this.atomicLong = atomicLong;
        }
    }

}
