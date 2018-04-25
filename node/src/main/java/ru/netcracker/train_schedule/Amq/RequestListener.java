package ru.netcracker.train_schedule.Amq;

import data.RequestMessage;
import dto.StationDTO;
import dto.TicketDTO;
import dto.TrainDTO;
import methods.BuyTicket;
import methods.GetAllTicketByUserId;
import methods.GetScheduleByDate;
import methods.GetTrainByID;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.netcracker.train_schedule.domain.*;
import ru.netcracker.train_schedule.domain.mapping.EntityConverter;
import ru.netcracker.train_schedule.repository.*;

import javax.inject.Inject;
import javax.jms.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 4/13/2016.
 */
@Service
public class RequestListener implements QueueListener {

    private static final Logger log = Logger.getLogger("amqLogger");

    @Inject
    private Environment env;

    @Inject
    private RouteRepository routeRepository;

    @Inject
    private StationRepository stationRepository;

    @Inject
    private TicketRepository ticketRepository;

    @Inject
    private FlightRepository flightRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private ResponseRepository responseRepository;

    @Inject
    private TrainRepository trainRepository;

    @Inject
    private EntityConverter entityConverter;

    @Override
    public void onMessage(Message message) {


        // {"method": "getStationList", "requestId": 2048, "requestData": ""}
        ObjectMapper mapper = new ObjectMapper();
        log.info("The message is received");
        TextMessage textMessage = (TextMessage) message;
        RequestMessage requestMessage = new RequestMessage();
        try {
            log.info("Message text: " + ((TextMessage) message).getText());
            requestMessage = mapper.readValue(((TextMessage) message).getText(), RequestMessage.class);
            log.info("Message in RequestMessage class: " + requestMessage.toString());
        } catch (IOException | JMSException e) {
            log.error("Error to convert JSON to class");
            e.printStackTrace();
        }
        Response response = new Response();
        switch (requestMessage.getMethod()) {
            case "getScheduleByDate":
                try {
                    String decodedData = new String(Base64.decodeBase64(requestMessage.getRequestData()));
                    requestMessage.setRequestData(decodedData);
                    log.info("Request Message with decode data: " + requestMessage.toString());
                    GetScheduleByDate getScheduleByDate = mapper.readValue(requestMessage.getRequestData(), GetScheduleByDate.class);
                    log.info("Get Schedule request data: " + getScheduleByDate.toString());
                    routeRepository.getRoute(
                        getScheduleByDate.getStationFromId().intValue(),
                        getScheduleByDate.getStationToId().intValue(),
                        getScheduleByDate.getDate(),
                        requestMessage.getRequestId().toString());
                } catch (IOException e) {
                    log.error("Error to convert JSON to class");
                    e.printStackTrace();
                }
                break;
            case "getStationList":
                response.setId(requestMessage.getRequestId());
                response.setCreatedDate(new LocalDateTime());
                response.setStatus("OK");
                List<Station> stations = stationRepository.findAll();
                log.info("Stations's list: " + stations);
                List<StationDTO> stationDTOs = new ArrayList<>();
                for (Station station : stations) {
                    stationDTOs.add(entityConverter.getDTOFromStation(station));
                }
                try {
                    response.setAnswer(mapper.writeValueAsString(stationDTOs));
                    log.info("Ready response class: " + response.toString());
                    responseRepository.save(response);
                } catch (IOException e) {
                    log.error("Error to convert stationDTOs to JSON");
                    e.printStackTrace();
                }
                break;
            case "BuyTicket":
                response.setId(requestMessage.getRequestId());
                response.setCreatedDate(new LocalDateTime());
                response.setStatus("OK");
                try {
                    String decodedData = new String(Base64.decodeBase64(requestMessage.getRequestData()));
                    requestMessage.setRequestData(decodedData);
                    log.info("Request Message with decode data: " + requestMessage.toString());
                    BuyTicket buyTicket = mapper.readValue(requestMessage.getRequestData(), BuyTicket.class);
                    log.info("Get Buy Ticket request data: " + buyTicket.toString());
                    Ticket ticket = new Ticket();
                    ticket.setFlight(flightRepository.findOne(buyTicket.getFlightId()));
                    ticket.setUser(userRepository.findOneById(buyTicket.getUserId()).get());
                    ticket.setCreatedDate(DateTime.now());
                    ticket.setQuantity(1);
                    ticket.setStatus("OK");
                    log.info("Ticket entity: " + ticket.toString());
                    ticketRepository.save(ticket);

                    Flight flight = ticket.getFlight();
                    flight.setNumberSeatsOccupied(flight.getNumberSeatsOccupied() + 1);
                    flightRepository.save(flight);

                    log.info("Ready response class: " + response.toString());
                    responseRepository.save(response);
                } catch (IOException e) {
                    log.error("Error to convert JSON to class");
                    e.printStackTrace();
                }
                break;
            case "getTrainByID":
                //вроде как готово получение поезда, но в базе пока ничего нет, и чет не уверен будет ли правильно работать
                TrainDTO trainDTO = new TrainDTO();
                try {
                    String decodedData = new String(Base64.decodeBase64(requestMessage.getRequestData()));
                    requestMessage.setRequestData(decodedData);
                    log.info("Request Message with decode data: " + requestMessage.toString());
                    GetTrainByID getTrainByID = mapper.readValue(requestMessage.getRequestData(), GetTrainByID.class);
                    log.info("Get Train request data: " + getTrainByID.toString());
                    Train train = new Train();
                    train = trainRepository.findOne(getTrainByID.getId());
                    log.info("Train with ID: " + train);
                    trainDTO = entityConverter.getDTOFromTrain(train);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                response = new Response();
                response.setId(requestMessage.getRequestId());
                try {
                    response.setAnswer(mapper.writeValueAsString(trainDTO));
                    response.setCreatedDate(new LocalDateTime());
                    response.setStatus(env.getProperty("spring.response_status.success"));
                    log.info("Ready response class: " + response.toString());
                    responseRepository.save(response);
                } catch (IOException e) {
                    log.error("Error to convert stationDTOs to JSON");
                    response.setStatus(env.getProperty("spring.response_status.error"));
                    e.printStackTrace();
                }
                break;
            case "getAllTicketByUserId":
                response.setId(requestMessage.getRequestId());
                response.setCreatedDate(new LocalDateTime());
                response.setStatus("OK");
                try {
                    String decodedData = new String(Base64.decodeBase64(requestMessage.getRequestData()));
                    requestMessage.setRequestData(decodedData);
                    log.info("Request Message with decode data: " + requestMessage.toString());
                    GetAllTicketByUserId getAllTicketByUserId = mapper.readValue(requestMessage.getRequestData(), GetAllTicketByUserId.class);
                    log.info("Get All User's Ticket request data: " + getAllTicketByUserId.toString());
                    List<Ticket> tickets = ticketRepository.findAllByUserId(getAllTicketByUserId.getId());
                    log.info("Tickets' list: " + tickets);
                    List<TicketDTO> ticketDTOs = new ArrayList<>();
                    for (Ticket ticket : tickets) {
                        ticketDTOs.add(entityConverter.getDTOFromTicket(ticket));
                    }
                    response.setAnswer(mapper.writeValueAsString(ticketDTOs));
                    log.info("Ready response class: " + response.toString());
                    responseRepository.save(response);
                } catch (IOException e) {
                    log.error("Error to convert JSON to class");
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    public void init(Session session) throws JMSException {
        Queue queue = session.createQueue(env.getProperty("spring.activemq.queue-name"));

        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(this);
    }
}
