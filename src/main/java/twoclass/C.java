package twoclass;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2020-04-25
 */
public class C {

    public static void main(String[] args) {
        son s = new son();
        parent p = (parent) s;
        p.print1();
    }
}

class parent {

    public void print1() {
        System.out.println("p1");
    }

    public static void print2() {
        System.out.println("p2");
    }
}

class son extends parent {

    @Override
    public void print1() {
        System.out.println("s1");
    }

    public static void print2() {
        System.out.println("s2");
    }
}
