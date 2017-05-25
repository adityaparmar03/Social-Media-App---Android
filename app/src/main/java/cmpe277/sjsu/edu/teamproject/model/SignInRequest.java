package cmpe277.sjsu.edu.teamproject.model;

/**
 * Created by adityaparmar on 5/23/17.
 */

public class SignInRequest {

    private String emailid;
    private String password;

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
