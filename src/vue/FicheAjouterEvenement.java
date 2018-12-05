package vue;

import controleur.ActionValiderAjouterEvemement;
import modele.coaching.Coureur;

import javax.swing.*;
import java.awt.*;

public class FicheAjouterEvenement extends JInternalFrame {

    private JTextField titreEvenement;
    private JTextField dateDebut;
    private JTextField heureDebut;
    private JTextField dateFin;
    private JTextField heureFin;

    private Coureur coureur;

    public FicheAjouterEvenement(Coureur coureur) {
        super("Ajouter événement", false,true,false,false);

        this.titreEvenement = new JTextField();
        this.dateDebut = new JTextField();
        this.heureDebut = new JTextField();
        this.dateFin = new JTextField();
        this.heureFin = new JTextField();

        this.coureur = coureur;

        this.getContentPane().setLayout(new BorderLayout());

        JPanel centre = new JPanel(new GridLayout(5,2));
        centre.add(new JLabel("Titre"));
        centre.add(this.titreEvenement);
        centre.add(new JLabel("Date de début (jj/mm/aaaa)"));
        centre.add(this.dateDebut);
        centre.add(new JLabel("Heure de début (hh:mm)"));
        centre.add(this.heureDebut);
        centre.add(new JLabel("Date de fin (jj/mm/aaaa)"));
        // Pas d'événements sur plusieurs jours pour le moment
        this.dateFin.setEditable(false);
        centre.add(this.dateFin);
        centre.add(new JLabel("Heure de fin (hh:mm)"));
        centre.add(this.heureFin);

        this.getContentPane().add(centre, BorderLayout.CENTER);

        JButton btn = new JButton("Créer");
        btn.addActionListener(new ActionValiderAjouterEvemement(this));
        this.getContentPane().add(btn, BorderLayout.SOUTH);

        this.pack();

    }

    public JTextField getTitreEvenement() {
        return titreEvenement;
    }

    public JTextField getDateDebut() {
        return dateDebut;
    }

    public JTextField getHeureDebut() {
        return heureDebut;
    }

    public JTextField getDateFin() {
        return dateFin;
    }

    public JTextField getHeureFin() {
        return heureFin;
    }

    public Coureur getCoureur() {
        return coureur;
    }
}
