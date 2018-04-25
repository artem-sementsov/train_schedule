package ru.netcracker.train_schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netcracker.train_schedule.Amq.RequestListener;

/**
 * Created by Artem on 4/13/2016.
 */
//@Configuration
public class ApplicationConfiguration {

//    @Bean
    public RequestListener requestListener(){
        return new RequestListener();
    }
}
