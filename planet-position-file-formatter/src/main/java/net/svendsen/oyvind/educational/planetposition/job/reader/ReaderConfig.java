package net.svendsen.oyvind.educational.planetposition.job.reader;

import net.svendsen.oyvind.educational.planetposition.domain.Helioweb;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

@Configuration
public class ReaderConfig {

    @Bean
    @Profile("SE")
    @StepScope
    public FlatFileItemReader<Helioweb> solarEclipticReader(final @Value("${inputDir}#{JobParameters['inputFilename']}") Resource inputFile,
            final @Qualifier("SE") FieldSetMapper<Helioweb> fieldSetMapper) {
        return getHeliowebReader(inputFile, fieldSetMapper)
                .names(new String[] {"YEAR", "DAY", "RAD_AU", "SE_LAT", "SE_LON"})
                .build();
    }

    @Bean
    @Profile("HG")
    @StepScope
    public FlatFileItemReader<Helioweb> helioGraphicReader(final @Value("${inputDir}#{JobParameters['inputFilename']}") Resource inputFile,
            final @Qualifier("HG") FieldSetMapper<Helioweb> fieldSetMapper) {
        return getHeliowebReader(inputFile, fieldSetMapper)
                .names(new String[] {"YEAR", "DAY", "RAD_AU", "HG_LAT", "HG_LON"})
                .build();
    }

    @Bean
    @Profile("HGI")
    @StepScope
    public FlatFileItemReader<Helioweb> helioGraphicInertialReader(final @Value("${inputDir}#{JobParameters['inputFilename']}") Resource inputFile,
            final @Qualifier("HGI") FieldSetMapper<Helioweb> fieldSetMapper) {
        return getHeliowebReader(inputFile, fieldSetMapper)
                .names(new String[] {"YEAR", "DAY", "RAD_AU", "HGI_LAT", "HGI_LON"})
                .build();
    }

    private FlatFileItemReaderBuilder.FixedLengthBuilder<Helioweb> getHeliowebReader(
            final Resource inputFile,
            final FieldSetMapper<Helioweb> fieldSetMapper) {
        return new FlatFileItemReaderBuilder<Helioweb>()
                .name("heliowebReader")
                .resource(inputFile)
                .strict(true)
                .fieldSetMapper(fieldSetMapper)
                .linesToSkip(1)
                .fixedLength()
                .addColumns(new Range(1, 4))
                .addColumns(new Range(5, 8))
                .addColumns(new Range(9, 16))
                .addColumns(new Range(17, 24))
                .addColumns(new Range(25));
    }


}
