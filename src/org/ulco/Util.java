package org.ulco;

/**
 * Created by antony on 06/11/15.
 */
public class Util {

    public static GraphicsObjects select(Layer layer, Point pt, double distance) {
        GraphicsObjects list = new GraphicsObjects();

        for (GraphicsObject object : layer) {
            if (object.isClosed(pt, distance)) {
                list.add(object);
            }
        }

        Group p;
        if ((p = layer.getGroup()) != null) {
            do {
                for (GraphicsObject o : p) {
                    if (o.isClosed(pt, distance)) {
                        list.add(o);
                    }
                }
            }while ((p = p.getGroup()) != null);
        }

        return list;
    }

    public static GraphicsObjects select(Document document, Point p, double distance) {
        GraphicsObjects list = new GraphicsObjects();

        for(Layer layer : document.getLayers()) {
            list.addAll(select(layer, p, distance));
        }

        return list;
    }

    public static int searchSeparator(String str) {
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

    public static  boolean isClosed(Point center,Point pt, double distance){
        return Math.sqrt((Math.pow(center.getX() - pt.getX(),2)) +
                (Math.pow(center.getY() - pt.getY(),2))  ) <= distance;
    }


}
