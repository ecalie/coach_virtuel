package controleur;

import modele.agenda.Calendrier;
import modele.agenda.Evenement;
import vue.Fenetre;
import vue.FicheEvenement;
import vue.FicheModifierEvenement;
import vue.FicheSupprimerEvenement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionAfficherEvemement implements ActionListener {

    private Fenetre fenetre;
    private Evenement evenement;

    public ActionAfficherEvemement(Fenetre fenetre, Evenement evenement) {
        this.fenetre = fenetre;
        this.evenement = evenement;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FicheEvenement fe = new FicheEvenement(fenetre.getProjet().getCoureur(), evenement);
        this.fenetre.getDesktop().add(fe);
        fe.show();
    }
}
