package controleur;

import modele.agenda.Calendrier;
import vue.Fenetre;
import vue.FicheSupprimerEvenement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionSupprimerEvenement implements ActionListener {

    FicheSupprimerEvenement ficheSupprimerEvenement;
    Calendrier calendrier;

    public ActionSupprimerEvenement(FicheSupprimerEvenement ficheSupprimerEvenement, Calendrier calendrier) {
        this.calendrier = calendrier;
        this.ficheSupprimerEvenement = ficheSupprimerEvenement;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.ficheSupprimerEvenement.maj(this.calendrier);
        this.ficheSupprimerEvenement.setVisible(true);
    }
}
