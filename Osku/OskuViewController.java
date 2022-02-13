/**
 * Osku -ohjelma
 */
package osku;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import static osku.Osku.openConnection;

/**
 * class OskuViewControl
 * 
 * Käyttöliittymän toiminnot.
 * 
 * @author Jenni Breite
 * @version 1.0 29.03.2021
 */
public class OskuViewController implements Initializable {

    @FXML
    private TextField txtAddStuFname;
    @FXML
    private TextField txtAddStuLname;
    @FXML
    private TextField txtModifyStuFname;
    @FXML
    private TextField txtModifyStuLname;
    @FXML
    private DialogPane dialogPaneAddStudent;
    @FXML
    private DialogPane dialogPaneModifyStudent;
    @FXML
    private DialogPane dialogPaneDeleteStudent;
    @FXML
    private TextField txtAddCourseName;
    @FXML
    private TextField txtAddCourseCredit;
    @FXML
    private TextField txtModifyCourseName;
    @FXML
    private TextField txtModifyCourseCredit;
    @FXML
    private DialogPane dialogPaneAddCourse;
    @FXML
    private DialogPane dialogPaneModifyCourse;
    @FXML
    private DialogPane dialogPaneDeleteCourse;
    @FXML
    private DatePicker datePickerAddAcco;
    @FXML
    private DatePicker datePickerModifyAcco;
    @FXML
    private Menu menuOsku;
    @FXML
    private Button dpAddStudentButton;
    @FXML
    private ComboBox<Opiskelija> comBoxModifyStudent;
    @FXML
    private Button modifyStudentButton;
    @FXML
    private Button dpModifyStudentButton;
    @FXML
    private Button dpDeleteStudentButton;
    @FXML
    private ComboBox<Opiskelija> comBoxDeleteStudent;
    @FXML
    private Button dpAddCourseButton;
    @FXML
    private Button dpModifyCourseButton;
    @FXML
    private Button dpDeleteCourseButton;
    @FXML
    private ComboBox<Kurssi> comBoxModifyCourse;
    @FXML
    private ComboBox<Kurssi> comBoxDeleteCourse;
    @FXML
    private Button modifyCourseButton;
    @FXML
    private ComboBox<Integer> comBoxAddAccoGrade;
    @FXML
    private ComboBox<Opiskelija> comBoxAddAccoStudent;
    @FXML
    private ComboBox<Kurssi> comBoxAddAccoCourse;
    @FXML
    private ComboBox<Opiskelija> comBoxModifyAccoStudent;
    @FXML
    private ComboBox<Integer> comBoxModifyAccoGrade;
    @FXML
    private ComboBox<Kurssi> comBoxModifyAccoCourse;
    @FXML
    private Button modifyAccoButton;
    @FXML
    private Button selectModifyAccoButton;
    @FXML
    private ComboBox<Opiskelija> comBoxDeleteAccoStudent;
    @FXML
    private ComboBox<Kurssi> comBoxDeleteAccoCourse;
    @FXML
    private Button addAccoButton;
    @FXML
    private Button deleteAccoButton;
    @FXML
    private Label labelDeleteAcco;
    @FXML
    private Label labelModifyAcco;
    @FXML
    private Label labelAddAcco;
    @FXML
    private Button okAddAccoButton;
    @FXML
    private Button okModifyAccoButton;
    @FXML
    private Button okDeleteAccoButton;
    @FXML
    private Button buttonSearchStudentAccos;
    @FXML
    private Button buttonSearchCoursesAccos;
    @FXML
    private ComboBox<Opiskelija> comBoxSelectStudentsAccos;
    @FXML
    private ComboBox<Kurssi> comBoxSelectCoursesAccos;

    @FXML
    private TableView<Suoritus> tblViewStudentsAccos;
    @FXML
    private TableColumn<Suoritus, Integer> stuAccosCourseID;
    @FXML
    private TableColumn<Suoritus, String> stuAccosCourseName;
    @FXML
    private TableColumn<Suoritus, Integer> stuAccosCredit;
    @FXML
    private TableColumn<Suoritus, Integer> stuAccosGrade;
    @FXML
    private TableColumn<Suoritus, String> stuAccosDate;

    @FXML
    private TableView<Suoritus> tblViewCoursesAccos;
    @FXML
    private TableColumn<Suoritus, Integer> courseAccosStudentID;
    @FXML
    private TableColumn<Suoritus, String> courseAccosFname;
    @FXML
    private TableColumn<Suoritus, String> courseAccosLname;
    @FXML
    private TableColumn<Suoritus, Integer> courseAccosGrade;
    @FXML
    private TableColumn<Suoritus, String> courseAccosDate;
    
    @FXML
    private Button btnClearTableStudentsAccos;
    @FXML
    private Button btnClearTableCourseAccos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * 
     * @param event Sulkee ohjelman
     */
    @FXML
    private void closeMenuItemClicked(ActionEvent event) {
        Platform.exit();
        System.exit(0); // 0 = statuskoodi
    }

    /**
     * 
     * @param event näyttää pop-ikkunan ohjelman tiedoista
     */
    @FXML
    private void tietojaMenuItemClicked(ActionEvent event) {
        Alert about = new Alert(Alert.AlertType.INFORMATION, "(c) Jenni Breite", ButtonType.OK);
        about.setTitle("Tietoja");
        about.setHeaderText("Osku \nOpiskelijoiden suoritusrekisteri \nv1.0 2021");
        about.show();
    }

    /**
     * 
     * @param event lisää opiskelijan tiedot tietokantaan
     * @throws SQLException 
     */
    @FXML
    private void addStudentButtonClicked(ActionEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        Opiskelija opiskelija = new Opiskelija(txtAddStuFname.getText(), txtAddStuLname.getText());

        Osku.addStudent(conn, opiskelija);

        dialogPaneAddStudent.setContentText(opiskelija.getEtunimi() + " " + opiskelija.getSukunimi());
        dpAddStudentButton.setDisable(false);

        txtAddStuFname.setText("");
        txtAddStuLname.setText("");

        Osku.closeConnection(conn);

    }

    /**
     * 
     * @param event muokkaa opiskelijan tiedot
     * @throws SQLException 
     */
    @FXML
    private void modifyStudentButtonClicked(ActionEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        Opiskelija o = comBoxModifyStudent.getSelectionModel().getSelectedItem();

        Opiskelija opiskelija = new Opiskelija(o.getOpiskelija_id(), txtModifyStuFname.getText(), txtModifyStuLname.getText());

        Osku.modifyStudent(conn, opiskelija);

        dialogPaneModifyStudent.setContentText(opiskelija.toString());
        dpModifyStudentButton.setDisable(false);

        comBoxModifyStudent.setValue(null);
        txtModifyStuFname.setText("");
        txtModifyStuLname.setText("");

        Osku.closeConnection(conn);
    }

    /**
     * 
     * @param event lisää kurssin tietokantaan
     * @throws SQLException 
     */
    @FXML
    private void addCourseButtonClicked(ActionEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        Kurssi kurssi = new Kurssi(txtAddCourseName.getText(), Integer.parseInt(txtAddCourseCredit.getText()));
        Osku.addCourse(conn, kurssi);

        dialogPaneAddCourse.setContentText(kurssi.getKurssin_nimi() + " " + kurssi.getOpintopisteet() + " op");
        dpAddCourseButton.setDisable(false);

        txtAddCourseName.setText("");
        txtAddCourseCredit.setText("");

        Osku.closeConnection(conn);
    }

    /**
     * 
     * @param event muokkaa kurssin tiedot
     * @throws SQLException 
     */
    @FXML
    private void modifyCourseButtonClicked(ActionEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        Kurssi k = comBoxModifyCourse.getSelectionModel().getSelectedItem();

        Kurssi kurssi = new Kurssi(k.getKurssi_id(), txtModifyCourseName.getText(), Integer.parseInt(txtModifyCourseCredit.getText()));

        Osku.modifyCourse(conn, kurssi);

        dialogPaneModifyCourse.setContentText(kurssi.toString());
        dpModifyCourseButton.setDisable(false);

        comBoxModifyCourse.setValue(null);
        txtModifyCourseName.setText("");
        txtModifyCourseCredit.setText("");
        modifyCourseButton.setDisable(true);

        Osku.closeConnection(conn);
    }

    /**
     * 
     * @param event poistaa kurssin tietokannasta
     * @throws SQLException 
     */
    @FXML
    private void deleteCourseButtonClicked(ActionEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        Kurssi ku = comBoxDeleteCourse.getSelectionModel().getSelectedItem();

        Osku.deleteCourse(conn, ku);

        dialogPaneDeleteCourse.setContentText(ku.toString());
        dpDeleteCourseButton.setDisable(false);

        comBoxDeleteCourse.setValue(null);

        Osku.closeConnection(conn);
    }

    /**
     * 
     * @param event lisää kurssin tietokantaan
     * @throws SQLException 
     */
    @FXML
    private void addAccoButtonClicked(ActionEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        Opiskelija o = comBoxAddAccoStudent.getSelectionModel().getSelectedItem();
        Kurssi k = comBoxAddAccoCourse.getSelectionModel().getSelectedItem();

        String accoDate = datePickerAddAcco.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        Suoritus suoritus = new Suoritus(o.getOpiskelija_id(), k.getKurssi_id(), comBoxAddAccoGrade.getValue(), accoDate);

        //System.out.println(suoritus.toString());
        Osku.addAccomplishment(conn, suoritus);

        comBoxAddAccoStudent.setValue(null);
        comBoxAddAccoCourse.setValue(null);
        comBoxAddAccoGrade.setValue(null);
        datePickerAddAcco.setValue(null);

        addAccoButton.setDisable(true);
        labelAddAcco.setText(suoritus.toString());
        okAddAccoButton.setDisable(false);

        Osku.closeConnection(conn);
    }

    /**
     * 
     * @param event muokkaa suorituksen tiedot
     * @throws SQLException 
     */
    @FXML
    private void modifyAccoButtonClicked(ActionEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        Opiskelija o = comBoxModifyAccoStudent.getSelectionModel().getSelectedItem();
        Kurssi k = comBoxModifyAccoCourse.getSelectionModel().getSelectedItem();

        String accoDate = datePickerModifyAcco.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        Suoritus suoritus = new Suoritus(o.getOpiskelija_id(), k.getKurssi_id(), comBoxModifyAccoGrade.getValue(), accoDate);

        Osku.modifyAccomplishment(conn, suoritus);

        labelModifyAcco.setText(suoritus.toString());
        okModifyAccoButton.setDisable(false);

        comBoxModifyAccoStudent.setValue(null);
        comBoxModifyAccoCourse.setValue(null);
        comBoxModifyAccoGrade.setValue(null);
        datePickerModifyAcco.setValue(null);
        modifyAccoButton.setDisable(true);

        selectModifyAccoButton.setDisable(true);

        Osku.closeConnection(conn);

    }

    /**
     * 
     * @param event tyhjentää ilmoituksen opiskelijan lisäyksestä
     */
    @FXML
    private void dpAddStudentButtonClicked(ActionEvent event) {
        dialogPaneAddStudent.setContentText("");
        dpAddStudentButton.setDisable(true);
    }

    /**
     * 
     * @param event hakee comboBoxiin olemassa olevat opiskelijat tietojen muokkaamiseksi
     * @throws SQLException 
     */
    @FXML
    private void modifyStudentSearch(MouseEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        ResultSet studentResult = Osku.selectStudent(conn);

        while (studentResult.next()) {
            Opiskelija op = new Opiskelija(studentResult.getInt("opiskelija_ID"), studentResult.getString("etunimi"), studentResult.getString("sukunimi"));
            comBoxModifyStudent.getItems().addAll(op);
        }

        Osku.closeConnection(conn);
    }

    /**
     * 
     * @param event hakee tekstikenttiin muokattavan opiskelijan tiedot
     * @throws SQLException 
     */
    @FXML
    private void selectStudentButtonClicked(ActionEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        Opiskelija o = comBoxModifyStudent.getSelectionModel().getSelectedItem();

        txtModifyStuFname.setDisable(false);
        txtModifyStuLname.setDisable(false);

        txtModifyStuFname.setText(o.getEtunimi());

        txtModifyStuLname.setText(o.getSukunimi());

        modifyStudentButton.setDisable(false);

        Osku.closeConnection(conn);
    }

    /**
     * 
     * @param event tyhjentää ilmoituksen opiskelijan muokkaamisesta
     */
    @FXML
    private void dpModifyStudentButtonClicked(ActionEvent event) {
        dialogPaneModifyStudent.setContentText("");
        dpModifyStudentButton.setDisable(true);

        txtModifyStuFname.setText("");
        txtModifyStuLname.setText("");
        modifyStudentButton.setDisable(true);

    }

    /**
     * 
     * @param event tyhjentää ilmoituksen opiskelijan poistamisesta
     * @throws SQLException 
     */
    @FXML
    private void dpDeleteStudentButtonClicked(ActionEvent event) throws SQLException {
        dialogPaneDeleteStudent.setContentText("");
        dpDeleteStudentButton.setDisable(true);

    }

    /**
     * 
     * @param event hakee opiskelijat comboBoxiin josta valitaan poistettava opiskelija
     * @throws SQLException 
     */
    @FXML
    private void deleteStudentSearch(MouseEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        ResultSet studentResult = Osku.selectStudent(conn);

        while (studentResult.next()) {
            Opiskelija op = new Opiskelija(studentResult.getInt("opiskelija_ID"), studentResult.getString("etunimi"), studentResult.getString("sukunimi"));
            comBoxDeleteStudent.getItems().addAll(op);
        }

        Osku.closeConnection(conn);
    }

    /**
     * 
     * @param event poistaa opiskelijan tietokannasta
     * @throws SQLException 
     */
    @FXML
    private void deleteStudentButtonClicked(ActionEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        Opiskelija o = comBoxDeleteStudent.getSelectionModel().getSelectedItem();

        Osku.deleteStudent(conn, o);

        dialogPaneDeleteStudent.setContentText(o.toString());
        dpDeleteStudentButton.setDisable(false);

        comBoxDeleteStudent.setValue(null);

        Osku.closeConnection(conn);
    }

    /**
     * 
     * @param event tyhjentää ilmoituksen kurssin lisäyksestä
     */
    @FXML
    private void dpAddCourseButtonClicked(ActionEvent event) {
        dialogPaneAddCourse.setContentText("");
        dpAddCourseButton.setDisable(true);

    }

    /**
     * 
     * @param event tyhjentää ilmoituksen kurssin tietojen muokkaamisesta
     */
    @FXML
    private void dpModifyCourseButtonClicked(ActionEvent event) {
        dialogPaneModifyCourse.setContentText("");
        dpModifyCourseButton.setDisable(true);
    }

    /**
     * 
     * @param event tyhjentää ilmoituksen kurssin poistamisesta
     */
    @FXML
    private void dpDeleteCourseButtonClicked(ActionEvent event) {
        dialogPaneDeleteCourse.setContentText("");
        dpDeleteCourseButton.setDisable(true);

    }

    /**
     * 
     * @param event hakee kurssit comboBoxiin kurssin tietojen muokkaamiseksi
     * @throws SQLException 
     */
    @FXML
    private void modifyCourseSearch(MouseEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        ResultSet courseResult = Osku.selectCourse(conn);

        while (courseResult.next()) {
            Kurssi ku = new Kurssi(courseResult.getInt("kurssi_ID"), courseResult.getString("kurssin_nimi"), courseResult.getInt("laajuus"));
            comBoxModifyCourse.getItems().addAll(ku);
        }

        Osku.closeConnection(conn);
    }

    /**
     * 
     * @param event hakee kurssit comboBoxiin poistettavan kurssin valitsemiseksi
     * @throws SQLException 
     */
    @FXML
    private void deleteCourseSearch(MouseEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        ResultSet courseResult = Osku.selectCourse(conn);

        while (courseResult.next()) {
            Kurssi ku = new Kurssi(courseResult.getInt("kurssi_ID"), courseResult.getString("kurssin_nimi"), courseResult.getInt("laajuus"));
            comBoxDeleteCourse.getItems().addAll(ku);
        }

        Osku.closeConnection(conn);
    }

    /**
     * 
     * @param event hakee muokattavan kurssin olemassa olevat tiedot tekstikenttiin
     * @throws SQLException 
     */
    @FXML
    private void selectCourseButtonClicked(ActionEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        Kurssi ku = comBoxModifyCourse.getSelectionModel().getSelectedItem();

        txtModifyCourseName.setDisable(false);
        txtModifyCourseCredit.setDisable(false);

        txtModifyCourseName.setText(ku.getKurssin_nimi());

        txtModifyCourseCredit.setText(Integer.toString(ku.getOpintopisteet()));

        modifyCourseButton.setDisable(false);

        Osku.closeConnection(conn);
    }

    /**
     * 
     * @param event hakee comboBoxiin opiskelijat, joille voidaan lisätä suoritus
     * @throws SQLException 
     */
    @FXML
    private void comBoxSearchAddAccoStudent(MouseEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        ResultSet studentResult = Osku.selectStudent(conn);

        while (studentResult.next()) {
            Opiskelija op = new Opiskelija(studentResult.getInt("opiskelija_ID"), studentResult.getString("etunimi"), studentResult.getString("sukunimi"));
            comBoxAddAccoStudent.getItems().addAll(op);
        }

        Osku.closeConnection(conn);

    }

    /**
     * 
     * @param event hakee comboBoxiin kurssit, joihin valitulla opiskelijalla ei ole vielä suorituksia
     * @throws SQLException 
     */
    @FXML
    private void comBoxSearchAddAccoCourse(MouseEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        Opiskelija o = comBoxAddAccoStudent.getSelectionModel().getSelectedItem();

        ResultSet courseResult = Osku.selectCoursesNotInAccomplisments(conn, o);

        while (courseResult.next()) {
            Kurssi ku = new Kurssi(courseResult.getInt("kurssi_ID"), courseResult.getString("kurssin_nimi"), courseResult.getInt("laajuus"));
            comBoxAddAccoCourse.getItems().addAll(ku);
        }

        Osku.closeConnection(conn);
    }

    /**
     * 
     * @param event asettaa lisää kurssi painikkeen näkyväksi kun suorituksen päivämäärä on valittu
     */
    @FXML
    private void addAccoDatePicked(ActionEvent event) {
        addAccoButton.setDisable(false);

    }

    /**
     * 
     * @param event 
     */
    @FXML
    private void modifyAccoDatePicked(ActionEvent event) {
    }

    /**
     * 
     * @param event hakee opiskelijat, joilla on suorituksia comboBoxiin suorituksen muokkaamiseksi
     * @throws SQLException 
     */
    @FXML
    private void comBoxSearchModifyAccoStudent(MouseEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        ResultSet studentResult = Osku.selectStudentsWithAccomplishments(conn);

        while (studentResult.next()) {
            Opiskelija op = new Opiskelija(studentResult.getInt("opiskelija_ID"), studentResult.getString("etunimi"), studentResult.getString("sukunimi"));
            comBoxModifyAccoStudent.getItems().addAll(op);
        }

        Osku.closeConnection(conn);

    }

    /**
     * 
     * @param event hakee valitun opiskelijan suoritetut kurssit comboBoxiin
     * @throws SQLException 
     */
    @FXML
    private void comBoxSearchModifyAccoCourse(MouseEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        Opiskelija o = comBoxModifyAccoStudent.getSelectionModel().getSelectedItem();

        ResultSet stuCourResult = Osku.selectStudentsCourses(conn, o);

        while (stuCourResult.next()) {
            Kurssi ku = new Kurssi(stuCourResult.getInt("kurssi_ID"), stuCourResult.getString("kurssin_nimi"), stuCourResult.getInt("laajuus"));
            comBoxModifyAccoCourse.getItems().addAll(ku);
        }

        selectModifyAccoButton.setDisable(false);

        Osku.closeConnection(conn);
    }

    /**
     * 
     * @param event hakee muokattavan suorituksen olemassa olevat tiedot (arvosana ja suoritus pvm)
     * @throws SQLException 
     */
    @FXML
    private void selectModifyAccoButtonClicked(ActionEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        //haetaan valitut opiskelija ja kurssi oliot
        Opiskelija o = comBoxModifyAccoStudent.getSelectionModel().getSelectedItem();
        Kurssi k = comBoxModifyAccoCourse.getSelectionModel().getSelectedItem();

        ResultSet gradeResult = Osku.selectAccomplishment(conn, o, k);

        while (gradeResult.next()) {
            Suoritus su = new Suoritus(gradeResult.getInt("opiskelija_ID"), gradeResult.getInt("kurssi_ID"), gradeResult.getInt("arvosana"), gradeResult.getString("suoritus_pvm"));
            comBoxModifyAccoGrade.setValue(su.getArvosana());

            //muokkaa päivämäärän Suoritus -oliolta datePickeriin
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = su.getSuoritus_pvm();
            LocalDate localDate = LocalDate.parse(date, formatter);
            datePickerModifyAcco.setValue(localDate);
        }

        comBoxModifyAccoStudent.setDisable(true);
        comBoxModifyAccoCourse.setDisable(true);
        selectModifyAccoButton.setDisable(true);

        comBoxModifyAccoGrade.setDisable(false);
        datePickerModifyAcco.setDisable(false);

        modifyAccoButton.setDisable(false);

        Osku.closeConnection(conn);
    }

    /**
     * 
     * @param event hakee opiskelijat, joilla on suorituksia comboBoxiin suorituksen poistamiseksi
     * @throws SQLException 
     */
    @FXML
    private void comBoxSelectDeleteAccoStudent(MouseEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        ResultSet studentResult = Osku.selectStudentsWithAccomplishments(conn);

        while (studentResult.next()) {
            Opiskelija op = new Opiskelija(studentResult.getInt("opiskelija_ID"), studentResult.getString("etunimi"), studentResult.getString("sukunimi"));
            comBoxDeleteAccoStudent.getItems().addAll(op);
        }

        Osku.closeConnection(conn);

    }

    /**
     * 
     * @param event hakee opiskelijan suorittamat kurssit comboBoxiin suorituksen poistamiseksi
     * @throws SQLException 
     */
    @FXML
    private void comBoxSelectDeleteAccoCourse(MouseEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        Opiskelija o = comBoxDeleteAccoStudent.getSelectionModel().getSelectedItem();

        ResultSet stuCourResult = Osku.selectStudentsCourses(conn, o);

        while (stuCourResult.next()) {
            Kurssi ku = new Kurssi(stuCourResult.getInt("kurssi_ID"), stuCourResult.getString("kurssin_nimi"), stuCourResult.getInt("laajuus"));
            comBoxDeleteAccoCourse.getItems().addAll(ku);
        }

        deleteAccoButton.setDisable(false);
        Osku.closeConnection(conn);
    }

    /**
     * 
     * @param event poistaa suorituksen tietokannasta
     * @throws SQLException 
     */
    @FXML
    private void deleteAccoButtonClicked(ActionEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        //haetaan valitut opiskelija ja kurssi oliot
        Opiskelija o = comBoxDeleteAccoStudent.getSelectionModel().getSelectedItem();
        Kurssi k = comBoxDeleteAccoCourse.getSelectionModel().getSelectedItem();

        ResultSet accoResult = Osku.selectAccomplishment(conn, o, k);

        while (accoResult.next()) {
            //päivämäärän tulostuksen muokkaus sql -> olio
            Date sqlDate = accoResult.getDate("suoritus_pvm");
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            String formatDate = format.format(sqlDate);

            Suoritus su = new Suoritus(accoResult.getInt("opiskelija_ID"), accoResult.getInt("kurssi_ID"), +accoResult.getInt("arvosana"), formatDate);

            Osku.deleteAccomplishment(conn, su);
            labelDeleteAcco.setText(su.toString());
        }

        comBoxDeleteAccoStudent.setValue(null);
        comBoxDeleteAccoCourse.setValue(null);

        deleteAccoButton.setDisable(true);
        okDeleteAccoButton.setDisable(false);

        Osku.closeConnection(conn);

    }

    /**
     * 
     * @param event tyhjentää ilmoituksen suorituksen lisäämisestä
     */
    @FXML
    private void okAddAccoButtonClicked(ActionEvent event) {
        labelAddAcco.setText("");
        okAddAccoButton.setDisable(true);
    }

    /**
     * 
     * @param event tyhjentää ilmoituksen suorituksen muokkkaamisesta
     */
    @FXML
    private void okModifyAccoButtonClicked(ActionEvent event) {
        labelModifyAcco.setText("");
        okModifyAccoButton.setDisable(true);

        comBoxModifyAccoStudent.setDisable(false);
        comBoxModifyAccoCourse.setDisable(false);
    }

    /**
     * 
     * @param event tyhjentää ilmoituksen suorituksen poistamisesta
     */
    @FXML
    private void okDeleteAccoButtonClicked(ActionEvent event) {
        labelDeleteAcco.setText("");
        okDeleteAccoButton.setDisable(true);

        comBoxDeleteAccoStudent.setValue(null);
        comBoxDeleteAccoCourse.setValue(null);
    }

    /**
     * 
     * @param event lisää arvosanat lisää suoritus comboBoxiin 
     * @throws SQLException 
     */
    @FXML
    private void comBoxAddAccoGradeClicked(MouseEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        ObservableList<Integer> grades = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5);

        comBoxAddAccoGrade.getItems().addAll(grades);

        Osku.closeConnection(conn);
    }

    /**
     * 
     * @param event lisää arvosanat muokkaa suoritusta comboBoxiin
     * @throws SQLException 
     */
    @FXML
    private void comBoxModifyAccoGradeClicked(MouseEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        ObservableList<Integer> grades = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5);

        comBoxModifyAccoGrade.getItems().addAll(grades);

        Osku.closeConnection(conn);
    }

    /**
     * 
     * @param event hakee valitun opiskelijan kaikki suoritukset tietokannasta
     * @throws SQLException 
     */
    @FXML
    private void searchStudentAccosButtonClicked(ActionEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        Opiskelija op = comBoxSelectStudentsAccos.getSelectionModel().getSelectedItem();

        ResultSet studentAccosResult = Osku.selectStudentsAccomplishments(conn, op);

        //pop-up ilmoitus
        Alert noData = new Alert(Alert.AlertType.NONE, "Opiskelijalla ei ole suorituksia.", ButtonType.OK);

        //alustetaan tableView:n sarakkeet huom. ->("olion atribuutin nimi")
        stuAccosCourseID.setCellValueFactory(new PropertyValueFactory<>("kurssi_id"));
        stuAccosCourseName.setCellValueFactory(new PropertyValueFactory<>("kurssin_nimi"));
        stuAccosCredit.setCellValueFactory(new PropertyValueFactory<>("opintopisteet"));
        stuAccosGrade.setCellValueFactory(new PropertyValueFactory<>("arvosana"));
        stuAccosDate.setCellValueFactory(new PropertyValueFactory<>("suoritus_pvm"));

        //jos opiskelijalla ei ole suorituksia näytetään pop-up ilmoitus
        if (studentAccosResult.next() == false) {
            System.out.println("Ei suorituksia");
            noData.show();

        } else {
            do { //päivämäärän tulostuksen muokkaus sql -> suomi-malli
                Date sqlDate = studentAccosResult.getDate("suoritus_pvm");
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                String formatDate = format.format(sqlDate);

                Suoritus s = new Suoritus(studentAccosResult.getInt("kurssi_ID"), studentAccosResult.getString("kurssin_nimi"), studentAccosResult.getInt("laajuus"), studentAccosResult.getInt("arvosana"), formatDate);

                tblViewStudentsAccos.getItems().addAll(s);

            } while (studentAccosResult.next());
        }

        Osku.closeConnection(conn);

    }

    /**
     * 
     * @param event haetaan valitun kurssin kaikki suoritukset
     * @throws SQLException 
     */
    @FXML
    private void searchCoursesAccosButtonClicked(ActionEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        Kurssi ku = comBoxSelectCoursesAccos.getSelectionModel().getSelectedItem();

        ResultSet courseAccosResult = Osku.selectCourseAccomplishments(conn, ku);

        //pop-up ilmoitus
        Alert noData = new Alert(Alert.AlertType.NONE, "Kurssilla ei ole suorituksia.", ButtonType.OK);

        //alustetaan tableView:n sarakkeet huom. ->("olion atribuutin nimi")
        courseAccosStudentID.setCellValueFactory(new PropertyValueFactory<>("opiskelija_id"));
        courseAccosFname.setCellValueFactory(new PropertyValueFactory<>("etunimi"));
        courseAccosLname.setCellValueFactory(new PropertyValueFactory<>("sukunimi"));
        courseAccosGrade.setCellValueFactory(new PropertyValueFactory<>("arvosana"));
        courseAccosDate.setCellValueFactory(new PropertyValueFactory<>("suoritus_pvm"));

        //jos kurssilla ei ole suorituksia näytetään pop-up ilmoitus
        if (courseAccosResult.next() == false) {

            noData.show();

        } else {
            do { //päivämäärän tulostuksen muokkaus sql -> suomi-malli
                Date sqlDate = courseAccosResult.getDate("suoritus_pvm");
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                String formatDate = format.format(sqlDate);

                Suoritus s = new Suoritus(courseAccosResult.getInt("opiskelija_id"), courseAccosResult.getString("etunimi"), courseAccosResult.getString("sukunimi"), courseAccosResult.getInt("arvosana"), formatDate);

                tblViewCoursesAccos.getItems().addAll(s);
            } while (courseAccosResult.next());
        }

        Osku.closeConnection(conn);

    }

    /**
     * 
     * @param event hakee kaikki opiskelijat comboBoxiin opiskelijan kaikkien suoritusten listaamiseksi
     * @throws SQLException 
     */
    @FXML
    private void comBoxSelectStudentsAccosClicked(MouseEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        ResultSet studentResult = Osku.selectStudent(conn);

        while (studentResult.next()) {
            Opiskelija op = new Opiskelija(studentResult.getInt("opiskelija_ID"), studentResult.getString("etunimi"), studentResult.getString("sukunimi"));
            comBoxSelectStudentsAccos.getItems().addAll(op);
        }

        Osku.closeConnection(conn);
    }

    /**
     * 
     * @param event hakee kaikki kurssit comboBoxiin kurssin kaikkien suoritusten listaamiseksi
     * @throws SQLException 
     */
    @FXML
    private void comBoxSelectCoursesAccosClicked(MouseEvent event) throws SQLException {
        Connection conn = openConnection("jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306?user=opiskelija&password=opiskelija1");
        Osku.useDatabase(conn, "karelia_jennibre");

        ResultSet courseResult = Osku.selectCourse(conn);

        while (courseResult.next()) {
            Kurssi ku = new Kurssi(courseResult.getInt("kurssi_ID"), courseResult.getString("kurssin_nimi"), courseResult.getInt("laajuus"));
            comBoxSelectCoursesAccos.getItems().addAll(ku);
        }

        Osku.closeConnection(conn);
    }

    /**
     * 
     * @param event tyhjentää opiskelijan suoritukset -taulun
     */
    @FXML
    private void btnClearTableStudentsAccosClicked(ActionEvent event) {
        tblViewStudentsAccos.getItems().clear();
        comBoxSelectStudentsAccos.setValue(null);
    }

    /**
     * 
     * @param event tyhjentää kurssin suoritukset -taulun
     */
    @FXML
    private void btnClearTableCourseAccosClicked(ActionEvent event) {
        tblViewCoursesAccos.getItems().clear();
        comBoxSelectCoursesAccos.setValue(null);
    }

}