package modele.coaching;

import modele.meteo.Meteo;

import java.io.*;
import java.util.Hashtable;

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

    public Seance getSeance(int key, Meteo meteo) {
        if (this.seancesPartagees.containsKey(key)) {
            Seance seance = seancesPartagees.get(key);
            //exemple de mise à jour contextuelle
            if (meteo == Meteo.pluie)
                seance.setDistance((int) (seance.getDistance() * 0.8));
            else if (meteo == Meteo.neige)
                seance.setDistance((int) (seance.getDistance() * 0.6));
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

    public Seance getSeance(Date date, Meteo meteo) {
        for (int cle : this.seancesPartagees.keySet())
            if (seancesPartagees.get(cle).getDate().equals(date))
                return this.getSeance(cle, meteo);
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
            System.out.println("pas de fichier seances");
        }
    }
}
