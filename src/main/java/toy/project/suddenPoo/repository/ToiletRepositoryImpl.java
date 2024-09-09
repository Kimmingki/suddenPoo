package toy.project.suddenPoo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import toy.project.suddenPoo.entity.Csv;

import java.util.List;

import static toy.project.suddenPoo.entity.QCsv.csv;

@Repository
public class ToiletRepositoryImpl implements ToiletRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public ToiletRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Csv> findToiletsByRange(String swLat, String neLat, String swLng, String neLng) {
        return queryFactory
                .selectFrom(csv)
                .where(
                        csv.latitude.between(swLat, neLat)
                                .and(csv.longitude.between(swLng, neLng))
                )
                .fetch();
    }
}
