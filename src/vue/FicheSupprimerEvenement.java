package vue;

import controleur.ActionValiderSupprimerEvenement;
import modele.agenda.Calendrier;
import modele.agenda.Date;
import modele.agenda.Evenement;

import javax.swing.*;
import java.awt.*;

public class FicheSupprimerEvenement extends JInternalFrame {
    public FicheSupprimerEvenement() {
        super("Supprimer événement", false, true, false, false);
    }

    /**
     * Mettre à jour la fenêtre.
     * @param calendrier Le calendrier avec les évènements existants.
     */
    public void maj(Calendrier calendrier) {
        int nbEvenements = 0;
        for (Date date : calendrier.keySet())
            nbEvenements += calendrier.get(date).size();
        JPanel form = new JPanel(new GridLayout(nbEvenements, 4));
        this.getContentPane().setLayout(new BorderLayout());

        for (Date date : calendrier.keySet()) {
            for (Evenement evenement : calendrier.get(date)) {
                form.add(new JLabel(evenement.getTitre()));
                form.add(new JLabel(evenement.getDateDebut().toString()));
                form.add(new JLabel(evenement.getDateFin().toString()));
                JButton btn = new JButton("Supprimer");
                btn.addActionListener(new ActionValiderSupprimerEvenement(this, calendrier, evenement));
                form.add(btn);
            }
        }

        this.getContentPane().add(form, BorderLayout.CENTER);

        this.setMinimumSize(new Dimension(200,50));
        this.pack();
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

}

