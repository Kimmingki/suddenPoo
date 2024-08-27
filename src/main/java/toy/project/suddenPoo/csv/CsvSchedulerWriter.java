package toy.project.suddenPoo.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import toy.project.suddenPoo.entity.Csv;
import toy.project.suddenPoo.repository.ToiletRepository;

@Configuration
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CsvSchedulerWriter implements ItemWriter<CsvDTO> {

    private final ToiletRepository toiletRepository;

    @Override
    @Transactional
    public void write(Chunk<? extends CsvDTO> chunk) throws Exception {
        Chunk<Csv> csvs = new Chunk<>();

        chunk.forEach(csvDTO -> {
           Csv csv = Csv.builder()
                   .csvDTO(csvDTO)
                   .build();
           csvs.add(csv);
        });

        toiletRepository.saveAll(csvs);
    }
}
