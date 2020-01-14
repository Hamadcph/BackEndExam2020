
package DTO;

import entities.MovieInfoPassthrough;
import entities.MoviePosterPassthrough;
import java.util.List;



public class movieInfo {

    private String title;
    private String year;
    private String plot;
    private String directors;
    private String genres;
    private String cast; 
    private String poster;
    List<movieScore> scores;

    public movieInfo(String title, String year, String plot, String directors, String genres, String cast) {
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.directors = directors;
        this.genres = genres;
        this.cast = cast;
    }

    public movieInfo(MovieInfoPassthrough m, MoviePosterPassthrough p) {
        this.title = m.getTitle();
        this.year = m.getYear();
        this.plot = m.getPlot();
        this.directors = m.getDirectors();
        this.genres = m.getGenres();
        this.cast = m.getCast();
        this.poster = p.getPoster();
    }

    @Override
    public String toString() {
        return "movieInfo{" + "title=" + title + ", year=" + year + ", plot=" + plot + ", directors=" + directors + ", genres=" + genres + ", cast=" + cast + ", poster=" + poster + ", scores=" + scores + '}';
    }

    public void setScores(List<movieScore> scores) {
        this.scores = scores;
    }
    
    

    public String getTitle() {
        return title;
    }
    
    public void addScore(movieScore score){
        this.scores.add(score);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public movieInfo(String title, String year, String plot, String directors, String genres, String cast, String poster) {
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.directors = directors;
        this.genres = genres;
        this.cast = cast;
        this.poster = poster;
    }
    
    


    

}
