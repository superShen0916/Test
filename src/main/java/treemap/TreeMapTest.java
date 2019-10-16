package treemap;

import java.util.TreeMap;

import com.google.common.collect.Maps;

/**
 * @Description: 测试TreeMap的一些方法
 * @Author: shenpeng
 * @Date: 2019-10-16
 */
public class TreeMapTest {

    public static void main(String[] args) {
        TreeMap<Integer, Integer> treeMap = Maps.newTreeMap();
        treeMap.put(2, 2);
        treeMap.put(3, 3);
        treeMap.put(1, 100);

        System.out.println(treeMap.lowerEntry(1)); //null
        System.out.println(treeMap.floorEntry(1)); // 1=100
        System.out.println(treeMap.lowerEntry(0)); // null
        System.out.println(treeMap.floorEntry(0)); // null

        System.out.println(treeMap.firstKey()); // 1

        System.out.println(treeMap.headMap(2)); // {1=100}
        System.out.println(treeMap.headMap(1)); // {}
    }

}
