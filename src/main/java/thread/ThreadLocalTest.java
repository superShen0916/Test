package thread;

/**
 * ThreadLocal提供了线程独有的局部变量，可以在整个线程存活的过程中随时取用，极大地方便了一些逻辑的实现.
 * 
 * 每个Thread内部都有一个ThreadLocalMap，我们调用ThreadLoacl的set方法的时候,
 * 其实是把这个ThreadLocal的弱引用当成keyset的值当成value存到ThreadLocalMap中。
 * 每当使用ThreadLocal的get(key)，寻找ThreadLocal实例的弱引用对应的value。
 * 
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal<String> sLocal = new ThreadLocal<>();
        sLocal.set("a");
        ThreadLocal<String> sLocal2 = new ThreadLocal<>();
        sLocal2.set("b");

        //这时候ThreadLocalMap里就有两个<k,v>,分别用sLocal和sLocal2的弱引用当key.
        System.out.println(sLocal.get());
        System.out.println(sLocal2.get());

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
