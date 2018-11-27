package Meteo;

import Coaching.Date;

import java.util.ArrayList;
import java.util.List;


public class PrevisionMeteo {

    public PrevisionMeteo() {
    }

    public Meteo previsionJour(Date date) {
        float probaSoleil = 0;
        float probaPluie = 0;
        float probaNeige = 0;
        float probaNuage = 0;

        switch(date.getMois()){
            case 1:
                probaSoleil -= 1.5;
            case 2:
                probaSoleil -= 1.5;
            case 3:
                probaSoleil -= 1.5;
                probaNeige  += 0.5;
            case 4:
                probaPluie  -= 1.0;
                probaNeige  += 0.5;
            case 5:
                probaPluie  -= 1.0;
            case 6:
                probaPluie  -= 1.0;
            case 7:
                probaPluie  += 1.0;
            case 8:
            case 9:
                probaSoleil += 1.5;
                probaNeige  -= 1.0;
            case 10:
                probaPluie  += 0.5;
            case 11:
            case 12:
                probaSoleil += 2.5;
                probaNeige  += 1.0;
        }

        probaSoleil /= 7;
        probaPluie  /= 7;
        probaNeige  /= 7;
        probaNuage   = 1 - (probaSoleil+probaPluie+probaNeige);

        probaPluie  += probaNeige;
        probaNuage  += probaPluie;
        probaSoleil += probaNuage;

        double r = Math.random();

        Meteo prevision =   r < probaNeige ? Meteo.neige : (
                            r < probaPluie ? Meteo.pluie : (
                            r < probaNuage ? Meteo.nuages :
                                             Meteo.soleil ));

       return prevision;
    }

    public List<Meteo> prevision5Jours(Date date) {

        List<Meteo> previsions = new ArrayList<>();

        for(int j = 0; j < 5; j++) {
            previsions.add(previsionJour(date.plus(j)));
        }

        return previsions;
    }
}
