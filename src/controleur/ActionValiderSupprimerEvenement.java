package controleur;

import modele.agenda.Calendrier;
import modele.agenda.Evenement;
import vue.FicheSupprimerEvenement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionValiderSupprimerEvenement implements ActionListener {

    FicheSupprimerEvenement ficheSupprimerEvenement;
    Calendrier calendrier;
    Evenement evenement;

    public ActionValiderSupprimerEvenement(FicheSupprimerEvenement ficheSupprimerEvenement, Calendrier calendrier, Evenement evenement) {
        this.ficheSupprimerEvenement = ficheSupprimerEvenement;
        this.calendrier = calendrier;
        this.evenement = evenement;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.calendrier.get(this.evenement.getDateDebut()).remove(evenement);
        ficheSupprimerEvenement.maj(this.calendrier);
    }
}
