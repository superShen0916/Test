package serialize;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2020-02-05
 */

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

public class SerializeUtils {

    /**
     * *序列化方法
     */
    public static <T> byte[] serialize(T t, Class<T> clazz) {
        return ProtobufIOUtil.toByteArray(t, RuntimeSchema.createFrom(clazz),
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
    }

    /**
     * *反序列化方法
     */
    public static <T> T deSerialize(byte[] data, Class<T> clazz) {
        RuntimeSchema<T> runtimeSchema = RuntimeSchema.createFrom(clazz);
        T t = runtimeSchema.newMessage();
        ProtobufIOUtil.mergeFrom(data, t, runtimeSchema);
        return t;
    }

    public static void main(String[] args) {
        String[] arr = new String[] { "a", "sc" };
        Person person = new Person(1, "2", arr);
        byte[] bytes = serialize(person, Person.class);
        Person person1 = deSerialize(bytes, Person.class);
        System.out.println(person1.getAge());
        System.out.println(person1.getName());
        System.out.println(person1.getArr()[1]);
    }
}
