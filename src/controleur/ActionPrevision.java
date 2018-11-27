package controleur;

import modele.coaching.Date;
import vue.Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionPrevision implements ActionListener {

    private Fenetre fenetre;

    public ActionPrevision(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.fenetre.prevoirMeteo();
    }
}
