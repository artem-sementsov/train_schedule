package ru.netcracker.train_schedule.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.*;
import ru.netcracker.train_schedule.Constants;

import javax.sql.DataSource;

@Configuration
@Profile(Constants.SPRING_PROFILE_CLOUD)
public class CloudDatabaseConfiguration extends AbstractCloudConfig {

    private final Logger log = LoggerFactory.getLogger(CloudDatabaseConfiguration.class);

    @Bean
    public DataSource dataSource() {
        log.info("Configuring JDBC datasource from a cloud provider");
        return connectionFactory().dataSource();
    }
}
