package cmpe277.sjsu.edu.teamproject.model;


public class FriendModel {

    private String mName;
    private String mImageUrl;
    private int mMutualCount;


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    public int getMutualCount() {
        return mMutualCount;
    }

    public void setMutualCount(int mutualCount) {
        this.mMutualCount = mutualCount;
    }
}
