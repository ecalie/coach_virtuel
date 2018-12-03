package modele.coaching;

import java.io.Serializable;

public class Seance implements Serializable {

    private int distance;
    private int duree;
    private Date date;

    //////////////////
    // CONSTRUCTEUR //
    //////////////////

    public Seance(int distance, int duree, Date date) {
        this.distance = distance;
        this.duree = duree;
        this.date = date;
    }

    ////////////////
    // ACCESSEURS //
    ////////////////

    public int getDistance() {
        return distance;
    }

    public int getDuree() {
        return duree;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
