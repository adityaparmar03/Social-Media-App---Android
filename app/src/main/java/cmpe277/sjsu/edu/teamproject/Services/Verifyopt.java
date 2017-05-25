package cmpe277.sjsu.edu.teamproject.Services;

import cmpe277.sjsu.edu.teamproject.model.StatusForAll;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by adityaparmar on 5/24/17.
 */

public interface Verifyopt {
    @POST("/verifyotp")
    Call<StatusForAll> signup(@Body Verifyopt verifyopt);
}
