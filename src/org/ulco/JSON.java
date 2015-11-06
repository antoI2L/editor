package org.ulco;

public class JSON {

    static public GraphicsObject parse(String json) {
        String str = json.replaceAll("\\s+", "");
        String type = str.substring(str.indexOf("type") + 5, str.indexOf(","));

        return GraphicsObjectFactory.createGraphicsObject(type, str);
    }

    static public Group parseGroup(String json) {
        return (Group) GraphicsObjectFactory.createGraphicsObjects(GraphicsObjectFactory.GROUP, json);
    }

    static public Layer parseLayer(String json) {
        return (Layer) GraphicsObjectFactory.createGraphicsObjects(GraphicsObjectFactory.LAYER, json);
    }

    static public Document parseDocument(String json) {
        return new Document(json);
    }
}
