package Controleur;

import Coaching.Seance;
import vue.Fenetre;
import vue.FicheSeance;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionAfficherSeances implements ActionListener {

    private Fenetre fenetre;

    public ActionAfficherSeances(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int x = 0;
        int y = 0;
        for (Seance seance : fenetre.getCoureur().getPlanEntrainement()) {
            FicheSeance fs = new FicheSeance(fenetre.getCoureur(), seance);
            fs.setLocation(new Point(x, y));
            x += 185;
            if (x + 175 > fenetre.getWidth()) {
                x = 0;
                y += 80;
            }
            fs.setTitle(seance.getDate().toString());
            fenetre.getDesktop().add(fs);
            fs.setVisible(true);
        }
    }
}
