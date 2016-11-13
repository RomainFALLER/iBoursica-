/**
 * <h1>Controller</h1>
 * La classe Controller permet de definir tous les objets ainsi que les methode controles (d'action).
 * <p>
 * Avec l'utilisation de javfx, cette classe "Controller" joue le role de controller mais egalement de view.
 * Cette classe implements initializable pour l'autoCompletion.
 *
 * VIEW: En effet, dans cette classe, on declare l'ensemble des objets definis dans notre "JavaFX Scene Biuilder",
 * c'est a dire les labels, boutons, les tablePane, les GridPane... \n
 *
 * CONTROLLER: On declare egalement dans cette classe l'ensemble des methodes FXML d'actions et de controles (lorsqu'on appuie
 * sur un bouton, qu'on change de tabpane).
 *
 *
 * @author FALLER Romain
 * @version 4.0
 * @since   18/12/2015
 */

package modules.MVC.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import modules.MVC.Main;
import modules.MVC.model.UrlGetSymbolsJSON;
import modules.MVC.model.Value;
import modules.MVC.model.Autocomplete.Action;
import modules.MVC.model.Autocomplete.Autocomplete;
import modules.MVC.model.UrlGetSymbol;
import modules.MVC.model.myHandler;

import java.awt.*;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.scene.paint.Color.web;


public class Controller implements Initializable {
    Autocomplete autocomplete;
    private String indiceTapp;
    private Integer compteur = 0;
    private String historique[] = new String[10];
    private String duree;
    private String indice1 = "EURGBP=X";
    private String indice2 = "EURUSD=X";
    private String indice3 = "EURJPY=X";
    private UrlGetSymbol XMLdata1 = new UrlGetSymbol(indice1);
    private UrlGetSymbol XMLdata2 = new UrlGetSymbol(indice2);
    private UrlGetSymbol XMLdata3 = new UrlGetSymbol(indice3);
    private InputStream ressource1 = XMLdata1.getResource();
    private InputStream ressource2 = XMLdata2.getResource();
    private InputStream ressource3 = XMLdata3.getResource();
    private myHandler handler1 = new myHandler(ressource1);
    private myHandler handler2 = new myHandler(ressource2);
    private myHandler handler3 = new myHandler(ressource3);
    Desktop desktop = null;


    /** Declaration de la menuBar + les items*/

    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu fichier;
    @FXML
    private Menu documentation;
    @FXML
    private Menu propos;
    @FXML
    private MenuItem fermer;
    @FXML
    private MenuItem indexD;
    @FXML
    private MenuItem supprimerChamp;
    @FXML
    private MenuItem explicationsAPI;
    @FXML
    private MenuItem explicationsJ;
    @FXML
    private MenuItem wolf;

    // Declaration des hperlinks pour les historiques
    @FXML
    private Hyperlink histo1;
    @FXML
    private Hyperlink histo2;
    @FXML
    private Hyperlink histo3;
    @FXML
    private Hyperlink histo4;
    @FXML
    private Hyperlink histo5;
    @FXML
    private Hyperlink histo6;
    @FXML
    private Hyperlink histo7;

    // Declaration du label d'erreur si le symbole n'existe pas
    @FXML
    private Label error;
    // Nom de l'entreprise du symbole tape
    @FXML
    private Text title;
    // Declaration des labels ou s'afficheront les donnees de l'action
    @FXML
    private Label coursActuelLabel;
    @FXML
    private Label clotureLabel;
    @FXML
    private Label ouvertureLabel;
    @FXML
    private Label changeLabel;
    @FXML
    private Label percentChangeLabel;
    @FXML
    private Label volumeJLabel;
    @FXML
    private Label volumeMLabel;
    @FXML
    private Label achatLabel;
    @FXML
    private Label venteLabel;
    @FXML
    private Label capBourLabel;
    @FXML
    private TextField indicee;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private ProgressIndicator progress;

    // Declaratin du TabPanes et des Tabs (selon la durees des graphes)
    @FXML
    private TabPane graphes;
    @FXML
    private Tab unJour;
    @FXML
    private Tab cinqJours;
    @FXML
    private Tab unMois;
    @FXML
    private Tab troisMois;
    @FXML
    private Tab sixMois;
    @FXML
    private Tab unAn;
    @FXML
    private Tab cinqAns;

    // Declaration des ImageView des graphes
    @FXML
    private ImageView graph1;
    @FXML
    private ImageView graph2;
    @FXML
    private ImageView graph3;
    @FXML
    private ImageView graph4;
    @FXML
    private ImageView graph5;
    @FXML
    private ImageView graph6;
    @FXML
    private ImageView graph7;
    @FXML
    private ImageView graph8;

    //Tab Panes Devises//Metaux
    @FXML
    private TabPane devises;
    @FXML
    private Tab monnaies;
    @FXML
    private Tab metaux;

    // Monnaies: Dollar
    @FXML
    private Label coursActuL;
    @FXML
    private Label achatL;
    @FXML
    private Label venteL;
    @FXML
    private ImageView graphL;

    // Euro
    @FXML
    private Label coursActuEu;
    @FXML
    private Label achatEu;
    @FXML
    private Label venteEu;
    @FXML
    private ImageView graphEu;

    // Chinois
    @FXML
    private Label coursActuY;
    @FXML
    private Label achatY;
    @FXML
    private Label venteY;
    @FXML
    private ImageView graphY;

    // Metaux: Or
    @FXML
    private Label coursActuOr;
    @FXML
    private Label achatOr;
    @FXML
    private Label venteOr;
    @FXML
    private ImageView graphOr;

    // Argent
    @FXML
    private Label coursActuArg;
    @FXML
    private Label achatArg;
    @FXML
    private Label venteArg;
    @FXML
    private ImageView graphArg;

    // Platine
    @FXML
    private Label coursActuPlat;
    @FXML
    private Label achatPlat;
    @FXML
    private Label ventePlat;
    @FXML
    private ImageView graphPlat;

    // Fonction d'actoin sur fermer
    @FXML
    protected void fermerFenetre() {
        System.exit(0);
    }

    // Fonction d'action sur indexDoxy
    @FXML
    protected void indexDoxy() {

        java.net.URI url;

        try {
            url = new java.net.URI("file:.../Documentation/index.html"); //remplacer l'adresse
            if (Desktop.isDesktopSupported()) {
                desktop = Desktop.getDesktop();
                desktop.browse(url);
            }
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    protected void apropos() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Copyrights");

        alert.setHeaderText("FALLER Romain, WolfExchanges");

        String infos = "L'application Java \"WolfExchanges\" a été programmé dans le cadre du projet JAVA de CIR 3 à l'Institut Supérieur de l'électronique et du numérique";

        alert.setContentText(infos);

        alert.show();
    }


    @FXML
    protected void clickExplicationsAPI() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("API utilisée");

        alert.setHeaderText("API Utilisée: Yahoo Finance");

        String infos = "L'application Java utilise ici une API yahoo finance ou. CEla renvoie les données et caractéristiques de toutes les actions d'entreprises mais pas des indices tels que le CAC 40, Dow Jones, ... car elle n'a pas les droit de transmettre ces données.";

        alert.setContentText(infos);

        alert.show();
    }


    //Actions sur les tabs monnaie, metaux
    @FXML
    protected void monnaieClick() {
        String indice1 = "EURGBP=X";
        String indice2 = "EURUSD=X";
        String indice3 = "EURJPY=X";
        coursActuL.setText(handler1.getCoursActu());
        achatL.setText(handler1.getAchat());
        venteL.setText(handler1.getVente());
        Image imgL = new Image("http://chart.finance.yahoo.com/z?s=EURGBP=X&t=1d");
        graphL.setImage(imgL);
        coursActuEu.setText(handler2.getCoursActu());
        achatEu.setText(handler2.getAchat());
        venteEu.setText(handler2.getVente());
        Image imgEu = new Image("http://chart.finance.yahoo.com/z?s=EURUSD=X&t=1d");
        graphEu.setImage(imgEu);
        coursActuY.setText(handler3.getCoursActu());
        achatY.setText(handler3.getAchat());
        venteY.setText(handler3.getVente());
        Image imgY = new Image("http://chart.finance.yahoo.com/z?s=EURJPY=X&t=1d");
        graphY.setImage(imgY);
    }

    @FXML
    protected void metauxClick() {
        String indice1 = "XAU=X";
        String indice2 = "XAG=X";
        String indice3 = "XPT=X";
        UrlGetSymbol XMLdata1 = new UrlGetSymbol(indice1);
        UrlGetSymbol XMLdata2 = new UrlGetSymbol(indice2);
        UrlGetSymbol XMLdata3 = new UrlGetSymbol(indice3);
        InputStream ressource1 = XMLdata1.getResource();
        InputStream ressource2 = XMLdata2.getResource();
        InputStream ressource3 = XMLdata3.getResource();
        myHandler handler1 = new myHandler(ressource1);
        myHandler handler2 = new myHandler(ressource2);
        myHandler handler3 = new myHandler(ressource3);
        coursActuOr.setText(handler1.getCoursActu());
        achatOr.setText(handler1.getAchat());
        venteOr.setText(handler1.getVente());
        Image imgOr = new Image("http://chart.finance.yahoo.com/z?s=XAU=X&t=1d");
        graphOr.setImage(imgOr);
        coursActuArg.setText(handler2.getCoursActu());
        achatArg.setText(handler2.getAchat());
        venteArg.setText(handler2.getVente());
        Image imgArg = new Image("http://chart.finance.yahoo.com/z?s=XAG=X&t=1d");
        graphArg.setImage(imgArg);
        coursActuPlat.setText(handler3.getCoursActu());
        achatPlat.setText(handler3.getAchat());
        ventePlat.setText(handler3.getVente());
        Image imgPlat = new Image("http://chart.finance.yahoo.com/z?s=XPT=X&t=1d");
        graphPlat.setImage(imgPlat);
    }

    @FXML
    protected String unJourClik() {
        duree = "1d";
        Image img = new Image("http://chart.finance.yahoo.com/z?s=" + indiceTapp + "&t=" + duree);
        graph1.setImage(img);
        return duree;
    }

    @FXML
    protected String cinqJourClik() {
        duree = "5d";
        Image img = new Image("http://chart.finance.yahoo.com/z?s=" + indiceTapp + "&t=" + duree);
        graph2.setImage(img);
        return duree;
    }

    @FXML
    protected String unMoisClik() {
        duree = "1m";
        Image img = new Image("http://chart.finance.yahoo.com/z?s=" + indiceTapp + "&t=" + duree);
        graph3.setImage(img);
        return duree;
    }

    @FXML
    protected String troisMoisClik() {
        duree = "3m";
        Image img = new Image("http://chart.finance.yahoo.com/z?s=" + indiceTapp + "&t=" + duree);
        graph4.setImage(img);
        return duree;
    }

    @FXML
    protected String sixMoisClik() {
        duree = "6m";
        Image img = new Image("http://chart.finance.yahoo.com/z?s=" + indiceTapp + "&t=" + duree);
        graph5.setImage(img);
        return duree;
    }

    @FXML
    protected String unAnClik() {
        duree = "1y";
        Image img = new Image("http://chart.finance.yahoo.com/z?s=" + indiceTapp + "&t=" + duree);
        graph6.setImage(img);
        return duree;
    }

    @FXML
    protected String deuxAnsClik() {
        duree = "2y";
        Image img = new Image("http://chart.finance.yahoo.com/z?s=" + indiceTapp + "&t=" + duree);
        graph7.setImage(img);
        return duree;
    }

    @FXML
    protected String cinqAnsClik() {
        duree = "5y";
        Image img = new Image("http://chart.finance.yahoo.com/z?s=" + indiceTapp + "&t=" + duree);
        graph8.setImage(img);
        return duree;
    }

    @FXML
    protected void histo1Click() throws Exception {
        indicee.setText(histo1.getText());
        buttonActived();
    }

    @FXML
    protected void histo2Click() throws Exception {
        indicee.setText(histo2.getText());
        buttonActived();
    }
    @FXML
    protected void histo3Click() throws Exception {
        indicee.setText(histo3.getText());
        buttonActived();
    }
    @FXML
    protected void histo4Click() throws Exception {
        indicee.setText(histo4.getText());
        buttonActived();
    }
    @FXML
    protected void histo5Click() throws Exception {
        indicee.setText(histo5.getText());
        buttonActived();
    }
    @FXML
    protected void histo6Click() throws Exception {
        indicee.setText(histo6.getText());
        buttonActived();
    }
    @FXML
    protected void histo7Click() throws Exception {
        indicee.setText(histo7.getText());
        buttonActived();
    }


/// Fonction d'action du bouton valider, il lance le parsage et l'affichage des donnees, graphs...

    @FXML
    private void buttonActived() throws Exception {

        compteur++;
        indiceTapp = indicee.getText();
        historique[compteur] = indiceTapp;
        UrlGetSymbol XMLdata = new UrlGetSymbol(indiceTapp);
        InputStream ressource = XMLdata.getResource();
        myHandler handler = new myHandler(ressource);
        title.setText("  " + handler.getName());

        histo1.setText(historique[1]);
        histo2.setText(historique[2]);
        histo3.setText(historique[3]);
        histo4.setText(historique[4]);
        histo5.setText(historique[5]);
        histo6.setText(historique[6]);
        histo7.setText(historique[7]);

        if (historique[8] != null)
        {
            compteur=0;
            for (int i=1; i<9;i++) {
                historique[i] = null;
            }
            histo1.setText(null);
            histo2.setText(null);
            histo3.setText(null);
            histo4.setText(null);
            histo5.setText(null);
            histo6.setText(null);
            histo7.setText(null);
        }

        Image img = new Image("http://chart.finance.yahoo.com/z?s=" + indiceTapp + "&t=" + duree);


        if (duree == "1d") {
            graph1.setImage(img);
        } else if (duree == "5d") {
            graph2.setImage(img);
        }
        if (duree == "1m") {
            graph3.setImage(img);
        } else if (duree == "3m") {
            graph4.setImage(img);
        } else if (duree == "6m") {
            graph5.setImage(img);
        } else if (duree == "1y") {
            graph6.setImage(img);
        } else if (duree == "2y") {
            graph7.setImage(img);
        } else {
            graph8.setImage(img);
        }
        try {
            coursActuelLabel.setText(handler.getCoursActu());
            clotureLabel.setText(handler.getCloture());
            ouvertureLabel.setText(handler.getOuverture());
            if ((handler.changeFlo) < 0) {
                changeLabel.setTextFill(web("#fe1313"));
                percentChangeLabel.setTextFill(web("#fe1313"));
            } else {
                changeLabel.setTextFill(web("#00e135"));
                percentChangeLabel.setTextFill(web("#00e135"));

            }
            changeLabel.setText(" " + handler.getChange());
            percentChangeLabel.setText(handler.getPercentChange());
            volumeJLabel.setText(handler.getVolumeJourn());
            volumeMLabel.setText(handler.getVolumeMoy());
            achatLabel.setText(handler.getAchat());
            venteLabel.setText(handler.getVente());
            capBourLabel.setText(handler.getCapBours());
            progress.setProgress(100);
            error.setVisible(false);
        } catch (Exception e) {
            e.toString();
        }
        if (handler.getCoursActu() == null) {
            error.setVisible(true);
        }
        indicee.setText("");
    }


    // Reference to the main application.
    private Main main;

    // Pour l'autocompletion
    public void initialize(URL location, ResourceBundle resources) {
        autocomplete = new Autocomplete(indicee, contextMenu);
        autocomplete.setAction(new Action() {
            @Override
            public java.util.List<? extends Object> methodForGettingItem(String search) {
                search = search.replaceAll(" ", "%20");
                UrlGetSymbolsJSON urlGetSymbol = new UrlGetSymbolsJSON(search);
                return urlGetSymbol.startRequest();
            }

            @Override
            public void methodWhenAnItemIsSelected(Object obj) throws Exception {

                if (obj instanceof Value) {
                    buttonActived();
                }
            }
        });
    }
}
