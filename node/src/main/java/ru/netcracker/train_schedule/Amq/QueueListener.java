package ru.netcracker.train_schedule.Amq;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.Session;

/**
 * Created by Artem on 4/13/2016.
 */
@Service
public interface QueueListener extends MessageListener {
    void init(Session session) throws JMSException;
}
