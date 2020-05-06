package hotswap;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2020-05-06
 */
public class HotSwapAgent {

    public static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation i) {
        System.out.println("premain");
        instrumentation = i;
    }

    public static void reload(Class<?> clazz, byte[] data)
            throws UnmodifiableClassException, ClassNotFoundException {
        ClassDefinition classDefinition = new ClassDefinition(clazz, data);
        instrumentation.redefineClasses(new ClassDefinition[] { classDefinition });
    }
}
