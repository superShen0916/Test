/**
 * Copyright (c) 2018 playcrab.All rights reserved.
 */

package test;

/**
 * 
 *
 * @date 2018-05-08
 * @author shenpeng
 */
public class Test {
    public static void main(String[] args) {
        Boolean a = false;
        Boolean b = new Boolean(false);
        System.out.println(a.equals(b));

        //        String request = Joiner.on("&").join(null, "1");
        //        System.out.println(request);


        long t1 = System.currentTimeMillis();
        sum();
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

        long t3 = System.currentTimeMillis();
        sum();
        long t4 = System.currentTimeMillis();
        System.out.println(t4 - t3);
    }

    /**
     * Hideously slow! Can you spot the object creation?
     * 
     * @return
     */
    private static long sum() {
        Long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }

    private static long sum2() {
        long sum = 0;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }
    //    public static void main(String[] args) {
    //        List<String> stringArrayList = new ArrayList<String>();
    //        List<Integer> integerArrayList = new ArrayList<Integer>();
    //
    //        Class classStringArrayList = stringArrayList.getClass();
    //        Class classIntegerArrayList = integerArrayList.getClass();
    //
    //        System.out.println(classIntegerArrayList);
    //        System.out.println(classStringArrayList);
    //
    //        if (classStringArrayList.equals(classIntegerArrayList)) {
    //            System.out.println("xiangtong");
    //        }
    //
    //        Generic<Integer> gInteger = new Generic<Integer>();
    //        Generic<Number> gNumber = new Generic<Number>();
    //
    //        showKeyValue1(gInteger);
    //    }

    public static void showKeyValue1(Generic<?> obj) {
        System.out.println("泛型测试" + "key value is ");
    }
}

class Generic<Number> {

}
