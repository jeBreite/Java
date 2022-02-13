import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * class Suoritus
 * 
 * Suorituksen tiedot <p>
 * Harjoitustyö osio 7 
 * 
 * @author Jenni Breite
 * @version 1.00 3/1/2021
 */
public class Suoritus {
    /**
     * opiskelijan id-numero
     */
    int opiskelija_id;

    /**
     * kurssin id-numero
     */
    int kurssi_id;

    /**
     * arvosana kurssista
     */
    int arvosana;

    /**
     * suorituksen päivämäärä (vvvvkkpp)
     */
    String suoritus_pvm;


    /**
     * Parametriton konstruktori
     */
    public Suoritus() {
    }

    /**
     * Konstruktori
     * 
     * @param opiskelija_id opiskelijan id-numero
     * @param kurssi_id     kurssin id-numero
     * @param arvosana      arvosana kurssista
     * @param suoritus_pvm  suorituksen päivämäärä (vvvvkkpp)
     */
    public Suoritus(int opiskelija_id, int kurssi_id, int arvosana, String suoritus_pvm) {
        this.opiskelija_id = opiskelija_id;
        this.kurssi_id = kurssi_id;
        this.arvosana = arvosana;
        this.suoritus_pvm = suoritus_pvm;
    }

    //lista Suoritus -oilioille
    public static ArrayList<Suoritus> suoritukset = new ArrayList<Suoritus>();

    //GETTERIT JA SETTERIT
    public int getOpiskelija_id() {
        return this.opiskelija_id;
    }

    public void setOpiskelija_id(int opiskelija_id) {
        this.opiskelija_id = opiskelija_id;
    }

    public int getKurssi_id() {
        return this.kurssi_id;
    }

    public void setKurssi_id(int kurssi_id) {
        this.kurssi_id = kurssi_id;
    }

    public int getArvosana() {
        return this.arvosana;
    }

    public void setArvosana(int arvosana) {
        this.arvosana = arvosana;
    }

    public String getSuoritus_pvm() {
        return this.suoritus_pvm;
    }

    public void setSuoritus_pvm(String suoritus_pvm) {
        this.suoritus_pvm = suoritus_pvm;
    }

    /**
     * Tulostaa Suoritus-olion tiedot
     */
    @Override
    public String toString() {
        return  "Opiskelija ID: " + this.opiskelija_id +
                "\nKurssi ID: " + this.kurssi_id +
                "\nArvosana: " + this.arvosana +
                "\nSuoritus pvm: " + this.suoritus_pvm;
    }

    /**
     * Lisää Suoritus-olion listalle suoritukset
     */
    public void lisaaSuoritus() {
        Suoritus s = new Suoritus();

        s.setOpiskelija_id(this.opiskelija_id);
        s.setKurssi_id(this.kurssi_id);
        s.setArvosana(this.arvosana);
        s.setSuoritus_pvm(this.suoritus_pvm);
        
        suoritukset.add(s); 
    }
    
}
