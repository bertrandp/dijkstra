package pestre.bertrand.path.service.impl;

import org.springframework.stereotype.Service;
import pestre.bertrand.path.model.Point;
import pestre.bertrand.path.service.IPathService;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Service
public class PathService implements IPathService {

    @Override
    public void findPath(Set<Point> points, Point begin, Point end) {
        begin.setDistance(0);

        Set<Point> settledNodes = new HashSet<>();
        Set<Point> unsettledNodes = new HashSet<>();

        unsettledNodes.add(begin);

        while (!settledNodes.contains(end)) {
            Point currentPoint = unsettledNodes.stream().min(Comparator.comparingInt(Point::getDistance)).get();
            unsettledNodes.remove(currentPoint);
            currentPoint.getLinks().forEach((linkedPoint, distance) -> {
                if (!settledNodes.contains(linkedPoint)) {
                    computeMinimumDistance(linkedPoint, distance, currentPoint);
                    unsettledNodes.add(linkedPoint);
                }
            });
            settledNodes.add(currentPoint);
        }
    }

    private void computeMinimumDistance(Point linkedPoint, Integer distance, Point currentPoint) {
        int sourceDistance = currentPoint.getDistance();
        if (sourceDistance + distance < linkedPoint.getDistance()) {
            linkedPoint.setDistance(sourceDistance + distance);
            linkedPoint.getPath().add(currentPoint);
        }
    }
}
