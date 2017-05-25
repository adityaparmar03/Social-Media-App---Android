package cmpe277.sjsu.edu.teamproject.Services;

import cmpe277.sjsu.edu.teamproject.model.GenericPostResponse;
import cmpe277.sjsu.edu.teamproject.model.Verifyopt;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;



public interface VerifyoptService {

    @POST("/verifyotp")
    Call<GenericPostResponse> verify(@Body Verifyopt verifyopt);
}
