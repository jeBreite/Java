/**
 * Osku -ohjelma
 */
package osku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * class Osku
 * 
 * Tietokannan hallinnointi.
 * 
 * @author Jenni Breite
 * @version 1.00 29.3.2021
 */
public class Osku extends Application {

    /**
     * Avaa yhteyden tietokantaan
     * @param connString yhteysosoite
     * @return con yhteys tietokantaan
     * @throws SQLException 
     */
    public static Connection openConnection(String connString) throws SQLException {
        Connection con = DriverManager.getConnection(connString);
        System.out.println("\t>> Yhteys ok");
        return con;
    }

    /**
     * Tietokannan käyttö
     * @param c yhteys tietokantaan
     * @param db tietokannan nimi
     * @throws SQLException 
     */
    public static void useDatabase(Connection c, String db) throws SQLException {
        Statement stmt = c.createStatement();
        stmt.executeQuery("USE " + db);
        System.out.println("\t>> Käytetään tietokantaa " + db);
    }

    /**
     * Sulkee yhteyden tietokantaan
     * @param c yhteyden tietokantaan
     * @throws SQLException 
     */
    public static void closeConnection(Connection c) throws SQLException {
        if (c != null) {
            c.close();
        }
        System.out.println("\t>> Tietokantayhteys suljettu");
    }

    /**
     * Luo tietokannan jos sitä ei ole olemassa
     * @param c yhteys tietokantaan
     * @param db tietokannan nimi
     * @throws SQLException 
     */
    private static void createDatabase(Connection c, String db) throws SQLException {
        Statement stmt = c.createStatement();
//        stmt.executeQuery("DROP DATABASE IF EXISTS " + db);
//        System.out.println("\t>> Tietokanta tuhottu" + db + " tuhottu");

        stmt.executeQuery("CREATE DATABASE IF NOT EXISTS " + db);
        System.out.println("\t>> Tietokanta " + db + " luotu");

        stmt.executeQuery("USE " + db);
        System.out.println("\t>> Käytetään tietokantaa " + db);

    }

    /**
     * Luo taulun tietokantaan
     * @param c yhteys tietokantaan
     * @param sql SQL-lauseke
     * @throws SQLException 
     */
    private static void createTable(Connection c, String sql) throws SQLException {
        Statement stmt = c.createStatement();
        stmt.executeQuery(sql);
        System.out.println("\t>> Taulu luotu");
    }

    /**
     * Lisää opiskelijan tiedot tauluun Opiskelijat
     * @param c yhteys tietokantaan
     * @param op Opiskelija olio
     * @throws SQLException 
     */
    public static void addStudent(Connection c, Opiskelija op) throws SQLException {
        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO opiskelijat (etunimi, sukunimi) "
                + "VALUES (?, ?);"
        );

        ps.setString(1, op.getEtunimi());
        ps.setString(2, op.getSukunimi());
        ps.execute();

        //System.out.println("\t>> Lisätty " + op);
    }

    /**
     * Valitsee opiskelijat taulusta kaikki opiskelijat
     * @param conn yhteys tietoikantaan
     * @return kyselyn tulokset
     * @throws SQLException 
     */
    public static ResultSet selectStudent(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT DISTINCT opiskelija_ID, etunimi, sukunimi FROM opiskelijat;"
        );

        return rs;

    }

    /**
     * Muokkaa opiskelijat taulun opiskelijan tietoja
     * @param conn yhteys tietokantaan
     * @param op Opiskelija olio
     * @throws SQLException 
     */
    public static void modifyStudent(Connection conn, Opiskelija op) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "UPDATE opiskelijat  "
                + "SET etunimi = ?, sukunimi = ?"
                + "WHERE opiskelija_ID = ?;"
        );

        ps.setInt(3, op.getOpiskelija_id());
        ps.setString(1, op.getEtunimi());
        ps.setString(2, op.getSukunimi());
        ps.execute();

    }

    /**
     * Poistaa opiskelijat taulusta opiskelijan tiedot 
     * sekä suoritukset taulusta opiskelijan suoritukset
     * @param conn yhteys tietokantaan
     * @param op Opiskelija olio
     * @throws SQLException 
     */
    public static void deleteStudent(Connection conn, Opiskelija op) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM suoritukset WHERE opiskelija_ID = ?; "
        );

        ps.setInt(1, op.getOpiskelija_id());
        ps.execute();

        PreparedStatement ps2 = conn.prepareStatement(
                "DELETE FROM opiskelijat WHERE opiskelija_ID = ?; "
        );
        ps2.setInt(1, op.getOpiskelija_id());
        ps2.execute();
    }

    /**
     * Lisää kurssin kurssit tauluun
     * @param conn yhteys tietokantaan
     * @param ku Kurssi olio
     * @throws SQLException 
     */
    public static void addCourse(Connection conn, Kurssi ku) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO kurssit (laajuus, kurssin_nimi) "
                + "VALUES (?, ?)"
        );

        ps.setInt(1, ku.getOpintopisteet());
        ps.setString(2, ku.getKurssin_nimi());

        ps.execute();
    }

    /**
     * Valitsee kaikki kurssit taulusta
     * @param conn yhteys tietokantaan
     * @return kyselyn tulokset
     * @throws SQLException 
     */
    public static ResultSet selectCourse(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT kurssi_ID, kurssin_nimi, laajuus FROM kurssit;"
        );

        return rs;

    }

    /**
     * Muokkaa kurssit taulun kurssin tietoja
     * @param conn yhteys tietokantaan
     * @param ku Kurssi olio
     * @throws SQLException 
     */
    public static void modifyCourse(Connection conn, Kurssi ku) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "UPDATE kurssit  "
                + "SET laajuus = ?, kurssin_nimi = ?"
                + "WHERE kurssi_ID = ?;"
        );

        ps.setInt(3, ku.getKurssi_id());
        ps.setInt(1, ku.getOpintopisteet());
        ps.setString(2, ku.getKurssin_nimi());
        ps.execute();

    }

    /**
     * Poistaa kurssit taulusta kurssin sekä 
     * suoritukset taulusta kurssin suoritukset
     * @param conn yhteys tietokantaan
     * @param ku Kurssi olio
     * @throws SQLException 
     */
    public static void deleteCourse(Connection conn, Kurssi ku) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM suoritukset WHERE kurssi_ID = ?; "
        );

        ps.setInt(1, ku.getKurssi_id());
        ps.execute();

        PreparedStatement ps2 = conn.prepareStatement(
                "DELETE FROM kurssit WHERE kurssi_ID = ?;"
        );

        ps2.setInt(1, ku.getKurssi_id());
        ps2.execute();

    }

    /**
     * Lisää suorituksen suoritukset tauluun
     * @param conn yhteys tietokantaan
     * @param su Suoritus olio
     * @throws SQLException 
     */
    public static void addAccomplishment(Connection conn, Suoritus su) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO suoritukset (suoritus_pvm, arvosana, opiskelija_ID, kurssi_ID) "
                + "VALUES (STR_TO_DATE(?, '%d.%m.%Y'), ?, ?, ?)"
        );

        ps.setString(1, su.getSuoritus_pvm());
        ps.setInt(2, su.getArvosana());
        ps.setInt(3, su.getOpiskelija_id());
        ps.setInt(4, su.getKurssi_id());
        ps.execute();
    }

    /**
     * Muokkaa suorituksen tietoja suoritukset taulusta
     * @param conn yhteys tietokantaan
     * @param su Suoritus olio
     * @throws SQLException 
     */
    public static void modifyAccomplishment(Connection conn, Suoritus su) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "UPDATE suoritukset "
                + "SET suoritus_pvm = (STR_TO_DATE(?, '%d.%m.%Y')), arvosana = ? "
                + "WHERE opiskelija_ID = ? AND kurssi_ID = ?;"
        );

        ps.setString(1, su.getSuoritus_pvm());
        ps.setInt(2, su.getArvosana());
        ps.setInt(3, su.getOpiskelija_id());
        ps.setInt(4, su.getKurssi_id());
        ps.execute();

    }

    /**
     * Valitsee kurssit, joita opiskelija ei ole suorittanut
     * @param conn yheteys tietokantaan
     * @param op Opiskelija olio
     * @return kyselyn tulokset
     * @throws SQLException 
     */
    public static ResultSet selectCoursesNotInAccomplisments(Connection conn, Opiskelija op) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT *\n"
                + "FROM kurssit\n"
                + "WHERE kurssi_ID NOT IN\n"
                + "	(SELECT kurssi_ID\n"
                + "	FROM suoritukset\n"
                + "	WHERE opiskelija_ID = " + op.getOpiskelija_id() + ");"
        );

        return rs;

    }

    /**
     * Valitsee kaikki suoritukset
     * @param conn yhteys tietokantaan
     * @param o Opiskelija olio
     * @param k Kurssi olio
     * @return kyselyn tulokset
     * @throws SQLException 
     */
    public static ResultSet selectAccomplishment(Connection conn, Opiskelija o, Kurssi k) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT opiskelija_ID, kurssi_ID, arvosana, suoritus_pvm "
                + "FROM suoritukset "
                + "WHERE opiskelija_ID = " + o.getOpiskelija_id()
                + " AND kurssi_ID = " + k.getKurssi_id() + ";"
        );

        return rs;

    }
    
    /**
     * Valitsee opiskelijat, joilla on suorituksia
     * @param conn yhteys tietokantaan
     * @return kyselyn tulokset
     * @throws SQLException 
     */
    public static ResultSet selectStudentsWithAccomplishments(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT O.opiskelija_ID, O.etunimi, O.sukunimi "
                + "FROM opiskelijat O, suoritukset S "
                + "WHERE O.opiskelija_ID = S.opiskelija_ID;"
        );
        
        return rs;
    }
    

    /**
     * Valitsee kurssit, jotka opiskelija on suorittanut
     * @param conn yhteys tietokantaan
     * @param op Opiskelija olio
     * @return kyselyn tuloksety
     * @throws SQLException 
     */
    public static ResultSet selectStudentsCourses(Connection conn, Opiskelija op) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT K.kurssi_ID, K.kurssin_nimi, K.laajuus "
                + "FROM kurssit K, opiskelijat O, suoritukset S "
                + "WHERE O.opiskelija_ID = S.opiskelija_ID "
                + "AND K.kurssi_ID = S.kurssi_ID "
                + "AND O.opiskelija_ID = " + op.getOpiskelija_id() + ";"
        );

        return rs;

    }

    /**
     * Poistaa suorituksen suoritukset taulusta
     * @param conn yhteys tietokantaan
     * @param su Suoritus olio
     * @throws SQLException 
     */
    public static void deleteAccomplishment(Connection conn, Suoritus su) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM suoritukset "
                + "WHERE opiskelija_ID = ? AND kurssi_ID = ?;"
        );

        ps.setInt(1, su.getOpiskelija_id());
        ps.setInt(2, su.getKurssi_id());
        ps.execute();

    }

    /**
     * Valitsee tietyn opiskelijan suoritukset
     * @param conn yhteys tietokantaan
     * @param op Opiskelija olio
     * @return kyselyn tulokset
     * @throws SQLException 
     */
    public static ResultSet selectStudentsAccomplishments(Connection conn, Opiskelija op) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT K.kurssi_ID, K.kurssin_nimi, K.laajuus, S.arvosana, S.suoritus_pvm "
                + "FROM kurssit K, opiskelijat O, suoritukset S "
                + "WHERE O.opiskelija_ID = S.opiskelija_ID "
                + "AND K.kurssi_ID = S.kurssi_ID "
                + "AND O.opiskelija_ID = " + op.getOpiskelija_id() + ";"
        );

        return rs;

    }

    /**
     * Valitsee kurssin suoritukset
     * @param conn yhteys tietokantaan
     * @param ku Kurssi olio
     * @return kyselyn tulokset
     * @throws SQLException 
     */
    public static ResultSet selectCourseAccomplishments(Connection conn, Kurssi ku) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT O.opiskelija_ID, O.etunimi, O.sukunimi, S.arvosana, S.suoritus_pvm "
                + "FROM kurssit K, opiskelijat O, suoritukset S "
                + "WHERE O.opiskelija_ID = S.opiskelija_ID "
                + "AND K.kurssi_ID = S.kurssi_ID "
                + "AND K.kurssi_ID = " + ku.getKurssi_id() + ";"
        );

        return rs;

    }

    /**
     * Käyttöliittymän käynnistys
     * @param stage käyttöliittymän näkymä
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("OskuView.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Osku");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args 
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        launch(args);

        //otetaan yhteys tietokantaan
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");

        //luodaan tietokanta
        createDatabase(conn, "karelia_jennibre");

        //luodaan opiskelijat taulu jos ei olemassa
        createTable(conn,
                "CREATE TABLE IF NOT EXISTS opiskelijat("
                + "opiskelija_ID INT NOT NULL AUTO_INCREMENT, "
                + "etunimi VARCHAR(25) NOT NULL, "
                + "sukunimi VARCHAR(50) NOT NULL, "
                + "PRIMARY KEY(opiskelija_ID)"
                + ");"
        );

        //luodaan kurssit taulu jos ei olemassa
        createTable(conn,
                "CREATE TABLE IF NOT EXISTS kurssit("
                + "kurssi_ID INT NOT NULL AUTO_INCREMENT,"
                + "laajuus INT NOT NULL,"
                + "kurssin_nimi VARCHAR(50) NOT NULL,"
                + "PRIMARY KEY (kurssi_ID)"
                + ");"
        );

        //luodaan suoritukset taulu jos ei olemassa
        createTable(conn,
                "CREATE TABLE IF NOT EXISTS suoritukset("
                + "  suoritus_pvm DATE NOT NULL,"
                + "  arvosana INT NOT NULL,"
                + "  opiskelija_ID INT NOT NULL,"
                + "  kurssi_ID INT NOT NULL,"
                + "  PRIMARY KEY (opiskelija_ID, kurssi_ID),"
                + "  FOREIGN KEY (opiskelija_ID) REFERENCES opiskelijat(opiskelija_ID),"
                + "  FOREIGN KEY (kurssi_ID) REFERENCES kurssit(kurssi_ID)"
                + ");"
        );

        //suljetaan yhteys tietokantaan
        closeConnection(conn);
    }

}
