//package ru.netcracker.rest.Amq;
//
//import org.springframework.stereotype.Service;
//
//import javax.inject.Inject;
//import javax.jms.*;
//
///**
// * Created by Artem on 4/13/2016.
// */
//@Service
//public class ResponseListener implements QueueListener {
//    @Inject
//    private RequestQueueSender requestQueueSender;
//    @Override
//    public void onMessage(Message message) {
//        System.out.print("MESSAGE!!!!!!!!!");
//        TextMessage textMessage = (TextMessage) message;
//        try {
//            requestQueueSender.sendRequest("Hello " + textMessage.getText());
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//    }
//    public void init(Session session) throws JMSException {
//        Queue queue = session.createQueue("RequestQueue");
//
//        MessageConsumer consumer = session.createConsumer(queue);
//        consumer.setMessageListener(this);
//    }
//}
