package uk.ac.man.cs.mig.coode.pizzafinder.model;

import org.semanticweb.owlapi.model.IRI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * User: matthewhorridge<br>
 * The Univeristy Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Oct 8, 2004<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public class Preferences {

    public static final String ONTOLOGY_LOCATION_ELEMENT_NAME = "OntologyLocation";

    public static final String PIZZA_CLASS_ELEMENT_NAME = "PizzaClass";

    public static final String TOPPING_CLASS_ELEMENT_NAME = "ToppingClass";

    public static final String VEG_PIZZA_CLASS_ELEMENT_NAME = "VegetarianPizzaClass";

    public static final String SPICY_PIZZA_CLASS_ELEMENT_NAME = "SpicyPizzaClass";

    public static final String TOPPING_PROPERTY_ELEMENT_NAME = "ToppingProperty";

    public static final String LANGUAGE_ELEMENT_NAME = "Language";



    public static final IRI DEFAULT_PIZZA_CLASS_IRI = IRI.create("http://www.co-ode.org/ontologies/pizza/pizza.owl#NamedPizza");

    public static final IRI DEFAULT_TOPPING_CLASS_IRI = IRI.create("http://www.co-ode.org/ontologies/pizza/pizza.owl#PizzaTopping");

    public static final IRI DEFAULT_VEGETARIAN_PIZZA_CLASS_IRI = IRI.create("http://www.co-ode.org/ontologies/pizza/pizza.owl#VegetarianPizza");

    public static final IRI DEFAULT_SPICY_PIZZA_CLASS_IRI = IRI.create("http://www.co-ode.org/ontologies/pizza/pizza.owl#SpicyPizza");

    public static final IRI DEFAULT_HAS_TOPPING_PROPERTY_IRI = IRI.create("http://www.co-ode.org/ontologies/pizza/pizza.owl#hasTopping");

    public static final IRI DEFAULT_ONTOLOGY_DOCUMENT_IRI = IRI.create("http://protege.stanford.edu/ontologies/pizza/pizza.owl");

    public static final String DEFAULT_LANGUAGE = "en";

    private static Preferences instance;

    // default values (will not be used unless the config.xml file cannot be read)
    private IRI ontologyURL = DEFAULT_ONTOLOGY_DOCUMENT_IRI;

    private IRI pizzaClassName = DEFAULT_PIZZA_CLASS_IRI;

    private IRI toppingClassName = DEFAULT_TOPPING_CLASS_IRI;

    private IRI vegetarianClassName = DEFAULT_VEGETARIAN_PIZZA_CLASS_IRI;

    private IRI spicyPizzaClassName = DEFAULT_SPICY_PIZZA_CLASS_IRI;

    private IRI toppingPropertyName = DEFAULT_HAS_TOPPING_PROPERTY_IRI;

    private String language = DEFAULT_LANGUAGE;




    public static final String FILE_NAME = "config.xml";

    private Preferences(String fileName) {
        loadPreferences(fileName);
    }

    public static synchronized Preferences getInstance() {
        if(instance == null) {
            instance = new Preferences(FILE_NAME);
        }
        return instance;
    }

    private void loadPreferences(String fileName) {
        Document doc = getDocument(fileName);
        loadOntologyLocation(doc);
        loadPizzaClassName(doc);
        loadToppingClassName(doc);
        loadVegetarianPizzaClassName(doc);
        loadHotToppingClassName(doc);
        loadToppingPropertyName(doc);
        loadLanguage(doc);
    }

    private void loadOntologyLocation(Document doc) {
        Element element = (Element)doc.getDocumentElement().getElementsByTagName(ONTOLOGY_LOCATION_ELEMENT_NAME).item(0);
        if (element != null) {
            ontologyURL = IRI.create(element.getAttribute("url"));
        }
    }

    private void loadPizzaClassName(Document doc) {
        Element element = (Element)doc.getDocumentElement().getElementsByTagName(PIZZA_CLASS_ELEMENT_NAME).item(0);
        if (element != null) {
            pizzaClassName = getIRI(element);
        }
    }

    private IRI getIRI(Element element) {
        return IRI.create(element.getAttribute("name"));
    }

    private void loadToppingClassName(Document doc) {
        Element element = (Element)doc.getDocumentElement().getElementsByTagName(TOPPING_CLASS_ELEMENT_NAME).item(0);
        if (element != null) {
            toppingClassName = getIRI(element);
        }
    }

    private void loadVegetarianPizzaClassName(Document doc) {
        Element element = (Element)doc.getDocumentElement().getElementsByTagName(VEG_PIZZA_CLASS_ELEMENT_NAME).item(0);
        if (element != null) {
            vegetarianClassName = getIRI(element);
        }
    }

    private void loadHotToppingClassName(Document doc) {
        Element element = (Element)doc.getDocumentElement().getElementsByTagName(SPICY_PIZZA_CLASS_ELEMENT_NAME).item(0);
        if (element != null) {
            spicyPizzaClassName = getIRI(element);
        }
    }

    private void loadToppingPropertyName(Document doc) {
        Element element = (Element)doc.getDocumentElement().getElementsByTagName(TOPPING_PROPERTY_ELEMENT_NAME).item(0);
        if (element != null) {
            toppingPropertyName = getIRI(element);
        }
    }

    private void loadLanguage(Document doc) {
        Element element = (Element) doc.getDocumentElement().getElementsByTagName(LANGUAGE_ELEMENT_NAME).item(0);
        if (language != null) {
            language = element.getAttribute("name");
        }
    }

    private Document getDocument(String fileName) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            // Attempt to load from file - if this doesn't
            // work, load the embedded file.
            File file = new File(fileName);
            if(file.exists()) {
                return documentBuilder.parse(file);
            }
            else {
                InputStream is = getClass().getResourceAsStream("/" + fileName);
                return documentBuilder.parse(is);
            }
        }
        catch(IOException ioEx) {
            ioEx.printStackTrace();
        }
        catch(SAXException saxEx) {
            saxEx.printStackTrace();
        }
        catch(ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public IRI getOntologyDocumentIRI() {
        return ontologyURL;
    }

    public IRI getPizzaClassName() {
        return pizzaClassName;
    }

    public IRI getPizzaToppingClassName() {
        return toppingClassName;
    }

    public IRI getVegetarianPizzaClassName() {
        return vegetarianClassName;
    }

    public IRI getSpicyPizzaClassName() {
        return spicyPizzaClassName;
    }

    public IRI getToppingPropertyName() {
        return toppingPropertyName;
    }

    public String getLanguage() {
        return language;
    }
}

