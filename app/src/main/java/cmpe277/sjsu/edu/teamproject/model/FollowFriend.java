package cmpe277.sjsu.edu.teamproject.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FollowFriend {

    @SerializedName("current_user")
    @Expose
    private String requestor;

    @SerializedName("followed_emailid")
    @Expose
    private String toBeFollowed;

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getToBeFollowed() {
        return toBeFollowed;
    }

    public void setToBeFollowed(String toBeFollowed) {
        this.toBeFollowed = toBeFollowed;
    }
}
