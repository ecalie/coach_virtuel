package controleur;

import modele.agenda.Calendrier;
import modele.agenda.Date;
import modele.agenda.Evenement;
import vue.FicheAjouterEvenement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        Date dateFin = Date.toDate(this.ficheAjouterEvenement.getDateFin().getText(), this.ficheAjouterEvenement.getHeureFin().getText());

        evenement.setDateDebut(dateDebut);
        evenement.setDateFin(dateFin);

        Calendrier calendrier = this.ficheAjouterEvenement.getCoureur().getCalendrier();

        if (!calendrier.containsKey(dateDebut))
            calendrier.put(dateDebut, new ArrayList<>());
        calendrier.get(dateDebut).add(evenement);

        this.ficheAjouterEvenement.setVisible(false);
    }
}
