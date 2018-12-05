package vue;

import controleur.ActionAfficherEvemement;
import controleur.ActionAjouterEvenement;
import controleur.ActionModifierEvenement;
import controleur.ActionValiderAjouterEvemement;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FicheModifierEvenement extends JInternalFrame {
    public FicheModifierEvenement() {
        super("Modifier évènement", false, true, false, false);
    }

    public void maj(List<FicheEvenement> fiches) {
        JPanel form = new JPanel(new GridLayout(fiches.size(), 4));
        this.getContentPane().setLayout(new BorderLayout());

        for (FicheEvenement f : fiches) {
            form.add(new JLabel(f.getTitreEvenement().getText()));
            form.add(new JLabel(f.getDateDebut().getText()));
            form.add(new JLabel(f.getDateFin().getText()));
            JButton btn = new JButton("Modifier");
            btn.addActionListener(new ActionAfficherEvemement(f));
            form.add(btn);
        }

        this.getContentPane().add(form, BorderLayout.CENTER);

        this.pack();
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

}
