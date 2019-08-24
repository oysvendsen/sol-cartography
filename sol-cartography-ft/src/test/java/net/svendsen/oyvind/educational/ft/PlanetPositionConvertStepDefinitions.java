package net.svendsen.oyvind.educational.ft;

import cucumber.api.java8.En;
import org.assertj.core.api.Assertions;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class PlanetPositionConvertStepDefinitions implements En {

    public PlanetPositionConvertStepDefinitions(PlanetPositionConvertFTData data, PlanetPositionConverterJobStarter jobStarter) {

        Given("file is \"(.*)\"", (String filename) -> {
            data.setInputFilename(filename);
        });

        When("PlanetPositionConvertJob is started", () -> {
            jobStarter.startJob(data);
        });

        Then("the job returns success", () -> {
            Assertions.assertThat(data.getBatchStatus())
                    .isEqualToIgnoringCase("COMPLETED");
        });

        Then("the produced file should be equal to \"(.*)\"", (String filename) -> {
            FileSystemResource file = new FileSystemResource("target/out/" + data.getOutputFilename());
            Resource expectedFileResult = new DefaultResourceLoader().getResource(filename);
            Assertions.assertThat(
                    FileEqualityValidator.builder()
                            .file(file)
                            .expectedFile(expectedFileResult)
                            .build()
                            .isEqual())
                    .describedAs("The content of file:" + file + " should equal the content of file:" +expectedFileResult)
                    .isTrue();
        });

        Given("profile is \"(.*)\"", (String profile) -> {
            data.setProfile(profile);
        });

    }
}
