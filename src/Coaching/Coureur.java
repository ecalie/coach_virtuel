package Coaching;

import patron_observer.Observable;

import java.util.ArrayList;
import java.util.List;

public class Coureur extends Observable {

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
    private List<Seance> performances;

    /**
     * Liste des séances pour remplir le prochain objectif.
     */
    private List<Seance> planEntrainement;

    //////////////////
    // CONSTRUCTEUR //
    //////////////////

    public Coureur() {
        this.performances = new ArrayList<>();
        this.planEntrainement = new ArrayList<>();
        ajouterObservateur(Coach.getInstance());
    }

    ////////////////
    // ACCESSEURS //
    ////////////////

    public void setObjectifDistance(int objectifDistance) {
        this.objectifDistance = objectifDistance;
    }

    public void setObjectifDuree(int objectifDuree) {
        this.objectifDuree = objectifDuree;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    public int getObjectifDistance() {
        return objectifDistance;
    }

    public int getObjectifDuree() {
        return objectifDuree;
    }

    public Date getDateLimite() {
        return dateLimite;
    }

    public List<Seance> getPlanEntrainement() {
        return planEntrainement;
    }

    public void setPlanEntrainement(List<Seance> planEntrainement) {
        this.planEntrainement = planEntrainement;
    }

    ///////////////////////////////
    // GESTION PLAN ENTRAINEMENT //
    ///////////////////////////////

    public void creerPlanEntrainement() {
        this.planEntrainement = Coach.getInstance().planEntrainement(this) ;
    }

    public void terminerSeance(Seance seance) {
        this.planEntrainement.remove(seance);
    }

}

