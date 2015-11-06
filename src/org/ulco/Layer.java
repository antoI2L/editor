package org.ulco;

public class Layer extends Group {
    public Layer() {
        super();
    }

    public void init(String json) {
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int endIndex = str.lastIndexOf("}");

        parseObjects(str.substring(objectsIndex + 9, endIndex - 1));
    }

    public GraphicsObject get(int index) {
        return this.elementAt(index);
    }

    public int getObjectNumber() {
        return this.size();
    }

    public GraphicsObjects select(Point pt, double distance) {
        GraphicsObjects list = new GraphicsObjects();

        for (GraphicsObject object : this) {
            if (object.isClosed(pt, distance)) {
                list.add(object);
            }
        }
        return list;
    }

    public String toJson() {
        String str = "{ type: layer, objects : { ";

        for (int i = 0; i < size(); ++i) {
            GraphicsObject element = elementAt(i);

            str += element.toJson();
            if (i < size() - 1) {
                str += ", ";
            }
        }

        if (null != group) {
            str += ", groups : { ";
            group.toJson();
        }

        return str + " } }";
    }
}
