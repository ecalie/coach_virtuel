package controleur;

import vue.Fenetre;
import vue.FicheObjectifs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionDefinirObjectifs implements ActionListener {

    private FicheObjectifs ficheObjectifs;

    public ActionDefinirObjectifs(FicheObjectifs ficheObjectifs) {
        this.ficheObjectifs = ficheObjectifs;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        this.ficheObjectifs.setVisible(true);
    }
}
