package cmpe277.sjsu.edu.teamproject.Services;

import cmpe277.sjsu.edu.teamproject.model.SettingsPostRequest;
import cmpe277.sjsu.edu.teamproject.model.SettingsGetResponse;
import cmpe277.sjsu.edu.teamproject.model.StatusForAll;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by adityaparmar on 5/23/17.
 */

public interface SettingsService {

    @GET("/settings/{emailid}")
    Call<SettingsGetResponse> getallSettings(@Path("emailid") String emailid);

    @POST("/settings/update")
    Call<StatusForAll> postallSettings(@Body SettingsPostRequest settingsPostRequest);

}
