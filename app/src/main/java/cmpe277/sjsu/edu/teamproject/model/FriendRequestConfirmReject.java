package cmpe277.sjsu.edu.teamproject.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FriendRequestConfirmReject {

    @SerializedName("requestor_emailid")
    @Expose
    private String requestor;

    @SerializedName("sender_emailid")
    @Expose
    private String sender;

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

}
