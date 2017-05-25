package cmpe277.sjsu.edu.teamproject.model;


public class FriendRequestModel {

    private String mId;
    private String mName;
    private String mProfilePic;
    private String newfriendemailid;

    public String getNewfriendemailid() {
        return newfriendemailid;
    }

    public void setNewfriendemailid(String newfriendemailid) {
        this.newfriendemailid = newfriendemailid;
    }
    //private int mMutualCount;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getProfilePic() {
        return mProfilePic;
    }

    public void setProfilePic(String profilePic) {
        this.mProfilePic = profilePic;
    }



    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }
}
