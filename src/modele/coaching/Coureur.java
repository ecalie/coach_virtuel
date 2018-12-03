package modele.coaching;

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
     * Liste des objectifs remplis.
     */
    // private List<Seance> performances;

    /**
     * Liste des clés des séances de la fabrique pour remplir le prochain objectif.
     */
    private List<Integer> planEntrainement;

    /**
     * Indice de la prochaine seance.
     */
    private int prochaineSeance;

    //////////////////
    // CONSTRUCTEUR //
    //////////////////

    public Coureur() {
        // this.performances = new ArrayList<>();
        this.planEntrainement = new ArrayList<>();
        this.ajouterObservateur(Coach.getInstance());
        this.prochaineSeance = 0;
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
            oos.writeObject(this);
            oos.flush();
            oos.close();
        } catch (Exception e) {
        }
    }

    public static Coureur initialiser() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("objectifs"));
            Coureur c = (Coureur) ois.readObject();
            c.ajouterObservateur(Coach.getInstance());
            ois.close();

            return c;
        } catch (Exception e) {
        }

        return new Coureur();
    }
}

