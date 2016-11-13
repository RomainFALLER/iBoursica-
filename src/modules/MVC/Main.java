package modules.MVC;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * <h1>Main</h1>
 * La classe Main permet de lancer le programme principal (ouvre la fenetre).
 * <p>
 * Elle extends Application de javafx car c'est javafx qui s'occupe de toute l'implementation (framework).
 *
 *
 * @author FALLER Romain
 * @version 2.0
 * @since   15/12/2015
 */
public class Main extends Application{


    /**
     * <h1>start</h1>
     * Cette methode est une m&eacute;thode impl&eacute;ment&eacute;e directement avec l'extend Application de JAVAFX. C'est cette m&eacute;thode qui va chercher
     * le fichier fxml que l'on a cr&eacute;er et qui gere le processus de lancement du programme.
     * @param primaryStage represente le Stage qui  va nous servir de fenetre principale.
     */
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/designFenetre.fxml"));
        primaryStage.setTitle("WolfExchanges");
        primaryStage.setScene(new Scene(root, 1004, 650));
        primaryStage.getIcons().add(new Image("file:logo.png"));
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
