package space.jachen.system.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import space.jachen.common.result.Result;
import space.jachen.common.result.ResultCodeEnum;
import space.jachen.system.execption.MyException;

import java.security.AccessControlException;

/**
 * 全局异常处理类
 *
 * @author JaChen
 * @date 2022/12/18 15:17
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * spring security异常
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result error(AccessDeniedException e) throws AccessDeniedException {
        return Result.build(null, ResultCodeEnum.PERMISSION);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){

        e.printStackTrace();

        return Result.fail();

    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return Result.fail().message("执行了特定异常处理");
    }


    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result error(MyException e){
        e.printStackTrace();
        return Result.fail().message(e.getMessage()).code(e.getCode());
    }

}
