package test;

public class D {

    public static void main(String[] args) {
        A a = new A() {

            @Override
            void say(String s) {
                // TODO Auto-generated method stub
                System.out.println(s);
            }
        };
        a.say("s");
    }
}
