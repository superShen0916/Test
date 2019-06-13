package thread;

public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal<String> sLocal = new ThreadLocal<>();
        sLocal.set("a");
        ThreadLocal<String> sLocal2 = new ThreadLocal<>();
        sLocal2.set("b");
        System.out.println(sLocal.get());
        //    public T get() {
        //        Thread t = Thread.currentThread();
        //        ThreadLocalMap map = getMap(t);
        //        if (map != null) {
        //            ThreadLocalMap.Entry e = map.getEntry(this);
        //            if (e != null) {
        //                @SuppressWarnings("unchecked")
        //                T result = (T)e.value;
        //                return result;
        //            }
        //        }
        //        return setInitialValue();
        //    }

    }
}
