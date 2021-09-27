package com.hhj.studyinterface;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 点击事件的在注解
 * @author hhj
 */
@EventType(listenerType = View.OnClickListener.class,setListener = "setOnClickListener")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Onclick {
     @IdRes int[] value();
}
