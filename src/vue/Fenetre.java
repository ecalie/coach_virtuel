package vue;

import controleur.*;
import modele.Projet;
import modele.coaching.Date;
import modele.coaching.SeanceFabrique;
import modele.patron_observer.IObserver;
import modele.patron_observer.Observable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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

        // L'espace des fenêtres internes
        this.desktop = new JDesktopPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GRAY);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        this.getContentPane().add(this.desktop);

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
        JMenuItem menuItemSeancesTerminees = new JMenuItem("Voir les séances terminées");
        menuItemDef.addActionListener(new ActionDefinirObjectifs(this));
        menuItemSeance.addActionListener(new ActionAfficherSeance(this));
        menuItemSeances.addActionListener(new ActionAfficherSeances(this));
        menuItemSeancesTerminees.addActionListener(new ActionAfficherTerminees(this));
        menuEntr.add(menuItemDef);
        menuEntr.add(menuItemSeance);
        menuEntr.add(menuItemSeances);
        menuEntr.add(menuItemSeancesTerminees);

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

        // Afficher la séance d'aujourd'hui s'il y en a une
        if (SeanceFabrique.getInstance().getSeance(Date.today()) != null)
            this.fichesSeances.get(0).majEtAfficher(this.projet.getMeteo());


        // Taille de la fenêtre
        this.setMinimumSize(new Dimension(500, 200));
        this.setExtendedState(MAXIMIZED_BOTH);

        // Afficher la fenêtre
        this.setVisible(true);

        // Arrêter l'applicatin à la fermeture de la fenêtre
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void afficherFicheObjectifs() {
        this.ficheObjectifs.setVisible(true);
    }

    public void afficherMeteo() {
        this.ficheMeteo.maj(this.projet.getPrevisions());
        this.ficheMeteo.setVisible(true);
    }

    public void afficherSeances(int nb) {
        for (FicheSeance fs : this.fichesSeances)
            fs.hide();
        if (nb == 1) {
            // Afficher la séance suivante
            this.fichesSeances.get(this.projet.getCoureur().getProchaineSeance()).show();
        } else if (nb > 0) {
            // Afficher toutes les séances
            int x = 0;
            int y = 0;

            for (int i = this.projet.getCoureur().getProchaineSeance(); i < this.fichesSeances.size(); i++) {
                FicheSeance fs = this.fichesSeances.get(i);
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
        for (int i : this.projet.getCoureur().getPlanEntrainement()) {
            FicheSeance fs = new FicheSeance(this.projet.getCoureur(), SeanceFabrique.getInstance().getSeance(i));
            this.fichesSeances.add(fs);
            this.desktop.add(fs);
        }
    }

    public void afficherTerminees() {
        int x = 0;
        int y = 0;
        for (FicheSeance fs : this.fichesSeances)
            fs.hide();

        for (int i = 0; i < this.projet.getCoureur().getProchaineSeance(); i++) {
            FicheSeance fs = this.fichesSeances.get(i);
            fs.setLocation(new Point(x, y));

            x += 185;
            if (x + 175 > this.getWidth()) {
                x = 0;
                y += 100;
            }
            fs.reinitialiser();
            fs.show();
        }
    }

    @Override
    public void notifier(Observable observable) {
        JInternalFrame[] fenetresAffichees = this.desktop.getAllFrames();
        int nbSeancesAffichees = 0;
        for (int i = 0; i < fenetresAffichees.length; i++)
            if (fenetresAffichees[i] instanceof FicheSeance && fenetresAffichees[i].isVisible())
                nbSeancesAffichees++;

        for (FicheSeance fs : this.fichesSeances)
            this.desktop.remove(fs);

        this.initialiserFicheSeances();
        this.afficherSeances(nbSeancesAffichees);
    }

    @Override
    public void dispose() {
        this.projet.enregistrerTout();
        super.dispose();
    }
}
