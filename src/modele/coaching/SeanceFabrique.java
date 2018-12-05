package modele.coaching;

import modele.agenda.Calendrier;
import modele.agenda.Date;
import modele.agenda.Evenement;
import modele.meteo.Meteo;

import java.io.*;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.PriorityQueue;

public class SeanceFabrique implements Serializable {

    public static SeanceFabrique singleton = new SeanceFabrique();

    /////////////////////////////////////
    // APPLICATION DU PATTER SINGLETON //
    /////////////////////////////////////
    private Hashtable<Integer, Seance> seancesPartagees = new Hashtable<>();

    private SeanceFabrique() {
    }

    public static SeanceFabrique getInstance() {
        return singleton;
    }

    ////////////////////////////////////////
    // RECUPERER UNE SEANCE DE A FABRIQUE //
    ////////////////////////////////////////

    public Seance getSeance(int key) {
        if (this.seancesPartagees.containsKey(key)) {
            return seancesPartagees.get(key);
        } else {
            System.out.println("La séance n'existe pas");
            return null;
        }
    }

    public Seance getSeance(int key, Meteo meteo, Calendrier calendrier) {
        if (this.seancesPartagees.containsKey(key)) {
            Seance seance = seancesPartagees.get(key);
            Boolean tempsDispo = false;

            if (calendrier.containsKey(seance.getDate())) {
                PriorityQueue<Evenement> listeEvenements = calendrier.get(seance.getDate());
                if (listeEvenements.size() > 0) {
                    Iterator<Evenement> iterator = listeEvenements.iterator();
                    Evenement evenementCourant = iterator.next();

                    if (listeEvenements.size() == 1)
                        tempsDispo = 24 * 60 - (evenementCourant.getDateDebut().ecartMinutes(evenementCourant.getDateFin())) > seance.getDuree() + 30;
                    else {
                        Evenement evenementSuivant = iterator.next();
                        for (int i = 0; i < listeEvenements.size() - 1; i++) {

                            if (evenementCourant.getDateFin().ecartMinutes(evenementSuivant.getDateDebut()) > seance.getDuree() + 30) {
                                tempsDispo = true;
                                break;
                            }
                            evenementCourant = iterator.next();
                            evenementSuivant = iterator.next();
                        }
                    }
                } else
                    tempsDispo = true;
            } else {
                tempsDispo = true;
            }

            if (tempsDispo) {
                //mise à jour contextuelle : meteo
                if (meteo == Meteo.pluie)
                    seance.setDistance((int) (seance.getDistance() * 0.8));
                else if (meteo == Meteo.neige)
                    seance.setDistance((int) (seance.getDistance() * 0.6));
            }
            else
                seance.setDate(seance.getDate().plus(1));
            return seance;
        } else {
            System.out.println("La séance n'existe pas");
            return null;
        }
    }

    public Seance getSeance(Date date) {
        for (int cle : this.seancesPartagees.keySet())
            if (seancesPartagees.get(cle).getDate().equals(date))
                return seancesPartagees.get(cle);
        return null;
    }

    public Seance getSeance(Date date, Meteo meteo, Calendrier calendrier) {
        for (int cle : this.seancesPartagees.keySet())
            if (seancesPartagees.get(cle).getDate().equals(date))
                return this.getSeance(cle, meteo, calendrier);
        return null;
    }

    public void addSeance(int key, int distance, int duree, Date date) {
        final Seance nouvelleSeance = new Seance(distance, duree, date);
        seancesPartagees.put(key, nouvelleSeance);
    }


    //////////////////
    // SERIALIZABLE //
    //////////////////

    public void enregistrer() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("seances"));
            oos.writeObject(this.seancesPartagees);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialiser() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("seances"));
            Hashtable<Integer, Seance> seances = (Hashtable<Integer, Seance>) ois.readObject();
            ois.close();

            this.seancesPartagees = seances;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
