package net.svendsen.oyvind.educational.planetposition.job;

import net.svendsen.oyvind.educational.planetposition.domain.Helioweb;
import net.svendsen.oyvind.educational.planetposition.domain.PlanetPositionXYZ;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class FileConverterJobConfig {

    @Autowired
    private JobBuilderFactory  jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job fileConverterJob(final Step fileConverterStep) {
            return jobBuilderFactory.get("PlanetPositionFileConverterJob")
                    .start(fileConverterStep)
                    .build();
    }

    @Bean
    public Step fileConverterStep(final ItemReader<Helioweb> reader,
            final ItemProcessor<Helioweb, PlanetPositionXYZ> processor,
            final ItemWriter<PlanetPositionXYZ> writer) {
        return stepBuilderFactory.get("HeliowebToCsvConverterStep")
                .<Helioweb, PlanetPositionXYZ>chunk(1)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<PlanetPositionXYZ> writer(final @Value("${outputDir}#{JobParameters['outputFilename']}") Resource outputFile, final LineAggregator<PlanetPositionXYZ> lineAggregator) {
        return new FlatFileItemWriterBuilder<PlanetPositionXYZ>()
                .name("csvWriter")
                .resource(outputFile)
                .lineAggregator(lineAggregator)
                .build();
    }

}
