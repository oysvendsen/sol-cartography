package net.svendsen.oyvind.educational.planetposition;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
public class BatchConfigurer extends DefaultBatchConfigurer {

    @Override
    public void setDataSource(DataSource dataSource) {
    }

}
