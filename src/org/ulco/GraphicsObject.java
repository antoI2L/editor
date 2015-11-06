package org.ulco;

abstract public class GraphicsObject {
    public GraphicsObject() {
        m_ID = ID.instance().generate();
    }

    abstract public void init(String json);

    abstract public GraphicsObject copy();

    public int getID() {
        return m_ID;
    }

    abstract boolean isClosed(Point pt, double distance);

    abstract void move(Point delta);

    abstract public String toJson();

    abstract public String toString();

    private int m_ID;
}
