package vue;

import controleur.ActionValiderFicheObjectifs;
import modele.coaching.Coureur;

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

        JPanel centre = new JPanel(new GridLayout(3, 2));
        centre.add(new JLabel("Distance"));
        centre.add(this.objectifDistance);
        centre.add(new JLabel("Durée"));
        centre.add(this.objectifDuree);
        centre.add(new JLabel("Date"));
        centre.add(this.dateLimite);

        // pré-remplir les champs
        if (coureur.getObjectifDuree() != 0)
            this.objectifDuree.setText("" + coureur.getObjectifDuree());
        if (coureur.getObjectifDistance() != 0)
            this.objectifDistance.setText("" + coureur.getObjectifDistance());
        if (coureur.getDateLimite() != null)
            this.dateLimite.setText(coureur.getDateLimite().toString());


        this.getContentPane().add(centre, BorderLayout.CENTER);

        JButton btn = new JButton("Valider");
        btn.addActionListener(new ActionValiderFicheObjectifs(this));
        this.getContentPane().add(btn, BorderLayout.SOUTH);

        this.setSize(new Dimension(300, 150));
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
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

