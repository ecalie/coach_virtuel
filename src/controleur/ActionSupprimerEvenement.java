package controleur;

import vue.Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionSupprimerEvenement implements ActionListener {

    Fenetre fenetre;

    public ActionSupprimerEvenement(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.fenetre.afficherListEvenements();

    }
}
