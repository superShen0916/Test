package test.lambda;

import java.util.HashMap;
import java.util.Map;

public class MapForeach {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("hello  %s lalala", "6");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        map.put("5", "5");

        map.forEach(System.out::printf);//PrintStream java.io.PrintStream.printf(String format, Object... args)  
        //printf接受两个参数，第一个作为String format 

        System.out.println();
        map.forEach((k, v) -> System.out.println(k + "  - " + v));
        //map.forEach(BiConsumer<? extends STRING, ? extends STRING>); 传入两个参数 key value ,
        System.out.println("------------");

        map.entrySet().forEach(System.out::println);//void java.lang.Iterable.forEach(Consumer<? super Entry<String, String>> action)

        map.entrySet().forEach(a -> System.out.println(a.getKey() + "==" + a.getValue()));
        System.out.println("-----------");

        map.entrySet().stream().forEach(System.out::println);//void java.util.stream.Stream.forEach(Consumer<? super Entry<String, String>> action)
        map.entrySet().stream().forEach(a -> System.out.println(a.getKey() + "==" + a.getValue()));
    }
}
