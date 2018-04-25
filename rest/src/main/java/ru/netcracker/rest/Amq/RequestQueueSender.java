package ru.netcracker.rest.Amq;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.jms.*;

/**
 * Created by Artem on 4/13/2016.
 */
@Service
public class RequestQueueSender {

    @Inject
    private Environment env;

    private MessageProducer messageProducer;

    private Session session;

    public void init(Session session) throws JMSException {
        this.session = session;
        Destination destination = session.createQueue(env.getProperty("spring.activemq.queue-name"));

        // Create a MessageProducer from the Session to the Topic or Queue
        messageProducer = session.createProducer(destination);

        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

    }
    public void sendRequest(String text) throws JMSException {
        TextMessage message = session.createTextMessage(text);

        messageProducer.send(message);

    }
}
