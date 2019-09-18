package integer;

/**
 * @Description: Integer的缓存
 * @Author: shenpeng
 * @Date: 2019-09-06
 */
public class CacheOfInteger {

    //+1
    /**
     * Integer会缓存-127到128之间的值，Integer.valueOf()返回的可能是缓存值
     *
     * @param [args]
     * @return void
     * @Author: shenpeng
     * @Date: 2019-09-06
     */
    public static void main(String[] args) {
        Integer a = Integer.valueOf(1);
        Integer b = Integer.valueOf(1);
        System.out.println(a == b); //true

        Integer c = Integer.valueOf(129);
        Integer d = Integer.valueOf(129);
        System.out.println(c == d); //false
    }

}
