package vue;

import Coaching.Coureur;
import Controleur.ActionAfficherSeance;
import Controleur.ActionAfficherSeances;
import Controleur.ActionDefinirObjectifs;
import Controleur.ActionDefinirPlan;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {

    private FicheObjectifs ficheObjectifs;
    private JDesktopPane desktop;

    private Coureur coureur;

    public Fenetre() {
        super("Coach sportif virtuel");

        this.coureur = new Coureur();

        /* L'espace des fenêtres internes */
        this.desktop = new JDesktopPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GRAY);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        this.getContentPane().add(this.desktop, BorderLayout.CENTER);

        // La fiche coureur
        this.ficheObjectifs = new FicheObjectifs(this.coureur);
        this.desktop.add(this.ficheObjectifs);

        // Les menus
        JMenu menuEntr = new JMenu("Plan entrainement");
        JMenuItem menuItemDef = new JMenuItem("Définir mes objectifs");
        JMenuItem menuItemPlan = new JMenuItem("Définir un plan d'entraînement");
        JMenuItem menuItemSeance = new JMenuItem("Voir la prochaine séance");
        JMenuItem menuItemSeances = new JMenuItem("Voir toutes les prochaines séances");
        menuItemDef.addActionListener(new ActionDefinirObjectifs(this));
        menuItemPlan.addActionListener(new ActionDefinirPlan(this));
        menuItemSeance.addActionListener(new ActionAfficherSeance(this));
        menuItemSeances.addActionListener(new ActionAfficherSeances(this));
        menuEntr.add(menuItemDef);
        menuEntr.add(menuItemPlan);
        menuEntr.add(menuItemSeance);
        menuEntr.add(menuItemSeances);

        JMenuBar barre = new JMenuBar();
        barre.add(menuEntr);
        this.setJMenuBar(barre);

        this.setExtendedState(MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JDesktopPane getDesktop() {
        return desktop;
    }

    public Coureur getCoureur() {
        return coureur;
    }

    public void afficherFicheObjectifs() {
        this.ficheObjectifs.setVisible(true);
    }
}
