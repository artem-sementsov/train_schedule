package ru.netcracker.rest.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.RequestMessage;
import dto.StationDTO;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.netcracker.rest.Amq.RequestQueueSender;
import ru.netcracker.rest.domain.Response;
import ru.netcracker.rest.filter.LoginFilter;
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
 * Created by Artem on 4/19/2016.
 */
@RestController
@RequestMapping("/api")
public class StationResource{

    private static final Logger log = Logger.getLogger(StationResource.class);
    @Inject
    private Environment env;
    @Inject
    private ResponseRepository responseRepository;
    @Inject
    private RequestQueueSender requestQueueSender;

    private LoginFilter loginFilter = new LoginFilter();

    @RequestMapping(value = "/stationlist",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StationDTO> getStationList() throws JMSException, JsonProcessingException, InterruptedException {
//        loginFilter.doFilter();
        String methodName = "getStationList";
        log.info(methodName + " request came to Rest #1");
        ObjectMapper mapper = new ObjectMapper();
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setMethod(methodName);
        requestMessage.setRequestId(UUID.randomUUID());
        requestMessage.setRequestData("");
        String requestMessageJson = mapper.writeValueAsString(requestMessage);
        System.out.println(requestMessageJson);
        requestQueueSender.sendRequest(requestMessageJson);
        log.info(methodName + " request sent to ActiveMQ");
        long endTimeMillis = System.currentTimeMillis() + new Long(env.getProperty("spring.wait.timeout"));
        List<StationDTO> stationDTOs = new ArrayList<>();
        while (System.currentTimeMillis() < endTimeMillis) {
            Optional<Response> findResponse = responseRepository.findOneById(requestMessage.getRequestId());
            if (findResponse.isPresent()) {
                try {
                    stationDTOs = mapper.readValue(findResponse.get().getAnswer(), new TypeReference<List<StationDTO>>() {
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            sleep(new Long((env.getProperty("spring.wait.sleep"))));
        }
        log.info("Response on request " + methodName + " found and sent to the user");
        return stationDTOs;
    }
}

