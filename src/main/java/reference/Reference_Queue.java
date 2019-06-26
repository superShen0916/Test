package reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @Description: 引用队列
 * @Author: shenpeng
 * @Date: 2019-06-18
 */
public class Reference_Queue {

    Object counter = new Object();

    ReferenceQueue referenceQueue = new ReferenceQueue();

    PhantomReference<Object> p = new PhantomReference<>(counter, referenceQueue);

}
