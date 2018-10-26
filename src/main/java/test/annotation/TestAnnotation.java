package test.annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

@ClassInfo("test class")
public class TestAnnotation {

    @FieldInfo({ 1, 2 })
    String fieldInfo = "fieldInfo";

    @FieldInfo(200)
    public int i = 100;

    @MethodInfo(name = "name", data = "data")
    private static String getMethodInfo() {
        return TestAnnotation.class.getSimpleName();
    }

    /**
     * @author shenpeng
     * @param args
     */
    public static void main(String[] args) {
        StringBuffer sBuffer = new StringBuffer();
        Class<?> cls = TestAnnotation.class;
        Constructor<?>[] constructors = cls.getConstructors();

        System.out.println(constructors[0]);
        //获取指定类型的注解
        sBuffer.append("class注解:").append("\n");
        ClassInfo classInfo = cls.getAnnotation(ClassInfo.class);
        if (classInfo != null) {
            sBuffer.append(Modifier.toString(cls.getModifiers())).append(" ").append("注解值: ")
                    .append(classInfo.value()).append("\n\n");
        }

        sBuffer.append("Field注解").append("\n");
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            FieldInfo fieldInfo = field.getAnnotation(FieldInfo.class);
            if (fieldInfo != null) {
                sBuffer.append(Modifier.toString(field.getModifiers())).append("  ||   ")
                        .append(field.getType().getSimpleName()).append(" ").append(field.getName())
                        .append("\n");
                sBuffer.append("注解值").append(Arrays.toString(fieldInfo.value())).append("\n\n");
            }
        }

        sBuffer.append("Method注解:").append("\n");
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);
            if (methodInfo != null) {
                sBuffer.append(Modifier.toString(method.getModifiers())).append("   ")
                        .append(method.getReturnType().getSimpleName()).append(" ")
                        .append(method.getName()).append("\n");
                sBuffer.append("注解值:").append("\n").append("name: ").append(methodInfo.name())
                        .append("\n").append("data: ").append(methodInfo.data()).append("\n")
                        .append("age: ").append(methodInfo.age()).append("\n");
            }
        }
        System.out.println(sBuffer.toString());
    }
}
