package modele.coaching;

import modele.agenda.Calendrier;
import modele.agenda.Date;
import modele.patron_observer.Observable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Coureur extends Observable implements Serializable {

    ///////////////
    // ATTRIBUTS //
    ///////////////

    /**
     * Caractéritique de distance du prochain objectif.
     */
    private int objectifDistance;

    /**
     * Caractéritique de temps du prochain objectif.
     */
    private int objectifDuree;

    /**
     * Date limite pour remplir l'objectif.
     */
    private Date dateLimite;

    /**
     * Liste des clés des séances de la fabrique pour remplir le prochain objectif.
     */
    private List<Integer> planEntrainement;

    /**
     * Indice de la prochaine seance.
     */
    private int prochaineSeance;

    /**
     * Calendrier du coureur.
     */
    private Calendrier calendrier;


//////////////////
    // CONSTRUCTEUR //
    //////////////////

    public Coureur() {
        this.planEntrainement = new ArrayList<>();
        this.prochaineSeance = 0;
        this.calendrier = new Calendrier();

        this.initialiser();
    }

    ////////////////
    // ACCESSEURS //
    ////////////////

    public int getObjectifDistance() {
        return objectifDistance;
    }

    public void setObjectifDistance(int objectifDistance) {
        this.objectifDistance = objectifDistance;
    }

    public int getObjectifDuree() {
        return objectifDuree;
    }

    public void setObjectifDuree(int objectifDuree) {
        this.objectifDuree = objectifDuree;
    }

    public Date getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    public List<Integer> getPlanEntrainement() {
        return planEntrainement;
    }

    public int getProchaineSeance() {
        return prochaineSeance;
    }

    public Calendrier getCalendrier() {
        return calendrier;
    }

    ///////////////////////////////
    // GESTION PLAN ENTRAINEMENT //
    ///////////////////////////////

    public void setPlanEntrainement(List<Integer> planEntrainement) {
        this.planEntrainement = planEntrainement;
    }

    public void terminerSeance(Seance seance) {
        this.prochaineSeance++;
    }

    //////////////////
    // SERIALIZABLE //
    //////////////////

    public void enregistrer() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("objectifs"));
            oos.writeObject(this.objectifDistance);
            oos.writeObject(this.objectifDuree);
            oos.writeObject(this.dateLimite);
            oos.writeObject(this.planEntrainement);
            oos.writeObject(this.prochaineSeance);
            oos.writeObject(this.calendrier);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialiser() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("objectifs"));
           this.setObjectifDistance((Integer) ois.readObject());
           this.setObjectifDuree((Integer) ois.readObject());
           this.setDateLimite((Date) ois.readObject());
           this.setPlanEntrainement((List<Integer>) ois.readObject());
           this.prochaineSeance = (Integer) ois.readObject();
           this.calendrier = (Calendrier) ois.readObject();
            ois.close();

        } catch (Exception e) {
        }
    }
}

