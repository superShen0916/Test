package exception.trywithsource;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2019-06-05
 */
public class TryWithSource {

    public static void main(String[] args) {
        try (Connection connection = new Connection()) {
            connection.sendData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
