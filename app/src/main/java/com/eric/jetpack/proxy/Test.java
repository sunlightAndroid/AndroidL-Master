package com.eric.jetpack.proxy;

import com.eric.jetpack.proxy.dynamicPro.TestProxy;
import com.eric.jetpack.proxy.staticPro.Sub;
import com.eric.jetpack.proxy.staticPro.SubImp;
import com.eric.jetpack.proxy.staticPro.SubProxy;

/**
 * @Author: eric
 * @CreateDate: 3/29/21 1:41 PM
 * @Description: java类作用描述
 */
class Test {
    public static void main(String[] args) {
//        Sub sub = new SubImp();
//        sub.doSomething();

//        Sub sub = new SubProxy();
//        sub.doSomething();

        TestProxy testProxy = new TestProxy();
        Sub sub1 = testProxy.create(Sub.class, new SubImp());
        sub1.doSomething();
        String tired = sub1.tired();
        System.out.println("tired() return: " + tired);
    }
}
