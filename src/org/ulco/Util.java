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
            for (GraphicsObject o : p) {
                if (o.isClosed(pt, distance)) {
                    list.add(o);
                }
            }

            while ((p = p.getGroup()) != null) {
                for (GraphicsObject o : p) {
                    if (o.isClosed(pt, distance)) {
                        list.add(o);
                    }
                }
            }
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
}
