package nerdcastle.datatheft;

/**
 * Created by Nazmul on 25-Sep-17.
 */

public class PhoneMessage {
    private String from;
    private String msg;

    public PhoneMessage(String from, String msg) {
        this.from = from;
        this.msg = msg;
    }

    public PhoneMessage() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
