package modele.coaching;
import modele.meteo.Meteo;

import java.util.Hashtable;

public class SeanceFabrique {

    private Hashtable<Integer, Seance> seancesPartagees = new Hashtable<>();

    SeanceFabrique() {}

    public Seance getSeance(int indiceSeance) {
        if(seancesPartagees.containsKey(indiceSeance)) {
            return seancesPartagees.get(indiceSeance);
        }
        else {
            System.out.println("La séance n'existe pas");
            return null;
        }
    }

    public Seance getSeance(int indiceSeance, Meteo meteo){
        if(seancesPartagees.containsKey(indiceSeance)) {
            Seance seance = seancesPartagees.get(indiceSeance);
            //exemple de mise à jour contextuelle
            if (meteo == Meteo.pluie)
                seance.setDuree((int) (seance.getDuree()*1.25));
            else if (meteo == Meteo.neige)
                seance.setDuree((int) (seance.getDuree()*1.5));
            return seance;
        }
        else {
            System.out.println("La séance n'existe pas");
            return null;
        }
    }

    public void addSeance(int distance, int duree, Date date) {
        final Seance nouvelleSeance = new Seance(distance, duree, date);
        seancesPartagees.put(seancesPartagees.size()+1, nouvelleSeance);
    }
}
