package vue;

import controleur.*;
import modele.agenda.Calendrier;
import modele.agenda.Date;
import modele.agenda.Evenement;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FicheModifierEvenement extends JInternalFrame {
    private Fenetre fenetre;

    public FicheModifierEvenement(Fenetre fenetre) {
        super("Modifier évènement", false, true, false, false);
        this.fenetre = fenetre;
    }

    public void maj(Calendrier calendrier) {
        this.getContentPane().removeAll();
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
                JButton btn = new JButton("Modifier");
                btn.addActionListener(new ActionAfficherEvemement(fenetre, evenement));
                form.add(btn);
            }
        }

        this.getContentPane().add(form, BorderLayout.CENTER);

        this.pack();
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

}
