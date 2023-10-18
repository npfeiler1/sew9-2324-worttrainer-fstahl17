import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Die Klasse Rechtschreibtrainer verwaltet die Wort-Bild-Paare und die Statistik eines Spiels.
 * @author Felix
 * @version 1-10-2023
 */
public class Rechtschreibtrainer {
    private List<WortBildManager> paare;
    private WortBildManager currentPaar;
    private Statistik stats;

    /**
     * Konstruktor der Klasse Rechtschreibtrainer.
     */
    public Rechtschreibtrainer(){
        stats = new Statistik();
        this.paare = new ArrayList<>();
    }

    /**
     * startet das Training.
     */
    public void starteTraining() {
        waehleRandom();
    }
    /**
     * Wählt ein Wort-Bild-Paar aus anhand des Indexes
     */
    public void waehlePaar(int index) {
        if (index < 0 || index >= paare.size()) {
            System.out.println("Der Index ist ungültig.");
            return;
        }

        currentPaar = paare.get(index);
    }

    /**
     * Wählt ein zufälliges Wort-Bild-Paar aus.
     */
    public void waehleRandom() {
        if (paare.isEmpty()) {
            System.out.println("Es sind keine Wort-Bild-Paare verfügbar.");
            return;
        }

        Random random = new Random();
        int index = random.nextInt(paare.size());
        currentPaar = paare.get(index);
    }

    /**
     * Vergleicht das Wort mit dem currentPaar.
     * @param antwort
     * @return
     */
    public boolean rateWort(String antwort) {
        if (currentPaar == null) {
            System.out.println("Es wurde kein Wort-Bild-Paar ausgewählt.");
            return false;
        }

        boolean istRichtig = currentPaar.getWort().equals(antwort);

        if (istRichtig) {
            stats.increaseRichtig();
        } else {
            stats.increaseFalsch();
        }

        stats.increaseGesamt();
        return istRichtig;
    }

    /**
     * Fügt ein neues Wort-Bild-Paar hinzu.
     * @param wort
     * @param bildURL
     */
    public void addPaare(String wort, String bildURL) {
        WortBildManager neuesWortBildPaar = new WortBildManager(wort, bildURL);
        paare.add(neuesWortBildPaar);
        if(currentPaar == null){
            currentPaar = neuesWortBildPaar;
        }
    }

    /**
     * Gibt Statistik zurück
     */
    public Statistik getStats(){
        return this.stats;
    }

    public WortBildManager getCurrentPaar() {
        return currentPaar;
    }

    public void setPaare(List<WortBildManager> paare) {
        this.paare = paare;
    }

    public void setCurrentPaar(WortBildManager currentPaar) {
        this.currentPaar = currentPaar;
    }

    public void setStats(Statistik stats) {
        this.stats = stats;
    }

    public List<WortBildManager> getPaare() {
        return paare;
    }
}
