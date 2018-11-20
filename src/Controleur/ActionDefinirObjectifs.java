package Controleur;

import vue.Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionDefinirObjectifs implements ActionListener {

    private Fenetre fenetre;

    public ActionDefinirObjectifs(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.fenetre.afficherFicheObjectifs();
    }
}
