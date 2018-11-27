package vue;

import controleur.*;
import modele.coaching.Coureur;
import modele.coaching.Date;
import modele.meteo.Meteo;
import modele.meteo.PrevisionMeteo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Fenetre extends JFrame {

    private FicheObjectifs ficheObjectifs;
    private FicheMeteo ficheMeteo;
    private JDesktopPane desktop;

    private PrevisionMeteo modulePrevision;
    private Coureur coureur;
    private List<Meteo> previsions;

    public Fenetre() {
        super("Coach sportif virtuel");

        this.coureur = new Coureur();
        this.modulePrevision = new PrevisionMeteo();

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

        // La fiche météo
        this.ficheMeteo = new FicheMeteo();
        this.desktop.add(ficheMeteo);

        // Les menus
        //      - menu entraiement
        JMenu menuEntr = new JMenu("Plan entrainement");
        JMenuItem menuItemDef = new JMenuItem("Définir mes objectifs");
        JMenuItem menuItemSeance = new JMenuItem("Voir la prochaine séance");
        JMenuItem menuItemSeances = new JMenuItem("Voir toutes les prochaines séances");
        menuItemDef.addActionListener(new ActionDefinirObjectifs(this));
        menuItemSeance.addActionListener(new ActionAfficherSeance(this));
        menuItemSeances.addActionListener(new ActionAfficherSeances(this));
        menuEntr.add(menuItemDef);
        menuEntr.add(menuItemSeance);
        menuEntr.add(menuItemSeances);

        //      -menu météo
        JMenu menuMeteo = new JMenu("Météo");
        JMenuItem menuItemPrevision = new JMenuItem("Prévoir la météo sur les 5 prochains jours");
        JMenuItem menuItemVoir = new JMenuItem("Voir la météo des 5 prochains jours");
        menuItemPrevision.addActionListener(new ActionPrevision(this));
        menuItemVoir.addActionListener(new ActionVoirMeteo(this));
        menuMeteo.add(menuItemPrevision);
        menuMeteo.add(menuItemVoir);

        JMenuBar barre = new JMenuBar();
        barre.add(menuEntr);
        barre.add(menuMeteo);
        this.setJMenuBar(barre);

        this.setMinimumSize(new Dimension(500,200));
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

    public PrevisionMeteo getModulePrevision() {
        return modulePrevision;
    }

    public void afficherFicheObjectifs() {
        this.ficheObjectifs.setVisible(true);
    }

    public void prevoirMeteo() {
        this.previsions = this.modulePrevision.prevision5Jours(Date.today());
    }

    public void afficherMeteo() {
        if (this.previsions == null) {
            JButton button = new JButton("Aucune prévisions ...");
            button.setBackground(Color.RED);
            PopupFactory factory = PopupFactory.getSharedInstance();
            final Popup popup = factory.getPopup(this, button,
                    (int) this.getLocation().getX() + this.getWidth()/2,
                    (int) this.getLocation().getY() + this.getHeight()/2);
            popup.show();
            ActionListener hider = e -> popup.hide();

            // Masquer le pop up après 3 seconces
            Timer timer = new Timer(3000, hider);
            timer.start();
        } else {
            this.ficheMeteo.maj(this.previsions);
            this.ficheMeteo.setVisible(true);
        }
    }
}
