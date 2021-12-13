package model;

import java.util.Set;
import java.util.TreeSet;

public class Lut {

    Set<Point> points;

    public Lut() {
        this.points = new TreeSet<>();
    }

    public Set<Point> getPoints() {
        return points;
    }

    public void setPoints(Set<Point> points) {
        this.points = points;
    }

    public void addPoint(double x, double y){
        this.points.add(new Point(x,y));
    }

    @Override
    public String toString() {
        return "Lut{" +
                "points=" + points +
                '}';
    }

}
