package org.ulco;

import java.lang.reflect.Constructor;

/**
 * Created by antony on 06/11/15.
 */
public class GraphicsObjectFactory {

    public final static String CIRCLE = "circle";
    public final static String GROUP = "group";
    public final static String LAYER = "layer";
    public final static String RECTANGLE = "rectangle";
    public final static String SQUARE = "square";

    public static GraphicsObject createGraphicsObject(String type, String json) {

        GraphicsObject o = null;
        try {
            Class goClass = Class.forName("org.ulco." + ucFirstType(type));

            o = (GraphicsObject) goClass.newInstance();
            o.init(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return o;
    }

    public static GraphicsObjects createGraphicsObjects(String type, String json) {

        GraphicsObjects o = null;
        try {
            Class goClass = Class.forName("org.ulco." + ucFirstType(type));

            o = (GraphicsObjects) goClass.newInstance();
            o.init(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return o;
    }

    private static String ucFirstType(String type) {
        return type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
    }
}
