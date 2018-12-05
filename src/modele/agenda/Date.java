package modele.agenda;

import java.io.Serializable;

public class Date implements Serializable {

    private int jour;
    private int mois;
    private int annee;
    private int heure;
    private int minute;
    //private Meteo prevision;


    public Date(int jour, int mois, int annee, int heure, int minute) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        this.heure = heure;
        this.minute = minute;
    }

    public Date(int jour, int mois, int annee) {
        this(jour, mois, annee, 0, 0);
    }

    /**
     * Calculer une date à partir d'un écart depuis la date de référence.
     */
    public static Date date(int ecart) {
        int annee = (int) (ecart / 365.25);
        double reste1 = ecart % 365.25;

        int mois = 1;
        int tmp = 0;
        double jour = reste1;
        for (Mois m : Mois.values()) {
            if (m.getMois() == 2 && annee % 4 == 0)
                tmp++;
            tmp += m.getNbJours();
            if (reste1 > tmp) {
                mois++;
                jour -= m.getNbJours();
            } else {
                break;
            }
        }

        if (jour - (int) jour == 0)
            return new Date((int) jour, mois, annee);
        else
            return new Date((int) jour + 1, mois, annee);
    }

    /**
     * Créer une date à partir d'une chaîne de caractères de la forme jj/mm/aaaa.
     */
    public static Date toDate(String date) {
        String[] donnees = date.split("/");
        return new Date(Integer.parseInt(donnees[0]), Integer.parseInt(donnees[1]), Integer.parseInt(donnees[2]));
    }

    public static Date toDate(String date, String heure) {
        String[] donnees = date.split("/");
        String[] donnees2 = heure.split(":");
        return new Date(Integer.parseInt(donnees[0]), Integer.parseInt(donnees[1]), Integer.parseInt(donnees[2]), Integer.parseInt(donnees2[0]), Integer.parseInt(donnees2[1]));
    }


    public static Date today() {
        java.util.Date tmp = new java.util.Date();
        return new Date(tmp.getDate() + 1, tmp.getMonth() + 1, tmp.getYear() + 1900);
        //return new Date(7,12,2018);
    }

    public int getHeure() {
        return heure;
    }

    public int getMinute() {
        return minute;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getMois() {
        return mois;
    }

    /**
     * Calculer l'écart entre la date et une date de référence (01/01/0000).
     */
    public int ecart() {
        int tmpMois = 0;
        for (Mois m : Mois.values())
            if (mois > m.getMois())
                tmpMois += m.getNbJours();

        if (mois >= 2 && this.annee % 4 == 0)
            tmpMois++;

        return this.jour + tmpMois + this.annee * 365 + (this.annee - 1) / 4;
    }

    public int ecartMinutes(Date other) {
        return Math.abs((this.heure*60+this.minute)-(other.heure*60+other.minute));
    }

    /**
     * Calculer l'écart en nombre de jours entre deux dates.
     */
    public int moins(Date d) {
        return this.ecart() - d.ecart();
    }

    /**
     * Calculer une date nbJours après la date courante.
     */
    public Date plus(int nbJours) {
        int tmp = this.ecart() + nbJours;
        return Date.date(tmp);
    }

    @Override
    public String toString() {
        if(this.heure == 0 && this.minute == 0)
            return this.jour + "/" + this.mois + "/" + this.annee;
        else return this.jour + "/" + this.mois + "/" + this.annee + "  " + this.heure + ":" + this.minute;
    }

    @Override
    public boolean equals(Object obj) {
        Date d = (Date) obj;
        return this.jour == d.jour && this.mois == d.mois && this.annee == d.annee;
    }



    enum Mois {
        JANVIER(1, 31),
        FEVRIER(2, 28),
        MARS(3, 31),
        AVRIL(4, 30),
        MAI(5, 31),
        JUIN(6, 30),
        JUILLET(7, 31),
        AOUT(8, 31),
        SEPTEMBRE(9, 30),
        OCTOBRE(10, 31),
        NOVEMBRE(11, 30),
        DECEMBRE(12, 31);

        private int mois;
        private int nbJours;

        Mois(int mois, int nbJours) {
            this.mois = mois;
            this.nbJours = nbJours;
        }

        public int getNbJours() {
            return this.nbJours;
        }

        public int getMois() {
            return this.mois;
        }


    }


}
