package classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: 类加载器和instanceof
 * @Author: shenpeng
 * @Date: 2018/10/8
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {
        ClassLoader myClassLoader = new ClassLoader() {

            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";

                    InputStream inputStream = getClass().getResourceAsStream(fileName);
                    if (inputStream == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[inputStream.available()];
                    inputStream.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        ClassLoaderTest classLoaderTest = new ClassLoaderTest();

        Object object = myClassLoader.loadClass("classloader.ClassLoaderTest").newInstance();

        System.out.println(object.getClass());
        //object 是自定义的类加载器加载的
        System.out.println(object instanceof ClassLoaderTest);
    }
}
