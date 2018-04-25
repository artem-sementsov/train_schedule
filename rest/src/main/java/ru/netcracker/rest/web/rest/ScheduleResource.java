package ru.netcracker.rest.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import data.RequestMessage;
import dto.ScheduleResponseDTO;
import methods.GetScheduleByDate;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.netcracker.rest.Amq.RequestQueueSender;
import ru.netcracker.rest.domain.Response;
import ru.netcracker.rest.repository.ResponseRepository;

import javax.inject.Inject;
import javax.jms.JMSException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class ScheduleResource {
    private static final Logger log = Logger.getLogger(StationResource.class);
    @Inject
    private Environment env;
    @Inject
    private ResponseRepository responseRepository;
    @Inject
    private RequestQueueSender requestQueueSender;
    /**
    * @param fromStation
    * @param toStation
    * @param date
    * */
    @RequestMapping(value = "/schedule/{fromStation}/{toStation}/{date}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ScheduleResponseDTO> getSchedule(@PathVariable long fromStation, @PathVariable long toStation, @PathVariable long date) throws JMSException, JsonProcessingException, InterruptedException {
        String methodName = "getScheduleByDate";
        log.info(methodName + " request came to Rest #1");
        GetScheduleByDate getScheduleByDate = new GetScheduleByDate();
        getScheduleByDate.setStationFromId(fromStation);
        getScheduleByDate.setStationToId(toStation);
        getScheduleByDate.setDate(date);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        mapper.setDateFormat(df);

        String classJson = mapper.writeValueAsString(getScheduleByDate);
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setMethod(methodName);
        requestMessage.setRequestId(UUID.randomUUID());
        requestMessage.setRequestData(new String(Base64.encodeBase64(classJson.getBytes())));
        String requestMessageJson = mapper.writeValueAsString(requestMessage);
        System.out.println(requestMessageJson);
        requestQueueSender.sendRequest(requestMessageJson);
        log.info(methodName + " request sent to ActiveMQ");
        long endTimeMillis = System.currentTimeMillis() + Long.valueOf(env.getProperty("spring.wait.timeout"));
        List<ScheduleResponseDTO> scheduleResponseDTOList = new ArrayList<>();
        while (System.currentTimeMillis() < endTimeMillis) {
            Optional<Response> findResponse = responseRepository.findOneById(requestMessage.getRequestId());
            if (findResponse.isPresent()) {
                try {
                    scheduleResponseDTOList = mapper.readValue(findResponse.get().getAnswer(),  new TypeReference<List<ScheduleResponseDTO>>() {});
                    log.info(scheduleResponseDTOList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            sleep(Long.valueOf(env.getProperty("spring.wait.sleep")));
        }
        log.info("Response on request " + methodName + " found and sent to the user");
        return scheduleResponseDTOList;
    }
}
