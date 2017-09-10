package nerdcastle.datatheft;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nazmul on 10-Sep-17.
 */

public class MOvieAdapter extends ArrayAdapter<Movie>{
    private Context context;
    private ArrayList<Movie>movies;

    public MOvieAdapter(@NonNull Context context,ArrayList<Movie>movies) {
        super(context, R.layout.movie_row,movies);
        this.movies=movies;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Movie movie=movies.get(position);
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        convertView=layoutInflater.inflate(R.layout.movie_row,parent,false);
        TextView moviewText=convertView.findViewById(R.id.movieNameText);
        TextView infoText=convertView.findViewById(R.id.infoText);
        moviewText.setText(movie.getMovieName());
        infoText.setText(movie.getInfo());
        return convertView;
    }
}
