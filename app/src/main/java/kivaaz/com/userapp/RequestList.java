package kivaaz.com.userapp;

/**
 * Created by Muguntan on 11/24/2017.
 */

public class RequestList {
    private String reqName;
    private String reqDesc;
    private String reqEmail;
    private Boolean reqAccepted;
    private String acceptedBy;

    public RequestList() {
    }

    public RequestList(String reqName, String reqDesc, String reqEmail, Boolean reqAccepted, String acceptedBy) {
        this.reqName = reqName;
        this.reqDesc = reqDesc;
        this.reqEmail = reqEmail;
        this.reqAccepted = reqAccepted;
        this.acceptedBy = acceptedBy;
    }

    public String getReqName() {
        return reqName;
    }

    public void setReqName(String reqName) {
        this.reqName = reqName;
    }

    public String getReqDesc() {
        return reqDesc;
    }

    public void setReqDesc(String reqDesc) {
        this.reqDesc = reqDesc;
    }

    public String getReqEmail() {
        return reqEmail;
    }

    public void setReqEmail(String reqEmail) {
        this.reqEmail = reqEmail;
    }

    public Boolean getReqAccepted() {
        return reqAccepted;
    }

    public void setReqAccepted(Boolean reqAccepted) {
        this.reqAccepted = reqAccepted;
    }

    public String getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(String acceptedBy) {
        this.acceptedBy = acceptedBy;
    }
}
