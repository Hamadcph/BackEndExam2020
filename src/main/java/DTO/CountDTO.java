package DTO;

/**
 *
 * @author hamad
 */
public class CountDTO {
   
    private int count;
    private movieInfo mi;

    public CountDTO(int count, movieInfo mi) {
        this.count = count;
        this.mi = mi;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public movieInfo getMi() {
        return mi;
    }

    public void setMi(movieInfo mi) {
        this.mi = mi;
    }
    
    
    
}
