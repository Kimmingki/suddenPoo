package toy.project.suddenPoo.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import toy.project.suddenPoo.csv.CsvDTO;
import toy.project.suddenPoo.csv.CsvReader;
import toy.project.suddenPoo.csv.CsvSchedulerWriter;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SimpleJobConfiguration {

    private final CsvReader csvReader;
    private final CsvSchedulerWriter csvSchedulerWriter;

//    @Bean
//    public Job toiletDataLoadJob(JobRepository jobRepository, Step toiletDataLoadStep) {
//        return new JobBuilder("toiletInformationLoadJob", jobRepository)
//                .start(toiletDataLoadStep)
//                .build();
//    }
//
//    @Bean
//    public Step toiletDataLoadStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
//        return new StepBuilder("toiletDataLoadStep", jobRepository)
//                .<CsvDTO, CsvDTO>chunk(100, platformTransactionManager)
//                .reader(csvReader.csvScheduleReader())
//                .writer(csvSchedulerWriter)
//                .build();
//    }
}