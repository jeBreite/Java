/**
 * Osku -ohjelma
 */
package osku;

import java.lang.*;


/**
 * class Opiskelija
 *
 * Opiskelijan tiedot.
 * <p>
 * Perii luokan Suoritus.
 * 
 *
 * @author Jenni Breite
 * @version 2.00 29/3/2021
 */
public class Opiskelija {

    /**
     * opiskelijan id-numero
     */
    int opiskelija_id;

    /**
     * opiskelijan etunimi
     */
    String etunimi;

    /**
     * opiskelijan sukunimi
     */
    String sukunimi;

    /**
     * Parametriton konstruktori
     */
    public Opiskelija() {
    }

    /**
     * Konstruktori
     *
     * @param opiskelija_id opiskelijan id-numero
     * @param etunimi opiskelijan etunimi
     * @param sukunimi opiskelijan sukunimi
     */
    public Opiskelija(int opiskelija_id, String etunimi, String sukunimi) {

        this.opiskelija_id = opiskelija_id;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
    }

    /**
     * Konstruktori opiskelijan  id-numero
     * 
     * @param opiskelija_id opiskelijan id-numero
     */
    public Opiskelija(int opiskelija_id) {

        this.opiskelija_id = opiskelija_id;
    }

    /**
     * Konstruktori opiskelijan etu- ja sukunimi
     * 
     * @param etunimi opiskelijan etunimi
     * @param sukunimi opiskelijan sukunimi
     */
    public Opiskelija(String etunimi, String sukunimi) {

        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
    }


    //GETTERIT JA SETTERIT
    public int getOpiskelija_id() {
        return this.opiskelija_id;
    }

    public void setOpiskelija_id(int opiskelija_id) {
        this.opiskelija_id = opiskelija_id;
    }

    public String getEtunimi() {
        return this.etunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return this.sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    /**
     * Tulostaa Opiskelija-olion tiedot
     */
    @Override
    public String toString() {
        return "Opiskelijanro: " + opiskelija_id
                + " - " + etunimi
                + " " + sukunimi;
    }

}
