import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveJSON implements SaveLoad{
    @Override
    public void saveToJSON(Rechtschreibtrainer r, String dateiName){
        JSONObject json = new JSONObject()
                .put("wort",r.getCurrentPaar().getWort())
                .put("bildURL", r.getCurrentPaar().getBildURL())
                .put("currentIndex", r.getPaare().indexOf(r.getCurrentPaar()))
                .put("gesamt",r.getStats().getGesamtVersuche())
                .put("richtig",r.getStats().getRichtigeVersuche())
                .put("falsch",r.getStats().getFalscheVersuche())
                .put("liste",r.getPaare());
        try (FileWriter fileWriter = new FileWriter(dateiName)) {
            json.write(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadFromJSON(Rechtschreibtrainer r, String dateiName) {
        int gesamt,richtig,falsch, index;
        try (FileReader reader = new FileReader(dateiName)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject js = new JSONObject(tokener);
            String currentWort = js.getString("wort");
            String currentBildURL = js.getString("bildURL");
            gesamt = (int) js.get("gesamt");
            richtig = (int) js.get("richtig");
            falsch = (int) js.get("falsch");
            index = (int) js.get("currentIndex");

            JSONArray list = js.getJSONArray("liste");
            r.setPaare(new ArrayList<>());
            for (int i = 0; i < list.length(); i++) {
                String wort_id = list.getJSONObject(i).getString("wort");
                System.out.println("wort: " + wort_id);
                String url_id = list.getJSONObject(i).getString("bildURL");
                r.addPaare(wort_id,url_id);
            }
            r.setCurrentPaar(new WortBildManager(currentWort,currentBildURL));
            r.setStats(new Statistik(gesamt,richtig,falsch));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
