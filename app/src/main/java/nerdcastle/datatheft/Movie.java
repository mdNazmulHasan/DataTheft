package nerdcastle.datatheft;

import java.util.ArrayList;

/**
 * Created by Nazmul on 10-Sep-17.
 */

public class Movie {
    private String movieName;
    private String info;
    private String imdbUrl;

    public Movie(String movieName, String info, String imdbUrl) {
        this.movieName = movieName;
        this.info = info;
        this.imdbUrl = imdbUrl;
    }

    public Movie(String movieName, String info) {
        this.movieName = movieName;
        this.info = info;
    }

    public Movie() {
    }

    public ArrayList<Movie>getAllMOvies(){
        ArrayList<Movie>allMOvies=new ArrayList<>();
        allMOvies.add(new Movie("Mother","Releaseing on 15 Sept.\nA couple's relationship is tested when ....","http://www.imdb.com/title/tt5109784/"));
        allMOvies.add(new Movie("American Assasin","Releaseing on 15 Sept.\nWhen Cold War veteran Stan Hurley takes CIA black ops ....","http://www.imdb.com/title/tt1961175/?ref_=nv_sr_1"));
        allMOvies.add(new Movie("Rebel in the Rye","Releaseing on 15 Sept.\nAuthor J.D. Salinger leads a reclusive ....","http://www.imdb.com/title/tt4986134/?ref_=nv_sr_1"));
        allMOvies.add(new Movie("Vengeance","Releaseing on 15 Sept.\nTeena is raped by four meth heads while her 12-year-old daughter ....","http://www.imdb.com/title/tt5460530/?ref_=nv_sr_1"));
        allMOvies.add(new Movie("Kingsman: The Golden Circle","Releaseing on 22 Sept.\nWith their headquarters destroyed and the world held hostage....","http://www.imdb.com/title/tt4649466/?ref_=nv_sr_1"));
        return allMOvies;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getInfo() {
        return info;
    }

    public String getImdbUrl() {
        return imdbUrl;
    }
}
