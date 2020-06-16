package com.dingx.personal.common.annontation;

import java.lang.annotation.*;

/**
 * 忽略Token验证
 * @author dingx
 * @date 2020年6月9日 22:25:41
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreLogin {

}
