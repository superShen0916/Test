package test;

public class D extends A {

    String id;

    public D(String id) {
        this.id = id;
    }

    @Override
    void say(String s) {

    }

    @Override
    void say2() {
        System.out.println("d");
    }

    //    public static void main(String[] args) {
    //        A a = new C();
    //
    //    }
}
