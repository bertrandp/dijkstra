package pestre.bertrand.path.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pestre.bertrand.path.model.Point;
import pestre.bertrand.path.service.IPathService;
import pestre.bertrand.path.webservice.binding.PointDTO;
import pestre.bertrand.path.webservice.binding.ResponsePointDTO;
import pestre.bertrand.path.webservice.binding.mapper.PointMapper;
import pestre.bertrand.path.webservice.validation.PointValidator;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("solve.json")
public class PathWebService {

    @Autowired
    private IPathService pathService;

    /**
     * Finds the shortest path between a begin point and an endpoint within the given list of points.
     *
     * @param points the list of points with the distance of the linked points
     * @param begin  the id of the begin point
     * @param end    the id of the end point
     * @return the path
     */
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponsePointDTO> findPath(@RequestBody List<PointDTO> points,
                                           @RequestParam("begin") Integer begin,
                                           @RequestParam("end") Integer end) {
        PointValidator.validatePoints(points);
        PointValidator.validatePoint(points, begin);
        PointValidator.validatePoint(points, end);

        Map<Integer, Point> pointMap = PointMapper.toPointMap(points);
        Point beginPoint = pointMap.get(begin);
        Point endPoint = pointMap.get(end);

        pathService.findPath(new HashSet<>(pointMap.values()), beginPoint, endPoint);

        return PointMapper.toResponse(beginPoint, endPoint);
    }
}