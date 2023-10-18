import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    private Rechtschreibtrainer trainer = new Rechtschreibtrainer();

    /**
     * Clear the Rechtschreibtrainer before each test
     */
    @BeforeEach
    public void clear() {
        this.trainer = new Rechtschreibtrainer();
    }

    /**
     * Testet die "addWort"-Methode in Rechtschreibtrainer
     */
    @Test
    public void testAddWort() {
        clear();
        trainer.addPaare("Mercedes","https://flotte.at/NewsImages-870x580/pic4645_1-die-wertvollsten-automarken-der-welt.jpg");
        assertEquals(1,trainer.getPaare().size());
    }

    /**
     * Testet die "speichern"-Methode in JSONSpeichern.java
     */
    @Test
    public void testSaveJSON() {
        clear();
        trainer.addPaare("Mercedes","https://flotte.at/NewsImages-870x580/pic4645_1-die-wertvollsten-automarken-der-welt.jpg");
        trainer.saveToJSON("testTest.json");

        File jsonFile = new File("testTest.json");
        assertTrue(jsonFile.exists());

        jsonFile.delete();
    }

    /**
     * Testet die "laden"-Methode in JSONSpeichern.java
     */
    @Test
    public void testeLadenJSON(){
        clear();
        trainer.addPaare("Mercedes","https://flotte.at/NewsImages-870x580/pic4645_1-die-wertvollsten-automarken-der-welt.jpg");
        trainer.addPaare("Maserati","https://i.pinimg.com/originals/5e/5f/0f/5e5f0f05f9703fa3db0cc93b5d2c8578.png");
        trainer.saveToJSON("testLoad.json");

        trainer.loadFromJSON("testLoad.json");
        assertEquals(2,trainer.getPaare().size());
    }
}
