package cmpe277.sjsu.edu.teamproject.model;

/**
 * Created by adityaparmar on 5/23/17.
 */

public class FriendRequestDecisionRequest {

    private String decision;
    private String newfriendemailid;

    public String getNewfriendemailid() {
        return newfriendemailid;
    }

    public void setNewfriendemailid(String newfriendemailid) {
        this.newfriendemailid = newfriendemailid;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}
