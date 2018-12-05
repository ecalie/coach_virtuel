package modele.agenda;

import java.io.Serializable;
import java.util.Comparator;

public class ComparerHeure implements Serializable, Comparator<Evenement> {

    @Override
    public int compare(Evenement o1, Evenement o2) {
        if(o1.getDateDebut().getHeure() == o2.getDateDebut().getHeure())
            return o1.getDateDebut().getMinute() - o2.getDateDebut().getMinute();
        else return o1.getDateDebut().getHeure() - o2.getDateDebut().getHeure();

    }
}
