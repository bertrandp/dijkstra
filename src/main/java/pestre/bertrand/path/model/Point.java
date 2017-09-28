package pestre.bertrand.path.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Point {
    private int id;
    private String name;
    private int distance = Integer.MAX_VALUE;
    private Map<Point, Integer> links = new HashMap<>();
    private List<Point> path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Map<Point, Integer> getLinks() {
        return links;
    }

    public void setLinks(Map<Point, Integer> links) {
        this.links = links;
    }

    public List<Point> getPath() {
        return path;
    }

    public void setPath(List<Point> path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return id == point.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static final class PointBuilder {
        private int id;
        private String name;
        private int distance = Integer.MAX_VALUE;
        private Map<Point, Integer> links;
        private List<Point> path;

        private PointBuilder() {
        }

        public static PointBuilder aPoint() {
            return new PointBuilder();
        }

        public PointBuilder id(int id) {
            this.id = id;
            return this;
        }

        public PointBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PointBuilder distance(int distance) {
            this.distance = distance;
            return this;
        }

        public PointBuilder links(Map<Point, Integer> links) {
            this.links = links;
            return this;
        }

        public PointBuilder path(List<Point> path) {
            this.path = path;
            return this;
        }

        public Point build() {
            Point point = new Point();
            point.setId(id);
            point.setName(name);
            point.setDistance(distance);
            point.setLinks(links);
            point.setPath(path);
            return point;
        }
    }
}
