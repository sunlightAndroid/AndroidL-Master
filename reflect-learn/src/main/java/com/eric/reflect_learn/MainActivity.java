package com.eric.reflect_learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadLocalPlugin();
        loadFromPlugin();
        findViewById(R.id.tv_action).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, ProxyActivity.class));
        });
    }

    private void loadLocalPlugin() {
        try {
            Class aClass = Class.forName("com.eric.reflect_learn.hide.Utils");
            // 获取第一个构造函数
            Constructor declaredConstructor = aClass.getDeclaredConstructors()[0];
            declaredConstructor.setAccessible(true);
            // 通过构造函数的方式去获取对象
            Object object = declaredConstructor.newInstance();
            // 获取方法
            String params = "有参数的";
            Method method_find = aClass.getDeclaredMethod("find", String.class);
            method_find.setAccessible(true);
            method_find.invoke(object, params);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    private void loadFromPlugin() {
        System.out.println("loadFromPlugin: 从插件加载");
        File f = new File(getCacheDir() + "/reflectable_plugin-debug.apk");
        if (!f.exists()) {
            try {
                InputStream is = getAssets().open("apk/reflectable_plugin-debug.apk");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(buffer);
                fos.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                DexClassLoader dexClassLoader = new DexClassLoader(f.getPath(), getCodeCacheDir().getPath(), "", null);
                Class aClass = dexClassLoader.loadClass("com.eric.reflectable_plugin.Utils");
                Constructor declaredConstructor = aClass.getDeclaredConstructors()[0];
                Object object = declaredConstructor.newInstance();
                Method find = aClass.getDeclaredMethod("find");
                find.invoke(object);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }


}