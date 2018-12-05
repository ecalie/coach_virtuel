package vue;

import modele.agenda.Calendrier;
import modele.agenda.Date;
import modele.agenda.Evenement;

import javax.swing.*;
import java.awt.*;

public class FicheCalendrier extends JInternalFrame {

    private Calendrier calendrier;

    public FicheCalendrier(Calendrier calendrier) {
        this.calendrier = calendrier;
    }

    public void maj() {
        this.getContentPane().setLayout(new BorderLayout());

        Date today = Date.today();

        JPanel centre = new JPanel(new GridBagLayout());

        this.getContentPane().add(centre, BorderLayout.CENTER);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);

        for (int i = 0; i < 7; i++) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = i + 1;
            gbc.gridy = 0;
            gbc.insets = new Insets(0, 5, 0, 5);
            centre.add(new JLabel(today.plus(i).toString()), gbc);
        }


        for (int i = 0; i < 25; i++) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            centre.add(new JLabel("" + i), gbc);
            gbc.insets = new Insets(0, 0, 0, 5);
        }

        for (int i = 0; i < 7; i++)
            if (calendrier.containsKey(today.plus(i)))
                for (Evenement e : calendrier.get(today.plus(i))) {
                    int duree = e.getDateDebut().ecartMinutes(e.getDateFin()) / 60;

                    JPanel event = new JPanel(new BorderLayout());
                    JLabel label = new JLabel(e.getTitre());
                    event.setBackground(Color.GRAY);
                    event.add(label, BorderLayout.CENTER);

                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = i + 1;
                    gbc.gridy = e.getDateDebut().getHeure() + 1;
                    gbc.gridwidth = 1;
                    gbc.gridheight = duree;
                    gbc.fill = GridBagConstraints.BOTH;
                    centre.add(event, gbc);
                }

        this.pack();
    }


}
