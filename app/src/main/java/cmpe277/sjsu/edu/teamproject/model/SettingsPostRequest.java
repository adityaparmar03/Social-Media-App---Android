package cmpe277.sjsu.edu.teamproject.model;

/**
 * Created by adityaparmar on 5/23/17.
 */

public class SettingsPostRequest {

    private String emailid;
    private Boolean visibility;
    private Boolean notification;


    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public Boolean getNotification() {
        return notification;
    }

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }
}
