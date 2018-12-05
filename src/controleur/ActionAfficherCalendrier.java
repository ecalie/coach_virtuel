package controleur;

import vue.Fenetre;
import vue.FicheCalendrier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionAfficherCalendrier implements ActionListener {
    private FicheCalendrier ficheCalendrier;

    public ActionAfficherCalendrier(FicheCalendrier ficheCalendrier) {
        this.ficheCalendrier = ficheCalendrier;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.ficheCalendrier.maj();
        this.ficheCalendrier.show();
    }
}
