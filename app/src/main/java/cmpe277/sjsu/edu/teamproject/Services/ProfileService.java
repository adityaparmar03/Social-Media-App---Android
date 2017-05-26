package cmpe277.sjsu.edu.teamproject.Services;


import cmpe277.sjsu.edu.teamproject.model.GenericPostResponse;
import cmpe277.sjsu.edu.teamproject.model.UpdateProfile;
import cmpe277.sjsu.edu.teamproject.model.UserProfile;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProfileService {

    @GET("/getUserProfile/{emailid}")
    Call<UserProfile> getUserProfile(@Path("emailid") String emailId);

    @POST("/updateprofile")
    Call<GenericPostResponse> updateprofile(@Body UpdateProfile updateProfile);


}
