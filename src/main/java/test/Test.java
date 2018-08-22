/**
 * Copyright (c) 2018 playcrab.All rights reserved.
 */

package test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * @date 2018-05-08
 * @author shenpeng
 */
public class Test {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 11; i++) {
            list.add(String.valueOf(i));
        }

        try {
            System.out.println(System.getProperty("java.class.path"));
            Field f = list.getClass().getDeclaredField("elementData");
            System.out.println(list.getClass().getName());
            System.out.println(list.getClass().getSimpleName());
            System.out.println(list.getClass().getClassLoader());
            System.out.println(list.getClass().getTypeName());
            f.setAccessible(true);
            Object[] objects = (Object[]) f.get(list);
            System.out.println(objects.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(list.getClass());
        System.out.println(3 >>> 2);
    }
}
