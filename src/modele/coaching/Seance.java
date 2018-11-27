package modele.coaching;

public class Seance {

    private int distance;
    private int duree;
    private Date date;

    public Seance(int distance, int duree, Date date) {
        this.distance = distance;
        this.duree = duree;
        this.date = date;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
