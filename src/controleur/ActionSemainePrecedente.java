package controleur;

import vue.FicheCalendrier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionSemainePrecedente implements ActionListener {
    private FicheCalendrier ficheCalendrier;

    public ActionSemainePrecedente(FicheCalendrier ficheCalendrier) {
        this.ficheCalendrier = ficheCalendrier;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.ficheCalendrier.setDebut(this.ficheCalendrier.getDebut().plus(-7));
        this.ficheCalendrier.maj();
        this.ficheCalendrier.validate();
    }
}
