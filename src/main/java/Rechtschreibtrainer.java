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

    public void saveToJSON(String dateiName){
        JSONObject json = new JSONObject()
                .put("wort",currentPaar.getWort())
                .put("bildURL", currentPaar.getBildURL())
                .put("currentIndex", paare.indexOf(currentPaar))
                .put("gesamt",getStats().getGesamtVersuche())
                .put("richtig",getStats().getRichtigeVersuche())
                .put("falsch",getStats().getFalscheVersuche())
                .put("liste",paare);
        try (FileWriter fileWriter = new FileWriter(dateiName)) {
            json.write(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromJSON(String dateipfad){
        int gesamt,richtig,falsch, index;
        try (FileReader reader = new FileReader(dateipfad)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject js = new JSONObject(tokener);
            String currentWort = js.getString("wort");
            String currentBildURL = js.getString("bildURL");
            gesamt = (int) js.get("gesamt");
            richtig = (int) js.get("richtig");
            falsch = (int) js.get("falsch");
            index = (int) js.get("currentIndex");

            JSONArray list = js.getJSONArray("liste");
            for (int i = 0; i < list.length(); i++) {
                String wort_id = list.getJSONObject(i).getString("wort");
                System.out.println("wort: " + wort_id);
                String url_id = list.getJSONObject(i).getString("bildURL");
                this.addPaare(wort_id,url_id);
            }

            this.currentPaar = paare.get(index);
            this.stats.setGesamtVersuche(gesamt);
            this.stats.setRichtigeVersuche(richtig);
            this.stats.setFalscheVersuche(falsch);

            waehlePaar(index);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
