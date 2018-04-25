package ru.netcracker.rest.Amq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;


/**
 * Created by Artem on 4/13/2016.
 */
@Service
public class AmqConfig {

    @Inject
    private Environment env;

    @Inject
    public RequestQueueSender requestQueueSender;

    @PostConstruct
    public void post() throws JMSException {
        String url = env.getProperty("spring.activemq.broker-url");
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        requestQueueSender.init(session);
    }
}
