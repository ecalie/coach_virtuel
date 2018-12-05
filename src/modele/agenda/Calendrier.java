package modele.agenda;

import java.io.Serializable;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Calendrier extends HashMap<Date, PriorityQueue<Evenement>> implements Serializable {

    @Override
    public PriorityQueue<Evenement> get(Object key) {
        for (Date d : this.keySet())
            if (d.equals(key))
                return super.get(d);
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        for (Date d : this.keySet())
            if (d.equals(key))
                return true;
        return false;
    }
}
