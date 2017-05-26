package cmpe277.sjsu.edu.teamproject.Services;

import java.util.List;

import cmpe277.sjsu.edu.teamproject.model.AddFriend;
import cmpe277.sjsu.edu.teamproject.model.FollowFriend;
import cmpe277.sjsu.edu.teamproject.model.FriendRequest;
import cmpe277.sjsu.edu.teamproject.model.FriendRequestConfirmReject;
import cmpe277.sjsu.edu.teamproject.model.GenericPostResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface FriendRequestService {

    @GET("/fetchPendingRequests/{emailid}")
    Call<List<FriendRequest>> fetchPendingRequests(@Path("emailid") String emailId);

    @GET("/fetchSentRequests/{emailid}")
    Call<List<FriendRequest>> fetchSentRequests(@Path("emailid") String emailId);

    @GET("/publicProfiles")
    Call<List<FriendRequest>> getAllPublicProfiles();

    @POST("/confirmPendingFrndRequest")
    Call<GenericPostResponse> confirmPendingFriendRequest(@Body FriendRequestConfirmReject FriendRequestConfirmReject);

    @POST("/rejectPendingFrndRequest")
    Call<GenericPostResponse> rejectPendingFrndRequest(@Body FriendRequestConfirmReject FriendRequestConfirmReject);

    @GET("fetchFriendsDtls/{emailid}")
    Call<List<FriendRequest>> fetchFriendsDtls(@Path("emailid") String emailId);

    @POST("/addFrndForExistingUser")
    Call<GenericPostResponse> addFrndForExistingUser(@Body AddFriend addFriend);

    @POST("/followUser")
    Call<GenericPostResponse> followUser(@Body FollowFriend followFriend);

}
