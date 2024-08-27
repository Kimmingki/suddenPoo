package toy.project.suddenPoo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.project.suddenPoo.csv.CsvDTO;
import toy.project.suddenPoo.dto.MapRangeDTO;
import toy.project.suddenPoo.entity.Csv;
import toy.project.suddenPoo.repository.ToiletRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToiletService {

    private final ToiletRepository toiletRepository;

    /**
     * 범위 내 화장실 찾기
     * @param mapRangeDTO 범위 값 DTO
     * @return
     */
    public List<CsvDTO> findToiletsByRange(MapRangeDTO mapRangeDTO) {
        List<Csv> toiletsByRange = toiletRepository.findToiletsByRange(mapRangeDTO.getSwLat(), mapRangeDTO.getNeLat(), mapRangeDTO.getSwLng(), mapRangeDTO.getNeLng());
        return toiletsByRange.stream().map(CsvDTO::new).toList();
    }
}
