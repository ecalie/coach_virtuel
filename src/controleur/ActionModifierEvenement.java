package controleur;

import vue.FicheEvenement;
import vue.FicheModifierEvenement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ActionModifierEvenement implements ActionListener {
    FicheModifierEvenement ficheModifierEvenement;
    List<FicheEvenement> ficheEvenements;

    public ActionModifierEvenement(FicheModifierEvenement ficheModifierEvenement, List<FicheEvenement> ficheEvenements) {
        this.ficheEvenements = ficheEvenements;
        this.ficheModifierEvenement = ficheModifierEvenement;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.ficheModifierEvenement.maj(ficheEvenements);
        this.ficheModifierEvenement.show();
    }
}
