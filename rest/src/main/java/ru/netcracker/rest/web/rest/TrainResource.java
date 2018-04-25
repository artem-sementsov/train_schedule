package ru.netcracker.rest.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.RequestMessage;
import dto.TrainDTO;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.netcracker.rest.Amq.RequestQueueSender;
import javax.inject.Inject;
import javax.jms.JMSException;
import methods.*;
import ru.netcracker.rest.domain.Response;
import ru.netcracker.rest.repository.ResponseRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.Thread.sleep;

/**
 * Created by Artem on 4/19/2016.
 */
@RestController
@RequestMapping("/api")
public class TrainResource {
    private static final Logger log = Logger.getLogger(StationResource.class);
    @Inject
    private Environment env;
    @Inject
    private ResponseRepository responseRepository;
    @Inject
    private RequestQueueSender requestQueueSender;
    /** @param id*/
    @RequestMapping(value = "/trains/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public TrainDTO getTrainByID(@PathVariable long id) throws JMSException, JsonProcessingException, InterruptedException {
        String methodName = "getTrainByID";
        log.info(methodName + " request came to Rest #1");
        GetTrainByID getTrainByID = new GetTrainByID();
        getTrainByID.setId(id);
        ObjectMapper mapper = new ObjectMapper();
        String classJson = mapper.writeValueAsString(getTrainByID);
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setMethod(methodName);
        requestMessage.setRequestId(UUID.randomUUID());
        requestMessage.setRequestData(new String(Base64.encodeBase64(classJson.getBytes())));
        String requestMessageJson = mapper.writeValueAsString(requestMessage);
        requestQueueSender.sendRequest(requestMessageJson);
        log.info(methodName + " request sent to ActiveMQ");
        long endTimeMillis = System.currentTimeMillis() + new Long(env.getProperty("spring.wait.timeout"));
        TrainDTO trainDTO = new TrainDTO();
        while (System.currentTimeMillis() < endTimeMillis) {
            Optional<Response> findResponse = responseRepository.findOneById(requestMessage.getRequestId());
            if (findResponse.isPresent()) {
                log.info("Response body getTrainByID: " + findResponse.get().toString());
                if (findResponse.get().getStatus().equals(env.getProperty("spring.response_status.success"))) {
                    try {
                        trainDTO = mapper.readValue(findResponse.get().getAnswer(), new TypeReference<TrainDTO>() {
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                if (findResponse.get().getStatus().equals(env.getProperty("spring.response_status.error"))){

                }
                sleep(new Long((env.getProperty("spring.wait.sleep"))));
            }
        }
        log.info("Response on request " + methodName + " found and sent to the user");
        return trainDTO;
    }
}
