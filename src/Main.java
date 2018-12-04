import modele.Projet;
import modele.agenda.Date;
import vue.Fenetre;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Projet p = new Projet();
        new Fenetre(p);
    }
}
