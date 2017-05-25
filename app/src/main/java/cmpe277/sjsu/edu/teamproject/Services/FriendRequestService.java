package cmpe277.sjsu.edu.teamproject.Services;

import java.util.List;

import cmpe277.sjsu.edu.teamproject.model.FriendRequestDecisionRequest;
import cmpe277.sjsu.edu.teamproject.model.FriendRequestResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by adityaparmar on 5/23/17.
 */

public interface FriendRequestService {

    @GET("/friendrequests/{emailid}")
    Call<List<FriendRequestResponse>> getAllfriendsRequests(@Path("emailid") String emailid);

    @POST("/friendrequests/decision/{emailid}")
    Call<ResponseBody> friendrequestdecision(@Path("emailid") String emailid, @Body FriendRequestDecisionRequest friendRequestDecisionRequest);


}
