package cmpe277.sjsu.edu.teamproject.Services;


import cmpe277.sjsu.edu.teamproject.model.GenericPostResponse;
import cmpe277.sjsu.edu.teamproject.model.PostPostBody;
import cmpe277.sjsu.edu.teamproject.model.Timeline;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TimelineFeedService {

    @GET("/posts/{emailid}")
    Call<Timeline> getFeed(@Path("emailid") String emailId);

    @POST("/posts/add")
    Call<GenericPostResponse> postPost(@Body PostPostBody postPostBody);
}
