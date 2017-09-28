package pestre.bertrand.path.webservice.binding.mapper;

import pestre.bertrand.path.model.Point;
import pestre.bertrand.path.webservice.binding.PointDTO;
import pestre.bertrand.path.webservice.binding.ResponsePointDTO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PointMapper {

    public static Map<Integer, Point> toPointMap(List<PointDTO> pointsDTO) {
        Map<Integer, PointDTO> mapDTO = pointsDTO.stream().collect(Collectors.toMap(PointDTO::getId, p -> p));

        Map<Integer, Point> points = pointsDTO.stream().map(dto -> Point.PointBuilder.aPoint()
                .id(dto.getId())
                .name(dto.getName())
                .links(new HashMap<>())
                .path(new LinkedList<>())
                .build())
                .collect(Collectors.toMap(Point::getId, p -> p));


        points.values().forEach(point -> mapDTO.get(point.getId()).getLinks()
                .forEach(linkDTO -> point.getLinks()
                        .put(points.get(linkDTO.getId()),
                                linkDTO.getDistance())));

        return points;
    }

    public static List<ResponsePointDTO> toResponse(Point begin, Point end) {
        List<ResponsePointDTO> path = new LinkedList<>();
        path.add(toResponsePoint(begin));
        path.addAll(end.getPath().stream().map(PointMapper::toResponsePoint).collect(Collectors.toList()));
        path.add(toResponsePoint(end));
        return path;
    }

    private static ResponsePointDTO toResponsePoint(Point point) {
        return ResponsePointDTO.ResponseDTOBuilder.aResponseDTO()
                .id(point.getId())
                .name(point.getName())
                .distance(point.getDistance())
                .build();
    }
}