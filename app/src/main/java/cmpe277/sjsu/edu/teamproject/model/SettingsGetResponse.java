package cmpe277.sjsu.edu.teamproject.model;

/**
 * Created by adityaparmar on 5/24/17.
 */

public class SettingsGetResponse {

    private String status;
    private String msg;
    private Boolean visibility;
    private Boolean notification;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public Boolean getNotification() {
        return notification;
    }

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }
}
