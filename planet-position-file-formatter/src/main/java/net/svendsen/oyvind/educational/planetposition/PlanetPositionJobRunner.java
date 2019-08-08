package net.svendsen.oyvind.educational.planetposition;

import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.boot.autoconfigure.batch.JobLauncherCommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class PlanetPositionJobRunner extends JobLauncherCommandLineRunner {

    public PlanetPositionJobRunner(JobLauncher jobLauncher, JobExplorer jobExplorer, JobRepository jobRepository) {
        super(jobLauncher, jobExplorer, jobRepository);
    }
}
