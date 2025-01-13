package org.winkey.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.winkey.validation.StateValidation;

import java.lang.annotation.*;

@Documented //元注解
@Target({ElementType.FIELD}) //元注解
@Retention(RetentionPolicy.RUNTIME) // 元注解
@Constraint(validatedBy = StateValidation.class) //制定校验规则类
public @interface State {
    //提供校验失败后的信息
    String message() default "{state参数只能是已发布或草稿}";
    //制定分组
    Class<?>[] groups() default {};
    // 获取到state的附加信息
    Class<? extends Payload>[] payload() default {};
}
