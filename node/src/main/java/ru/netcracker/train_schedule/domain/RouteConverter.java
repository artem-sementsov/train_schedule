package ru.netcracker.train_schedule.domain;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.persistence.AttributeConverter;
import javax.validation.constraints.NotNull;

/**
 * Created by aleks on 26.04.2016.
 */
@javax.persistence.Converter
@Service
public class RouteConverter implements AttributeConverter<RouteListStation, String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @NotNull
    public String convertToDatabaseColumn(@NotNull RouteListStation routeListStation) {
        try {
            return objectMapper.writeValueAsString(routeListStation);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @NotNull
    public RouteListStation convertToEntityAttribute(@NotNull String databaseDataAsJSONString) {
        try {
            return objectMapper.readValue(databaseDataAsJSONString, RouteListStation.class);
        } catch (Exception ex) {
            return null;
        }
    }
}
