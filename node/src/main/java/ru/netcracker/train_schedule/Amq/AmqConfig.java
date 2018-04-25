package ru.netcracker.train_schedule.Amq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;


/**
 * Created by Artem on 4/13/2016.
 */
@Service
@DependsOn("requestListener")
public class AmqConfig {

    @Inject
    private Environment env;

    @Inject
    private QueueListener requestListener;
//    @Inject
//    private ResponseQueueSender responseQueueSender;

    @PostConstruct
    public void post() throws JMSException {
        String url = env.getProperty("spring.activemq.broker-url");
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false,
            Session.AUTO_ACKNOWLEDGE);
        requestListener.init(session);
//        responseQueueSender.init(session);
    }
}
