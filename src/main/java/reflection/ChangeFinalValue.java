package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @Description: 反射改变final值
 * @Author: shenpeng
 * @Date: 2019-12-26
 */
public class ChangeFinalValue {

    public static void main(String[] args) {
        System.out.println(Bean.str);
        Field field = null;
        try {
            field = Bean.class.getDeclaredField("obj");

            field.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(null, "s2");
            System.out.println(Bean.obj);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class Bean {

    //Stirng 和基本类型改不了
    public static final String str = "s1";

    //Object Integer等可以
    public static final Object obj = "s1";
}
