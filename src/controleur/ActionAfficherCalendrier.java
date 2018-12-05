package controleur;

import vue.Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionAfficherCalendrier implements ActionListener {
    private Fenetre fenetre;

    public ActionAfficherCalendrier(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.fenetre.afficherCalendrier();
    }
}
