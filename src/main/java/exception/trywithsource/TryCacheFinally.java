package exception.trywithsource;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2019-06-05
 */
public class TryCacheFinally {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = new Connection();
            connection.sendData();
            System.out.println(test());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试finally中语句的执行 -----》会执行return 2；
     * 
     * @param []
     * @return int
     * @Author: shenpeng
     * @Date: 2020-03-17
     */
    public static int test() {
        int a = 0;
        try {
            return a;
        } catch (Exception e) {

        } finally {
            a++;
            return 2;
        }
    }
}
