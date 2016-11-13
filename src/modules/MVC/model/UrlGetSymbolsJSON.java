package modules.MVC.model;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <h1>UrlGetSymbolSJSON</h1>
 * Cette classe contient les methodes qui recuperent l'ensemble des symboles ressemblant a celui que l'utilisateur tape. Cette methode est uniquement
 * utile pour l'autoCompletion.
 * <p>
 * Cette classe contient:
 * - une methode qui definit le lien URL selon l'indice tapp&eacute; dans l'interface. Ce lien contient une liste de symboles et entreprises.
 * - une methode qui permet de parser le JSON et afficher les valeur dans la liste.
 *
 * @author FALLER Romain
 * @version 4.0
 * @since 14/12/2015
 */
public class UrlGetSymbolsJSON {
    private String url;
    private Logger logger;

    /**
     * <h1>UrlGetSymbolsJSON</h1>
     * Cette methode permet de generer le lien selon ce que l'utilisateur tape (selon le nom ou symbol tape).
     * @param nom est le nom/symbole que l'utilisateur est en train de taper dans le textField.
     */
    public UrlGetSymbolsJSON(String nom){
        this.logger = Logger.getLogger(this.getClass().getName());
        this.url = "https://s.yimg.com/aq/autoc?query=" + nom + "&region=FR&lang=fr-FR";
    }

    /**
     * <h1>startRequest</h1>
     * Cette methode permet de parser du  JSON.
     * @return  List qui correspond a la liste des noms/symbols recup.
     */
    public List<Value> startRequest(){
        try{
            this.logger.log(Level.INFO, "Recuperation donnees");
            URL url = new URL(this.url);
            URLConnection connection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder content = new StringBuilder();
            String line;
            this.logger.log(Level.INFO, "donnes recuperees");
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line);
            }
            bufferedReader.close();
            this.logger.log(Level.INFO, "JSON parse");
            JSONObject obj = (new JSONObject(content.toString())).getJSONObject("ResultSet");
            JSONArray listOfResult = obj.getJSONArray("Result");
            List<Value> list = new ArrayList<>();
            for (int i = 0; i < 5; i++)
            {
                String symbol = listOfResult.getJSONObject(i).getString("symbol");
                list.add(new Value(symbol));
            }
            return list;
        }
        catch (Exception e){
            this.logger.log(Level.SEVERE, "Erreur: "+e.getMessage());
        }
        return null;
    }
}