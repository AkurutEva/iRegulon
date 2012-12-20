package domainmodel;

import infrastructure.Configuration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;

public final class GenePutativeRegulatoryRegion {
    private static Map<String, GenePutativeRegulatoryRegion> CODE2COLLECTION = new LinkedHashMap<String, GenePutativeRegulatoryRegion>();
    public static GenePutativeRegulatoryRegion UNKNOWN = new GenePutativeRegulatoryRegion("?", "");

    private static final String GROUP_TAGNAME = "regulatory-region-delineations";
    private static final String TAGNAME = "delineation";
    private static final String ATTRIBUTENAME = "id";

    static {
        final Document document = Configuration.getDocument();
        for (Element child : findElements(document.getElementsByTagName(GROUP_TAGNAME), TAGNAME)) {
            final String description = child.getTextContent().trim();
            final String code = child.getAttribute(ATTRIBUTENAME);
            CODE2COLLECTION.put(code, new GenePutativeRegulatoryRegion(code, description));
        }
    }

    private static List<Element> findElements(final NodeList nodeList, final String tagName) {
        final List<Element> elements = new ArrayList<Element>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            final NodeList nodes = nodeList.item(i).getChildNodes();
            for (int j = 0; j < nodes.getLength(); j++) {
                final Node node = nodes.item(j);
                if (node instanceof Element && ((Element) node).getTagName().equals(tagName)) {
                    elements.add((Element) node);
                }
            }
        }
        return elements;
    }

    public static GenePutativeRegulatoryRegion forCode(final String code) {
        return CODE2COLLECTION.containsKey(code) ? CODE2COLLECTION.get(code) : UNKNOWN;
    }

    public static List<GenePutativeRegulatoryRegion> all() {
        return new ArrayList<GenePutativeRegulatoryRegion>(CODE2COLLECTION.values());
    }

    private final String code;
    private final String description;

    private GenePutativeRegulatoryRegion(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenePutativeRegulatoryRegion that = (GenePutativeRegulatoryRegion) o;

        if (!code.equals(that.code)) return false;
        if (!description.equals(that.description)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return description;
    }
}