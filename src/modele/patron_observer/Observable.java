package modele.patron_observer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Observable {

    private List<IObserver> observateurs;

    public Observable() {
        observateurs = new LinkedList<>();
    }

    public void notifierObservateurs() throws Exception {
        Iterator<IObserver> it = observateurs.iterator();
        // Notifier tous les observers
        while (it.hasNext()) {
            IObserver obs = it.next();
            obs.notifier(this);
        }
    }

    public void ajouterObservateur(IObserver observateur) {
        observateurs.add(observateur);
    }

}

