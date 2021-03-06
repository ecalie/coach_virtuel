package vue;

import controleur.*;
import modele.Projet;
import modele.agenda.Date;
import modele.agenda.Evenement;
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
    private FicheSupprimerEvenement ficheSupprimerEvenement;
    private FicheModifierEvenement ficheModifierEvenement;
    private JDesktopPane desktop;
    private List<FicheSeance> fichesSeances;
    private List<FicheEvenement> fichesEvenements;
    private FicheCalendrier ficheCalendrier;


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

        // La fiche supprimer événement
        this.ficheSupprimerEvenement = new FicheSupprimerEvenement();
        this.desktop.add(ficheSupprimerEvenement);

        // La fiche modifier événement
        this.ficheModifierEvenement = new FicheModifierEvenement(this);
        this.desktop.add(ficheModifierEvenement);

        // La fiche calendrier
        this.ficheCalendrier = new FicheCalendrier(this.projet.getCoureur().getCalendrier());
        this.desktop.add(ficheCalendrier);

        // La fiche calendrier
        this.initialiserFichesEvenements();

        // les fiches séances
        this.initialiserFichesSeances();

        // Les menus
        //      - menu entraiement
        JMenu menuEntr = new JMenu("Plan entrainement");
        JMenuItem menuItemDef = new JMenuItem("Définir mes objectifs");
        JMenuItem menuItemSeance = new JMenuItem("Voir la prochaine séance");
        JMenuItem menuItemSeances = new JMenuItem("Voir toutes les prochaines séances");
        JMenuItem menuItemSeancesTerminees = new JMenuItem("Voir les séances terminées");
        menuItemDef.addActionListener(new ActionDefinirObjectifs(this.ficheObjectifs));
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

        //      -menu agenda
        JMenu menuAgenda = new JMenu("Agenda");
        JMenuItem menuItemAjouter = new JMenuItem("Ajouter événement");
        JMenuItem menuItemSupprimer = new JMenuItem("Supprimer événement");
        JMenuItem menuItemModifier = new JMenuItem("Modifier événement");
        JMenuItem menuItemVoirCalendrier = new JMenuItem("Voir le caldendrier");
        menuItemAjouter.addActionListener(new ActionAjouterEvenement(this));
        menuItemSupprimer.addActionListener(new ActionSupprimerEvenement(this.ficheSupprimerEvenement, this.projet.getCoureur().getCalendrier()));
        menuItemModifier.addActionListener(new ActionModifierEvenement(this.ficheModifierEvenement, this.projet.getCoureur().getCalendrier()));
        menuItemVoirCalendrier.addActionListener(new ActionAfficherCalendrier(this.ficheCalendrier));
        menuAgenda.add(menuItemAjouter);
        menuAgenda.add(menuItemSupprimer);
        menuAgenda.add(menuItemModifier);
        menuAgenda.add(menuItemVoirCalendrier);

        // La barre des menus
        JMenuBar barre = new JMenuBar();
        barre.add(menuEntr);
        barre.add(menuMeteo);
        barre.add(menuAgenda);
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

    public JDesktopPane getDesktop() {
        return desktop;
    }

    public Projet getProjet() {
        return projet;
    }

    public void afficherMeteo() {
        this.ficheMeteo.maj(this.projet.getPrevisions());
        this.ficheMeteo.setVisible(true);
    }

    /**
     * Afficher les fiches des prochaines séances.
     * @param nb Positif si afficher toutes les prochaines séances, égal à 1 pour afficher seulement la suivante
     */
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

    /**
     * Initialiser la liste des fiches séances à partie de plan d'entrainement.
     */
    public void initialiserFichesSeances() {
        this.fichesSeances = new ArrayList<>();
        for (int i : this.projet.getCoureur().getPlanEntrainement()) {
            FicheSeance fs = new FicheSeance(this.projet.getCoureur(), SeanceFabrique.getInstance().getSeance(i));
            this.fichesSeances.add(fs);
            this.desktop.add(fs);
        }
    }

    /**
     * Initialiser la liste des fiches évènement à partir du calendrier du coureur.
     */
    public void initialiserFichesEvenements() {
        this.fichesEvenements = new ArrayList<>();
        for (Date d : this.projet.getCoureur().getCalendrier().keySet())
            for (Evenement e : this.projet.getCoureur().getCalendrier().get(d)) {
                FicheEvenement fe = new FicheEvenement(this.projet.getCoureur(), e);
                this.fichesEvenements.add(fe);
                this.desktop.add(fe);
            }
    }

    /**
     * Afficher toutes les séances terminées.
     */
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

    /**
     * Afficher un nouveau formulare pour créer un évènement.
     */
    public void afficherFormEvenement() {
        FicheEvenement ficheEvenement = new FicheEvenement(this.projet.getCoureur());
        this.desktop.add(ficheEvenement);
        ficheEvenement.setVisible(true);
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

        this.initialiserFichesSeances();
        this.afficherSeances(nbSeancesAffichees);
    }

    @Override
    public void dispose() {
        this.projet.enregistrerTout();
        super.dispose();
    }
}
