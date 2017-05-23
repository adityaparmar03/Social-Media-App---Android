package cmpe277.sjsu.edu.teamproject.model;

import java.util.ArrayList;


public class ProfileModel {

    private String mName;
    private String mProfilePic, mCoverPic;
    private int mFriendsCount;
    private ArrayList<FriendModel> mFriendsList;
    private String mWorksAt, mLivesIn;
    private ArrayList<PostModel> mPostFeed;
    private boolean mIsSelf;
    private boolean mIsFriend;

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

    public String getCoverPic() {
        return mCoverPic;
    }

    public void setCoverPic(String coverPic) {
        this.mCoverPic = coverPic;
    }

    public int getFriendsCount() {
        return mFriendsCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.mFriendsCount = friendsCount;
    }

    public ArrayList<FriendModel> getFriendsList() {
        return mFriendsList;
    }

    public void setFriendsList(ArrayList<FriendModel> friendsList) {
        this.mFriendsList = friendsList;
    }

    public String getWorksAt() {
        return mWorksAt;
    }

    public void setWorksAt(String worksAt) {
        this.mWorksAt = worksAt;
    }

    public String getLivesIn() {
        return mLivesIn;
    }

    public void setLivesIn(String livesIn) {
        this.mLivesIn = livesIn;
    }

    public ArrayList<PostModel> getPostFeed() {
        return mPostFeed;
    }

    public void setPostFeed(ArrayList<PostModel> postFeed) {
        this.mPostFeed = postFeed;
    }

    public boolean isSelf() {
        return mIsSelf;
    }

    public void setIsSelf(boolean isSelf) {
        this.mIsSelf = isSelf;
    }

    public boolean isFriend() {
        return mIsFriend;
    }

    public void setIsFriend(boolean isFriend) {
        this.mIsFriend = isFriend;
    }
}
