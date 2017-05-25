package cmpe277.sjsu.edu.teamproject.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostPostBody {

    private String content;

    @SerializedName("emailid")
    @Expose
    private String emailId;

    @SerializedName("media_url")
    @Expose
    private String mediaURL;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMediaURL() {
        return mediaURL;
    }

    public void setMediaURL(String mediaURL) {
        this.mediaURL = mediaURL;
    }
}
