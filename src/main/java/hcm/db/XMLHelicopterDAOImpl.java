package hcm.db;

import hcm.entities.Helicopter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XMLHelicopterDAOImpl implements DAO<Helicopter> {
    private static final String XML_FILE = "helicopters.xml";

    @Override
    public Helicopter getObjectById(String objectID) {
        return null;
    }

    @Override
    public List<Helicopter> getAllObjects() {
        return Collections.emptyList();
    }

    @Override
    public int save(Helicopter object) {
        return 0;
    }

    @Override
    public int update(Helicopter object) {
        return 0;
    }

    @Override
    public int delete(Helicopter object) {
        return 0;
    }

    @Override
    public int insert(Helicopter object) {
        return 0;
    }

    private Document getDocument() throws Exception {
        File file = new File(XML_FILE);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        return file.exists() ? builder.parse(file) : builder.newDocument();
    }

    private void saveDocument(Document doc) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(new File(XML_FILE)));
    }

    public void createHelicopter(String modelName) {
        try {
            Document doc = getDocument();
            Element root = doc.getDocumentElement();
            if (root == null) {
                root = doc.createElement("helicopters");
                doc.appendChild(root);
            }
            Element helicopter = doc.createElement("helicopter");
            helicopter.appendChild(doc.createTextNode(modelName));
            root.appendChild(helicopter);
            saveDocument(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> readHelicopters() {
        List<String> helicopters = new ArrayList<>();
        try {
            Document doc = getDocument();
            NodeList nodes = doc.getElementsByTagName("helicopter");
            for (int i = 0; i < nodes.getLength(); i++) {
                helicopters.add(nodes.item(i).getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return helicopters;
    }


    public void updateHelicopter(String oldName, String newName) {
        try {
            Document doc = getDocument();
            NodeList nodes = doc.getElementsByTagName("helicopter");
            for (int i = 0; i < nodes.getLength(); i++) {
                if (nodes.item(i).getTextContent().equals(oldName)) {
                    nodes.item(i).setTextContent(newName);
                }
            }
            saveDocument(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteHelicopter(String modelName) {
        try {
            Document doc = getDocument();
            NodeList nodes = doc.getElementsByTagName("helicopter");
            for (int i = 0; i < nodes.getLength(); i++) {
                if (nodes.item(i).getTextContent().equals(modelName)) {
                    nodes.item(i).getParentNode().removeChild(nodes.item(i));
                }
            }
            saveDocument(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
