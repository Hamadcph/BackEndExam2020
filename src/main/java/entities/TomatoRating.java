/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author hamad
 */
//Genereret fra https://codebeautify.org/json-to-java-converter
public class TomatoRating {

    private String title;
    private String source;
    Viewer viewer;
    Critic critic;

    // Getter Methods 
    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public Critic getCritic() {
        return critic;
    }

    // Setter Methods 
    public void setTitle(String title) {
        this.title = title;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setViewer(Viewer viewerObject) {
        this.viewer = viewerObject;
    }

    public void setCritic(Critic criticObject) {
        this.critic = criticObject;
    }
    
    public float getRating(){
        return this.critic.getRating();
    }

    public class Critic {

        private float rating;
        private float numReviews;
        private float meter;

        // Getter Methods 
        public float getRating() {
            return rating;
        }

        public float getNumReviews() {
            return numReviews;
        }

        public float getMeter() {
            return meter;
        }

        // Setter Methods 
        public void setRating(float rating) {
            this.rating = rating;
        }

        public void setNumReviews(float numReviews) {
            this.numReviews = numReviews;
        }

        public void setMeter(float meter) {
            this.meter = meter;
        }
    }

    public class Viewer {

        private float rating;
        private float numReviews;
        private float meter;

        // Getter Methods 
        public float getRating() {
            return rating;
        }

        public float getNumReviews() {
            return numReviews;
        }

        public float getMeter() {
            return meter;
        }

        // Setter Methods 
        public void setRating(float rating) {
            this.rating = rating;
        }

        public void setNumReviews(float numReviews) {
            this.numReviews = numReviews;
        }

        public void setMeter(float meter) {
            this.meter = meter;
        }
    }
}
