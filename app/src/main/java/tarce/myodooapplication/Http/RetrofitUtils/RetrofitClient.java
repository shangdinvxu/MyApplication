package tarce.myodooapplication.Http.RetrofitUtils;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Daniel.Xu on 2016/12/15.
 */

public class RetrofitClient {
    private  static  Retrofit retrofit;

   private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                //设置OKHttpClient
                .client(new OKHttpFactory().getOkHttpClient())

                //baseUrl
                .baseUrl("https://api.github.com/")
                //gson转化器
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static Retrofit getInstance() {
        if (retrofit==null){
            new RetrofitClient();
        }
        return retrofit;
    }
}
