package cmpe277.sjsu.edu.teamproject.model;

import java.util.ArrayList;

public class PostModel {

    private int mAuthorId;
    private String mAuthorName;
    private String mTitle;
    private boolean mIsMediaAttached;
    private String mAttachedPhoto;
    private int mLikeCount;
    private int mCommentCount;
    private int mShareCount;
    private boolean mYouLikedIt;
    private String mSharedTimeStamp;
    private String mLocationShared;
    private ArrayList<FriendModel> mTaggedFriendList;
    private String mPrivacy;

    public int getAuthorId() {
        return mAuthorId;
    }

    public void setAuthorId(int authorId) {
        this.mAuthorId = authorId;
    }

    public String getAuthorName() {
        return mAuthorName;
    }

    public void setAuthorName(String authorName) {
        this.mAuthorName = authorName;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public boolean isMediaAttached() {
        return mIsMediaAttached;
    }

    public void setIsMediaAttached(boolean isMediaAttached) {
        this.mIsMediaAttached = isMediaAttached;
    }

    public String getAttachedPhoto() {
        return mAttachedPhoto;
    }

    public void setAttachedPhoto(String attachedPhoto) {
        this.mAttachedPhoto = attachedPhoto;
    }

    public int getLikeCount() {
        return mLikeCount;
    }

    public void setLikeCount(int likeCount) {
        this.mLikeCount = likeCount;
    }

    public int getCommentCount() {
        return mCommentCount;
    }

    public void setCommentCount(int commentCount) {
        this.mCommentCount = commentCount;
    }

    public int getShareCount() {
        return mShareCount;
    }

    public void setShareCount(int shareCount) {
        this.mShareCount = shareCount;
    }

    public boolean isYouLikedIt() {
        return mYouLikedIt;
    }

    public void setYouLikedIt(boolean youLikedIt) {
        this.mYouLikedIt = youLikedIt;
    }

    public String getSharedTimeStamp() {
        return mSharedTimeStamp;
    }

    public void setSharedTimeStamp(String sharedTimeStamp) {
        this.mSharedTimeStamp = sharedTimeStamp;
    }

    public String getLocationShared() {
        return mLocationShared;
    }

    public void setLocationShared(String locationShared) {
        this.mLocationShared = locationShared;
    }

    public ArrayList<FriendModel> getTaggedFriendList() {
        return mTaggedFriendList;
    }

    public void setTaggedFriendList(ArrayList<FriendModel> taggedFriendList) {
        this.mTaggedFriendList = taggedFriendList;
    }

    public String getPrivacy() {
        return mPrivacy;
    }

    public void setPrivacy(String privacy) {
        this.mPrivacy = privacy;
    }
}
