package vue;

import controleur.ActionTerminerSeance;
import modele.coaching.Coureur;
import modele.coaching.Date;
import modele.coaching.Seance;
import modele.meteo.Meteo;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class FicheMeteo extends JInternalFrame {

    private List<JLabel> jours;

    public FicheMeteo() {
        super("Prévision météo", false, true, false, true);
    }

    public void maj(List<Meteo> previsions) {
        this.jours = new ArrayList<>();

        JPanel form = new JPanel(new GridLayout(previsions.size(),2));
        this.getContentPane().setLayout(new BorderLayout());

        for (int i = 0 ; i < previsions.size() ; i++) {
            jours.add(new JLabel("     " + previsions.get(i)));
            form.add(new JLabel(Date.today().plus(i).toString()));
            form.add(jours.get(i));
        }

        this.getContentPane().add(form, BorderLayout.CENTER);

        this.pack();
    }

}
