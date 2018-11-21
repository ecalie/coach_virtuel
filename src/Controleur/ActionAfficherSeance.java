package Controleur;

import Coaching.Seance;
import vue.Fenetre;
import vue.FicheSeance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionAfficherSeance implements ActionListener {

    private Fenetre fenetre;

    public ActionAfficherSeance(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Seance seance = fenetre.getCoureur().getPlanEntrainement().get(0);
        FicheSeance fs = new FicheSeance(seance.getDistance(), seance.getDurée(), seance.getDate());
        fenetre.getDesktop().add(fs);
        fs.setVisible(true);
    }
}
