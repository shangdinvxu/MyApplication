package tarce.myodooapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import tarce.myodooapplication.Http.RetrofitUtils.RetrofitApi.GitHubService;
import tarce.myodooapplication.Http.RetrofitUtils.RetrofitApi.RequestFactory;
import tarce.myodooapplication.Http.RetrofitUtils.RetrofitClient;
import tarce.myodooapplication.Http.RetrofitUtils.bean.User;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.textView)
    TextView textView;
    @InjectView(R.id.button)
    Button button;
    @InjectView(R.id.activity_main)
    RelativeLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.button)
    void setButton(View view) {
        RequestFactory.getInstance().getUserInformation("shangdinvxu")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        textView.setText(e.toString());
                    }

                    @Override
                    public void onNext(User user) {
                        textView.setText(user.getName());
                    }
                });
    }
}
