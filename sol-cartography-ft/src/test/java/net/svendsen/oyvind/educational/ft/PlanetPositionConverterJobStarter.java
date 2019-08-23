package net.svendsen.oyvind.educational.ft;

import net.svendsen.oyvind.educational.planetposition.PlanetPositionJobRunner;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class PlanetPositionConverterJobStarter {

    public void startJob(PlanetPositionConvertFTData data) {
        data.setOutputFilename(createOutputfilename(data.getInputFilename()));
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("inputFilename", data.getInputFilename())
                .addString("outputFilename", data.getOutputFilename())
                .toJobParameters();

        ConfigurableApplicationContext run = SpringApplication.run(
                PlanetPositionJobRunner.class,
                "inputFilename="+data.getInputFilename(),
                "outputFilename="+data.getOutputFilename()
        );
        JobRepository jobRepository = (JobRepository) run.getBean("jobRepository");
        BatchStatus status = jobRepository.getLastJobExecution("PlanetPositionFileConverterJob", jobParameters)
                .getStatus();
        run.close();

        data.setBatchStatus(status.toString());
    }

    private String createOutputfilename(String inputFilename) {
        String[] split = inputFilename.split("/");
        return split[split.length - 1].split("\\.")[0] + ".csv";
    }

}
