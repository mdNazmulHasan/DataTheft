package nerdcastle.datatheft;

import java.util.ArrayList;

/**
 * Created by Nazmul on 10-Sep-17.
 */

public class Info {
    private String userPhoneNUmber;
    private ArrayList<Contact>contacts;

    public Info(String userPhoneNUmber, ArrayList<Contact> contacts) {
        this.userPhoneNUmber = userPhoneNUmber;
        this.contacts = contacts;
    }

    public Info() {
    }

    public String getUserPhoneNUmber() {
        return userPhoneNUmber;
    }

    public void setUserPhoneNUmber(String userPhoneNUmber) {
        this.userPhoneNUmber = userPhoneNUmber;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }
}
