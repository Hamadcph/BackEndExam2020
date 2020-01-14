/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.MovieInfoPassthrough;
import entities.MoviePosterPassthrough;
import entities.TomatoRating;
import entities.imdbRating;
import entities.metaRating;

/**
 *
 * @author hamad
 */
public class movieScore {

    String title;
    String source;
    String rating;

    public movieScore(String title, String source, String rating) {
        this.title = title;
        this.source = source;
        this.rating = rating;
    }

    public movieScore(imdbRating i) {
        this.source = i.getSource();
        this.rating = String.valueOf(i.getImdbRating());
    }

    public movieScore(metaRating i) {
        this.source = i.getSource();
        this.rating = String.valueOf(i.getMetacritic());
    }

    public movieScore(TomatoRating i) {
        this.source = i.getSource();
        this.rating = String.valueOf(i.getRating());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

}
