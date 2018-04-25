//package ru.netcracker.train_schedule.Amq;
//
//import org.springframework.stereotype.Service;
//
//import javax.jms.*;
//
///**
// * Created by Artem on 4/13/2016.
// */
//@Service
//public class ResponseQueueSender {
//    private MessageProducer messageProducer;
//    private Session session;
//    public void init(Session session) throws JMSException {
//        this.session = session;
//        Destination destination = session.createQueue("ResponceQueue");
//
//        // Create a MessageProducer from tэhe Session to the Topic or Queue
//        messageProducer = session.createProducer(destination);
//
//        messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//
//    }
//    public void sendResponse(String text) throws JMSException {
//        TextMessage message = session.createTextMessage(text);
//
//        messageProducer.send(message);
//
//    }
//}
