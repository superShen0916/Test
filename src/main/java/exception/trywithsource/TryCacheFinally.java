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
}
