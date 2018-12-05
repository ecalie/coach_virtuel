package vue;

import controleur.ActionSemainePrecedente;
import controleur.ActionSemaineSuivante;
import modele.agenda.Calendrier;
import modele.agenda.Date;
import modele.agenda.Evenement;
import modele.coaching.SeanceFabrique;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FicheCalendrier extends JInternalFrame {

    private Calendrier calendrier;
    private List<Component> evenementsSemaine;
    private Date debut;
    private JPanel centre;

    public FicheCalendrier(Calendrier calendrier) {
        super("Calendrier", false, true, false, true);
        this.calendrier = calendrier;
        this.debut = Date.today();
        this.evenementsSemaine = new ArrayList<>();

        this.getContentPane().setLayout(new BorderLayout());

        centre = new JPanel(new GridBagLayout());

        this.getContentPane().add(centre, BorderLayout.CENTER);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);


        for (int i = 0; i < 25; i++) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = i + 2;
            centre.add(new JLabel("" + i), gbc);
            gbc.insets = new Insets(0, 0, 0, 5);
        }

        JButton btnSuiv = new JButton(">>");
        btnSuiv.addActionListener(new ActionSemaineSuivante(this));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 8;
        gbc.gridy = 0;
        centre.add(btnSuiv, gbc);

        JButton btnPrec = new JButton("<<");
        btnPrec.addActionListener(new ActionSemainePrecedente(this));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        centre.add(btnPrec, gbc);

        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public void maj() {
        for (Component c : evenementsSemaine)
            this.centre.remove(c);


        for (int i = 0; i < 7; i++) {
            JLabel date = new JLabel(debut.plus(i).toString());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = i + 1;
            gbc.gridy = 0;
            gbc.insets = new Insets(0, 5, 0, 5);
            centre.add(date, gbc);
            this.evenementsSemaine.add(date);
        }

        for (int i = 0; i < 7; i++) {
            if (calendrier.containsKey(debut.plus(i)))
                for (Evenement e : calendrier.get(debut.plus(i))) {
                    int duree = e.getDateDebut().ecartMinutes(e.getDateFin()) / 60;

                    JPanel event = new JPanel(new BorderLayout());
                    JLabel label = new JLabel(e.getTitre());
                    event.setBackground(Color.GRAY);
                    event.add(label, BorderLayout.CENTER);

                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = i + 1;
                    gbc.gridy = e.getDateDebut().getHeure() + 2;
                    gbc.gridwidth = 1;
                    gbc.gridheight = duree;
                    gbc.fill = GridBagConstraints.BOTH;
                    centre.add(event, gbc);
                    this.evenementsSemaine.add(event);
                }
            if (SeanceFabrique.getInstance().getSeance(debut.plus(i)) != null) {
                JLabel seance = new JLabel("Séance");

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = i + 1;
                gbc.gridy = 1;
                centre.add(seance, gbc);
                this.evenementsSemaine.add(seance);
            }

        }

        this.pack();
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }
}
