import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * class Opiskelija
 * 
 * Opiskelijan tiedot <p>
 * Perii luokan Suoritus <p>
 * Harjoitustyö osio 7 
 * 
 * @author Jenni Breite
 * @version 1.00 3/1/2021
 */
public class Opiskelija extends Suoritus{
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
     * opiskelijan lähiosoite
     */
    String lahiosoite;

    /**
     * opiskelijan postitoimipaikka
     */
    String postitoimipaikka;

    /**
     * opiskelijan postinumero
     */
    String postinro;

    /**
     * opiskelijan sähköposti
     */
    String email;

    /**
     * opiskelijan puhelinnumero
     */
    String puhelinnro;


    /**
     * Parametriton konstruktori
     */
    public Opiskelija() {
    }


    /**
     * Konstruktori
     * 
     * @param opiskelija_id     opiskelijan id-numero
     * @param etunimi           opiskelijan etunimi
     * @param sukunimi          opiskelijan sukunimi
     * @param lahiosoite        opiskelijan lähiosoite
     * @param postitoimipaikka  opiskelijan postitoimipaikka
     * @param postinro          opiskelijan postinumero
     * @param email             opsikelijan sähköposti
     * @param puhelinnro        opiskelijan puhelinnumero
     */
    public Opiskelija(int opiskelija_id, String etunimi, String sukunimi, String lahiosoite, 
                    String postitoimipaikka, String postinro, String email, String puhelinnro) {

        this.opiskelija_id = opiskelija_id;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.lahiosoite = lahiosoite;
        this.postitoimipaikka = postitoimipaikka;
        this.postinro = postinro;
        this.email = email;
        this.puhelinnro = puhelinnro;
    }

    //lista Opiskelija-olioille
    public static ArrayList<Opiskelija> opiskelijat = new ArrayList<Opiskelija>();


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

    public String getLahiosoite() {
        return this.lahiosoite;
    }

    public void setLahiosoite(String lahiosoite) {
        this.lahiosoite = lahiosoite;
    }

    public String getPostitoimipaikka() {
        return this.postitoimipaikka;
    }

    public void setPostitoimipaikka(String postitoimipaikka) {
        this.postitoimipaikka = postitoimipaikka;
    }

    public String getPostinro() {
        return this.postinro;
    }

    public void setPostinro(String postinro) {
        this.postinro = postinro;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPuhelinnro() {
        return this.puhelinnro;
    }

    public void setPuhelinnro(String puhelinnro) {
        this.puhelinnro = puhelinnro;
    }
    
    /**
     * Tulostaa Opiskelija-olion tiedot
     */
    @Override
    public String toString() {
        return  opiskelija_id +
                "\n" + etunimi +
                "\n" + sukunimi +
                "\n" + lahiosoite +
                "\n" + postitoimipaikka +
                "\n" + postinro +
                "\n" + email +
                "\n" + puhelinnro;
    }

    /**
     * Lisää Opiskelija-olion listalle opiskelijat
     */
    public void lisaaOpiskelija() {
        Opiskelija o = new Opiskelija();

        o.setOpiskelija_id(this.opiskelija_id);
        o.setEtunimi(this.etunimi);
        o.setSukunimi(this.sukunimi);
        o.setLahiosoite(this.lahiosoite);
        o.setPostitoimipaikka(this.postitoimipaikka);
        o.setPostinro(this.postinro);
        o.setEmail(this.email);
        o.setPuhelinnro(this.puhelinnro);
        
        opiskelijat.add(o);
    }

    /**
     * Tulostaa opiskelijat -listan
     */
    public static void tulostaLista() {
        for (Opiskelija opiskelija : opiskelijat) {
            System.out.println(opiskelija);
        }
    }

    /**
     * Tulostaa opiskelijan suoritukset haettavan opiskelijan id-numeron perusteella
     * 
     * @param op_id opiskelijan id-numero
     */
    public static void getOpiskelijanSuoritukset(int op_id) {
        for (Suoritus suoritus : suoritukset) {
            if (suoritus.getOpiskelija_id() == op_id) {
                System.out.println(suoritus);
            }
        }
    }

}