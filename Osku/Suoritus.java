/**
 * Osku -ohjelma
 */
package osku;

import java.lang.*;

/**
 * class Suoritus
 *
 * Suorituksen tiedot.
 * <p>
 * Perii luokan Kurssi.
 *
 * @author Jenni Breite
 * @version 2.00 29/3/2021
 */
public class Suoritus extends Kurssi {

    /**
     * arvosana kurssista
     */
    int arvosana;

    /**
     * suorituksen päivämäärä
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
     * @param kurssi_id kurssin id-numero
     * @param arvosana arvosana kurssista
     * @param suoritus_pvm suorituksen kirjauspäivä
     */
    public Suoritus(int opiskelija_id, int kurssi_id, int arvosana, String suoritus_pvm) {
        super(kurssi_id, opiskelija_id);
        this.arvosana = arvosana;
        this.suoritus_pvm = suoritus_pvm;
    }

    /**
     * Konstruktori
     * 
     * @param opiskelija_id opiskelijan id-numero
     * @param kurssi_id kurssin id-numero
     */
    public Suoritus(int opiskelija_id, int kurssi_id) {
        super(kurssi_id, opiskelija_id);
    }

    /**
     * Konstruktori
     * 
     * @param kurssi_id kurssin id-numero
     * @param kurssin_nimi kurssin nimi
     * @param opintopisteet kurssin opintopisteet
     * @param arvosana arvosana kurssista
     * @param suoritus_pvm suorituksen kirjauspäivä
     */
    public Suoritus(int kurssi_id, String kurssin_nimi, int opintopisteet, int arvosana, String suoritus_pvm) {
        super(kurssi_id, kurssin_nimi, opintopisteet);
        this.arvosana = arvosana;
        this.suoritus_pvm = suoritus_pvm;
    }
    
    /**
     * Konstruktori
     * 
     * @param opiskelija_id opiskelijan id-numero
     * @param etunimi opiskelijan etunimi
     * @param sukunimi opiskelijan sukunimi
     * @param arvosana arvosana kurssista
     * @param suoritus_pvm suorituksen kirjauspäivä
     */
    public Suoritus(int opiskelija_id, String etunimi, String sukunimi, int arvosana, String suoritus_pvm) {
        super(opiskelija_id, etunimi, sukunimi);
        this.arvosana = arvosana;
        this.suoritus_pvm = suoritus_pvm;
        
    }

    
    //GETTERIT JA SETTERIT

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

        return "Opiskelijanumero: " + super.opiskelija_id 
                + "\nKurssi nro: " + super.kurssi_id
                + "\nArvosana: " + this.arvosana
                + "\nPvm: " + this.suoritus_pvm;
    }
}
