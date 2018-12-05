package controleur;

import vue.FicheEvenement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionAfficherEvemement implements ActionListener {
    private FicheEvenement ficheEvenement;

    public ActionAfficherEvemement(FicheEvenement ficheEvenement) {
        this.ficheEvenement = ficheEvenement;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.ficheEvenement.show();
    }
}
