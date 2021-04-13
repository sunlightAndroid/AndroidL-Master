package com.eric.jetpack.proxy.dynamicPro;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: eric
 * @CreateDate: 3/29/21 2:40 PM
 * @Description: java类作用描述
 */
public class TestProxy {
    // https://blog.csdn.net/chris_hhao/article/details/106546671
    public <T> T create(final Class<T> clazz, Object realObj) {

        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before Proxy.newProxyInstance .... ");
                Object res =  method.invoke(realObj,args);
                System.out.println("end Proxy.newProxyInstance .... ");
                return res == null ? null : res.toString();
            }
        });
        return (T)o;
    }
}
