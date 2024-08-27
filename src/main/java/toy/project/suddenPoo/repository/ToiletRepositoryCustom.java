package toy.project.suddenPoo.repository;

import toy.project.suddenPoo.entity.Csv;

import java.util.List;

public interface ToiletRepositoryCustom {

    List<Csv> findToiletsByRange(String swLat, String neLat, String swLng, String neLng);
}
