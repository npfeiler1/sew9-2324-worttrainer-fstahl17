package fstahl.java;

/**
 * Die Klasse Statistik speichert die Statistik eines Spiels.
 * @author Felix
 * @version 1-10-2023
 */
public class Statistik {
    private int gesamtVersuche, richtigeVersuche, falscheVersuche;

    /**
     * Konstruktor der Klasse Statistik.
     */
    public Statistik(){
        setFalscheVersuche(0);
        setGesamtVersuche(0);
        setRichtigeVersuche(0);
    }

    /**
     * Konstruktor der Klasse Statistik.
     * @param gesamt
     * @param richtig
     * @param falsch
     */
    public Statistik(int gesamt, int richtig, int falsch){
        setFalscheVersuche(falsch);
        setGesamtVersuche(gesamt);
        setRichtigeVersuche(richtig);
    }

    /**
     * Erhöht die Anzahl der Versuche um 1.
     */
    public void increaseGesamt(){
        setGesamtVersuche(getGesamtVersuche() + 1);
    }

    /**
     * Erhöht die Anzahl der richtigen Versuche um 1.
     */
    public void increaseRichtig(){
        setRichtigeVersuche(getRichtigeVersuche() + 1);
    }
    /**
     * Erhöht die Anzahl der falschen Versuche um 1.
     */
    public void increaseFalsch(){
        setFalscheVersuche(getFalscheVersuche() + 1);
    }
    //Getter und Setter
    public int getGesamtVersuche() {
        return this.gesamtVersuche;
    }

    public void setGesamtVersuche(int gesamtVersuche) {
        this.gesamtVersuche = gesamtVersuche;
    }

    public int getRichtigeVersuche() {
        return this.richtigeVersuche;
    }

    public void setRichtigeVersuche(int richtigeVersuche) {
        this.richtigeVersuche = richtigeVersuche;
    }

    public int getFalscheVersuche() {
        return this.falscheVersuche;
    }

    public void setFalscheVersuche(int falscheVersuche) {
        this.falscheVersuche = falscheVersuche;
    }
}
