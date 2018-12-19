package modele.coaching;

import modele.agenda.Calendrier;
import modele.agenda.Date;
import modele.patron_observer.Observable;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
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
    protected int objectifDistance;

    /**
     * Caractéritique de temps du prochain objectif.
     */
    protected int objectifDuree;

    /**
     * Date limite pour remplir l'objectif.
     */
    protected Date dateLimite;

    /**
     * Liste des clés des séances de la fabrique pour remplir le prochain objectif.
     */
    protected List<Integer> planEntrainement;

    /**
     * Indice de la prochaine seance.
     */
    protected int prochaineSeance;

    /**
     * Calendrier du coureur.
     */
    protected Calendrier calendrier;


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

    public void setPlanEntrainement(List<Integer> planEntrainement) {
        this.planEntrainement = planEntrainement;
    }

    public int getProchaineSeance() {
        return prochaineSeance;
    }

    public void setCalendrier(Calendrier calendrier) {
        this.calendrier = calendrier;
    }

    ///////////////////////////////
    // GESTION PLAN ENTRAINEMENT //
    ///////////////////////////////

    public Calendrier getCalendrier() {
        return calendrier;
    }

    public void terminerSeance(Seance seance) {
        this.prochaineSeance++;
    }

    //////////////////
    // SERIALIZABLE //
    //////////////////

    public void enregistrer() {
        try {
            XMLEncoder e = new XMLEncoder(
                    new BufferedOutputStream(
                            new FileOutputStream("objectifs.xml")));
            e.writeObject(this.objectifDistance);
            e.writeObject(this.objectifDuree);
            e.writeObject(this.dateLimite);
            e.writeObject(this.planEntrainement);
            e.writeObject(this.prochaineSeance);
            e.writeObject(this.calendrier);
            e.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialiser() {
        try {
            XMLDecoder d = new XMLDecoder(
                    new BufferedInputStream(
                            new FileInputStream("objectifs.xml")));
            this.objectifDistance = (Integer) d.readObject();
            this.objectifDuree = (Integer) d.readObject();
            this.dateLimite = (Date) d.readObject();
            this.planEntrainement = (List<Integer>) d.readObject();
            this.prochaineSeance = (Integer) d.readObject();
            this.calendrier = (Calendrier) d.readObject();
            d.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

