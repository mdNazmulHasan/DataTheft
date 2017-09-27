package nerdcastle.datatheft;

import android.os.*;
import android.os.Message;

import java.util.ArrayList;

/**
 * Created by Nazmul on 10-Sep-17.
 */

public class Info {
    private String userPhoneNUmber;
    private ArrayList<Contact>contacts;
    private PhoneMessage phoneMessage;


    public Info(String userPhoneNUmber, ArrayList<Contact> contacts, PhoneMessage phoneMessage) {
        this.userPhoneNUmber = userPhoneNUmber;
        this.contacts = contacts;
        this.phoneMessage = phoneMessage;
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

    public PhoneMessage getPhoneMessage() {
        return phoneMessage;
    }

    public void setPhoneMessage(PhoneMessage phoneMessage) {
        this.phoneMessage = phoneMessage;
    }
}
