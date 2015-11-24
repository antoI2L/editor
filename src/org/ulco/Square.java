package org.ulco;

public class Square extends GraphicsObject {
    public Square(Point center, double length) {
        this.m_origin = center;
        this.m_length = length;
    }

    public Square() {
        this.m_origin = new Point(0, 0);
        this.m_length = 0.0;
    }

    public void init(String json) {
        String str = json.replaceAll("\\s+", "");
        int centerIndex = str.indexOf("center");
        int lengthIndex = str.indexOf("length");
        int endIndex = str.lastIndexOf("}");

        m_origin = new Point(str.substring(centerIndex + 7, lengthIndex - 1));
        m_length = Double.parseDouble(str.substring(lengthIndex + 7, endIndex));
    }

    public GraphicsObject copy() {
        return new Square(m_origin.copy(), m_length);
    }

    public Point getOrigin() {
        return m_origin;
    }

    public boolean isClosed(Point pt, double distance) {
        Point center = new Point(m_origin.getX() + m_length / 2, m_origin.getY() + m_length / 2);

        return Util.isClosed(center,pt,distance);
    }

    void move(Point delta) {
        m_origin.move(delta);
    }

    public String toJson() {
        return "{ type: square, center: " + m_origin.toJson() + ", length: " + this.m_length + " }";
    }

    public String toString() {
        return "square[" + m_origin.toString() + "," + m_length + "]";
    }

    private Point m_origin;
    private double m_length;
}
