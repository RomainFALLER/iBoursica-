package modules.MVC.model;

import org.xml.sax.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


/**
 * <h1>myHandler</h1>
 * La classe myHandler permet de parser (rcuperer, analyser/trier, retourner) notre XML.
 * <p>
 * Cette classe permet donc d'exploiter un site sous une forme brute afin d&rsquo;en tirer les informations utiles. C'est &agrave; dire qu'on r&eacute;cupere
 * le XML de la page web et ensuite on l'annalyse selon ses balises.
 * La m&eacute;thode de parsage utilis&eacute;e est SAX qui est l'acronyme de Simple API for XML. Le handler doit impl&eacute;menter des m&eacute;thodes particuli&egrave;res
 * pour analyser le XML et fournir les traitements &agrave; r&eacute;aliser : selon les &eacute;v&eacute;nements startELement, endELement, le parseur appelle ces m&eacute;thodes.
 *
 * @author FALLER Romain
 * @version 4.0
 * @since 07/12/2015
 */
public class myHandler implements ContentHandler {

    boolean nameB = false;
    private String name;

    boolean coursActuB = false;
    private String coursActu;

    boolean clotureB = false;
    private String cloture;

    boolean ouvertureB = false;
    private String ouverture;

    boolean changeB = false;
    private String change;
    public Float changeFlo;

    boolean percentChangeB = false;
    public String percentChange;

    boolean volumeJournB = false;
    private String volumeJourn;

    boolean volumeMoyB = false;
    private String volumeMoy;

    boolean achatB = false;
    private String achat;

    boolean venteB = false;
    private String vente;

    boolean capBoursB = false;
    private String capBours;

    private XMLReader ressource;
    private String lastNode;
    private Map<String, String> response = new HashMap<String, String>();


    /**
     * <h1>getName</h1>
     * Cette methode correspond au getter de l'attribut private name. Il permet donc de recuperer le nom (dans la classe controller par exemple)
     * qui a ete attribue apres analyse du XML par le handler.
     * @return String correspondant au nom de l'entreprise.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * <h1>getCoursActu</h1>
     * Cette methode correspond au getter de l'attribut private coursActu. Il permet donc de recuperer le cours actuel (dans la classe controller par exemple)
     * qui a ete attribu&eacute; apres analyse du XML par le handler.
     * @return String correspondant au cours actuel.
     */
    public String getCoursActu() {
        return coursActu;
    }

    /**
     * <h1>getCloture</h1>
     * Cette methode correspond au getter de l'attribut private cloture. Il permet donc de recuperer le prix de cloture (dans la classe controller par exemple)
     * qui a ete attribue apres analyse du XML par le handler.
     * @return String correspondant au prix de cloture.
     */
    public String getCloture() {
        return cloture;
    }

    /**
     * <h1>getOuverture</h1>
     * Cette methode correspond au getter de l'attribut private ouverture. Il permet donc de recuperer le prix d'ouverture (dans la classe controller par exemple)
     * qui a ete attribue apres analyse du XML par le handler.
     * @return String correspondant au prix d'ouverture de l'entreprise.
     */
    public String getOuverture() {
        return ouverture;
    }

    /**
     * <h1>getChange</h1>
     * Cette methode correspond au getter de l'attribut private change. Il permet donc de recuperer le change (dans la classe controller par exemple)
     * qui a ete attribue apres analyse du XML par le handler.
     * @return String correspondant au change du cours boursier.
     */
    public String getChange() {
        return change;
    }

    /**
     * <h1>getPercentChange</h1>
     * Cette methode correspond au getter de l'attribut private percentChange. Il permet donc de recuperer le change en % (dans la classe controller par exemple)
     * qui a ete attribue apres analyse du XML par le handler.
     * @return String correspondant au change en %  .
     */
    public String getPercentChange() {
        return percentChange;
    }

    /**
     * <h1>getVolumeJourn</h1>
     * Cette methode correspond au getter de l'attribut private volumeJourn. Il permet donc de recuperer le volume journalier (dans la classe controller par exemple)
     * qui a ete attribue apres analyse du XML par le handler.
     * @return String correspondant au volume journalier du cours.
     */
    public String getVolumeJourn() {
        return volumeJourn;
    }

    /**
     * <h1>getVolumeMoy</h1>
     * Cette methode correspond au getter de l'attribut private volumeMoy. Il permet donc de recuperer le volume moyen sur 3 jours (dans la classe controller par exemple)
     * qui a ete attribue apres analyse du XML par le handler.
     * @return String correspondant au volume moyen sur 3 jours du cours.
     */
    public String getVolumeMoy() {
        return volumeMoy;
    }

    /**
     * <h1>getAchat</h1>
     * Cette methode correspond au getter de l'attribut private achat. Il permet donc de recuperer le prix d'achat(dans la classe controller par exemple)
     * qui a ete attribue apres analyse du XML par le handler.
     * @return String correspondant au prix d'achat.
     */
    public String getAchat() {
        return achat;
    }

    /**
     * <h1>getVente</h1>
     * Cette methode correspond au getter de l'attribut private vente. Il permet donc de recuperer le prix de vente (dans la classe controller par exemple)
     * qui a ete attribue apres analyse du XML par le handler.
     * @return String correspondant au prix de vente.
     */
    public String getVente() {
        return vente;
    }

    /**
     * <h1>getCapBours</h1>
     * Cette methode correspond au getter de l'attribut private capBours. Il permet donc de recuperer la capitalisation boursiere (dans la classe controller par exemple)
     * qui a ete attribue apres analyse du XML par le handler.
     * @return String correspondant a la capitalisation boursiere.
     */
    public String getCapBours() {
        return capBours;
    }

    /**
     * <h1>myHandler</h1>
     * Cette methode correspond au constructeur, elle effectue le parsage XML avec la methode SAX (SAXParser XMLReader,...).
     * @param xmlString qui correspond a la chaine de caractere representant le XML recuper&eacute; sur la page web.
     */
    public myHandler (InputStream xmlString) {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser spp = spf.newSAXParser();
            XMLReader xr = spp.getXMLReader();
            xr.setContentHandler(this);
            xr.parse(new InputSource(xmlString));
            this.ressource = xr;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <h1>startElement</h1>
     * Cette methode fait partie du handler SAX, elle permet "d'accepter" le demarrage c'est a dire qu'elle notifie le "start" d'un element
     * , elle permet de mettre les attributs a true (egalement a voir si ils existent dans le xml).
     * @param uri l'URI
     * @param localName le nom local
     * @param qName le nom qualifie (name, open, etc...)
     * @param attributes les attributs attaches a l'element.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        String node = qName;

        if (node.equals("Name"))
        {
            nameB=true;
        }
        if (node.equals("LastTradePriceOnly")) {
            coursActuB = true;
        }
        if (node.equals("PreviousClose"))
        {
            clotureB = true;
        }
        if (node.equals("Open"))
        {
            ouvertureB = true;
        }
        if (node.equals("Change")) {
            changeB = true;
        }
        if (node.equals("PercentChange"))
        {
            percentChangeB = true;
        }
        if (node.equals("Volume")) {
            volumeJournB = true;
        }
        if (node.equals("AverageDailyVolume")) {
            volumeMoyB = true;
        }
        if (node.equals("Bid")) {
            achatB = true;
        }
        if (node.equals("Ask")) {
            venteB = true;
        }
        if (node.equals("MarketCapitalization")) {
            capBoursB = true;
        }
    }


    /**
     * <h1>endElement</h1>
     * Cette methode fait partie du handler SAX, elle permet "d'accepter" la fin c'est a dire qu'elle notifie la fin d'un element
     * , elle permet de mettre les attributs a false (egalement a voir si ils existent dans le xml).
     * @param uri l'URI
     * @param localName le nom local
     * @param qName le nom qualifie (name, open, etc...)
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException{

        String node = qName;

        if (node.equals("Name"))
        {
            nameB = false;
        }
        if (node.equals("LastTradePriceOnly")) {
            coursActuB = false;
        }
        if (node.equals("PreviousClose"))
        {
            clotureB = false;
        }
        if (node.equals("Open"))
        {
            ouvertureB = false;
        }
        if (node.equals("Change")) {
            changeB = false;
        }
        if (node.equals("PercentChange"))
        {
            percentChangeB = false;
        }
        if (node.equals("Volume")) {
            volumeJournB = false;
        }
        if (node.equals("AverageDailyVolume")) {
            volumeMoyB = false;
        }
        if (node.equals("Bid")) {
            achatB = false;
        }
        if (node.equals("Ask")) {
            venteB = false;
        }
        if (node.equals("MarketCapitalization")) {
            capBoursB = false;
        }
    }


    /**
     * <h1>characters</h1>
     * Cette methode fait partie du handler SAX, elle permet de recuperer la chaine de caractere si le startelement a bien fonctionne et que l'attribut
     * existe bien dans le XML (qu'il est a trou). Alors cela recupere la chaine et la range dans une autre que l'ont cree a l'aide de lengt et start.
     * @param ch la chaine de caracteres representant la chaine que l'on veut (l'element comme "Apple")
     * @param start la position de depart dans le tableau de caractere
     * @param length la taille de la chaine
     */
    @Override
    public void characters(char ch[], int start, int length) throws SAXException{

        String data = new String(ch, start, length).trim();
        response.put(lastNode, data);

        if (nameB)
        {
            name = new String (ch, start, length);
        }
        if (coursActuB) {
            coursActu = new String(ch, start, length);
        }
        if (clotureB)
        {
            cloture = new String(ch, start, length);
        }
        if (ouvertureB)
        {
            ouverture = new String(ch, start, length);
        }
        if (changeB) {

            change = new String(ch, start, length);
            changeFlo = Float.parseFloat(change);
        }
        if (percentChangeB)
        {
            percentChange = new String(ch, start, length);
        }
        if (volumeJournB) {

            volumeJourn = new String(ch, start, length);

        }
        if (volumeMoyB) {

            volumeMoy = new String(ch, start, length);

        }
        if (achatB) {

            achat = new String(ch, start, length);

        }
        if (venteB) {

            vente = new String(ch, start, length);

        }
        if (capBoursB) {

            capBours = new String(ch, start, length);

        }

    }

    @Override
    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startPrefixMapping(String s, String s1) throws SAXException {

    }

    @Override
    public void endPrefixMapping(String s) throws SAXException {

    }
    @Override
    public void ignorableWhitespace(char[] chars, int i, int i1) throws SAXException {

    }

    @Override
    public void processingInstruction(String s, String s1) throws SAXException {

    }

    @Override
    public void skippedEntity(String s) throws SAXException {

    }

}
