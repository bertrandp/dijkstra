package pestre.bertrand.path.webservice.validation;

import pestre.bertrand.path.webservice.binding.PointDTO;

import java.util.List;
import java.util.Objects;

public class PointValidator {

    public static void validatePoints(List<PointDTO> points) {
        if (points.stream().anyMatch(pointDTO -> pointDTO.getId() == null
                || pointDTO.getLinks() == null
                || pointDTO.getLinks().isEmpty())) {
            throw new IllegalArgumentException("Invalid payload, id or links cannot be null or empty");
        }

        if (points.stream().anyMatch(pointDTO ->
                pointDTO.getLinks().stream().anyMatch(pointLink ->
                        points.stream().noneMatch(pointDTO1 ->
                                pointDTO1.getId() == pointLink.getId())))) {
            throw new IllegalArgumentException("Invalid id in payload");
        }
    }

    public static void validatePoint(List<PointDTO> points, Integer begin) {
        if (points.stream().noneMatch(pointDTO -> Objects.equals(pointDTO.getId(), begin))) {
            throw new IllegalArgumentException("Invalid begin/end parameters");
        }
    }
}
