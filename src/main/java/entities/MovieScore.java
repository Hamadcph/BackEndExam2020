package entities;

import DTO.movieScore;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author hamad
 */
@Entity
@Table (name="MovieScore")
public class MovieScore implements Serializable {

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
    
    @ManyToOne (cascade = CascadeType.PERSIST)
    MovieInfo parent;
    
   
    String source;
    String rating;

    public MovieScore(movieScore m, MovieInfo mf) {
        this.source = m.getSource();
        this.rating = m.getRating();
        this.parent = mf;
    }

    public MovieScore() {
    }

    public MovieScore(imdbRating i) {
        this.source = i.getSource();
        this.rating = String.valueOf(i.getImdbRating());
    }

    public MovieScore(metaRating i) {
        this.source = i.getSource();
        this.rating = String.valueOf(i.getMetacritic());
    }

    public MovieScore(TomatoRating i) {
        this.source = i.getSource();
        this.rating = String.valueOf(i.getRating());
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MovieScore other = (MovieScore) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "entities.MovieScore[ id=" + id + " ]";
    }
    
}
