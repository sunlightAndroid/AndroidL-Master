package com.eric.modulars;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.eric.modulars.service.LoginService;
import com.eric.modulars.service.LoginServiceImp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginServiceImp imp = new LoginServiceImp();
        imp.login();
        System.out.println("===================");

        // 反射获取
        try {
            Class<?> aClass = Class.forName("com.eric.modulars.service.LoginServiceImp");
            Method method = aClass.getDeclaredMethod("login");
            Object o = aClass.newInstance();
            method.invoke(o);


            // 或者这样
            LoginService service = (LoginService) o;
            service.login();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}