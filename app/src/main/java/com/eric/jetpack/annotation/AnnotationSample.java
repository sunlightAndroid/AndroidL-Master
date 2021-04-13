package com.eric.jetpack.annotation;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: eric
 * @CreateDate: 3/23/21 8:15 PM
 * @Description: java类作用描述
 */
public class AnnotationSample {

    public static final int TYPE_A = 1;
    public static final int TYPE_B = 2;
    public static final int TYPE_C = 3;

    private int type;

    @IntDef({TYPE_A,TYPE_B,TYPE_C})
    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE{

    }

    public void setType(@TYPE int type){
        this.type = type;
    }

    public int getType(){
        return  type;
    }


    public static void main(String[] args) {
        AnnotationSample sample = new AnnotationSample();
        sample.setType(TYPE_A);

        int type = sample.getType();
        System.out.println(type);
    }
}
