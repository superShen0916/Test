package singleton;

/**
 * @Description: 公有的静态实例
 * @Author: shenpeng
 * @Date: 2019-09-30
 */
public class StaticInstance {

    public static StaticInstance instance = new StaticInstance();

    private StaticInstance() {

    }

    public volatile int num;

    public void addNum() {
        this.num++;
    }

    public static StaticInstance getInstance() {
        return instance;
    }

    public static void setInstance(StaticInstance instance) {
        StaticInstance.instance = instance;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
