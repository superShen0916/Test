package hotswap;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2020-05-06
 */
public class Service {

    int num;

    /**
     * 打印方法，热更测试用
     * 
     * @param []
     * @return void
     * @Author: shenpeng
     * @Date: 2020-05-06
     */
    public void print() {
        //  System.out.println("origin" + num);
        System.out.println("hotfixed" + num);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
