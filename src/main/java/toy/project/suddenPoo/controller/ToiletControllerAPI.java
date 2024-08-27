package toy.project.suddenPoo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toy.project.suddenPoo.csv.CsvDTO;
import toy.project.suddenPoo.dto.MapRangeDTO;
import toy.project.suddenPoo.service.ToiletService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ToiletControllerAPI {

    private final ToiletService toiletService;

    @PostMapping("/api/toilets")
    public List<CsvDTO> findToiletsByRange(@RequestBody MapRangeDTO mapRangeDTO) {
        return toiletService.findToiletsByRange(mapRangeDTO);
    }
}
