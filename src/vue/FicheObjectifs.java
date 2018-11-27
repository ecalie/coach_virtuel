package vue;

import modele.coaching.Coureur;
import controleur.ActionValiderFicheObjectifs;

import javax.swing.*;
import java.awt.*;

public class FicheObjectifs extends JInternalFrame {

    private JTextField objectifDistance;
    private JTextField objectifDuree;
    private JTextField dateLimite;

    private Coureur coureur;


    public FicheObjectifs(Coureur coureur) {
        super("Mes objectifs", false, true, false, true);

        this.objectifDistance = new JTextField();
        this.objectifDuree = new JTextField();
        this.dateLimite = new JTextField();

        this.coureur = coureur;

        this.getContentPane().setLayout(new BorderLayout());

        JPanel centre = new JPanel(new GridLayout(3,2));
        centre.add(new JLabel("Distance"));
        centre.add(this.objectifDistance);
        centre.add(new JLabel("Durée"));
        centre.add(this.objectifDuree);
        centre.add(new JLabel("Date"));
        centre.add(this.dateLimite);

        this.getContentPane().add(centre, BorderLayout.CENTER);

        this.getContentPane().add(new JLabel("Mes Objectifs"), BorderLayout.NORTH);

        JButton btn = new JButton("Valider");
        btn.addActionListener(new ActionValiderFicheObjectifs(this));
        this.getContentPane().add(btn, BorderLayout.SOUTH);

        this.setSize(new Dimension(300,150));
    }

    public JTextField getObjectifDistance() {
        return objectifDistance;
    }

    public JTextField getObjectifDuree() {
        return objectifDuree;
    }

    public JTextField getDateLimite() {
        return dateLimite;
    }

    public Coureur getCoureur() {
        return coureur;
    }
}

