package modele.patron_observer;

import modele.coaching.ManqueSeanceException;

public interface IObserver {
    void notifier(Observable observable) throws Exception;
}
