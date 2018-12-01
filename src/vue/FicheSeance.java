package vue;

import modele.coaching.Coureur;
import modele.coaching.Seance;
import controleur.ActionTerminerSeance;

import javax.swing.*;
import java.awt.*;

public class FicheSeance extends JInternalFrame {

    private JLabel distance;
    private JLabel duree;
    private JLabel date;

    private Coureur coureur;
    private Seance seance;


    public FicheSeance(Coureur coureur, Seance seance) {
        super("" + seance.getDate(), false, true, false, true);

        this.distance = new JLabel(seance.getDistance() + "km");
        this.duree = new JLabel(seance.getDuree() + "minutes");
        this.date = new JLabel(seance.getDate().toString());

        this.coureur = coureur;
        this.seance = seance;

        JPanel form = new JPanel(new GridLayout(3,2));
        this.getContentPane().setLayout(new BorderLayout());

        form.add(new JLabel("Distance"));
        form.add(this.distance);
        form.add(new JLabel("Durée"));
        form.add(this.duree);
        form.add(new JLabel("Date"));
        form.add(this.date);

        this.getContentPane().add(form, BorderLayout.CENTER);

        JButton btn = new JButton("Séance terminée");
        btn.addActionListener(new ActionTerminerSeance(this));
        this.getContentPane().add(btn, BorderLayout.SOUTH);

        this.setSize(new Dimension(180,95));
    }

    public Coureur getCoureur() {
        return coureur;
    }

    public Seance getSeance() {
        return seance;
    }
}
