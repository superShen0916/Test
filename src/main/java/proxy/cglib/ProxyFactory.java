package proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2019-09-05
 *
 *        https://segmentfault.com/a/1190000011291179
 */
public class ProxyFactory implements MethodInterceptor {

    public Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        // 工具类
        Enhancer enhancer = new Enhancer();
        //设置父类 （认他作爹...）
        enhancer.setSuperclass(target.getClass());
        //设置回调函数
        enhancer.setCallback(this);
        //返回子类对象代理
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
            throws Throwable {
        System.out.println("before");
        Object returnValue = method.invoke(target, args);
        System.out.println("after");
        return returnValue;
    }
}
