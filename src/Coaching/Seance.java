package Coaching;

public class Seance {

    private int distance;
    private int durée;
    private Date date;

    public Seance(int distance, int durée, Date date) {
        this.distance = distance;
        this.durée = durée;
        this.date = date;
    }

    public int getDistance() {
        return distance;
    }

    public int getDurée() {
        return durée;
    }

    public Date getDate() {
        return date;
    }
}
