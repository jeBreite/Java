import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * class Paaohjelma
 * 
 * Pääohjelma, jossa testataan luokkien 
 * Opiskelija, Kurssi ja Suoritus toimintaa <p>
 * 
 * Harjoitustyö osio 7 <p>
 * 
 * - olioden luonti
 * - tietyn opiskelijan suoritusten listaus
 * - tietyn kurssin suoritusten listaus
 * - tietyn kurssin opiskelijoiden ja arvosanojen listaus
 *
 * @author Jenni Breite
 * @version 1.00 3/1/2021
 */
public class Paaohjelma {
    public static void main(String[] args) {

        //luodaan opiskelija-oliot
        Opiskelija op1 = new Opiskelija(2006227, "Jenni", "Breite", "Rautilantie 8", "Uusikaupunki", "23500", "jenni.breite@edu.karelia.fi", "0405081937");
        Opiskelija op2 = new Opiskelija(1234567, "Olivia", "Opiskelija", "Koulukatu 2", "Oppila", "12345", "Olivia.Opiskelija@edu.karelia.fi", "0401234567");

        //lisätään opiskelijat listalle
        op1.lisaaOpiskelija();
        op2.lisaaOpiskelija();
        

        //luodaan kurssi oliot
        Kurssi ku1 = new Kurssi(6038, "ICT-liiketoiminta ja yrittäjyys", 5, "Liiketalouden ja yritystoiminnan perusteet");
        Kurssi ku2 = new Kurssi(6056, "Ohjelmoinnin perusteet", 5, "Ohjelmoinnin peruskäsitteet ja perusrakenteet");
        Kurssi ku3 = new Kurssi(6002, "Johdatus tietojenkäsittelyalaan", 5, "Tietojenkäsittelyn perusteet");

        //lisätään kurssit listalle
        ku1.lisaaKurssi();
        ku2.lisaaKurssi();
        ku3.lisaaKurssi();


        //luodaan suoritus oliot
        Suoritus su1 = new Suoritus(2006227, 6038, 5, "20201204");  //(vvvvkkpp)
        Suoritus su2 = new Suoritus(2006227, 6056, 5, "20201120");
        Suoritus su3 = new Suoritus(2006227, 6002, 4, "20200525");

        Suoritus su4 = new Suoritus(1234567, 6038, 3, "20201204");  
        Suoritus su5 = new Suoritus(1234567, 6056, 4, "20201120");
        Suoritus su6 = new Suoritus(1234567, 6002, 5, "20200525");

        //lisätään suoritukset 
        su1.lisaaSuoritus();
        su2.lisaaSuoritus();
        su3.lisaaSuoritus();

        su4.lisaaSuoritus();
        su5.lisaaSuoritus();
        su6.lisaaSuoritus();

        //testi
        //Opiskelija.tulostaLista();

        //metodin kutsu - tulostetaan olion op1 suoritukset
        Opiskelija.getOpiskelijanSuoritukset(2006227);

        //metodin kutsu - haetaan kurssi id:llä kurssin suoritukset
        Kurssi.getKurssinSuoritukset(6056);

        //metodin kutsu - haetaan kurssi id:llä kurssin opiskelijat ja arvosanat
        Kurssi.getKurssinOpiskelijat(6038);
    }
}
