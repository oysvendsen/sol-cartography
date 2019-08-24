package net.svendsen.oyvind.educational.planetposition;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.JobLauncherCommandLineRunner;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
@EnableBatchProcessing
public class PlanetPositionJobRunner extends JobLauncherCommandLineRunner {

    public PlanetPositionJobRunner(JobLauncher jobLauncher, JobExplorer jobExplorer,
            JobRepository jobRepository) {
        super(jobLauncher, jobExplorer, jobRepository);
    }

    public static void main(String[] args) {
        SpringApplication.run(PlanetPositionJobRunner.class, args);
    }
}
