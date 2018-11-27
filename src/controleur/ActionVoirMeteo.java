package controleur;

import vue.Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionVoirMeteo implements ActionListener {
    private Fenetre fenetre;

    public ActionVoirMeteo(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.fenetre.afficherMeteo();
    }
}
