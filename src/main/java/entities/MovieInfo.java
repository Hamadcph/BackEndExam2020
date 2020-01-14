package entities;

import DTO.movieInfo;
import DTO.movieScore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import entities.MovieScore;

/**
 *
 * @author hamad
 */
@Entity
@Table (name="MovieInfo")
public class MovieInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    private String title;
    private String year;
    private String plot;
    private String directors;

    public MovieInfo() {
    }
    private String genres;
    private String cast; 
    private String poster;
    
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "parent")
    List<MovieScore> scores;

    public MovieInfo(int id, String title, String year, String plot, String directors, String genres, String cast, String poster, List<MovieScore> scores) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.directors = directors;
        this.genres = genres;
        this.cast = cast;
        this.poster = poster;
        this.scores = scores;
    }

    public List<MovieScore> getScores() {
        return scores;
    }

    public MovieInfo(movieInfo m) {
        this.title = m.getTitle();
        this.year = m.getYear();
        this.plot = m.getPlot();
        this.directors = m.getDirectors();
        this.genres = m.getGenres();
        this.cast = m.getCast();
        this.poster = m.getPoster();
    }

    public MovieInfo(MovieInfoPassthrough m, MoviePosterPassthrough p) {
        this.title = m.getTitle();
        this.year = m.getYear();
        this.plot = m.getPlot();
        this.directors = m.getDirectors();
        this.genres = m.getGenres();
        this.cast = m.getCast();
        this.poster = p.getPoster();
    }

    public void setScores(List<MovieScore> scores) {
        this.scores = scores;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void addScore(MovieScore score){
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

    public MovieInfo(String title, String year, String plot, String directors, String genres, String cast, String poster) {
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.directors = directors;
        this.genres = genres;
        this.cast = cast;
        this.poster = poster;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovieInfo)) {
            return false;
        }
        MovieInfo other = (MovieInfo) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MovieInfo[ id=" + id + " ]";
    }
    
}
