package ru.netcracker.train_schedule.domain.mapping;

import dto.*;
import org.springframework.stereotype.Service;
import ru.netcracker.train_schedule.domain.*;
import ru.netcracker.train_schedule.repository.FlightRepository;
import ru.netcracker.train_schedule.service.UserService;

import javax.inject.Inject;

@Service
public class EntityConverter {

    @Inject
    private UserService userService;

    @Inject
    private FlightRepository flightRepository;

    @Inject
    private RouteConverter routeConverter;

    public User getUserFromDTO(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword((userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    public UserDTO getDTOFromUser(User user) {
        return new UserDTO(
            user.getLogin(),
            user.getPassword(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail()
        );
    }

    public Train getTrainFromDTO(TrainDTO trainDTO) {
        Train train = new Train();
        train.setNumber(trainDTO.getNumber());
        train.setNumberOfSeats(trainDTO.getNumberOfSeats());
        return train;
    }

    public TrainDTO getDTOFromTrain(Train train) {
        return new TrainDTO(
            train.getNumber(),
            train.getNumberOfSeats()
        );
    }

    public Ticket getTicketFromDTO(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setStatus(ticketDTO.getStatus());
        ticket.setQuantity(ticketDTO.getQuantity());
        ticket.setCreatedDate(ticketDTO.getCreateDate());
        ticket.setFlight(flightRepository.findOne(ticketDTO.getFlightId()));
        return ticket;
    }

    public TicketDTO getDTOFromTicket(Ticket ticket) {
        return new TicketDTO(
            ticket.getStatus(),
            ticket.getQuantity(),
            ticket.getCreatedDate(),
            ticket.getFlight().getId()
        );
    }

    public Station getStationFromDTO(StationDTO stationDTO) {
        Station station = new Station();
        station.setName(stationDTO.getName());
        return station;
    }

    public StationDTO getDTOFromStation(Station station) {
        return new StationDTO(
            station.getId(),
            station.getName()
        );
    }

    public Schedule getScheduleFromDTO(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setWeekSchedule(scheduleDTO.getWeekSchedule());
        return schedule;
    }

    public ScheduleDTO getDTOFromSchedule(Schedule schedule) {
        return new ScheduleDTO(
            schedule.getWeekSchedule(),
            schedule.getTime(),
            schedule.getRoute().getId()
        );
    }

    public Route getRouteFromDTO(RouteDTO routeDTO) {
        Route route = new Route();
        route.setListStation(routeConverter.convertToEntityAttribute(routeDTO.getListStation()));
        return route;
    }

    public RouteDTO getDTOFromRoute(Route route) {
        return new RouteDTO(
            routeConverter.convertToDatabaseColumn(route.getListStation())
        );
    }

    public Flight getFlightFromDTO(FlightDTO flightDTO) {
        Flight flight = new Flight();
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setNumberSeatsOccupied(flightDTO.getNumberSeatsOccupied());
        flight.setDelay(flightDTO.getDelay());
        return flight;
    }

    public FlightDTO getDTOFromFlight(Flight flight) {
        return new FlightDTO(
            flight.getDepartureTime(),
            flight.getNumberSeatsOccupied(),
            flight.getDelay(),
            flight.getStationTo().getId(),
            flight.getStationTo().getId(),
            flight.getRoute().getId()
        );
    }
}
