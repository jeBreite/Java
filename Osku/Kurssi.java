/**
 * Osku -ohjelma
 */
package osku;

import java.lang.*;

/**
 * class Kurssi
 *
 * Kurssin tiedot.
 * <p>
 * Perii luokan Opiskelija.
 *
 *
 * @author Jenni Breite
 * @version 2.00 29/3/2021
 */
public class Kurssi extends Opiskelija {

    /**
     * kurssin id-numero
     */
    int kurssi_id;

    /**
     * kurssin nimi
     */
    String kurssin_nimi;

    /**
     * kurssin opintopisteet
     */
    int opintopisteet;

    /**
     * Parametriton konstruktori
     */
    public Kurssi() {
    }

    /**
     * Konstruktori
     *
     * @param kurssi_id kurssin id-numero
     * @param kurssin_nimi kurssin nimi
     * @param opintopisteet kurssista saatavat opintopisteet
     */
    public Kurssi(int kurssi_id, String kurssin_nimi, int opintopisteet) {
        this.kurssi_id = kurssi_id;
        this.kurssin_nimi = kurssin_nimi;
        this.opintopisteet = opintopisteet;
    }

    /**
     * Konstruktori kurssin nimi ja opintopisteet
     *
     * @param kurssin_nimi kurssin nimi
     * @param opintopisteet kurssista saatavat opintopisteet
     */
    public Kurssi(String kurssin_nimi, int opintopisteet) {
        this.kurssin_nimi = kurssin_nimi;
        this.opintopisteet = opintopisteet;

    }

    /**
     * Konstruktori kurssin id-numero
     * 
     * @param kurssi_id 
     */
    public Kurssi(int kurssi_id) {
        this.kurssi_id = kurssi_id;
    }

    /**
     * Konstruktori
     * 
     * @param kurssi_id kurssin id-numero
     * @param opiskelija_id opiskelijan id-numero
     */
    public Kurssi(int kurssi_id, int opiskelija_id) {
        super(opiskelija_id);
        this.kurssi_id = kurssi_id;
    }

    /**
     * Konstruktori 
     * 
     * @param opiskelija_id opiskelijan id-numero
     * @param etunimi opiskelijan etunimi
     * @param sukunimi opiskleijan sukunimi
     */
    public Kurssi(int opiskelija_id, String etunimi, String sukunimi) {
        super(opiskelija_id, etunimi, sukunimi);
    }
    
    
    //GETTERIT JA SETTERIT
    public int getKurssi_id() {
        return this.kurssi_id;
    }

    public void setKurssi_id(int kurssi_id) {
        this.kurssi_id = kurssi_id;
    }

    public String getKurssin_nimi() {
        return this.kurssin_nimi;
    }

    public void setKurssin_nimi(String kurssin_nimi) {
        this.kurssin_nimi = kurssin_nimi;
    }

    public int getOpintopisteet() {
        return this.opintopisteet;
    }

    public void setOpintopisteet(int opintopisteet) {
        this.opintopisteet = opintopisteet;
    }

    /**
     * Tulostaa Kurssi-olion tiedot
     */
    @Override
    public String toString() {
        return "Kurssinro: " + this.kurssi_id
                + " - " + this.kurssin_nimi
                + " - " + this.opintopisteet + " op";
    }

}
