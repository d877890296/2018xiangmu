package com.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NetJsonFiled {
	public String name() default "";
	//默认不忽略错误，如果没有这个字段会报错
	//public int error_ignore() default 0;
	public String defaultValue() default "";
	
	public String objClassName() default "";
	
}
