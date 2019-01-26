package test.effectiveJava.comparable;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2019/1/22
 */
public class CompareClient {

    public static void main(String[] args) {
        Value v1 = new Value(2, 2);
        Value v2 = new Value(4, 4);
        Value v3 = new Value(3, 3);

        List<Value> list = Lists.newArrayList();

        list.add(v1);
        list.add(v2);
        list.add(v3);

        List<Integer> list2 = Lists.newArrayList();

        list2.add(3);
        list2.add(4);
        list2.add(2);

        Set<Integer> set = Sets.newHashSet();
        set.add(3);
        set.add(4);
        set.add(2);

        Set<Integer> set2 = Sets.newLinkedHashSet();
        set2.add(3);
        set2.add(4);
        set2.add(2);

        Set<Integer> set3 = Sets.newTreeSet();
        set3.add(3);
        set3.add(4);
        set3.add(2);

        Set<Value> set4 = Sets.newHashSet();
        set4.add(v1);
        set4.add(v2);
        set4.add(v3);

        Set<Value> set5 = Sets.newTreeSet();
        set5.add(v1);
        set5.add(v2);
        set5.add(v3);

        //list必须手动sort
        Collections.sort(list);
        Collections.sort(list2);_
        System.out.println(list);
        System.out.println(list2);

        System.out.println("hashSet        " + set);
        //linkedHashSet 是有序的，保证了元素插入的顺序
        System.out.println("linkedHashSet  " + set2);
        System.out.println("treeSet        " + set3);
        System.out.println(set4);
        //treeSet 自动调用compareTo方法
        System.out.println(set5);

    }
}
