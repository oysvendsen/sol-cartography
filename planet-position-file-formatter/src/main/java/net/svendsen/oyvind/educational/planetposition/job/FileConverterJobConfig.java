package net.svendsen.oyvind.educational.planetposition.job;

import net.svendsen.oyvind.educational.planetposition.domain.PlanetPositionXYZ;
import net.svendsen.oyvind.educational.planetposition.domain.Helioweb;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public FlatFileItemReader<Helioweb> solarEclipticReader(final @Value("${inputDir}#{JobParameters['inputFilename']}") Resource inputFile,
            final FieldSetMapper<Helioweb> fieldSetMapper) {
        return new FlatFileItemReaderBuilder<Helioweb>()
                .name("heliowebReader")
                .resource(inputFile)
                .strict(true)
                .fieldSetMapper(fieldSetMapper)
                .linesToSkip(1)
                .fixedLength()
                .addColumns(new Range(1,4))
                .addColumns(new Range(5,8))
                .addColumns(new Range(9,16))
                .addColumns(new Range(17,24))
                .addColumns(new Range(25))
                .names(new String[] {"YEAR", "DAY", "RAD_AU", "SE_LAT", "SE_LON"})
                .build();
    }
//@Bean
//    @StepScope
//    public FlatFileItemReader<Helioweb> reader(final @Value("${inputDir}#{JobParameters['inputFilename']}") Resource inputFile,
//            final FieldSetMapper<Helioweb> fieldSetMapper) {
//        return new FlatFileItemReaderBuilder<Helioweb>()
//                .name("heliowebReader")
//                .resource(inputFile)
//                .strict(true)
//                .fieldSetMapper(fieldSetMapper)
//                .linesToSkip(1)
//                .fixedLength()
//                .addColumns(new Range(1, 5))
//                .addColumns(new Range(6, 9))
//                .addColumns(new Range(10, 16))
//                .addColumns(new Range(17, 23))
//                .addColumns(new Range(24, 31))
//                .addColumns(new Range(32, 39))
//                .addColumns(new Range(40, 47))
//                .addColumns(new Range(48))
//                .names(new String[] {"YYYY", "DOY", "AU", "ELAT", "ELON", "HG_LAT", "HG_LON", "HGI_LON"})
//                .build();
//    }

    @Bean
    @StepScope
    public FlatFileItemWriter<PlanetPositionXYZ> writer(final @Value("${outputDir}#{JobParameters['outputFilename']}") Resource outputFile, final LineAggregator<PlanetPositionXYZ> lineAggregator) {
        return new FlatFileItemWriterBuilder<PlanetPositionXYZ>()
                .name("csvWriter")
//                .append(true)
                .resource(outputFile)
                .lineAggregator(lineAggregator)
                .build();
    }

}
