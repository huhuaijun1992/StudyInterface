package com.hhj.studyinterface;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InjectUtils {

    private static final String TAG = "inject";

    public static void inject(Activity activity) {
        if (activity == null) {
            return;
        }
        Class<? extends Activity> clazz = activity.getClass();
        //获取类中的所有的方法
        Method[] methods = clazz.getDeclaredMethods();
        if (methods == null) {
            return;
        }
        for (Method method : methods) {
            //获取方法上的所有注解
            Annotation[] annotations = method.getDeclaredAnnotations();
            //判断是否存在注解
            if (annotations == null) {
                continue;
            }
            //遍历注解
            for (Annotation annotation: annotations) {
                //
              Class<? extends Annotation> annotationType = annotation.annotationType();
              if (annotationType.isAnnotationPresent(EventType.class)){
                  //获取具体的注解
                   EventType eventType =annotationType.getAnnotation(EventType.class);
                   //取值
                   Class listenerType =  eventType.listenerType();
                   Log.d(TAG, "inject: "+ listenerType);
                   String methodName = eventType.setListener();
                  try {
                   Method value =  annotationType.getDeclaredMethod("value");
                    int[] ids = (int[]) value.invoke(annotation);
                    method.setAccessible(true);
                    ListenerHandler handler = new ListenerHandler(method,activity);
                    Object proxy = Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{listenerType},handler );
                      for (int id: ids) {
                          View view = activity.findViewById(id);
                          Method method1  = view.getClass().getMethod(methodName,listenerType);
                          method1.invoke(view,proxy);
                      }

                  } catch (NoSuchMethodException e) {
                      e.printStackTrace();
                  } catch (IllegalAccessException e) {
                      e.printStackTrace();
                  } catch (InvocationTargetException e) {
                      e.printStackTrace();
                  }
              }
            }
        }
    }
    static  class  ListenerHandler<T> implements InvocationHandler{
        private  Method method;
        private T target;

        public ListenerHandler(Method method, T target) {
            this.method = method;
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return this.method.invoke(target,args);
        }
    }
}
