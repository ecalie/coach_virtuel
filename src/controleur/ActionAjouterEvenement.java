package controleur;

import vue.Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionAjouterEvenement implements ActionListener {

    Fenetre fenetre;

    public ActionAjouterEvenement(Fenetre fenetre) {this.fenetre = fenetre;}

    @Override
    public void actionPerformed(ActionEvent e) {
        this.fenetre.afficherFormEvenement();
    }
}
