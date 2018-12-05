package controleur;

import modele.agenda.Calendrier;
import modele.agenda.ComparerHeure;
import modele.agenda.Date;
import modele.agenda.Evenement;
import vue.FicheAjouterEvenement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ActionValiderAjouterEvemement implements ActionListener {

    private FicheAjouterEvenement ficheAjouterEvenement;

    public ActionValiderAjouterEvemement(FicheAjouterEvenement ficheAjouterEvenement) {
        this.ficheAjouterEvenement = ficheAjouterEvenement;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Evenement evenement = new Evenement();

        evenement.setTitre(this.ficheAjouterEvenement.getTitreEvenement().getText());

        Date dateDebut = Date.toDate(this.ficheAjouterEvenement.getDateDebut().getText(), this.ficheAjouterEvenement.getHeureDebut().getText());
        String dateFinTexte = this.ficheAjouterEvenement.getDateFin().getText();
        Date dateFin = null;
        // TODO
         dateFin = Date.toDate(this.ficheAjouterEvenement.getDateDebut().getText(), this.ficheAjouterEvenement.getHeureFin().getText());

        evenement.setDateDebut(dateDebut);
        evenement.setDateFin(dateFin);

        Calendrier calendrier = this.ficheAjouterEvenement.getCoureur().getCalendrier();

        if (!calendrier.containsKey(dateDebut))
            calendrier.put(dateDebut, new PriorityQueue<>(10, new ComparerHeure()));
        calendrier.get(dateDebut).add(evenement);

        this.ficheAjouterEvenement.setVisible(false);
    }
}
