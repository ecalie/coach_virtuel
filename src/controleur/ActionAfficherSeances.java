package controleur;

import modele.coaching.Seance;
import vue.Fenetre;
import vue.FicheSeance;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionAfficherSeances implements ActionListener {

    private Fenetre fenetre;

    public ActionAfficherSeances(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        fenetre.afficherSeances(2);
    }
}
