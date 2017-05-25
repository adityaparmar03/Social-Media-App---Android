package cmpe277.sjsu.edu.teamproject.Services;

import cmpe277.sjsu.edu.teamproject.model.GenericPostResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;



public interface Verifyopt {

    @POST("/verifyotp")
    Call<GenericPostResponse> signup(@Body Verifyopt verifyopt);
}
