package kivaaz.com.userapp;

/**
 * Created by Muguntan on 11/24/2017.
 */

public class RequestList {
    private String reqName;
    private String reqDesc;
    private String reqEmail;

    public RequestList() {
    }

    public RequestList(String reqName, String reqDesc, String reqEmail) {
        this.reqName = reqName;
        this.reqDesc = reqDesc;
        this.reqEmail = reqEmail;
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
}
