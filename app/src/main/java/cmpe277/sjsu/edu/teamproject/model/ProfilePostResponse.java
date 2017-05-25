package cmpe277.sjsu.edu.teamproject.model;


public class ProfilePostResponse {

    private String screenname;
    private String date;
    private String profilepicurl;
    private String postpicurl;
    private String posttext;


    public String getScreenname() {
        return screenname;
    }

    public void setScreenname(String screenname) {
        this.screenname = screenname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProfilepicurl() {
        return profilepicurl;
    }

    public void setProfilepicurl(String profilepicurl) {
        this.profilepicurl = profilepicurl;
    }

    public String getPostpicurl() {
        return postpicurl;
    }

    public void setPostpicurl(String postpicurl) {
        this.postpicurl = postpicurl;
    }

    public String getPosttext() {
        return posttext;
    }

    public void setPosttext(String posttext) {
        this.posttext = posttext;
    }
}
