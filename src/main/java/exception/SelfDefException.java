package exception;

/**
 * @Description: 自定义异常
 * @Author: shenpeng
 * @Date: 2019-06-06
 */
public class SelfDefException extends Exception {

    private static final long serialVersionUID = 3549619435483634775L;

    public SelfDefException(String message, Throwable cause) {
        super(message, cause);
    }
}
