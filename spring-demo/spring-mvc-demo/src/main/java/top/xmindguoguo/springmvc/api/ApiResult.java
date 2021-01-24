package top.xmindguoguo.springmvc.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/24 23:29
 * @Version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult<T> implements Serializable {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private static Integer SUCCESS_CODE = 200;


    /**
     * 自定义code
     */
    private Integer code;
    /**
     * 提示消息
     */
    private String message;
    /**
     * 实体json数据
     */
    private T data;

    public static ApiResult sendOk(Object data) {
        return new ApiResult(SUCCESS_CODE, SUCCESS, data);
    }

    public static ApiResult sendOk(String message, Object data) {
        return new ApiResult(SUCCESS_CODE, message, data);
    }

    public static ApiResult sendMessage(String message) {
        return new ApiResult(SUCCESS_CODE, message, null);
    }
}
