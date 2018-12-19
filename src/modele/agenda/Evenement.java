package modele.agenda;


import java.io.Serializable;

public class Evenement  implements Serializable, Comparable {

    private Date dateDebut;
    private Date dateFin;
    private String titre;


    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public String getTitre() {
        return titre;
    }

    @Override
    public int compareTo(Object o) {
        Evenement e = (Evenement) o;
        if(this.getDateDebut().getHeure() == e.getDateDebut().getHeure())
            return this.getDateDebut().getMinute() - e.getDateDebut().getMinute();
        else
            return this.getDateDebut().getHeure() - e.getDateDebut().getHeure();

    }
}
