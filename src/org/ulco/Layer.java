package org.ulco;

import java.util.Vector;

public class Layer extends GraphicsObjects {
    public Layer() {
        m_ID = ++ID.ID;
    }

    public void init(String json) {
        String str = json.replaceAll("\\s+", "");
        int objectsIndex = str.indexOf("objects");
        int endIndex = str.lastIndexOf("}");

        parseObjects(str.substring(objectsIndex + 9, endIndex - 1));
    }

    public GraphicsObject get(int index) {
        return this.elementAt(index);
    }

    public int getObjectNumber() {
        return size();
    }

    public int getID() {
        return m_ID;
    }

    private void parseObjects(String objectsStr) {
        while (!objectsStr.isEmpty()) {
            int separatorIndex = searchSeparator(objectsStr);
            String objectStr;

            if (separatorIndex == -1) {
                objectStr = objectsStr;
            } else {
                objectStr = objectsStr.substring(0, separatorIndex);
            }

            add(JSON.parse(objectStr));

            if (separatorIndex == -1) {
                objectsStr = "";
            } else {
                objectsStr = objectsStr.substring(separatorIndex + 1);
            }
        }
    }

    private int searchSeparator(String str) {
        int index = 0;
        int level = 0;
        boolean found = false;

        while (!found && index < str.length()) {
            if (str.charAt(index) == '{') {
                ++level;
                ++index;
            } else if (str.charAt(index) == '}') {
                --level;
                ++index;
            } else if (str.charAt(index) == ',' && level == 0) {
                found = true;
            } else {
                ++index;
            }
        }
        if (found) {
            return index;
        } else {
            return -1;
        }
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
        return str + " } }";
    }

    private int m_ID;
}
