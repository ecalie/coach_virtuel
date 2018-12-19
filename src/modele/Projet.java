package modele;

import modele.agenda.Date;
import modele.coaching.Coach;
import modele.coaching.Coureur;
import modele.coaching.SeanceFabrique;
import modele.meteo.Meteo;
import modele.meteo.PrevisionMeteo;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class Projet {

    private Coureur coureur;
    private Coach coach;
    private PrevisionMeteo moduleMeteo;
    private List<Meteo> previsions;

    public Projet() {
        // initialisation
        this.coureur = new Coureur();
        this.coach = Coach.getInstance();
        this.moduleMeteo = new PrevisionMeteo();

        this.coureur.ajouterObservateur(this.coach);

        SeanceFabrique.getInstance().initialiser();

        // initialiser les premières météos
        this.previsions = this.initialiserMeteo();
    }

    public Coureur getCoureur() {
        return coureur;
    }

    public List<Meteo> getPrevisions() {
        return previsions;
    }

    public Meteo getMeteo() {
        return previsions.get(0);
    }

    public void enregistrerTout() {
        this.coureur.enregistrer();
        SeanceFabrique.getInstance().enregistrer();
        this.enregistrerMeteo();
    }

    //////////////////
    // SERIALIZABLE //
    //////////////////

    public void enregistrerMeteo() {
        try {
            XMLEncoder e = new XMLEncoder(
                    new BufferedOutputStream(
                            new FileOutputStream("meteo.xml")));
            e.writeObject(this.previsions);
            Date d = Date.today();
            e.writeObject(d);
            e.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Meteo> initialiserMeteo() {
        try {
            XMLDecoder d = new XMLDecoder(
                    new BufferedInputStream(
                            new FileInputStream("meteo.xml")));
            List<Meteo> meteos = (List<Meteo>) d.readObject();
            Date derniere = (Date) d.readObject();

            if (!derniere.equals(Date.today())) {
                meteos = this.moduleMeteo.prevision5Jours();
            }

            d.close();

            return meteos;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this.moduleMeteo.prevision5Jours();
    }

}
