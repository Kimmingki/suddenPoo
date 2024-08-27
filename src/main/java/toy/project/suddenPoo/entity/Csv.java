package toy.project.suddenPoo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.project.suddenPoo.csv.CsvDTO;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Csv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String toiletName;
    private String roadName;
    private String latitude;
    private String longitude;

    @Builder
    public Csv(CsvDTO csvDTO) {
        this.toiletName = csvDTO.getToiletName();
        this.roadName = csvDTO.getRoadName();
        this.latitude = csvDTO.getLatitude();
        this.longitude = csvDTO.getLongitude();
    }
}
