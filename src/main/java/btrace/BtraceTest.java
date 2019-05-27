package btrace;

import static com.sun.btrace.BTraceUtils.heapUsage;
import static com.sun.btrace.BTraceUtils.println;

import com.sun.btrace.annotations.*;

/**
 * @Description: btrace追踪脚本
 * @Author: shenpeng
 * @Date: 2019/1/9
 */

@BTrace
public class BtraceTest {

    private static long count;

    //    static {
    //        println("---------------------------JVM properties:---------------------------");
    //        printVmArguments();
    //        println("---------------------------System properties:------------------------");
    //        printProperties();
    //        println("---------------------------OS properties:----------------------------");
    //        printEnv();
    //        exit();
    //    }

    @OnMethod(clazz = "test.btrace.Calculator", method = "add", location = @Location(Kind.RETURN))
    public static void trace1(int a, int b, @Return int sum, @Duration long duration) {
        println("trace1:a=" + a + ",b=" + b + ",sum=" + sum + ",duration=" + duration);
    }

    @OnTimer(4000)
    public static void trace2() {
        println(heapUsage());
    }

}
