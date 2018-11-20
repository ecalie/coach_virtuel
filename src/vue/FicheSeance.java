package vue;

import Coaching.Date;

import javax.swing.*;
import java.awt.*;

public class FicheSeance extends JInternalFrame {

    private JLabel distance;
    private JLabel duree;
    private JLabel date;

    public FicheSeance(int distance, int duree, Date date) {
        super("Séance", false, true, false, true);

        this.distance = new JLabel(distance + "km");
        this.duree = new JLabel(duree + "minutes");
        this.date = new JLabel(date.toString());

        this.getContentPane().setLayout(new GridLayout(3,2));
        this.getContentPane().add(new JLabel("Distance"));
        this.getContentPane().add(this.distance);
        this.getContentPane().add(new JLabel("Durée"));
        this.getContentPane().add(this.duree);
        this.getContentPane().add(new JLabel("Date"));
        this.getContentPane().add(this.date);

        this.setSize(new Dimension(175,70));
    }
}
