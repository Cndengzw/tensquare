package entity;

/**
 * @author Deng Zhiwen
 * @date 2021/4/5 15:11
 */
public class PageResult2<T> extends Result {


    public PageResult2(Boolean flag, Integer code, String message, Object data) {
        super(flag, code, message, data);
    }

    public PageResult2(Boolean flag, Integer code, String message) {
        super(flag, code, message);
    }
}
