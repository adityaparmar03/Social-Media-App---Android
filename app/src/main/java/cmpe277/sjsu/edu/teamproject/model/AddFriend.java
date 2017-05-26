package cmpe277.sjsu.edu.teamproject.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddFriend {

    @SerializedName("requester_emailid")
    @Expose
    private String requestor;

    @SerializedName("to_be_friend_emailid")
    @Expose
    private String toBeFriend;

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getToBeFriend() {
        return toBeFriend;
    }

    public void setToBeFriend(String toBeFriend) {
        this.toBeFriend = toBeFriend;
    }
}
