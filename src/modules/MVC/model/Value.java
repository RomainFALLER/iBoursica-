package modules.MVC.model;

/**
 * <h1>Value</h1>
 * Cette classe contient contient la methode qui recupere le nom et le symbol (autocompletion).
 * <p>
 * Cette classe contient:
 * - une methode qui definit le lien URL selon l'indice tapp&eacute; dans l'interface.
 * - une methode qui permet de recuperer le contenu XML de l'api selon les symbole tape.
 *
 * @author FALLER Romain
 * @version 1.0
 * @since 05/12/2015
 */
public class Value {

    private String symbol;

    /**
     * <h1>Value</h1>
     * Cette methode permet de ranger le symbol et le nom dans les variables symbol et name.
     * @param symbol represente le symbol de l'entreprise.
     * @param name represente le nom de l'entreprise.
     */
    public Value(String symbol) {
        this.symbol = symbol;
    }

    /**
     * <h1>toString</h1>
     * Cette methode permet de retourner la valeur du symbole
     * @return String representant la valeur du symbole.
     */
    public String toString(){
        return this.symbol;
    }
}
