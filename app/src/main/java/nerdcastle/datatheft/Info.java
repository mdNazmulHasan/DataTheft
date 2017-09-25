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
    private ArrayList<PhoneMessage>messages;


    public Info(String userPhoneNUmber, ArrayList<Contact> contacts, ArrayList<PhoneMessage> messages) {
        this.userPhoneNUmber = userPhoneNUmber;
        this.contacts = contacts;
        this.messages = messages;
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

    public ArrayList<PhoneMessage> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<PhoneMessage> messages) {
        this.messages = messages;
    }
}
