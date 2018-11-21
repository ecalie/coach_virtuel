package patron_observer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Observable {

    private List<IObserver> observateurs;

    public Observable() {
        observateurs = new LinkedList<IObserver>();
    }

    public void notifierObservateurs() {
        Iterator<IObserver> it = observateurs.iterator();
        // Notifier tous les observers
        while(it.hasNext()){
            IObserver obs = it.next();
            obs.notifier(this);
        }
    }

    void ajouterObservateur(IObserver observateur) {
        observateurs.add(observateur);
    }

}

