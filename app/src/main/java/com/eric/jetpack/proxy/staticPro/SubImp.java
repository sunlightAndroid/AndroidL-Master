package com.eric.jetpack.proxy.staticPro;

/**
 * @Author: eric
 * @CreateDate: 3/29/21 1:43 PM
 * @Description: java类作用描述
 */
public class SubImp implements Sub {
    @Override
    public void doSomething() {
       System.out.println("SubImp : doSomething()");
    }

    @Override
    public String tired() {
        System.out.println("SubImp : tired()");
        return "------------";
    }
}
