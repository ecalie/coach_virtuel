package vue;

import controleur.ActionTerminerSeance;
import modele.coaching.Coureur;
import modele.agenda.Date;
import modele.coaching.Seance;
import modele.coaching.SeanceFabrique;
import modele.meteo.Meteo;

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

        JPanel form = new JPanel(new GridLayout(3, 2));
        this.getContentPane().setLayout(new BorderLayout());

        form.add(new JLabel("Distance"));
        form.add(this.distance);
        form.add(new JLabel("Durée"));
        form.add(this.duree);
        form.add(new JLabel("Date"));
        form.add(this.date);

        this.getContentPane().add(form, BorderLayout.CENTER);

        this.setSize(new Dimension(180, 95));
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public Coureur getCoureur() {
        return coureur;
    }

    public Seance getSeance() {
        return seance;
    }

    /**
     * Mettre en forme la fenetre pour indique la météo de la séance du jour.
     * @param meteo La météo du jour
     */
    public void majEtAfficher(Meteo meteo) {
        this.setTitle("La séance d'aujourd'hui");
        String texte = "La météo du jour est " + meteo;
        if (meteo == Meteo.pluie)
            texte += "\n La séance est raccourcie : 80% de la distance prévue";
        if (meteo == Meteo.neige)
            texte += "\n La séance est raccourcie : 60% de la distance prévue";

        JLabel labelMeteo = new JLabel(texte);
        this.getContentPane().add(labelMeteo, BorderLayout.NORTH);

        Seance s = SeanceFabrique.getInstance().getSeance(Date.today(), meteo, this.coureur.getCalendrier());
        this.distance.setText("" + s.getDistance());

        JButton btn = new JButton("Séance terminée");
        btn.addActionListener(new ActionTerminerSeance(this));
        this.getContentPane().add(btn, BorderLayout.SOUTH);

        this.setSize(new Dimension(200,150));
        this.setVisible(true);

        if (!s.getDate().equals(Date.today())) {
            this.reinitialiser();
            this.hide();
        }
    }

    /**
     * Réinitialiser au format classique la fenetre.
     */
    public void reinitialiser() {
        this.setTitle("" + seance.getDate());

        this.getContentPane().removeAll();

        JPanel form = new JPanel(new GridLayout(3, 2));
        this.getContentPane().setLayout(new BorderLayout());

        form.add(new JLabel("Distance"));
        form.add(this.distance);
        form.add(new JLabel("Durée"));
        form.add(this.duree);
        form.add(new JLabel("Date"));
        form.add(this.date);

        this.getContentPane().add(form, BorderLayout.CENTER);

        this.setSize(new Dimension(180, 95));
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
}
