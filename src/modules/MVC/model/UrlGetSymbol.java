package modules.MVC.model;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

/**
 * <h1>UrlGetSymbol</h1>
 * Cette classe contient les methodes qui recuperent le contenu en XML.
 * <p>
 * Cette classe contient:
 * - une methode qui definit le lien URL selon l'indice tap&eacute; dans l'interface. Ce lien contient l'ensemble des donnees d'un symbole boursier (cours actuel, volume, etc...).
 * - une methode qui permet de recuperer le contenu XML de l'api selon les symbole tape (cela recupere tout !).
 *
 * @author FALLER Romain
 * @version 1.0
 * @since 05/12/2015
 */
public class UrlGetSymbol {
    private String myURL;
    private Logger logger;

    /**
     * <h1>UrlGetSymbol</h1>
     * Cette m&eacute;thode permet de d&eacute;finir le lien URL selon l'indice tap&eacute; par l'utilisateur dans l'interface graphique. Ce lien dirige vers une page web
     * qui renvoie les donnees caracteristiques de bourses par rapport au symbole recherche.
     * @param symbol represente le symbol de l'entreprise que l'utilisateur a valide dans l'interface graph.
     */
    public UrlGetSymbol(String symbol){
        this.logger = Logger.getLogger(this.getClass().getName());
        myURL = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20%28%22" + symbol + "%22%29&env=store://datatables.org/alltableswithkeys";
    }

    /**
     * <h1>getResource</h1>
     * Cette methode permet de creer une connexion et de pouvoir lire le contenu de la page.
     * @return InputStream un stream qui permet de lire le contenu de la page web.
     */
    public InputStream getResource(){
        HttpURLConnection connection = null;
        try{
            URL url = new URL(myURL);
            this.logger = Logger.getLogger(this.getClass().getName());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            return conn.getInputStream();

        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
    }
}