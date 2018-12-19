package modele.coaching;

import modele.agenda.Date;

import java.io.Serializable;

public class Seance implements Serializable {

    private int distance;
    private int duree;
    private Date date;

    //////////////////
    // CONSTRUCTEUR //
    //////////////////

    public Seance() {}

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

    public Date getDate() {
        return date;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
}
