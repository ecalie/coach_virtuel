package controleur;

import vue.Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionAfficherSeance implements ActionListener {

    private Fenetre fenetre;

    public ActionAfficherSeance(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        fenetre.afficherSeances(1);
    }
}
