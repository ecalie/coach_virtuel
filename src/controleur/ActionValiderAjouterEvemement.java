package controleur;

import modele.agenda.Calendrier;
import modele.agenda.Date;
import modele.agenda.Evenement;
import vue.FicheEvenement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.PriorityQueue;

public class ActionValiderAjouterEvemement implements ActionListener {

    private FicheEvenement ficheEvenement;

    public ActionValiderAjouterEvemement(FicheEvenement ficheEvenement) {
        this.ficheEvenement = ficheEvenement;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Evenement evenement;
        // Si nouvel event, on le crée, sinon on le récupère pour le modifier
        if (this.ficheEvenement.getEvenement() == null)
            evenement = new Evenement();
        else
            evenement = this.ficheEvenement.getEvenement();

        // Modifier le titre
        evenement.setTitre(this.ficheEvenement.getTitreEvenement().getText());

        // Modifier la date de début et de fin
        Date dateDebut = Date.toDate(this.ficheEvenement.getDateDebut().getText(), this.ficheEvenement.getHeureDebut().getText());
        Date dateFin;
        if (this.ficheEvenement.getDateFin().getText() == "")
            dateFin = Date.toDate(this.ficheEvenement.getDateFin().getText(), this.ficheEvenement.getHeureFin().getText());
        else
            dateFin = Date.toDate(this.ficheEvenement.getDateDebut().getText(), this.ficheEvenement.getHeureFin().getText());

        // on supprime l'évènement au calendrier si modification
        Calendrier calendrier = this.ficheEvenement.getCoureur().getCalendrier();
        if (this.ficheEvenement.getEvenement() != null)
            calendrier.get(evenement.getDateDebut()).remove(evenement);

        // mettre à jour les dates
        evenement.setDateDebut(dateDebut);
        evenement.setDateFin(dateFin);

        // ajouter l'évènement au calendrier
        if (!calendrier.containsKey(dateDebut))
            calendrier.put(dateDebut, new PriorityQueue<>());
        calendrier.get(dateDebut).add(evenement);


        // mettre à jour l'evènement
        this.ficheEvenement.setEvenement(evenement);

        this.ficheEvenement.setVisible(false);
    }
}
