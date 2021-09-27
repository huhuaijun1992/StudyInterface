package com.hhj.studyinterface;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**事件类型定义
 * ElementType.ANNOTATION_TYPE 是对注解进行注解
 * @author hhj
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventType {
    Class listenerType();
    String setListener();
}
