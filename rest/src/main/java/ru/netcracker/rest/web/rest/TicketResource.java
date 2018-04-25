package ru.netcracker.rest.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.RequestMessage;
import dto.TicketDTO;
import methods.BuyTicket;
import methods.GetAllTicketByUserId;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.netcracker.rest.Amq.RequestQueueSender;
import ru.netcracker.rest.domain.Response;
import ru.netcracker.rest.repository.ResponseRepository;

import javax.inject.Inject;
import javax.jms.JMSException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.Thread.sleep;

/**
 * Created by Artem on 4/20/2016.
 */
@RestController
@RequestMapping("/api")
public class TicketResource {
    private static final Logger log = Logger.getLogger(StationResource.class);
    @Inject
    private Environment env;
    @Inject
    private ResponseRepository responseRepository;
    @Inject
    private RequestQueueSender requestQueueSender;

    /** @param buyTicket
    */
    @RequestMapping(value = "/ticket",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public void BuyTicket(@RequestBody BuyTicket buyTicket) throws JMSException, JsonProcessingException, InterruptedException {
        String methodName = "BuyTicket";
        log.info(methodName + " request came to Rest #1");
        ObjectMapper mapper = new ObjectMapper();
        String classJson = mapper.writeValueAsString(buyTicket);
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setMethod(methodName);
        requestMessage.setRequestId(UUID.randomUUID());
        requestMessage.setRequestData(new String(Base64.encodeBase64(classJson.getBytes())));
        String requestMessageJson = mapper.writeValueAsString(requestMessage);
        log.info("Request class: " + requestMessage);
        requestQueueSender.sendRequest(requestMessageJson);
        log.info(methodName + " request sent to ActiveMQ");
        long endTimeMillis = System.currentTimeMillis() + Long.valueOf(env.getProperty("spring.wait.timeout"));
        while (System.currentTimeMillis() < endTimeMillis) {
            Optional<Response> findResponse = responseRepository.findOneById(requestMessage.getRequestId());
            if (findResponse.isPresent()) {
                break;
            }
            sleep(Long.valueOf(env.getProperty("spring.wait.sleep")));
        }
        log.info("Response on request " + methodName + " found and sent to the user");
    }

    /** @param userId
     */
    @RequestMapping(value = "/ticket/{userId}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TicketDTO> getAllTicketByUserId(@PathVariable long userId) throws JMSException, JsonProcessingException, InterruptedException {
        String methodName = "getAllTicketByUserId";
        log.info(methodName + " request came to Rest #1");
        GetAllTicketByUserId getAllTicketByUserId = new GetAllTicketByUserId();
        getAllTicketByUserId.setId(userId);
        ObjectMapper mapper = new ObjectMapper();
        String classJson = mapper.writeValueAsString(getAllTicketByUserId);
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setMethod(methodName);
        requestMessage.setRequestId(UUID.randomUUID());
        requestMessage.setRequestData(new String(Base64.encodeBase64(classJson.getBytes())));
        String requestMessageJson = mapper.writeValueAsString(requestMessage);
        System.out.println(requestMessageJson);
        requestQueueSender.sendRequest(requestMessageJson);
        log.info(methodName + " request sent to ActiveMQ");
        long endTimeMillis = System.currentTimeMillis() + Long.valueOf(env.getProperty("spring.wait.timeout"));
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        while (System.currentTimeMillis() < endTimeMillis) {
            Optional<Response> findResponse = responseRepository.findOneById(requestMessage.getRequestId());
            if (findResponse.isPresent()) {
                try {
                    ticketDTOs = mapper.readValue(findResponse.get().getAnswer(), new TypeReference<List<TicketDTO>>() {
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            sleep(Long.valueOf(env.getProperty("spring.wait.sleep")));
        }
        log.info("Response on request " + methodName + " found and sent to the user");
        return ticketDTOs;

    }
}

