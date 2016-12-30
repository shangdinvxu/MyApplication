package tarce.myodooapplication.Http.RetrofitUtils.RetrofitApi;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import tarce.myodooapplication.Http.RetrofitUtils.bean.User;

/**
 * Created by Daniel.Xu on 2016/12/12.
 */

public interface GitHubService {
    @GET("users/{user}")
    Observable<User> getUserInformation(@Path("user") String user);
}
