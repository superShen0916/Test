/**
 * Copyright (c) 2018 playcrab.All rights reserved.
 */

package test;

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
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();

        System.out.println(classIntegerArrayList);
        System.out.println(classStringArrayList);

        if (classStringArrayList.equals(classIntegerArrayList)) {
            System.out.println("xiangtong");
        }

        Generic<Integer> gInteger = new Generic<Integer>();
        Generic<Number> gNumber = new Generic<Number>();

        showKeyValue1(gInteger);
    }

    public static void showKeyValue1(Generic<?> obj) {
        System.out.println("泛型测试" + "key value is ");
    }
}

class Generic<Number> {

}
