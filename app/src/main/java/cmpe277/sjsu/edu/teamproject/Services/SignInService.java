package cmpe277.sjsu.edu.teamproject.Services;

import cmpe277.sjsu.edu.teamproject.model.SignInRequest;
import cmpe277.sjsu.edu.teamproject.model.StatusForAll;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by adityaparmar on 5/23/17.
 */

public interface SignInService {

    @POST("/signin")
    Call<StatusForAll> signin(@Body SignInRequest signInRequest);
}
