package cmpe277.sjsu.edu.teamproject.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FriendRequest {

    @SerializedName("screenname")
    @Expose
    private String screenName;

    @SerializedName("profile_pic")
    @Expose
    private String profileImageURL;

    @SerializedName("emailid")
    @Expose
    private String emailId;

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public String getEmail() {
        return emailId;
    }

    public void setEmail(String email) {
        this.emailId = email;
    }

}
