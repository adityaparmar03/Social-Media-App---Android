package cmpe277.sjsu.edu.teamproject.Services;

import cmpe277.sjsu.edu.teamproject.model.GenericPostResponse;
import cmpe277.sjsu.edu.teamproject.model.SignUpRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface SignUpService {

    @POST("/signup")
    Call<GenericPostResponse> signUp(@Body SignUpRequest signUpRequest);
}
