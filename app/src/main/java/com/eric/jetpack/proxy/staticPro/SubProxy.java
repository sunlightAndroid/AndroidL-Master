package com.eric.jetpack.proxy.staticPro;

/**
 * @Author: eric
 * @CreateDate: 3/29/21 1:46 PM
 * @Description: java类作用描述
 */
public class SubProxy implements Sub{
    SubImp subImp = new SubImp();
    @Override
    public void doSomething() {
        subImp.doSomething();
    }

    @Override
    public String tired() {
       return  subImp.tired();
    }
}
