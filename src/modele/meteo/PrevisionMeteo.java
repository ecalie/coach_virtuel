package modele.meteo;

import modele.agenda.Date;

import java.util.ArrayList;
import java.util.List;


public class PrevisionMeteo {

    public PrevisionMeteo() {
    }

    public Meteo previsionJour(Date date) {
        double probaSoleil = 0;
        double probaPluie = 0;
        double probaNeige = 0;
        double probaNuage;

        switch (date.getMois()) {
            case 1:
            case 2:
            case 3:
            case 11:
            case 12:
                probaSoleil = 2.5;
                probaNeige = 1.0;
                break;
            case 4:
                probaSoleil = 4.0;
                probaNeige = 0.5;
                break;
            case 5:
                probaSoleil = 4.0;
                probaPluie = 1.0;
                break;
            case 6:
            case 8:
            case 9:
                probaSoleil = 4.0;
                probaPluie = 2.0;
                break;
            case 7:
                probaSoleil = 4.0;
                probaPluie = 3.0;
                break;
            case 10:
                probaSoleil = 2.5;
                probaPluie = 0.5;
                probaNeige = 1.0;
                break;
        }

        probaSoleil /= 7;
        probaPluie /= 7;
        probaNeige /= 7;
        probaNuage = 1 - (probaSoleil + probaPluie + probaNeige);

        probaPluie += probaNeige;
        probaNuage += probaPluie;

        double r = Math.random();

        return r < probaNeige ? Meteo.neige :
                r < probaPluie ? Meteo.pluie :
                        r < probaNuage ? Meteo.nuages :
                                Meteo.soleil;

    }

    public List<Meteo> prevision5Jours(Date date) {

        List<Meteo> previsions = new ArrayList<>();

        for (int j = 0; j < 5; j++) {
            previsions.add(previsionJour(date.plus(j)));
        }

        return previsions;
    }
}
