package Controleur;

import Coaching.Coureur;
import Coaching.Date;
import vue.FicheObjectifs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionValiderFicheObjectifs implements ActionListener {

    private FicheObjectifs ficheObjectifs;

    public ActionValiderFicheObjectifs(FicheObjectifs ficheObjectifs) {
        this.ficheObjectifs = ficheObjectifs;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        this.ficheObjectifs.getCoureur().setDateLimite(Date.toDate(this.ficheObjectifs.getDateLimite().getText()));
        this.ficheObjectifs.getCoureur().setObjectifDuree(Integer.parseInt(this.ficheObjectifs.getObjectifDuree().getText()));
        this.ficheObjectifs.getCoureur().setObjectifDistance(Integer.parseInt(this.ficheObjectifs.getObjectifDistance().getText()));

        this.ficheObjectifs.setVisible(false);
    }
}
