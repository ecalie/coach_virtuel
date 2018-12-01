package modele;

import modele.coaching.Coach;
import modele.coaching.Coureur;
import modele.coaching.Date;
import modele.meteo.Meteo;
import modele.meteo.PrevisionMeteo;

import java.util.List;

public class Projet {

    private Coureur coureur;
    private Coach coach;
    private PrevisionMeteo moduleMeteo;
    private List<Meteo> previsions;

    public Projet() {
        // initialisation
        this.coureur = Coureur.initialiser();
        this.coach = Coach.getInstance();
        this.moduleMeteo = new PrevisionMeteo();

        // initialiser les premières météos
        this.previsions = this.moduleMeteo.prevision5Jours(Date.today());
    }

    public Coureur getCoureur() {
        return coureur;
    }

    public Coach getCoach() {
        return coach;
    }

    public PrevisionMeteo getModuleMeteo() {
        return moduleMeteo;
    }

    public List<Meteo> getPrevisions() {
        return previsions;
    }
}
