package toy.project.suddenPoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.project.suddenPoo.entity.Csv;

public interface ToiletRepository extends JpaRepository<Csv, Long>, ToiletRepositoryCustom {
}
