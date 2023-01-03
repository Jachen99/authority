package space.jachen.system.execption;

import lombok.Data;
import space.jachen.common.result.ResultCodeEnum;

/**
 * 自定义异常类
 *
 * @author JaChen
 * @date 2022/12/18 15:25
 */
@Data
public class MyException extends RuntimeException{


    private Integer code;

    private String message;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param code
     * @param message
     */
    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 接收枚举类型对象
     * @param resultCodeEnum
     */
    public MyException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "MyException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }

}
