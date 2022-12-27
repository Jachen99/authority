package space.jachen.system.annotation;

/**
 *
 * 自定义操作日志记录注解
 *
 * @author JaChen
 * @date 2022/12/26 12:00
 */

import space.jachen.system.enums.BusinessType;
import space.jachen.system.enums.OperatorType;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块名称
     */
    String title() default "";

    /**
     * 业务操作类型
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求参数
     */
    boolean isSaveRequestData() default false;

    /**
     * 是否保存响应数据
     */
    boolean isSaveResponseData() default true;
}