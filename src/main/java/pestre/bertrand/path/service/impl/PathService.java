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

        Set<Point> settledPoints = new HashSet<>();
        Set<Point> unsettledPoints = new HashSet<>();

        unsettledPoints.add(begin);

        while (unsettledPoints.size() != 0) {
            Point currentPoint = unsettledPoints.stream().min(Comparator.comparingInt(Point::getDistance)).get();
            unsettledPoints.remove(currentPoint);
            currentPoint.getLinks().forEach((linkedPoint, distance) -> {
                if (!settledPoints.contains(linkedPoint)) {
                    computeMinimumDistance(linkedPoint, distance, currentPoint);
                    unsettledPoints.add(linkedPoint);
                }
            });
            settledPoints.add(currentPoint);
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
