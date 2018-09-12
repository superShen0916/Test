package test.lambda;

import java.util.function.Function;

public class FuntionInterface {

    public static void main(String[] args) {
        Function<Integer, String> toString = (a) -> {
            System.out.println("toString");
            return a.toString();
        };
        Function<String, Boolean> print = (a) -> {
            System.out.println(a);
            return true;
        };

        toString.andThen(print).apply(123);
    }
}
