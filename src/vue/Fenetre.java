package vue;

import controleur.*;
import modele.Projet;
import modele.coaching.Coureur;
import modele.coaching.Date;
import modele.coaching.Seance;
import modele.meteo.Meteo;
import modele.meteo.PrevisionMeteo;
import modele.patron_observer.IObserver;
import modele.patron_observer.Observable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Fenetre extends JFrame implements IObserver {

    private FicheObjectifs ficheObjectifs;
    private FicheMeteo ficheMeteo;
    private JDesktopPane desktop;
    private List<FicheSeance> fichesSeances;

    private Projet projet;

    public Fenetre(Projet p) {
        super("Coach sportif virtuel");

        this.projet = p;

        // Ajouter la fenêtre dans la liste des observateurs du coureur
        this.projet.getCoureur().ajouterObservateur(this);

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
        this.ficheObjectifs = new FicheObjectifs(this.projet.getCoureur());
        this.desktop.add(this.ficheObjectifs);

        // La fiche météo
        this.ficheMeteo = new FicheMeteo();
        this.desktop.add(ficheMeteo);

        // les fiches séances
        this.initialiserFicheSeances();

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
        JMenuItem menuItemVoir = new JMenuItem("Voir la météo des 5 prochains jours");
        menuItemVoir.addActionListener(new ActionVoirMeteo(this));
        menuMeteo.add(menuItemVoir);

        // La barre des menus
        JMenuBar barre = new JMenuBar();
        barre.add(menuEntr);
        barre.add(menuMeteo);
        this.setJMenuBar(barre);

        // Taille de la fenêtre
        this.setMinimumSize(new Dimension(500, 200));
        this.setExtendedState(MAXIMIZED_BOTH);

        // Afficher la fenêtre
        this.setVisible(true);

        // Arrêter l'applicatin à la fermeture de la fenêtre
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void afficherFicheObjectifs() {
        this.ficheObjectifs.setVisible(true);
    }

    public void afficherMeteo() {
        this.ficheMeteo.maj(this.projet.getPrevisions());
        this.ficheMeteo.setVisible(true);
    }

    public void afficherSeances(int nb) {
        if (nb == 1) {
            // Afficher la séance suivante
            this.fichesSeances.get(0).show();
        } else if (nb > 0) {
            // Afficher toutes les séances
            int x = 0;
            int y = 0;

            for (FicheSeance fs : this.fichesSeances) {
                fs.setLocation(new Point(x, y));

                x += 185;
                if (x + 175 > this.getWidth()) {
                    x = 0;
                    y += 100;
                }
                fs.show();
            }
        }
    }

    public void initialiserFicheSeances() {
        this.fichesSeances = new ArrayList<>();
        for (Seance s : this.projet.getCoureur().getPlanEntrainement()){
            FicheSeance fs = new FicheSeance(this.projet.getCoureur(), s);
            this.fichesSeances.add(fs);
            this.desktop.add(fs);
        }
    }

    @Override
    public void notifier(Observable observable) {
        JInternalFrame[] fenetresAffichees = this.desktop.getAllFrames();
        int nbSeancesAffichees = 0;
        for (int i = 0 ; i < fenetresAffichees.length ; i++)
            if (fenetresAffichees[i] instanceof FicheSeance && fenetresAffichees[i].isVisible())
                nbSeancesAffichees++;

        for (FicheSeance fs : this.fichesSeances)
            this.desktop.remove(fs);

        this.initialiserFicheSeances();
        this.afficherSeances(nbSeancesAffichees);
    }
}
