/**
 * Die Klasse WortBildManager speichert ein Wort und ein Bild zu diesem Wort.
 * @author Felix
 * @version 1-10-2023
 */
public class WortBildManager {
    private String wort;
    private String bildURL;

    /**
     * Konstruktor der Klasse WortBildManager.
     * @param wort
     * @param bildURL
     */
    public WortBildManager(String wort, String bildURL){
        setWort(wort);
        setBildURL(bildURL);
    }
    // Getter und Setter
    public String getWort() {
        return wort;
    }

    public void setWort(String wort) {
        this.wort = wort;
    }

    public String getBildURL() {
        return bildURL;
    }

    public void setBildURL(String bildURL) {
        this.bildURL = bildURL;
    }
}
