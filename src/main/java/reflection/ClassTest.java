package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2020-02-05
 */
public class ClassTest {

    public static void main(String[] args)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException,
            NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Class c1 = Class.forName("reflection.Employee"); // 第1种，forName 方式获取Class对象
        Class c2 = Employee.class; // 第2种，直接通过类获取Class对象
        Employee employee = new Employee("小明", "18", "写代码", 1, "Java攻城狮", 100000);
        Class c3 = employee.getClass(); // 第3种，通过调用对象的getClass()方法获取Class对象

        if (c1 == c2 && c1 == c3) { // 可以通过 == 比较Class对象是否为同一个对象
            System.out.println("c1、c2、c3 为同一个对象");
            System.out.println(c1); // class reflect.Employee
        }

        //获取属性
        System.out.println(c1.getDeclaredField("empNo").get(employee));

        Method method = c2.getDeclaredMethod("sayHello");
        method.setAccessible(true);
        //调用方法
        System.out.println(method.invoke(employee));
    }
}
