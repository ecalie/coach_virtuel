package Coaching;

import patron_observer.IObserver;
import patron_observer.Observable;

import java.util.ArrayList;
import java.util.List;

public class Coach implements IObserver {

    /////////////////////////////////////
    // APPLICATION DU PATTER SINGLETON //
    /////////////////////////////////////

    public static Coach singleton = new Coach();

    private Coach() {
    }

    public static Coach getInstance() {
        return singleton;
    }

    ///////////////////////////////////////
    // CREATION D'UN PLAN D'ENTRAINEMENT //
    ///////////////////////////////////////

    public List<Seance> planEntrainement(Coureur coureur) {
        // calculer le nombre de séances (une tous 3 jours)
        java.util.Date tmp = new java.util.Date();
        // on commence demain
        Date debut = new Date(tmp.getDate() + 1, tmp.getMonth() + 1, tmp.getYear() + 1900);
        Date fin = coureur.getDateLimite();
        int nbJours = fin.moins(debut) + 1;
        int nbSeances = (nbJours) / 3 + (nbJours % 3 == 0 ? 0 : 1);

        // erreur si moins de 12 séances
        if (nbSeances < 13)
            return null;

        // construire le plan d'entrainement
        List<Seance> res = new ArrayList<>();
        int objAllure = coureur.getObjectifDistance() * 60 / coureur.getObjectifDuree();
        Date date = debut;
        //      -les 3 premières séances
        //              - seance 1
        int dureeSeance = coureur.getObjectifDuree() / 2;
        int allureSeance = objAllure - 1;
        int distSeance = allureSeance * dureeSeance / 60;
        res.add(new Seance(distSeance, dureeSeance, date));

        date = date.plus(3);
        //              - seance 2
        dureeSeance = coureur.getObjectifDuree();
        allureSeance = objAllure - 3;
        distSeance = allureSeance * dureeSeance / 60;
        res.add(new Seance(distSeance, dureeSeance, date));

        date = date.plus(3);
        //              - seance 3
        dureeSeance = (int) (coureur.getObjectifDuree() * 1.5);
        allureSeance = objAllure - 3;
        distSeance = allureSeance * dureeSeance / 60;
        res.add(new Seance(distSeance, dureeSeance, date));

        date = date.plus(3);

        //      -les séances intermédiaires
        for (int seance = 4; seance < nbSeances - 3; seance++) {
            if (seance % 3 == 1) {
                dureeSeance = coureur.getObjectifDuree();
                allureSeance = objAllure - 1;
                distSeance = allureSeance * dureeSeance / 60;
                res.add(new Seance(distSeance, dureeSeance, date));

                date = date.plus(3);
            } else if (seance % 3 == 2) {
                dureeSeance = (int) (coureur.getObjectifDuree() * 1.25);
                // augmenter l'allure semaine après semaine
                //      -calculer le numéro de la "semaine" (3séances)
                int numSemaine = (seance - 4) / 3 + 1;
                //      -calculer le nombre de semaines 'inermédaires"
                int nbSemaines = nbSeances / 3 - 2;
                //      -calculer l'allure
                if (numSemaine <= nbSemaines / 3)
                    allureSeance = objAllure - 3;
                else if (numSemaine <= 2 * nbSemaines / 3)
                    allureSeance = objAllure - 2;
                else
                    allureSeance = objAllure - 1;

                distSeance = allureSeance * dureeSeance / 60;
                res.add(new Seance(distSeance, dureeSeance, date));

                date = date.plus(3);
            } else if (seance % 3 == 0) {
                dureeSeance = coureur.getObjectifDuree() * 2;
                allureSeance = objAllure - 3;
                distSeance = allureSeance * dureeSeance / 60;
                res.add(new Seance(distSeance, dureeSeance, date));

                date = date.plus(3);
            }
        }

        //      -les 3 dernières séances
        dureeSeance = (int) (coureur.getObjectifDuree() * 0.7);
        allureSeance = objAllure - 1;
        distSeance = allureSeance * dureeSeance / 60;
        res.add(new Seance(distSeance, dureeSeance, date));

        date = date.plus(3);

        dureeSeance = (int) (coureur.getObjectifDuree() * 0.8);
        allureSeance = objAllure - 2;
        distSeance = allureSeance * dureeSeance / 60;
        res.add(new Seance(distSeance, dureeSeance, date));

        date = date.plus(3);

        dureeSeance = (int) (coureur.getObjectifDuree() * 0.9);
        allureSeance = objAllure - 3;
        distSeance = allureSeance * dureeSeance / 60;
        res.add(new Seance(distSeance, dureeSeance, date));

        date = date.plus(3);

        //      - le jour de la course
        dureeSeance = (int) (coureur.getObjectifDuree() * 1);
        allureSeance = objAllure;
        distSeance = allureSeance * dureeSeance / 60;
        res.add(new Seance(distSeance, dureeSeance, date));

        return res;
    }

    /////////////////////////////////////
    // APPLICATION DU PATTERN OBSERVER //
    /////////////////////////////////////

    @Override
    public void notifier(Observable observable) {
        // Mettre à jour le plan d'entrainement
        ((Coureur) observable).setPlanEntrainement(this.planEntrainement((Coureur) observable));
    }
}
