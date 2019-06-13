
/**
 * Copyright (c) 2018 playcrab.All rights reserved.
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * 
 *
 * @date 2018-05-08
 * @author shenpeng
 */
public class Test {

    public static void main(String[] args) {

        // Player player = PlayerService.loadPlayerByUId(10033);
        ImmutableList<String> immutableList = ImmutableList.of("a", "b");
        ImmutableList<String> immutableList2 = ImmutableList.<String> builder().add("1").add("2")
                .build();
        immutableList.add("s");
        immutableList2.add("s");
        System.out.println(immutableList.toString());
        System.out.println(immutableList2.toString());

        Boolean a = false;
        Boolean b = new Boolean(false);
        System.out.println(a.equals(b)); // true

        //        String request = Joiner.on("&").join(null, "1");
        //        System.out.println(request);

        Map<String, String> map = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        String s = new String("1");
        String s2 = new String("1");

        map.put(s, s);
        map2.put(s2, s2);

        // System.out.println(map.equals(map2));
        Set<String> set = Sets.newHashSet();
        List<String> list = Lists.newArrayList();
        list.add("1");
        list.add("1");
        list.add("2");
        set.addAll(list);
        System.out.println(set.toString()); //   [1, 2]
        //        for (int i = 0; i < 100; i++) {
        //            try {
        //                System.out.println(i);
        //                Thread.sleep(1000);
        //
        //            } catch (InterruptedException e) {
        //                // TODO Auto-generated catch block
        //                e.printStackTrace();
        //            }
        //        }
        //        System.out.println(0);
        // System.out.println(11 + 'a');//11+97=108

        //        long t1 = System.currentTimeMillis();
        //        sum();
        //        long t2 = System.currentTimeMillis();
        //        System.out.println(t2 - t1);
        //
        //        long t3 = System.currentTimeMillis();
        //        sum();
        //        long t4 = System.currentTimeMillis();
        //        System.out.println(t4 - t3);

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
    //add 2
}
