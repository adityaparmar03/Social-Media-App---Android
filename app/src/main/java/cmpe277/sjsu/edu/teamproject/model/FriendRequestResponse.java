package cmpe277.sjsu.edu.teamproject.model;

/**
 * Created by adityaparmar on 5/23/17.
 */

public class FriendRequestResponse {

   // @SerializedName("friendrequestemailid")
    //@Expose
    private String friendrequestemailid;


   // @SerializedName("Friendrequestname")
   // @Expose
    private String Friendrequestname;

   // @SerializedName("Friendrequestprofilepicurl")
    //@Expose
    private String Friendrequestprofilepicurl;

    public String getFriendrequestemailid() {
        return friendrequestemailid;
    }

    public void setFriendrequestemailid(String friendrequestemailid) {
        this.friendrequestemailid = friendrequestemailid;
    }

    public String getFriendrequestname() {
        return Friendrequestname;
    }

    public void setFriendrequestname(String friendrequestname) {
        Friendrequestname = friendrequestname;
    }

    public String getFriendrequestprofilepicurl() {
        return Friendrequestprofilepicurl;
    }

    public void setFriendrequestprofilepicurl(String friendrequestprofilepicurl) {
        Friendrequestprofilepicurl = friendrequestprofilepicurl;
    }
}
