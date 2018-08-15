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
            Field f = list.getClass().getDeclaredField("elementData");
            f.setAccessible(true);
            Object[] objects = (Object[]) f.get(list);
            System.out.println(objects.length);
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(list.getClass());
        System.out.println(3 >> 1);
    }
}
