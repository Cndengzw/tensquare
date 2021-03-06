package entity;

import lombok.AllArgsConstructor;
import lombok.Data;




/**
 * @author Deng Zhiwen
 * @date 2021/4/5 14:56
 */
@Data
@AllArgsConstructor
public class Result {
    private Boolean flag;   // 是否成功
    private Integer code;   // 返回码
    private String message;   // 返回信息
    private Object data;    // 返回数据

    public Result(Boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

}
