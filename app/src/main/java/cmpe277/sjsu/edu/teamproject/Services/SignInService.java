package cmpe277.sjsu.edu.teamproject.Services;

import cmpe277.sjsu.edu.teamproject.model.GenericPostResponse;
import cmpe277.sjsu.edu.teamproject.model.SignInRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface SignInService {

    @POST("/signin")
    Call<GenericPostResponse> signIn(@Body SignInRequest signInRequest);
}
