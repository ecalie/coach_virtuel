package modele;

import modele.coaching.Coach;
import modele.coaching.Coureur;
import modele.agenda.Date;
import modele.coaching.SeanceFabrique;
import modele.meteo.Meteo;
import modele.meteo.PrevisionMeteo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("meteo"));
            oos.writeObject(this.previsions);
            oos.writeObject(Date.today());
            oos.flush();
            oos.close();
        } catch (Exception e) {
        }
    }

    public List<Meteo> initialiserMeteo() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("meteo"));
            List<Meteo> meteos = (List<Meteo>) ois.readObject();
            Date derniere = (Date) ois.readObject();

            if (!derniere.equals(Date.today())) {
                meteos = this.moduleMeteo.prevision5Jours(Date.today());
                System.out.println("maj meteo");
            }

            ois.close();

            return meteos;
        } catch (Exception e) {
        }

        return this.moduleMeteo.prevision5Jours(Date.today());
    }

}
