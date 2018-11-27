import vue.Fenetre;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        setBestLookAndFeelAvailable();
        new Fenetre();
    }

    public static void setBestLookAndFeelAvailable(){
        String system_lf = UIManager.getSystemLookAndFeelClassName().toLowerCase();
        if(system_lf.contains("metal")){
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            }catch (Exception e) {}
        }else{
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }catch (Exception e) {}
        }
    }
}
