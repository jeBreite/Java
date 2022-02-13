import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * class Kurssi
 * 
 * Kurssin tiedot <p>
 * Perii luokan Opiskelija <p>
 * Harjoitustyö osio 7 
 * 
 * @author Jenni Breite
 * @version 1.00 3/1/2021
 */
public class Kurssi extends Opiskelija {
    /**
     * kurssin id-numero
     */
    int kurssi_id;

    /**
     * kurssin nimi
     */
    String nimi;

    /**
     * kurssin opintopisteet
     */
    int opintopisteet;

    /**
     * kurssin kuvaus
     */
    String kuvaus;
    

    /**
     * Konstruktori
     * 
     * @param kurssi_id     kurssin id-numero
     * @param nimi          kurssin nimi
     * @param opintopisteet kurssista saatavat opintopisteet
     * @param kuvaus        kurssin kuvaus
     */
    public Kurssi(int kurssi_id, String nimi, int opintopisteet, String kuvaus) {
        this.kurssi_id = kurssi_id;
        this.nimi = nimi;
        this.opintopisteet = opintopisteet;
        this.kuvaus = kuvaus;
    }

    /**
     * Parametriton konstruktori
     */
    public Kurssi() {
    }

    //lista Kurssi-oiloille
    public static ArrayList<Kurssi> kurssit = new ArrayList<Kurssi>();


    //GETTERIT JA SETTERIT

    public int getKurssi_id() {
        return this.kurssi_id;
    }

    public void setKurssi_id(int kurssi_id) {
        this.kurssi_id = kurssi_id;
    }

    public String getNimi() {
        return this.nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int getOpintopisteet() {
        return this.opintopisteet;
    }

    public void setOpintopisteet(int opintopisteet) {
        this.opintopisteet = opintopisteet;
    }

    public String getKuvaus() {
        return this.kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }
    
    /**
     * Tulostaa Kurssi-olion tiedot
     */
    @Override
    public String toString() {
        return  "Kurssi ID: " + this.kurssi_id +
                "\nKurssin nimi: " + this.nimi +
                "\nOpintopisteet: " + this.opintopisteet +
                "\nKuvaus: " + this.kuvaus;
    }
    
    /**
     * Lisää Kurssi-olion listalle kurssit
     */
    public void lisaaKurssi() {
        Kurssi k = new Kurssi();

        k.setKurssi_id(this.kurssi_id);
        k.setNimi(this.nimi);
        k.setOpintopisteet(this.opintopisteet);
        k.setKuvaus(this.kuvaus);
        
        kurssit.add(k); 
    }
    
    /**
     * Tulostaa kurssin suoritukset haettavan kurssi-id:n perusteella
     * 
     * @param ku_id haettavan kurssin id-numero
     */
    public static void getKurssinSuoritukset(int ku_id) {
        for (Suoritus suoritus : suoritukset) {
            if (suoritus.getKurssi_id() == ku_id) {
                System.out.println(suoritus);  
            }
        } 
    }

    /**
     * Tulostaa kurssin opiskelijat haettavan kurssi-id:n perusteella
     * 
     * @param k_id haettavan kurssin id-numero
     */
    public static void getKurssinOpiskelijat(int k_id) {
        System.out.println("Kurssi ID " + k_id + " opiskelijat: ");

        for (Suoritus suoritus : suoritukset) {
            if (suoritus.getKurssi_id() == k_id) {
                int op_id = suoritus.getOpiskelija_id();

                for (Opiskelija opiskelija : opiskelijat) {
                    if (opiskelija.getOpiskelija_id() == op_id) {
                        System.out.println(opiskelija); 
                        System.out.println("Arvosana: " + suoritus.getArvosana()); 
                    }
                }
            }
        } 
    }

}
