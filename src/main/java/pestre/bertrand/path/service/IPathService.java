package pestre.bertrand.path.service;

import pestre.bertrand.path.model.Point;

import java.util.Set;

public interface IPathService {

    /**
     * Find shortest path from begin to end within the given points.
     *
     * @param points the list of points
     * @param begin  the begin point
     * @param end    the end point
     */
    void findPath(Set<Point> points, Point begin, Point end);
}
