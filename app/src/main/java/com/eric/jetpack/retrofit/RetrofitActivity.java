package com.eric.jetpack.retrofit;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eric.jetpack.R;
import com.eric.jetpack.retrofit.service.ApiService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author: eric
 * @CreateDate: 3/10/21 11:15 AM
 * @Description: java类作用描述
 */
public class RetrofitActivity extends AppCompatActivity {
    private final String baseUrl = "https://blog.csdn.net/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        String query = "1001.2014.3001.5501";
        String id = "-1";
        apiService.getData(id,query).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String name = Thread.currentThread().getName();
                ResponseBody body = response.body();
                String string = body.toString();
                System.out.println(body.toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                String message = t.getMessage();
                System.out.println(message);
            }
        });
    }
}
