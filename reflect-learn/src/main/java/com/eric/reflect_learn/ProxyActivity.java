package com.eric.reflect_learn;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class ProxyActivity extends Activity {
    private Context mContext = this;
    private PackageInfo packageInfo;//包信息
    private DexClassLoader dexClassLoader;//类加载器
    private Resources resources;//资源包
    private String realActivityName = "com.eric.reflectable_plugin.MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadPluginApk();
        super.onCreate(savedInstanceState);
        try {// 原则，反射创建RealActivity对象，但是，去拿这个它的class，只能用dexClassLoader
            Class<?> realActivityClz = dexClassLoader.loadClass(realActivityName);
            Object obj = realActivityClz.newInstance();

            Method test = realActivityClz.getDeclaredMethod("test");
            test.invoke(obj);

            Method attach_method = realActivityClz.getDeclaredMethod("attach" , Activity.class);
            attach_method.invoke(obj,mContext);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public ClassLoader getClassLoader() {
        return dexClassLoader != null ? dexClassLoader : super.getClassLoader();
    }

    @Override
    public Resources getResources() {
        return resources != null ? resources : super.getResources();
    }

    public void loadPluginApk() {
        String apkPath = getApkPath();
        //先拿到包信息
        packageInfo = mContext.getPackageManager().getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);//只拿Activity
        if (packageInfo == null)
            throw new RuntimeException("插件加载失败");//如果apkPath是传的错的，那就拿不到包信息了，下面的代码也就不用执行

        //类加载器，DexClassLoader专门负责外部dex的类
        File outFile = mContext.getDir("odex", Context.MODE_PRIVATE);
        dexClassLoader = new DexClassLoader(apkPath, outFile.getAbsolutePath(), null, mContext.getClassLoader());

        //创建AssetManager，然后创建Resources
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            method.invoke(assetManager, apkPath);
            resources = new Resources(assetManager,
                    mContext.getResources().getDisplayMetrics(),
                    mContext.getResources().getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getApkPath(){
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
        return f.getPath();
    }
}