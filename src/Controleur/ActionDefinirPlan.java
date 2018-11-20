package Controleur;

import vue.Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionDefinirPlan implements ActionListener {
    private Fenetre fenetre;

    public ActionDefinirPlan(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.fenetre.getCoureur().creerPlanEntrainement();
    }
}
