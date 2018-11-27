package controleur;

import vue.FicheSeance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionTerminerSeance implements ActionListener {

    private FicheSeance ficheSeance;

    public ActionTerminerSeance(FicheSeance ficheSeance) {
        this.ficheSeance = ficheSeance;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.ficheSeance.getCoureur().terminerSeance(this.ficheSeance.getSeance());
        this.ficheSeance.hide();
    }
}
