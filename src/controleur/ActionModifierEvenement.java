package controleur;

import modele.agenda.Calendrier;
import vue.FicheEvenement;
import vue.FicheModifierEvenement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ActionModifierEvenement implements ActionListener {
    FicheModifierEvenement ficheModifierEvenement;
    Calendrier calendrier;

    public ActionModifierEvenement(FicheModifierEvenement ficheModifierEvenement, Calendrier calendrier) {
        this.calendrier = calendrier;
        this.ficheModifierEvenement = ficheModifierEvenement;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.ficheModifierEvenement.maj(calendrier);
        this.ficheModifierEvenement.show();
    }
}
