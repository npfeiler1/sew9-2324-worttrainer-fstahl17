import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class Main {
    public static void main(String[] args) {
        //1. Persitente Daten nicht vorhanden erstellen
        Rechtschreibtrainer r = new Rechtschreibtrainer();
        r.addPaare("Mercedes","https://flotte.at/NewsImages-870x580/pic4645_1-die-wertvollsten-automarken-der-welt.jpg");
        r.addPaare("Maserati","https://i.pinimg.com/originals/5e/5f/0f/5e5f0f05f9703fa3db0cc93b5d2c8578.png");
        r.addPaare("Ferrari","https://upload.wikimedia.org/wikipedia/de/thumb/c/c0/Scuderia_Ferrari_Logo.svg/1200px-Scuderia_Ferrari_Logo.svg.png");
        r.addPaare("Skoda","https://cdn.icon-icons.com/icons2/2402/PNG/512/skoda_logo_icon_145773.png");
        r.addPaare("Audi","https://www.audi-mediacenter.com/system/production/media/1282/images/bde751ee18fe149036c6b47d7595f6784f8901f8/AL090142_web_2880.jpg?1581961854");

        String text;
        String input;

        if(new File("test.json").exists()){
            Rechtschreibtrainer rr = new Rechtschreibtrainer();
            rr.loadFromJSON("test.json");
            while(true){
                rr.starteTraining();

                ImageIcon bildIcon = null;
                try {
                    bildIcon = new ImageIcon(new URL(rr.getCurrentPaar().getBildURL()));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                Image image = bildIcon.getImage(); // transform it
                Image newimg = image.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                ImageIcon bild = new ImageIcon(newimg);  // transform it back

                input = JOptionPane.showInputDialog(
                        null,
                        bild,
                        "Rechtschreibtrainer",
                        JOptionPane.PLAIN_MESSAGE
                );
                if (input.equals("")) {
                    break;
                }
                rr.rateWort(input);
                text = "Gesamt: " + rr.getStats().getGesamtVersuche() + "\nRichtig: " + rr.getStats().getRichtigeVersuche() + "\nFalsch: " + rr.getStats().getFalscheVersuche();
                JOptionPane.showMessageDialog(null, text);
                rr.saveToJSON("test.json");
            }
        } else {
            while (true) {
                r.starteTraining();
                ImageIcon bildIcon = null;
                try {
                    bildIcon = new ImageIcon(new URL(r.getCurrentPaar().getBildURL()));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                Image image = bildIcon.getImage(); // transform it
                Image newimg = image.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                ImageIcon bild = new ImageIcon(newimg);  // transform it back

                input = JOptionPane.showInputDialog(
                        null,
                        bild,
                        "Rechtschreibtrainer",
                        JOptionPane.PLAIN_MESSAGE
                );
                if (input.equals("")) {
                    break;
                }
                r.rateWort(input);
                text = "Gesamt: " + r.getStats().getGesamtVersuche() + "\nRichtig: " + r.getStats().getRichtigeVersuche() + "\nFalsch: " + r.getStats().getFalscheVersuche();
                JOptionPane.showMessageDialog(null, text);
                r.saveToJSON("test.json");
            }
        }
    }
}
