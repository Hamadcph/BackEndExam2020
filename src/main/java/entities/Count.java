/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 *
 * @author hamad
 */
@Entity
@Table(name = "count")
public class Count implements Serializable {

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
    
    
    private int count; 
    
    @OneToOne (cascade = CascadeType.PERSIST)
    private MovieInfo mf;

    public Count() {
    }
    
    
    public Count(int count, MovieInfo mf) {
        this.count = count;
        this.mf = mf;
    }
    
    public void count () {
        this.count++;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public MovieInfo getMf() {
        return mf;
    }

    public void setMf(MovieInfo mf) {
        this.mf = mf;
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
        if (!(object instanceof Count)) {
            return false;
        }
        Count other = (Count) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Count[ id=" + id + " ]";
    }
    
}
