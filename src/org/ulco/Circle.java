package org.ulco;

public class Circle extends GraphicsObject {
    public Circle(Point center, double radius) {
        this.m_center = center;
        this.m_radius = radius;
    }

    public Circle() {
        this.m_center = new Point(0, 0);
        this.m_radius = 0.0;
    }

    public void init(String json) {
        String str = json.replaceAll("\\s+", "");
        int centerIndex = str.indexOf("center");
        int radiusIndex = str.indexOf("radius");
        int endIndex = str.lastIndexOf("}");

        m_center = new Point(str.substring(centerIndex + 7, radiusIndex - 1));
        m_radius = Double.parseDouble(str.substring(radiusIndex + 7, endIndex));
    }

    public GraphicsObject copy() {
        return new Circle(m_center.copy(), m_radius);
    }

    public Point getCenter() {
        return m_center;
    }

    public boolean isClosed(Point pt, double distance) {
        return Util.isClosed(m_center,pt,distance);
    }

    void move(Point delta) {
        m_center.move(delta);
    }

    public String toJson() {
        return "{ type: circle, center: " + m_center.toJson() + ", radius: " + this.m_radius + " }";
    }

    public String toString() {
        return "circle[" + m_center.toString() + "," + m_radius + "]";
    }

    private Point m_center;
    private double m_radius;
}
